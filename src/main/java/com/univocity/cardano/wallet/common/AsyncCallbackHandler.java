package com.univocity.cardano.wallet.common;

import com.univocity.cardano.wallet.api.service.*;

import java.util.function.*;

public class AsyncCallbackHandler<T, O> implements WalletApiCallback<T> {

	private O lastResult;
	private boolean fetching;

	private final Consumer<WalletApiCallback<T>> action;
	private final Function<T, O> conversion;

	public AsyncCallbackHandler(Consumer<WalletApiCallback<T>> action, Function<T, O> conversion) {
		this.action = action;
		this.conversion = conversion;
	}

	@Override
	public void onResponse(T response) {
		try {
			lastResult = conversion.apply(response);
		} finally {
			fetching = false;
		}

	}

	@Override
	public void onFailure(Throwable error) {
		fetching = false;
		WalletApiCallback.super.onFailure(error);
	}

	public O get() {
		if (!fetching) {
			synchronized (this) {
				if (!fetching) {
					fetching = true;
					action.accept(this);
				}
			}
		}
		return lastResult;
	}
}


