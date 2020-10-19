package com.univocity.cardano.wallet;

import com.univocity.cardano.wallet.builders.*;

import java.io.*;
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

		RemoteWalletServer remoteServer = WalletServer.remote("http://localhost").connectToPort(4444);

		while (true) {
			Thread.sleep(10000);
			try {
				remoteServer.api().async().getNetworkInformation(System.out::println);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				System.out.println(remoteServer.api().sync().getNetworkParameters());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				System.out.println(remoteServer.api().sync().listByronWallets());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				System.out.println(remoteServer.api().sync().listWallets());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
