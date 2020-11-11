package com.univocity.cardano.wallet.api.service.exception;

import com.univocity.cardano.wallet.api.service.*;

public class StakePoolAlreadyJoinedException extends WalletApiException {

	private final String stakePoolId;

	public StakePoolAlreadyJoinedException(WalletApiError error, String stakePoolId) {
		super(error);
		this.stakePoolId = stakePoolId;
	}

	public String getStakePoolId() {
		return stakePoolId;
	}
}
