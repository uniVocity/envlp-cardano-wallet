package com.univocity.cardano.wallet.builders.wallets;


public interface ByronWalletRestoration {

	WalletPassword fromSeed(String fromSeed);

	WalletPassword fromPrivateKey(String privateKey);

}
