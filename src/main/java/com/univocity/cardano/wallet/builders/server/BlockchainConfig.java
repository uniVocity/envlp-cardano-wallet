package com.univocity.cardano.wallet.builders.server;

public interface BlockchainConfig {

	PortConfig<WalletBuilder> storeBlockchainIn(String pathToBlockchainDir);
}
