package com.univocity.cardano.wallet.common;

import com.univocity.cardano.wallet.api.*;

public abstract class WrapperWithId<T> extends Wrapper<T> implements ObjectWithId {

	public WrapperWithId(T original, WalletApi api) {
		super(original, api);
	}

	public abstract String id();

	@Override
	public final int hashCode() {
		return id().hashCode();
	}

	@Override
	public final boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (o.getClass().isAssignableFrom(this.getClass())) {
			WrapperWithId<?> other = ((WrapperWithId<?>) o);
			return id().equals(other.id());
		}
		return false;
	}
}
