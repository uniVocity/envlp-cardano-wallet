package com.univocity.cardano.wallet.builders.server;

public interface PortConfig<T> {

	ProcessOutput<T> port(int port);
}
