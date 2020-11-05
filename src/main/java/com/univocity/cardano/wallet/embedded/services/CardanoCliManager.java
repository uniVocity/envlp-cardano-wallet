package com.univocity.cardano.wallet.embedded.services;

import com.univocity.cardano.wallet.common.*;
import org.apache.commons.io.*;
import org.apache.commons.lang3.*;
import org.slf4j.*;

import java.io.*;
import java.math.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;

public class CardanoCliManager extends ProcessManager {

	private static final Logger log = LoggerFactory.getLogger(CardanoCliManager.class);

	private File tempDir;

	public CardanoCliManager(String toolDirPath) {
		super(toolDirPath, "cardano-cli", System.out::println);

		try {
			tempDir = File.createTempFile("tmp", ".txt").getParentFile();
		} catch (Exception e) {
			log.warn("Unable to determine temporary directory", e);
		}
	}

	public CardanoNodeManager createTemporaryShelleyNetwork(long testnetMagicCode, long port, Consumer<String> nodeOutputConsumer) {
		return createTemporaryShelleyNetwork(testnetMagicCode, new BigInteger("1000000000"), tempDir, port, nodeOutputConsumer);
	}

	public CardanoNodeManager createTemporaryShelleyNetwork(long testnetMagicCode, BigInteger lovelaceSupply, long port, Consumer<String> nodeOutputConsumer) {
		return createTemporaryShelleyNetwork(testnetMagicCode, lovelaceSupply, tempDir, port, nodeOutputConsumer);
	}

	private File createDir(File root, String subDir) {
		File out = root.toPath().resolve(subDir).toFile();
		try {
			FileUtils.forceMkdir(out);
		} catch (Exception e) {
			throw new IllegalStateException("Unable to create directory: " + out.getAbsolutePath(), e);
		}
		return out;
	}

	private File buildFile(File root, String... path) {
		Path tmp = root.toPath();
		for (int i = 0; i < path.length; i++) {
			tmp = tmp.resolve(path[i]);
		}
		return tmp.toFile();
	}

