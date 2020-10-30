package com.univocity.cardano.wallet.builders.wallets;

public interface ShelleyWalletPassword extends WalletPassword<ShelleyWallet> {

	WalletPassword<ShelleyWallet> secondFactor(String secondFactorPhrase);

}
