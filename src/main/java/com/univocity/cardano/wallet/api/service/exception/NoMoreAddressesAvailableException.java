package com.univocity.cardano.wallet.api.service.exception;

public class NoMoreAddressesAvailableException extends WalletApiException {

	private final String walletId;
	private final String walletName;

	public NoMoreAddressesAvailableException(String walletId, String walletName) {
		super("No more addresses available for Byron wallet '" + walletName + "' (id: " + walletId + "). Please provide your password to create additional addresses.");
		this.walletId = walletId;
		this.walletName = walletName;
	}

	public String getWalletId() {
		return walletId;
	}

	public String getWalletName() {
		return walletName;
	}
}
