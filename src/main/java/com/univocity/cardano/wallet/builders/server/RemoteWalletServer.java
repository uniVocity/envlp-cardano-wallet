package com.univocity.cardano.wallet.builders.server;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.builders.network.*;
import com.univocity.cardano.wallet.builders.stakepools.*;

public class RemoteWalletServer {

	private final WalletApi api;
	private StakePools stakePools;
	private Network network;

	public RemoteWalletServer(WalletServer.WalletServerConfig config) {
		ApiConfiguration configuration = new ApiConfiguration();
		configuration.setWalletServiceHost(config.walletHost);
		configuration.setWalletServicePort(config.walletPort);

		api = new WalletApi(configuration);

		stakePools = new StakePools(api);
		network = new Network(api);
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
}
