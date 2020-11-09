package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.util.function.*;

public class Fees<T extends Transaction> extends Wrapper<AbstractFeeResponse> {

	private final Function<String, T> transactionExecution;

	Fees(AbstractFeeResponse original, WalletApi api, Function<String, T> transactionExecution) {
		super(original, api);
		this.transactionExecution = transactionExecution;
	}

	public BigDecimal minimum(){
		return lovelaceToAda(original.getEstimatedMin());
	}

	public BigDecimal maximum(){
		return lovelaceToAda(original.getEstimatedMax());
	}

	public BigDecimal average(){
		return minimum().add(maximum()).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP);
	}

	public T authorize(String password) {
		return transactionExecution.apply(password);
	}
}
