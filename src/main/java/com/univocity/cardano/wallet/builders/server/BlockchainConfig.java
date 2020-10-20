package com.univocity.cardano.wallet.builders.server;

public interface BlockchainConfig {

	PortConfig<Wallet> storeBlockchainIn(String pathToBlockchainDir);
}
