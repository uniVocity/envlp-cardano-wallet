package com.univocity.cardano.wallet.embedded.services;

import java.util.function.*;

public class CardanoNodeManager extends ProcessManager {

	public CardanoNodeManager(String nodeDirPath) {
		this(nodeDirPath, null);
	}

	public CardanoNodeManager(String nodeDirPath, Consumer<String> outputConsumer) {
		super(nodeDirPath, "cardano-node", outputConsumer);
	}

	@Override
	protected String getStartupCommand() {
		return "run" +
				" --topology {config.dir}/mainnet-topology.json" +
				" --database-path {blockchain.dir}" +
				" --socket-path {blockchain.dir}/node.socket" +
				" --host-addr 0.0.0.0" +
				" --port {cardano.node.port}" +
				" --config {config.dir}/mainnet-config.json";
	}

	public static void main(String... args) throws Exception {
		new CardanoNodeManager("/tmp/", System.out::println).startProcess();

		Thread.sleep(10000000);
	}
}
