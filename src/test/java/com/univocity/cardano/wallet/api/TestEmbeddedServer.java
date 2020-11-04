package com.univocity.cardano.wallet.api;

import com.univocity.cardano.wallet.builders.server.*;
import org.testng.annotations.*;

@Ignore
public class TestEmbeddedServer {


	public static void main(String... args) throws InterruptedException {
		final String HOME = System.getProperty("user.home");
		final String CONFIGS = HOME + "/dev/repository/free-commerce/src/main/resources/";
		final String DOWNLOADS = HOME + "/Downloads";

		EmbeddedWalletServer server = WalletServer.embedded()
				.binariesIn(DOWNLOADS + "/cardano-wallet-2020.10.13/")
				.mainnetNode()
				.configuration(CONFIGS + "mainnet-config.json")
				.topology(CONFIGS + "mainnet-topology.json")
				.storeBlockchainIn(HOME + "/Downloads/blockchain")
				.port(3333)
				.consumeOutput(System.out::println)
				.wallet()
				.port(4444)
				.consumeOutput(System.out::println);

		server.start();
		server.waitForServerToStop();
	}
}
