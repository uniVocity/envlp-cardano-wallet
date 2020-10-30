package com.univocity.cardano.wallet.api.service.exception;

import com.univocity.cardano.wallet.api.service.*;

public class InvalidWalletPasswordException extends WalletApiException {

	private final String walletId;

	public InvalidWalletPasswordException(WalletApiError error, String walletId) {
		super(error);
		this.walletId = walletId;
	}

	public String getWalletId() {
		return walletId;
	}
}
