package com.univocity.cardano.wallet.embedded.services;

import java.util.function.*;

public class CardanoWalletManager extends ProcessManager {

	public CardanoWalletManager(String walletDirPath) {
		this(walletDirPath, null);
	}

	public CardanoWalletManager(String walletDirPath, Consumer<String> outputConsumer) {
		super(walletDirPath, "cardano-wallet", outputConsumer);
	}

	@Override
	protected String getStartupCommand() {
		return "serve" +
				" --mainnet" +
				" --database {blockchain.dir}" +
				" --node-socket {blockchain.dir}/node.socket" +
				" --port {cardano.wallet.port}";
	}

	public static void main(String... args) throws Exception {
		new CardanoWalletManager("/tmp/", System.out::println).startProcess();

		Thread.sleep(10000000);
	}
}

