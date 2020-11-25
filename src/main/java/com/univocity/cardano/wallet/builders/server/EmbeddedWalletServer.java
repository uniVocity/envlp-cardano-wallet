package com.univocity.cardano.wallet.builders.server;

import com.univocity.cardano.wallet.common.x509.*;
import com.univocity.cardano.wallet.embedded.services.*;
import org.apache.commons.lang3.*;
import org.slf4j.*;

import java.util.*;

public class EmbeddedWalletServer extends RemoteWalletServer {

	private static final Logger log = LoggerFactory.getLogger(EmbeddedWalletServer.class);
	private final CardanoNodeManager nodeManager;
	private final CardanoWalletManager walletManager;

	private static Chain certificateChain = null;

	public static synchronized Chain getCertificateChain() {
		return certificateChain;
	}

	private static synchronized Chain createCertificateChain(){
		if (certificateChain == null) {
			try {
				certificateChain = WalletCertificateGenerator.generate();
			} catch (Exception e) {
				log.warn("Could not generate wallet server certificate chain", e);
			}
		}
		return certificateChain;
	}

	public EmbeddedWalletServer(WalletServer.WalletServerConfig config) {
		super(config);

		String networkParam = "--mainnet";

		String cardanoTools = config.cardanoToolsDir.getAbsolutePath();

		if (config.buildTemporaryBlockchain) {
			TemporaryBlockchainHelper tmp = new TemporaryBlockchainHelper(cardanoTools);
			nodeManager = tmp.createTemporaryShelleyNetwork(config.testnetMagic, config.nodePort, config.nodeOutputConsumer);
			networkParam = "--testnet " + tmp.getGenesisFile().getAbsolutePath();
		} else {
			String blockchainPath = config.blockchainDir.getAbsolutePath();
			String socketPath = config.blockchainDir.toPath().resolve("node.socket").toFile().getAbsolutePath();

			if (SystemUtils.IS_OS_WINDOWS) {
				socketPath = "\\\\.\\pipe\\" + UUID.randomUUID().toString();
			}

			nodeManager = new CardanoNodeManager(cardanoTools, config.nodeOutputConsumer);
			nodeManager.setStartupCommand(
					"run" +
							" --topology " + config.nodeTopologyFile.getAbsolutePath() +
							" --database-path " + blockchainPath +
							" --socket-path " + socketPath +
							" --host-addr 0.0.0.0" +
							" --port " + config.nodePort +
							" --config " + config.nodeConfigurationFile);
		}

		walletManager = new CardanoWalletManager(cardanoTools, config.walletOutputConsumer);

		String command = "serve " +
				networkParam +
				" --database " + nodeManager.getBlockchainDirPath() +
				" --node-socket " + nodeManager.getSocketPath() +
				" --port " + config.walletPort;

		if(config.enableHttps) {
			createCertificateChain();
			if (certificateChain != null) {
				command = command +
						" --tls-ca-cert " + certificateChain.getRootCACertificatePath() +
						" --tls-sv-cert " + certificateChain.getServerCertificatePath() +
						" --tls-sv-key " + certificateChain.getServerKeyPath();
			}
		}

		walletManager.setStartupCommand(command);

	}

	public CardanoNodeManager getNodeManager() {
		return nodeManager;
	}

	public CardanoWalletManager getWalletManager() {
		return walletManager;
	}

	public void start() {
		nodeManager.startProcess();
		walletManager.startProcess();
	}

	public void stop() {
		try {
			walletManager.stopProcess();
		} finally {
			nodeManager.stopProcess();
		}
	}

	public void waitForServerToStop() {
		try {
			nodeManager.waitForProcess();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
