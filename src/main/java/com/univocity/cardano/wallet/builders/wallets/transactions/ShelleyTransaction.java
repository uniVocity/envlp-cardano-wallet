package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.common.*;

public class ShelleyTransaction  extends Wrapper<AbstractTransaction> implements Transaction {

	public ShelleyTransaction(AbstractTransaction original, WalletApi api) {
		super(original, api);
	}

}
