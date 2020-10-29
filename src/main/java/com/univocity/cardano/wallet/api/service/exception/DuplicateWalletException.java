package com.univocity.cardano.wallet.api.service.exception;

import com.univocity.cardano.wallet.api.service.*;

public class DuplicateWalletException extends WalletApiException {

	private final String walletId;

	public DuplicateWalletException(WalletApiError error, String walletId) {
		super(error);
		this.walletId = walletId;
	}

	public String getWalletId() {
		return walletId;
	}
}
