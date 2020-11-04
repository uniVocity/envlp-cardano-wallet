package com.univocity.cardano.wallet.builders.server;

public interface Node {

	NodeConfig mainnetNode();

	NodeConfig testnetNode(long testnetMagicCode);

	PortConfig<WalletBuilder> temporaryBlockchainNode();
}
