package com.univocity.cardano.wallet.builders.server;

public interface RemoteWallet {
	RemoteWalletServer connectToPort(int port);
}
