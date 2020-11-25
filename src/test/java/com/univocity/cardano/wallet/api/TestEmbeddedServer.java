package com.univocity.cardano.wallet.api;

import com.univocity.cardano.wallet.builders.server.*;
import com.univocity.cardano.wallet.common.x509.*;
import org.testng.annotations.*;

@Ignore
public class TestEmbeddedServer {


	public static void main(String... args) throws Exception {
		final String HOME = System.getProperty("user.home");
		final String CONFIGS = HOME + "/dev/repository/free-commerce/src/main/resources/";
		final String DOWNLOADS = HOME + "/Downloads";

		EmbeddedWalletServer server = WalletServer.embedded()
				.binariesIn(DOWNLOADS + "/cardano-wallet-linux64/")
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

//		System.out.println("WAITING--------------------------");
//		Thread.sleep(10000);
//		WalletCertificateGenerator.main(null);

		server.waitForServerToStop();
	}
}
