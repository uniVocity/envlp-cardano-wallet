package com.univocity.cardano.wallet.builders.server;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.builders.network.*;
import com.univocity.cardano.wallet.builders.stakepools.*;
import com.univocity.cardano.wallet.builders.wallets.*;

public class RemoteWalletServer {

	private final WalletApi api;
	private final StakePools stakePools;
	private final Network network;
	private final Wallets wallets;

	public RemoteWalletServer(WalletServer.WalletServerConfig config) {
		ApiConfiguration configuration = new ApiConfiguration();
		configuration.setWalletServiceHost(config.walletHost);
		configuration.setWalletServicePort(config.walletPort);

		api = new WalletApi(configuration);

		stakePools = new StakePools(api);
		network = new Network(api);
		wallets = new Wallets(api);
	}

	public WalletApi api() {
		return api;
	}

	public StakePools stakePools(){
		return stakePools;
	}

	public Network network(){
		return network;
	}

	public Wallets wallets(){
		return wallets;
	}
}
