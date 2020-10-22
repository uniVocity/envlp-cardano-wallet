package com.univocity.cardano.wallet.builders.wallets;

public interface ShelleyWalletPassword extends WalletPassword {

	WalletPassword secondFactor(String secondFactorPhrase);

}
