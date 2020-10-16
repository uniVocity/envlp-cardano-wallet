package com.univocity.cardano.wallet.api;

public class WalletApiConfiguration {

	private int walletServicePort = 3002;
	private String walletServiceHost = "http://localhost";



	public int getWalletServicePort() {
		return walletServicePort;
	}

	public String getWalletServiceBaseUrl() {
		return walletServiceHost + ":" + walletServicePort;
	}
}
