package com.univocity.cardano.wallet.embedded.services;

import com.univocity.cardano.wallet.common.*;
import org.apache.commons.io.*;
import org.apache.commons.lang3.*;

import java.io.*;
import java.math.*;
import java.nio.charset.*;
import java.util.*;
import java.util.function.*;

public class TemporaryBlockchainHelper extends CardanoCliManager {

	private File genesisFile;

	public TemporaryBlockchainHelper(String toolDirPath) {
		super(toolDirPath);
	}

	public CardanoNodeManager createTemporaryShelleyNetwork(long testnetMagicCode, long port, Consumer<String> nodeOutputConsumer) {
		return createTemporaryShelleyNetwork(testnetMagicCode, new BigInteger("1000000000"), tempDir, port, nodeOutputConsumer);
	}

	public CardanoNodeManager createTemporaryShelleyNetwork(long testnetMagicCode, BigInteger lovelaceSupply, long port, Consumer<String> nodeOutputConsumer) {
		return createTemporaryShelleyNetwork(testnetMagicCode, lovelaceSupply, tempDir, port, nodeOutputConsumer);
	}

	/**
	 * Creates a temporary Shelley blockchain from scratch, used for testing.
	 *
	 * All steps executed here are adapted from this tutorial:
	 * https://docs.cardano.org/projects/cardano-node/en/latest/reference/shelley-genesis.html
	 *
	 * @param testnetMagicCode   a testnet magic number
	 * @param lovelaceSupply     the total lovelace supply of the blockchain
	 * @param rootDir            directory to where all blockchain files should reside
	 * @param port               the port used by the cardano-node instance that will manage the blockchain
	 * @param nodeOutputConsumer a consumer for all console messages produced by the cardano-node
	 *
	 * @return a {@link CardanoNodeManager}, with a reference to the active cardano-node process that is running the temporary blockchain.
	 */
	public CardanoNodeManager createTemporaryShelleyNetwork(long testnetMagicCode, BigInteger lovelaceSupply, File rootDir, long port, Consumer<String> nodeOutputConsumer) {
		File shelleyGenesisDir = createDir(rootDir, "shelley-" + UUID.randomUUID());
		if (createShelleyGenesis(testnetMagicCode, lovelaceSupply, shelleyGenesisDir)) {
			File coldSigningKeyFile = buildFile(shelleyGenesisDir, "delegate-keys", "delegate1.skey");
			File operationalCertificateIssueCounter = buildFile(shelleyGenesisDir, "delegate-keys", "delegate1.counter");
			genesisFile = buildFile(shelleyGenesisDir, "genesis.json");

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
					" --host-addr 0.0.0.0" +
					" --port " + port;

			CardanoNodeManager nodeManager = new CardanoNodeManager(toolDir.getAbsolutePath(), nodeOutputConsumer);
			nodeManager.setStartupCommand(command);
			nodeManager.startProcess();

			try {
				Thread.sleep(2_000);
			} catch (Exception e) {
				Thread.currentThread().interrupt();
				throw new IllegalStateException("Error waiting 2 seconds until genesis start time", e);
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

	public File getGenesisFile() {
		return genesisFile;
	}
}
