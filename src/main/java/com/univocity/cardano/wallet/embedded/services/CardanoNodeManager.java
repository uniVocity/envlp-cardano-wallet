package com.univocity.cardano.wallet.embedded.services;

import java.util.function.*;

public class CardanoNodeManager extends ProcessManager {

	public CardanoNodeManager(String nodeDirPath) {
		this(nodeDirPath, null);
	}

	public CardanoNodeManager(String nodeDirPath, Consumer<String> outputConsumer) {
		super(nodeDirPath, "cardano-node", outputConsumer);
	}

	public static void main(String... args) throws Exception {
		new CardanoNodeManager("/tmp/", System.out::println).startProcess();

		Thread.sleep(10000000);
	}
}
