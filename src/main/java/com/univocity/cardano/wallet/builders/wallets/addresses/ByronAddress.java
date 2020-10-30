package com.univocity.cardano.wallet.builders.wallets.addresses;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.common.*;

public class ByronAddress extends WrapperWithId<AbstractAddress> implements Address {

	private final Address.State state;

	public ByronAddress(AbstractAddress original, WalletApi api) {
		super(original, api);
		state = Address.State.valueOf(original.getState());
	}

	@Override
	public String id() {
		return original.getId();
	}

	@Override
	public State state() {
		return state;
	}
}
