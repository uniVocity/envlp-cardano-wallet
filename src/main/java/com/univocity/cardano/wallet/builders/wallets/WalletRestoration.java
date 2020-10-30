package com.univocity.cardano.wallet.builders.wallets;


public interface WalletRestoration<T extends Wallet> {

	WalletPassword<T> fromSeed(String fromSeed);

	T fromPublicKey(String publicKey);

}
