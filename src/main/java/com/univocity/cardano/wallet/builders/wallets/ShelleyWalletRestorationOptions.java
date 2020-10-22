package com.univocity.cardano.wallet.builders.wallets;

public interface ShelleyWalletRestorationOptions extends ShelleyWalletRestoration{

	ShelleyWalletRestoration addressPoolGap(int gap);

}
