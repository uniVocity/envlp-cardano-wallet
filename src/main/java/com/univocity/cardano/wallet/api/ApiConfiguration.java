package com.univocity.cardano.wallet.api;

public class ApiConfiguration {

	private int walletServicePort = 3002;
	private String walletServiceHost = "http://localhost";

	public void setWalletServicePort(int walletServicePort) {
		this.walletServicePort = walletServicePort;
	}

	public void setWalletServiceHost(String walletServiceHost) {
		this.walletServiceHost = walletServiceHost;
	}

	public int getWalletServicePort() {
		return walletServicePort;
	}

	public String getWalletServiceBaseUrl() {
		return walletServiceHost + ":" + walletServicePort;
	}
}
