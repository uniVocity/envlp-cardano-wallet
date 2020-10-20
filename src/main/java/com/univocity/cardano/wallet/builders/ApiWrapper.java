package com.univocity.cardano.wallet.builders;

import com.univocity.cardano.wallet.api.*;

public abstract class ApiWrapper {
	protected final WalletApi api;

	protected ApiWrapper(WalletApi api) {
		this.api = api;
	}

}
