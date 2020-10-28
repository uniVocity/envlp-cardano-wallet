package com.univocity.cardano.wallet.common;

import com.univocity.cardano.wallet.api.service.*;

import java.util.concurrent.*;
import java.util.function.*;

public class AsyncCallbackHandler<T, O> implements WalletApiCallback<T> {

	private O lastResult;
	private boolean fetching;

	private final Consumer<WalletApiCallback<T>> action;
	private final Function<T, O> conversion;
	private final O defaultValue;

	public AsyncCallbackHandler(Consumer<WalletApiCallback<T>> action, Function<T, O> conversion) {
		this(null, action, conversion);
	}

	public AsyncCallbackHandler(O defaultValue, Consumer<WalletApiCallback<T>> action, Function<T, O> conversion) {
		this.action = action;
		this.conversion = conversion;
		this.defaultValue = defaultValue;
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

	public Future<O> getAsync() {
		return getAsync(null);
	}

	public Future<O> getAsync(Consumer<Throwable> errorHandler) {
		fetching = true;
		WalletApiCallbackFuture<T, O> future = new WalletApiCallbackFuture<>(defaultValue, conversion, errorHandler == null ? this::onFailure : error -> {
			fetching = false;
			errorHandler.accept(error);
		});
		action.accept(future);
		return future;
	}

	public static <O> O sync(Future<O> future, O defaultValue) {
		try {
			return future.get();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			if (cause != null) {
				if (cause instanceof RuntimeException) {
					throw (RuntimeException) cause;
				}
				throw new IllegalStateException(cause);
			}
			throw new IllegalStateException(e);
		}
		return defaultValue;
	}

	public O getSync() {
		fetching = true;
		try {
			return sync(getAsync(), defaultValue);
		} finally {
			fetching = false;
		}
	}

	public O getEventually() {
		if (!fetching) {
			synchronized (this) {
				if (!fetching) {
					fetching = true;
					action.accept(this);
				}
			}
		}
		return lastResult == null ? defaultValue : lastResult;
	}
}


