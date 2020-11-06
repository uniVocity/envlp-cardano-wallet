package com.univocity.cardano.wallet.embedded.services;

import org.apache.commons.io.*;
import org.apache.commons.lang3.*;
import org.slf4j.*;

import java.io.*;
import java.math.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;

import static java.time.temporal.ChronoUnit.*;

public class CardanoCliManager extends ProcessManager {

	private static final Logger log = LoggerFactory.getLogger(CardanoCliManager.class);

	protected File tempDir;

	public CardanoCliManager(String toolDirPath) {
		super(toolDirPath, "cardano-cli", System.out::println);

		try {
			tempDir = File.createTempFile("tmp", ".txt").getParentFile();
		} catch (Exception e) {
			log.warn("Unable to determine temporary directory", e);
		}
	}

	protected File createDir(File root, String subDir) {
		File out = root.toPath().resolve(subDir).toFile();
		try {
			FileUtils.forceMkdir(out);
		} catch (Exception e) {
			throw new IllegalStateException("Unable to create directory: " + out.getAbsolutePath(), e);
		}
		return out;
	}

	protected File buildFile(File root, String... path) {
		Path tmp = root.toPath();
		for (int i = 0; i < path.length; i++) {
			tmp = tmp.resolve(path[i]);
		}
		return tmp.toFile();
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
		String startTime = LocalDateTime.now().plus(2, SECONDS).atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss'Z'"));

		String command = "" +
				"shelley genesis create" +
				networkIdentifierString(testnetMagicCode) +
				" --start-time " + startTime +
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
