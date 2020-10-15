package com.univocity.cardano.wallet.api.service;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * A functional interface used together with the {@link AsynchronousWalletApi} to provide a non-blocking REST client
 * that interacts with the cardano-wallet API.
 *
 * @param <T> the return type from the callback
 */
@FunctionalInterface
public interface WalletApiCallback<T> {

	/**
	 * Invoked whenever a response comes back from the {@link InternalWalletApiService}.
	 *
	 * @param response the expected response object
	 */
	void onResponse(T response);

	/**
	 * Invoked whenever an error occurs.
	 *
	 * @param error the error received when executing the operation.
	 */
	default void onFailure(Throwable error) {
	}
}