package com.univocity.cardano.wallet.builders.wallets;

public interface ShelleyWalletRestorationOptions extends ShelleyWalletRestoration<ShelleyWallet>{

	ShelleyWalletRestoration<ShelleyWallet> addressPoolGap(int gap);

}
