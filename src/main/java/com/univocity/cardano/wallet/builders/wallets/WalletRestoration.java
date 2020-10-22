package com.univocity.cardano.wallet.builders.wallets;


public interface WalletRestoration {

	WalletPassword fromSeed(String fromSeed);

	Wallet fromPublicKey(String publicKey);

}
