package com.univocity.cardano.wallet.api;

public class WalletApiConfiguration {

	private int port = 3002;
	private String host = "http://localhost";

	public int getWalletServicePort() {
		return port;
	}

	public String getWalletServiceBaseUrl() {
		return host + ":" + port;
	}
}
