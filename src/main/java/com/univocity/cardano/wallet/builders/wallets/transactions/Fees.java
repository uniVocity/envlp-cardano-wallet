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

	public BigInteger minimumInLovelace(){
		return original.getEstimatedMin().getQuantity();
	}

	public BigInteger maximumInLovelace(){
		return original.getEstimatedMax().getQuantity();
	}

	public BigInteger averageInLovelace(){
		return maximumInLovelace().add(minimumInLovelace()).divide(new BigInteger("2"));
	}


	public BigDecimal minimumInAda(){
		return lovelaceToAda(minimumInLovelace());
	}

	public BigDecimal maximumInAda(){
		return lovelaceToAda(maximumInLovelace());
	}

	public BigDecimal averageInAda(){
		return minimumInAda().add(maximumInAda()).divide(new BigDecimal(2), 6, RoundingMode.HALF_UP);
	}

	public BigInteger getDepositInLovelace(){
		return original.getDeposit().getQuantity();
	}

	public BigDecimal getDepositInAda(){
		return lovelaceToAda(getDepositInLovelace());
	}

	public T authorize(String password) {
		return transactionExecution.apply(password);
	}
}
