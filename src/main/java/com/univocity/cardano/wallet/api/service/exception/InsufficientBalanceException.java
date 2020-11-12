package com.univocity.cardano.wallet.api.service.exception;

import com.univocity.cardano.wallet.api.service.*;

import java.math.*;

public class InsufficientBalanceException extends WalletApiException {

	private final BigDecimal totalUtxoBalance;
	private final BigDecimal requiredUtxoBalance;

	public InsufficientBalanceException(WalletApiError error, BigDecimal totalUtxoBalance, BigDecimal requiredUtxoBalance) {
		super(error);
		this.totalUtxoBalance = totalUtxoBalance;
		this.requiredUtxoBalance = requiredUtxoBalance;
	}

	public BigDecimal getTotalUtxoBalance() {
		return totalUtxoBalance;
	}

	public BigDecimal getRequiredUtxoBalance() {
		return requiredUtxoBalance;
	}
}
