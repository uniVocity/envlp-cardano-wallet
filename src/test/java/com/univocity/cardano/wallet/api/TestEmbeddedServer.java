package com.univocity.cardano.wallet.api;

import com.univocity.cardano.wallet.builders.server.*;
import com.univocity.cardano.wallet.builders.stakepools.*;
import org.testng.annotations.*;

import java.net.*;
import java.util.*;
import java.util.function.*;

@Ignore
public class TestEmbeddedServer {


	public static void main(String... args) throws InterruptedException {
		final String HOME = System.getProperty("user.home");
		final String CONFIGS = HOME + "/dev/repository/free-commerce/src/main/resources/";
		final String DOWNLOADS = HOME + "/Downloads";

		EmbeddedWalletServer server = WalletServer.embedded()
				.binariesIn(DOWNLOADS + "/cardano-wallet-2020.10.13/")
				.node()
				.configuration(CONFIGS + "mainnet-config.json")
				.topology(CONFIGS + "mainnet-topology.json")
				.storeBlockchainIn(HOME + "/Downloads/blockchain")
				.port(3333)
				.consumeOutput(System.out::println)
				.wallet()
				.port(4444)
				.consumeOutput(System.out::println);


		while (true) {
			Thread.sleep(10000);

			printResult(() -> server.network().clock());
			printResult(() -> server.network().information());
			printResult(() -> server.network().parameters());

			printResult(() -> {
				List<StakePool> pools = server.stakePools().list();
				StringBuilder tmp = new StringBuilder();

				pools.forEach(p -> {
					try {
						tmp.append(p.ticker()).append(" - ").append(p.formattedMarginPercentage()).append('\n');
					} catch (Exception e) {
						if (e.getCause() instanceof SocketTimeoutException) {
							System.err.println(e.getMessage());
						} else {
							e.printStackTrace();
						}
					}
				});
				return tmp.toString();
			});

			printResult(() -> server.wallets().list());
		}

	}

	private static void printResult(Supplier<Object> supplier) {
		try {
			System.out.println(supplier.get());
		} catch (Exception e) {
			if (e.getCause() instanceof SocketTimeoutException) {
				System.err.println(e.getMessage());
			} else {
				e.printStackTrace();
			}
		}
	}
}
