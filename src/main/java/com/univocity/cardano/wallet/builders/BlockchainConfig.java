package com.univocity.cardano.wallet.builders;

public interface BlockchainConfig {

	PortConfig<Wallet> storeBlockchainIn(String pathToBlockchainDir);
}
