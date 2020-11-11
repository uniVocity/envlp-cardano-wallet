package com.univocity.cardano.wallet.api.service.exception;

import com.univocity.cardano.wallet.api.service.*;

import java.math.*;

public class RewardsNotRedeemedException extends WalletApiException {

	private final BigDecimal rewardsBalance;

	public RewardsNotRedeemedException(WalletApiError error, BigDecimal rewardsBalance) {
		super(error);
		this.rewardsBalance = rewardsBalance;
	}

	public BigDecimal getRewardsBalance() {
		return rewardsBalance;
	}
}
