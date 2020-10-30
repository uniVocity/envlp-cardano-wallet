package com.univocity.cardano.wallet.builders.wallets.addresses;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.addresses.*;
import com.univocity.cardano.wallet.common.*;

public class ShelleyAddress extends WrapperWithId<ListAddressesResponseItem> implements Address {

	private final State state;

	public ShelleyAddress(ListAddressesResponseItem original, WalletApi api) {
		super(original, api);
		state = State.valueOf(original.getState());
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
