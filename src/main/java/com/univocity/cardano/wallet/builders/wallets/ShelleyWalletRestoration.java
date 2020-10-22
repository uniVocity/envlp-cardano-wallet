package com.univocity.cardano.wallet.builders.wallets;


public interface ShelleyWalletRestoration {

	ShelleyWalletPassword fromSeed(String seedPhrase);

	Wallet fromPublicKey(String publicKey);

}
