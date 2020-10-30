package com.univocity.cardano.wallet.builders.server;

import com.univocity.cardano.wallet.embedded.services.*;

public class EmbeddedWalletServer extends RemoteWalletServer {

	private final CardanoNodeManager nodeManager;
	private final CardanoWalletManager walletManager;

	public EmbeddedWalletServer(WalletServer.WalletServerConfig config) {
		super(config);

		String cardanoTools = config.cardanoToolsDir.getAbsolutePath();
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


		walletManager = new CardanoWalletManager(cardanoTools, config.walletOutputConsumer);
		walletManager.setStartupCommand(
				"serve" +
						" --mainnet" +
						" --database " + blockchainPath +
						" --node-socket " + socketPath +
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
