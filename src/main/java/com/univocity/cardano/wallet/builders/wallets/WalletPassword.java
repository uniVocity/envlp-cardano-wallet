package com.univocity.cardano.wallet.builders.wallets;

public interface WalletPassword<T extends Wallet> {

	T password(String password);
}