	private File getNodeConfigurationFile(File shelleyGenesisDir, File genesisFile, File nodeSocket) {
		String nodeConfigYaml = Utils.readTextFromResource("test-node-configuration.yaml", StandardCharsets.UTF_8);
		nodeConfigYaml = StringUtils.replace(nodeConfigYaml, "path_to_genesis_file", genesisFile.getAbsolutePath());
		nodeConfigYaml = StringUtils.replace(nodeConfigYaml, "path_to_node_socket_file", nodeSocket.getAbsolutePath());
		File nodeConfigurationFile = buildFile(shelleyGenesisDir, "node-configuration.yaml");
		try {
			FileUtils.write(nodeConfigurationFile, nodeConfigYaml, StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new IllegalStateException("Unable to create topology file: " + nodeConfigurationFile.getAbsolutePath(), e);
		}
		return nodeConfigurationFile;
	}

	private File getTopologyFile(File shelleyGenesisDir) {
		File topologyFile = buildFile(shelleyGenesisDir, "node-topology.json");
		try {
			FileUtils.write(topologyFile, "{\"Producers\": []}", StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new IllegalStateException("Unable to create topology file: " + topologyFile.getAbsolutePath(), e);
		}
		return topologyFile;
	}

	public CardanoNodeManager createTemporaryShelleyNetwork(long testnetMagicCode, BigInteger lovelaceSupply, File rootDir, long port, Consumer<String> nodeOutputConsumer) {
		File shelleyGenesisDir = createDir(rootDir, "shelley-" + UUID.randomUUID());
		if (createShelleyGenesis(testnetMagicCode, lovelaceSupply, shelleyGenesisDir)) {
			File coldSigningKeyFile = buildFile(shelleyGenesisDir, "delegate-keys", "delegate1.skey");
			File operationalCertificateIssueCounter = buildFile(shelleyGenesisDir, "delegate-keys", "delegate1.counter");
			File genesisFile = buildFile(shelleyGenesisDir, "genesis.json");

			File nodeDir = createDir(shelleyGenesisDir, "node");
			File[] kesKeys = createKesKeys(nodeDir, "kes");
			File kesVkey = kesKeys[0];
			File kesSkey = kesKeys[1];
			File operationalCertificate = issueOperationalCertificate(buildFile(nodeDir, "op.cert"), kesVkey, 0L, coldSigningKeyFile, operationalCertificateIssueCounter);

			File nodeDb = buildFile(nodeDir, "db");
			File nodeSocket = buildFile(nodeDb, "node.socket");

			setEnvironmentVariable("CARDANO_NODE_SOCKET_PATH", nodeSocket.getAbsolutePath());

			File vrfKey = buildFile(shelleyGenesisDir, "delegate-keys", "delegate1.vrf.skey");

			File nodeConfigurationFile = getNodeConfigurationFile(shelleyGenesisDir, genesisFile, nodeSocket);
			File topologyFile = getTopologyFile(shelleyGenesisDir);

			String command = "" +
					"run" +
					" --config " + nodeConfigurationFile.getAbsolutePath() +
					" --topology " + topologyFile.getAbsolutePath() +
					" --database-path " + nodeDb.getAbsolutePath() +
					" --socket-path " + nodeSocket.getAbsolutePath() +
					" --shelley-kes-key " + kesSkey.getAbsolutePath() +
					" --shelley-vrf-key " + vrfKey.getAbsolutePath() +
					" --shelley-operational-certificate " + operationalCertificate.getAbsolutePath() +
					" --port " + port;


			CardanoNodeManager nodeManager = new CardanoNodeManager(toolDir.getAbsolutePath(), nodeOutputConsumer);
			nodeManager.setStartupCommand(command);
			nodeManager.startProcess();

			try {
				Thread.sleep(30_000);
			} catch (Exception e) {
				Thread.currentThread().interrupt();
				throw new IllegalStateException("Error waiting 30 seconds until genesis start time", e);
			}

			File utxo1VKey = buildFile(shelleyGenesisDir, "utxo-keys", "utxo1.vkey");
			String utxo1Address = getPaymentAddressFromVKey(utxo1VKey, testnetMagicCode);
			String utxo1TxHash = getTxHash(utxo1Address, testnetMagicCode);
			String genesisTransactionHash = getGenesisTransactionHash(utxo1VKey, testnetMagicCode);

			File[] paymentFiles = generatePaymentAddressFiles(shelleyGenesisDir, "payment");
			File paymentVKey = paymentFiles[0];
			File paymentSKey = paymentFiles[1];
			String paymentAddress = getPaymentAddressFromVKey(paymentVKey, testnetMagicCode);

			File transactionDraft = buildTransaction(genesisTransactionHash, paymentAddress, lovelaceSupply, BigInteger.ZERO);

			File utxo1SKey = buildFile(shelleyGenesisDir, "utxo-keys", "utxo1.skey");
			File signedTransaction = signTransaction(transactionDraft, utxo1SKey, testnetMagicCode);
			submitTransaction(signedTransaction, testnetMagicCode);
			return nodeManager;
		}
		throw new IllegalStateException("Unable to initialize temporary shelley network configuration");
	}

	private File createTempFile(String prefix) {
		try {
			return File.createTempFile(prefix, ".tmp", tempDir);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public File buildTransaction(String transactionIn, String transactionOut, BigInteger lovelaceAmount, BigInteger lovelaceFee) {
		File tmpTransaction = createTempFile("rawtx");
		String command = "" +
				"shelley transaction build-raw" +
				" --tx-in " + transactionIn +
				" --tx-out " + transactionOut + "+" + lovelaceAmount +
				" --ttl 3600" +
				" --fee " + lovelaceFee +
				" --tx-body-file " + tmpTransaction.getAbsolutePath();

		if (executeExpectingNoOutput("Build transaction", null, command)) {
			return tmpTransaction;
		}
		throw new IllegalStateException("Unable to build draft transaction file");
	}

	public File signTransaction(File draftTransaction, File signingKey, long testnetMagicCode) {
		File signedTransaction = createTempFile("signed");
		String command = "" +
				"shelley transaction sign" +
				" --tx-body-file " + draftTransaction.getAbsolutePath() +
				" --signing-key-file " + signingKey.getAbsolutePath() +
				networkIdentifierString(testnetMagicCode) +
				" --tx-file " + signedTransaction.getAbsolutePath();

		if (executeExpectingNoOutput("Sign transaction", null, command)) {
			return signedTransaction;
		}
		throw new IllegalStateException("Unable to sign draft transaction file " + draftTransaction.getAbsolutePath());
	}

	public void submitTransaction(File signedTransactionFile, long testnetMagicCode) {
		String command = "" +
				"shelley transaction submit" +
				" --tx-file " + signedTransactionFile.getAbsolutePath() +
				networkIdentifierString(testnetMagicCode) +
				" --shelley-mode";

		if (!executeExpectingNoOutput("Sign transaction", null, command)) {
			throw new IllegalStateException("Unable to sign draft transaction file " + signedTransactionFile.getAbsolutePath());
		}
	}

	public String getGenesisTransactionHash(File utxoVKey, long testnetMagicCode) {
		String command = "" +
				"shelley genesis initial-txin" +
				" --verification-key-file " + utxoVKey.getAbsolutePath() +
				networkIdentifierString(testnetMagicCode);
		return execute("Get genesis transaction hash", null, command, false);
	}

	public String getTxHash(String address, long testnetMagicCode) {
		String output = getTransactions(address, testnetMagicCode);
		String[] lines = output.trim().split("\n");
		String lastLine = lines[lines.length - 1];
		String hash = StringUtils.substringBefore(lastLine, " ");
		return hash;
	}

	public String getTransactions(String address, long testnetMagicCode) {
		String command = "shelley query utxo" +
				networkIdentifierString(testnetMagicCode) +
				" --shelley-mode" +
				" --address " + address;

		String output = execute("Get transactions from address", null, command, false);
		String lower = output.toLowerCase();
		if (lower.contains("error") || lower.contains(toolName)) {
			throw new IllegalStateException(output);
		}
		return output;
	}

	public String getPaymentAddressFromVKey(File vKey, long testnetMagicCode) {
		String command = "" +
				"shelley address build" +
				" --payment-verification-key-file " + vKey.getAbsolutePath() +
				networkIdentifierString(testnetMagicCode);

		return execute("Payment address from vkey", null, command, false);
	}

	public File issueOperationalCertificate(File certificateFile, File kesVkey, long kesPeriod, File coldSigningKeyFile, File operationalCertificateIssueCounter) {
		if (certificateFile.exists()) {
			throw new IllegalStateException("Can't overwrite existing certificate file in " + certificateFile);
		}
		String command = "" +
				"shelley node issue-op-cert" +
				" --kes-verification-key-file " + kesVkey.getAbsolutePath() +
				" --cold-signing-key-file " + coldSigningKeyFile.getAbsolutePath() +
				" --operational-certificate-issue-counter " + operationalCertificateIssueCounter.getAbsolutePath() +
				" --kes-period " + kesPeriod +
				" --out-file " + certificateFile.getAbsolutePath();

		if (executeExpectingNoOutput("Create KES keys", null, command)) {
			if (certificateFile.exists()) {
				return certificateFile;
			}
		}
		throw new IllegalStateException("Unable to create certificate file in " + certificateFile);
	}

	public File[] createKesKeys(File targetDir, String filePrefix) {
		File[] files = generateKeyFiles(targetDir, filePrefix);
		String command = "" +
				"shelley node key-gen-KES" +
				" --verification-key-file " + files[0].getAbsolutePath() +
				" --signing-key-file " + files[1].getAbsolutePath();

		if (executeExpectingNoOutput("Create KES keys", null, command)) {
			return validateKeyFiles(files);
		}
		throw new IllegalStateException("Unable to create KES key files under " + targetDir);
	}

	public boolean createShelleyGenesis(long testnetMagicCode, BigInteger lovelaceSupply, File targetDir) {
		String command = "" +
				"shelley genesis create" +
				networkIdentifierString(testnetMagicCode) +
				" --gen-genesis-keys 1" +
				" --gen-utxo-keys 1" +
				" --supply " + lovelaceSupply +
				" --genesis-dir " + targetDir.getAbsolutePath();

		return executeExpectingNoOutput("Generate genesis file", null, command);
	}


	public File[] generatePaymentAddressFiles(File targetDir, String filePrefix) {
		File[] files = generateKeyFiles(targetDir, filePrefix);
		String command = "" +
				"shelley address key-gen" +
				" --verification-key-file " + files[0].getAbsolutePath() +
				" --signing-key-file " + files[1].getAbsolutePath();

		if (executeExpectingNoOutput("Create payment address key files", null, command)) {
			return validateKeyFiles(files);
		}

		throw new IllegalStateException("Unable to create payment address key files under " + targetDir);
	}

	private File[] generateKeyFiles(File targetDir, String filePrefix) {
		File vKey = buildFile(targetDir, filePrefix + ".vkey");
		File sKey = buildFile(targetDir, filePrefix + ".skey");
		return new File[]{vKey, sKey};
	}

	private File[] validateKeyFiles(File[] files) {
		for (File file : files) {
			if (!file.exists()) {
				throw new IllegalStateException("Could not create file: " + file.getAbsolutePath());
			}
		}
		return files;
	}

	public static String networkIdentifierString(long testnetMagic) {
		if (testnetMagic > 0) {
			return " --testnet-magic " + testnetMagic;
		} else {
			return " --mainnet";
		}
	}
}
