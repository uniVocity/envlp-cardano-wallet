package com.univocity.cardano.wallet;

import com.univocity.cardano.wallet.embedded.services.*;

public class EmbeddedWalletServer extends RemoteWalletServer {

	private final CardanoNodeManager nodeManager;
	private final CardanoWalletManager walletManager;

	public EmbeddedWalletServer(WalletServer.WalletServerConfig config) {
		super(config);

		String cardanoTools = config.cardanoToolsDir.getAbsolutePath();
		String blockchainPath = config.blockchainDir.getAbsolutePath();
		String socketPath = config.blockchainDir.toPath().resolve("node.socket").toFile().getAbsolutePath();


		nodeManager = new CardanoNodeManager(cardanoTools);
		nodeManager.setStartupCommand(
				"run" +
						" --topology " + config.nodeTopologyFile.getAbsolutePath() +
						" --database-path " + blockchainPath +
						" --socket-path " + socketPath +
						" --host-addr 0.0.0.0" +
						" --port " + config.nodePort +
						" --config " + config.nodeConfigurationFile);
		nodeManager.startProcess();

		walletManager = new CardanoWalletManager(cardanoTools);
		walletManager.setStartupCommand(
				"serve" +
						" --mainnet" +
						" --database " + blockchainPath +
						" --node-socket " + socketPath +
						" --port " + config.walletPort
		);
		walletManager.startProcess();
	}

	public CardanoNodeManager getNodeManager() {
		return nodeManager;
	}

	public CardanoWalletManager getWalletManager() {
		return walletManager;
	}
}
