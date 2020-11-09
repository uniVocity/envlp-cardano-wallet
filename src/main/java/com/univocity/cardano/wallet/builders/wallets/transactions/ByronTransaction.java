package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.builders.wallets.*;

public class ByronTransaction extends AbstractTransaction<ByronWallet, ByronTransaction> {

	public ByronTransaction(ByronWallet byronWallet, com.univocity.cardano.wallet.api.generated.common.AbstractTransaction original, WalletApi api) {
		super(byronWallet, original, api);
	}

	@Override
	public void forget() {
		api.sync().deleteByronTransaction(getWallet().id(), id());
	}
}
