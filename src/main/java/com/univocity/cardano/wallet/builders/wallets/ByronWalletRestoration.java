package com.univocity.cardano.wallet.builders.wallets;


public interface ByronWalletRestoration<T extends Wallet> {

	WalletPassword<T> fromSeed(String fromSeed);

	WalletPassword<T> fromPrivateKey(String privateKey);

}
