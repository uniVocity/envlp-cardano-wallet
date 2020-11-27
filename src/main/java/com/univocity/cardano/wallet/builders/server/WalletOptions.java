package com.univocity.cardano.wallet.builders.server;

public interface WalletOptions extends PortConfig<EmbeddedWalletServer> {
	PortConfig<EmbeddedWalletServer> enableHttps();
}
