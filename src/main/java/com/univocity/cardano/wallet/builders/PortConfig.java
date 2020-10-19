package com.univocity.cardano.wallet.builders;

public interface PortConfig<T> {

	ProcessOutput<T> port(int port);
}
