package com.univocity.cardano.wallet.common;

public interface ObjectWithId extends Comparable<ObjectWithId>{

	String id();

	@Override
	default int compareTo(ObjectWithId o) {
		return id().compareTo(o.id());
	}
}
