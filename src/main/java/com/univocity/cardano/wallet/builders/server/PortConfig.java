package com.univocity.cardano.wallet.builders.server;

public interface PortConfig<T> {

	ProcessOutput<T> randomPort();

	ProcessOutput<T> port(int port);
}
