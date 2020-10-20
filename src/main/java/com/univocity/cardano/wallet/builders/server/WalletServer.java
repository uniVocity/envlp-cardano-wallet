package com.univocity.cardano.wallet.builders.server;

import com.univocity.cardano.wallet.builders.stakepools.*;

import java.io.*;
import java.util.*;
import java.util.function.*;

public class WalletServer {

	public static EmbeddedWallet embedded() {
		return new WalletServerConfig("http://localhost");
	}

	public static RemoteWallet remote(String host) {
		return new WalletServerConfig(host);
	}

	public static class WalletServerConfig implements EmbeddedWallet, RemoteWallet, Node {

		final String walletHost;
		int walletPort;
		int nodePort;
		File cardanoToolsDir;
		File blockchainDir;
		File nodeConfigurationFile;
		File nodeTopologyFile;
		Consumer<String> walletOutputConsumer;
		Consumer<String> nodeOutputConsumer;

		class NodeServerBuilder implements NodeConfig, BlockchainConfig, TopologyConfig, PortConfig<Wallet>, ProcessOutput<Wallet>, Wallet {
			@Override
			public TopologyConfig configuration(String pathToNodeConfiguration) {
				nodeConfigurationFile = toValidatedFile(pathToNodeConfiguration, false);
				return this;
			}


			@Override
			public BlockchainConfig topology(String pathToTopologyFile) {
				nodeTopologyFile = toValidatedFile(pathToTopologyFile, false);
				return this;
			}

			@Override
			public ProcessOutput<Wallet> port(int port) {
				nodePort = port;
				return this;
			}

			@Override
			public PortConfig storeBlockchainIn(String pathToBlockchain) {
				blockchainDir = toValidatedFile(pathToBlockchain, true);
				return this;
			}

			@Override
			public Wallet consumeOutput(Consumer<String> outputConsumer) {
				nodeOutputConsumer = outputConsumer;
				return this;
			}

			@Override
			public Wallet ignoreOutput() {
				return this;
			}

			@Override
			public WalletServerBuilder wallet() {
				return new WalletServerBuilder();
			}
		}

		public class WalletServerBuilder implements PortConfig<EmbeddedWalletServer>, ProcessOutput<EmbeddedWalletServer> {
			@Override
			public ProcessOutput<EmbeddedWalletServer> port(int port) {
				walletPort = port;
				return this;
			}

			@Override
			public EmbeddedWalletServer consumeOutput(Consumer<String> outputConsumer) {
				walletOutputConsumer = outputConsumer;
				return new EmbeddedWalletServer(WalletServerConfig.this);
			}

			@Override
			public EmbeddedWalletServer ignoreOutput() {
				return new EmbeddedWalletServer(WalletServerConfig.this);
			}
		}

		public NodeConfig node() {
			return new NodeServerBuilder();
		}

		public WalletServerConfig(String host) {
			this.walletHost = host;
		}

		@Override
		public RemoteWalletServer connectToPort(int walletPort) {
			this.walletPort = walletPort;
			return new RemoteWalletServer(this);
		}

		@Override
		public Node toolsIn(String pathToCardanoTools) {
			cardanoToolsDir = toValidatedFile(pathToCardanoTools, true);
			return this;
		}

		private static File toValidatedFile(String path, boolean isDir) {
			File file = new File(path);
			if (isDir) {
				if (!file.exists()) {
					file.mkdirs();
				}
			}
			if (!file.exists()) {
				throw new IllegalArgumentException((isDir ? "Directory " : "File ") + path + " does not exist");
			}
			return file;
		}
	}


	public static void main(String... args) throws InterruptedException {
		final String HOME = System.getProperty("user.home");
		final String CONFIGS = HOME + "/dev/repository/free-commerce/src/main/resources/";

		EmbeddedWalletServer server = WalletServer.embedded()
				.toolsIn(HOME + "/dev/repository/free-commerce/src/main/resources/cli/lin")
				.node()
				.configuration(CONFIGS + "mainnet-config.json")
				.topology(CONFIGS + "mainnet-topology.json")
				.storeBlockchainIn(HOME + "/Downloads/blockchain")
				.port(3333)
				.consumeOutput(System.out::println)
				.wallet()
				.port(4444)
				.consumeOutput(System.out::println);

		RemoteWalletServer remoteServer = server;//WalletServer.remote("http://localhost").connectToPort(4444);
//
//		remoteServer.wallets().list();
//		remoteServer.wallets().create("wallet name").shelley().fromSeed("seed abc a").password("qwerty").addressPoolGap(10).secondFactor("");
//		remoteServer.wallets().create("wallet name").shelley().fromPublicKey("hex xpub").addressPoolGap(10);
//
//		remoteServer.wallets().create("wallet name").byron().fromSeed("seed abc a").password("qwerty");
//		remoteServer.wallets().create("wallet name").byron().fromPrivateKey("rootprivatekey HEX").password("qwerty");
//		remoteServer.wallets().create("wallet name").icarus().fromSeed("seed abc a").password("qwerty");
//		remoteServer.wallets().create("wallet name").ledger().fromSeed("seed abc a").password("qwerty");
//		remoteServer.wallets().create("wallet name").trezor().fromSeed("seed abc a").password("qwerty");
//		remoteServer.wallets().create("wallet name").icarus().fromPublicKey("hex xpub").addressPoolGap(10);
//		remoteServer.wallets().create("wallet name").ledger().fromPublicKey("hex xpub").addressPoolGap(10);
//		remoteServer.wallets().create("wallet name").trezor().fromPublicKey("hex xpub").addressPoolGap(10);
//
//
//		Wallet wallet = remoteServer.wallets().getById("hex xpub");
//		wallet.getUTxoStatistics();
//		wallet.delete();
//		wallet.rename("new wallet name");
//		wallet.updatePassword("old", "new");
//
//		wallet.addresses().unused().list();
//		wallet.addresses().used().list();
//		wallet.addresses().all().list();
//
//		wallet.stakePool().quit();
//		wallet.stakePool().join(stakePool);
//
//		Transaction transaction = wallet.transfer().to("address", 50000L).andTo("address 2", 25000L).withMetadata("cardano", 1, "object[]").authorize("password");
//
//		wallet.transactions().from("date").to("date").ascending().list();
//		wallet.transactions().from("date").to("date").descending().minWithdrawal(100L).list();
//
//		Transaction transaction = wallet.transactions().get("id hex");
//		transaction.forget();

		while (true) {
			Thread.sleep(10000);

			printResult(() -> remoteServer.network().clock());
			printResult(() -> remoteServer.network().information());
			printResult(() -> remoteServer.network().parameters());
			printResult(() -> {
				List<StakePool> pools = remoteServer.stakePools().list();
				StringBuilder tmp = new StringBuilder();
				pools.forEach(p -> tmp.append(p.ticker()).append(" - ").append(p.formattedMarginPercentage()).append('\n'));
				return tmp.toString();
			});
		}
	}

	private static void printResult(Supplier<Object> supplier) {
		try {
			System.out.println(supplier.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
