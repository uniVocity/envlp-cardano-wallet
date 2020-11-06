package com.univocity.cardano.wallet.builders.server;

import com.univocity.cardano.wallet.embedded.services.*;

public class EmbeddedWalletServer extends RemoteWalletServer {

	private final CardanoNodeManager nodeManager;
	private final CardanoWalletManager walletManager;

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

		walletManager.setStartupCommand(
				"serve " +
						networkParam +
						" --database " + nodeManager.getBlockchainDirPath() +
						" --node-socket " + nodeManager.getSocketPath() +
						" --port " + config.walletPort
		);

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
