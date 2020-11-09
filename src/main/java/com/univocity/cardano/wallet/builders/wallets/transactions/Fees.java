package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;

public class Fees extends Wrapper<AbstractFeeResponse> {
	public Fees(AbstractFeeResponse original, WalletApi api) {
		super(original, api);
	}

	public BigDecimal minimum(){
		return lovelaceToAda(original.getEstimatedMin());
	}

	public BigDecimal maximum(){
		return lovelaceToAda(original.getEstimatedMax());
	}
}
