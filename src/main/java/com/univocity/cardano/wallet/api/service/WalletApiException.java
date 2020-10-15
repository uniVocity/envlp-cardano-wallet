package com.univocity.cardano.wallet.api.service;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * An exception which can occur while invoking methods of the {@link InternalWalletApiService}.
 */
public class WalletApiException extends RuntimeException {

	private static final long serialVersionUID = 3788669840036201041L;
	/**
	 * Error response object returned by cardano-wallet.
	 */
	private WalletApiError error;

	/**
	 * Instantiates a new wallet api exception.
	 *
	 * @param error an error response object
	 */
	public WalletApiException(WalletApiError error) {
		this.error = error;
	}

	/**
	 * Instantiates a new wallet api exception.
	 */
	public WalletApiException() {
		super();
	}

	/**
	 * Instantiates a new wallet api exception.
	 *
	 * @param message the message
	 */
	public WalletApiException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new wallet api exception.
	 *
	 * @param cause the cause
	 */
	public WalletApiException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new wallet api exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public WalletApiException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @return the response error object from cardano-wallet, or {@code null}
	 * if no response object was returned (e.g. server returned 500).
	 */
	public WalletApiError getError() {
		return error;
	}

	@Override
	public String getMessage() {
		if (error != null) {
			return error.getMessage();
		}
		return super.getMessage();
	}
}
