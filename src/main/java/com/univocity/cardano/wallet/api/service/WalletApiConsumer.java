package com.univocity.cardano.wallet.api.service;


import com.univocity.cardano.wallet.api.service.exception.*;

import java.util.function.*;

public class WalletApiConsumer<I, O> implements WalletApiCallback<I> {

	private final Function<I, O> converter;
	private final Consumer<O> consumer;
	private final Consumer<Throwable> errorHandler;

	public WalletApiConsumer(Function<I, O> converter, Consumer<O> consumer) {
		this(converter, consumer, null);
	}

	public WalletApiConsumer(Function<I, O> converter, Consumer<O> consumer, Consumer<Throwable> errorHandler) {
		this.converter = converter;
		this.consumer = consumer;
		this.errorHandler = errorHandler;
	}

	@Override
	public void onResponse(I response) {
		consumer.accept(converter.apply(response));
	}

	@Override
	public void onFailure(Throwable error) {
		WalletApiCallback.super.onFailure(error);
		if (errorHandler != null) {
			errorHandler.accept(error);
		}
	}
}