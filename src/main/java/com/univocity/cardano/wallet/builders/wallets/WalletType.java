package com.univocity.cardano.wallet.builders.wallets;

public interface WalletType {

	ShelleyWalletRestorationOptions shelley();

	ByronWalletRestoration byron();

	WalletRestoration icarus();

	WalletRestoration ledger();

	WalletRestoration trezor();

}
