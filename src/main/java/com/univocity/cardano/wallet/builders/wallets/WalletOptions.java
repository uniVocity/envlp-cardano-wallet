package com.univocity.cardano.wallet.builders.wallets;

public interface WalletOptions extends WalletType {

	WalletType addressPoolGap(int gap);
}
