package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.*;

public class ByronTransaction extends AbstractTransaction {

	public ByronTransaction(com.univocity.cardano.wallet.api.generated.common.AbstractTransaction original, WalletApi api) {
		super(original, api);
	}
}
