package com.univocity.cardano.wallet.builders.wallets.transactions;

public interface Authorization<T extends Transaction> {

	T authorize(String password);

	Fees estimateFees();
}
