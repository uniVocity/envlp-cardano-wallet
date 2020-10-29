package com.univocity.cardano.wallet.api.service.exception;

import com.univocity.cardano.wallet.api.generated.*;
import com.univocity.cardano.wallet.api.service.*;
import org.apache.commons.lang3.*;

/**
 * An exception which can occur while invoking methods of the {@link InternalWalletApiService}.
 */
public class WalletApiException extends RuntimeException {

	private static final long serialVersionUID = 3788669840036201041L;
	/**
	 * Error response object returned by cardano-wallet.
	 */
	private WalletApiError error;

	public static WalletApiException translateError(WalletApiError error) {
		String message = error.getMessage();
		if (StringUtils.isNotBlank(message)) {
			if (message.contains("would yield a wallet with")) {
				String id = StringUtils.substringBetween(message, "id: ", " ");
				if (StringUtils.isNotBlank(id)) {
					return new DuplicateWalletException(error, id);
				}
			}

		}
		return new WalletApiException(error);
	}

	/**
	 * Instantiates a new wallet api exception.
	 *
	 * @param error an error response object
	 */
	protected WalletApiException(WalletApiError error) {
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
