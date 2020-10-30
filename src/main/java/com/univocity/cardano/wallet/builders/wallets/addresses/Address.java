package com.univocity.cardano.wallet.builders.wallets.addresses;

import com.univocity.cardano.wallet.common.*;

public interface Address extends ObjectWithId {

	enum State {
		used, unused
	}

	State state();

	default boolean used() {
		return state() == State.used;
	}

	default boolean unused() {
		return state() == State.unused;
	}
}
