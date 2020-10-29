package com.univocity.cardano.wallet.api.service;

import com.univocity.cardano.wallet.api.service.exception.*;
import retrofit2.*;

import java.io.*;


/**
 * A wrapper which transforms a Retrofit {@code Callback} into a {@link WalletApiCallback} which is exposed to the client.
 */
public final class WalletApiCallbackAdapter<T> implements Callback<T> {

	private final WalletApiCallback<T> callback;

	public WalletApiCallbackAdapter(WalletApiCallback<T> callback) {
		this.callback = callback;
	}

	public void onResponse(Call<T> call, Response<T> response) {
		if (response.isSuccessful()) {
			callback.onResponse(response.body());
		} else {
			try {
				WalletApiError apiError = InternalWalletApiServiceGenerator.getWalletApiError(response);
				onFailure(call, WalletApiException.translateError(apiError));
			} catch (IOException e) {
				onFailure(call, new WalletApiException(e));
			}
		}
	}

	@Override
	public void onFailure(Call<T> call, Throwable throwable) {
		if (throwable instanceof WalletApiException) {
			callback.onFailure(throwable);
		} else {
			callback.onFailure(new WalletApiException(throwable));
		}
	}
}
