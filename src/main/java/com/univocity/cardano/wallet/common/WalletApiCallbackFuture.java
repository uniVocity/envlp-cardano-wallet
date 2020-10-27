package com.univocity.cardano.wallet.common;

import com.univocity.cardano.wallet.api.service.*;

import java.util.concurrent.*;
import java.util.function.*;

public class WalletApiCallbackFuture<T, O> implements Future<O>, WalletApiCallback<T> {

	private volatile O result;
	private volatile boolean cancelled;
	private final CountDownLatch countDownLatch;
	private final Consumer<Throwable> errorHandler;
	private final Function<T, O> conversion;

	public WalletApiCallbackFuture(O defaultValue, Function<T, O> conversion, Consumer<Throwable> errorHandler) {
		this.result = defaultValue;
		this.conversion = conversion;
		this.errorHandler = errorHandler;
		this.countDownLatch = new CountDownLatch(1);
	}

	@Override
	public boolean cancel(final boolean mayInterruptIfRunning) {
		if (isDone()) {
			return false;
		} else {
			cancelled = true;
			countDownLatch.countDown();
			return !isDone();
		}
	}

	@Override
	public O get() throws InterruptedException {
		countDownLatch.await();
		return result;
	}

	@Override
	public O get(final long timeout, final TimeUnit unit) throws InterruptedException {
		countDownLatch.await(timeout, unit);
		return result;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public boolean isDone() {
		return countDownLatch.getCount() == 0;
	}

	@Override
	public void onResponse(T response) {
		try {
			O output = conversion.apply(response);
			if (output != null) {
				this.result = output;
			}
		} finally {
			countDownLatch.countDown();
		}
	}

	@Override
	public void onFailure(Throwable error) {
		try {
			if (errorHandler != null) {
				errorHandler.accept(error);
			} else {
				WalletApiCallback.super.onFailure(error);
			}
		} finally {
			countDownLatch.countDown();
		}
	}
}
