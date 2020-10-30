package com.univocity.cardano.wallet.api.service.exception;

import com.univocity.cardano.wallet.api.service.*;

public class WalletNotFoundException extends WalletApiException {

	private final String walletId;

	public WalletNotFoundException(WalletApiError error, String walletId) {
		super(error);
		this.walletId = walletId;
	}

	public String getWalletId() {
		return walletId;
	}
}
