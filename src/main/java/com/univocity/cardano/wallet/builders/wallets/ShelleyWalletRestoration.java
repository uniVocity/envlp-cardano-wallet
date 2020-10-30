package com.univocity.cardano.wallet.builders.wallets;


public interface ShelleyWalletRestoration<T extends Wallet> {

	ShelleyWalletPassword fromSeed(String seedPhrase);

	T fromPublicKey(String publicKey);

}
