package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.*;

public class ShelleyTransaction extends AbstractTransaction {

	public ShelleyTransaction(com.univocity.cardano.wallet.api.generated.common.AbstractTransaction original, WalletApi api) {
		super(original, api);
	}
}
