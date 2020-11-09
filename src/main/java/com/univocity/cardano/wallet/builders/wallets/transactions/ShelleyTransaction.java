package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.builders.wallets.*;

public class ShelleyTransaction extends AbstractTransaction<ShelleyWallet, ShelleyTransaction> {

	public ShelleyTransaction(ShelleyWallet wallet, com.univocity.cardano.wallet.api.generated.common.AbstractTransaction original, WalletApi api) {
		super(wallet, original, api);
	}

	@Override
	public void forget() {
		api.sync().deleteTransaction(getWallet().id(), id());
	}
}
