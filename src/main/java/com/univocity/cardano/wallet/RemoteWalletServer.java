package com.univocity.cardano.wallet;

import com.univocity.cardano.wallet.api.*;

public class RemoteWalletServer {

	private final WalletApi api;

	RemoteWalletServer(WalletServer.WalletServerConfig config) {
		ApiConfiguration configuration = new ApiConfiguration();
		configuration.setWalletServiceHost(config.walletHost);
		configuration.setWalletServicePort(config.walletPort);

		api = new WalletApi(configuration);
	}

	public WalletApi api() {
		return api;
	}
}
