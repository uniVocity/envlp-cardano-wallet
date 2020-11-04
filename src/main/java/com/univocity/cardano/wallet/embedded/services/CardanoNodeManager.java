package com.univocity.cardano.wallet.embedded.services;

import org.apache.commons.lang3.*;

import java.util.function.*;

public class CardanoNodeManager extends ProcessManager {

	public CardanoNodeManager(String nodeDirPath) {
		this(nodeDirPath, null);
	}

	public CardanoNodeManager(String nodeDirPath, Consumer<String> outputConsumer) {
		super(nodeDirPath, "cardano-node", outputConsumer);
	}

	public String getNodePort() {
		return getParameterValue("port");
	}

	public String getBlockchainDirPath() {
		return getParameterValue("database");
	}

	public String getSocketPath() {
		return getParameterValue("socket-path");
	}

	private String getParameterValue(String param) {
		String str = StringUtils.substringAfter(getStartupCommand(), "--" + param);
		if (StringUtils.isBlank(str)) {
			return null;
		}
		str = str.trim();
		String value = StringUtils.substringBefore(str, "--");
		if (StringUtils.isBlank(value)) {
			return str;
		}
		return value.trim();
	}

	public static void main(String... args) throws Exception {
		new CardanoNodeManager("/tmp/", System.out::println).startProcess();

		Thread.sleep(10000000);
	}
}
