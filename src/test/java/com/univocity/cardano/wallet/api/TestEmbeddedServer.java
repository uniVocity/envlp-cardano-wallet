package com.univocity.cardano.wallet.api;

import com.univocity.cardano.wallet.builders.server.*;
import com.univocity.cardano.wallet.common.x509.*;
import org.testng.annotations.*;

@Ignore
public class TestEmbeddedServer {


	public static void main(String... args) throws Exception {
		final String HOME = System.getProperty("user.home");
		final String CONFIGS = HOME + "/dev/repository/envlp/src/main/resources/";
		final String DOWNLOADS = HOME + "/Downloads";

		EmbeddedWalletServer server = WalletServer.embedded()
				.binariesIn(DOWNLOADS + "/cardano-wallet-linux64/")
				.mainnetNode()
				.configuration(CONFIGS + "mainnet-config.json")
				.topology(CONFIGS + "mainnet-topology.json")
				.storeBlockchainIn(HOME + "/Downloads/blockchain")
				.randomPort()
				.consumeOutput(System.out::println)
				.wallet()
				//.enableHttps()
				.port(4444)
				.consumeOutput(System.out::println);

		server.start();

//		System.out.println("WAITING--------------------------");
//		Thread.sleep(2000);
//		CertificateGenerator.main("https://localhost:" + server.getWalletPort());

		server.waitForServerToStop();
	}
}
