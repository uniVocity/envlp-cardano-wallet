package com.univocity.cardano.wallet.api;

import com.univocity.cardano.wallet.api.generated.*;
import com.univocity.cardano.wallet.api.service.*;

public class WalletApi {

	private final AsynchronousWalletApi async;
	private final SynchronousWalletApi sync;

	public WalletApi(WalletApiConfiguration configuration) {
		InternalWalletApiService api = InternalWalletApiServiceGenerator.createService(configuration, InternalWalletApiService.class);
		async = new AsynchronousWalletApi(api);
		sync = new SynchronousWalletApi(api);
	}

	public AsynchronousWalletApi async() {
		return async;
	}

	public SynchronousWalletApi sync() {
		return sync;
	}
}

