package com.univocity.cardano.wallet.builders;

public interface BlockchainConfig {

	PortConfig blockchain(String pathToBlockchainDir);
}
