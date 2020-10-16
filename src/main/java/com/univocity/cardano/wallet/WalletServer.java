package com.univocity.cardano.wallet;

import com.univocity.cardano.wallet.builders.*;

import java.io.*;

public class WalletServer {

	public static EmbeddedWallet embedded() {
		return new WalletServerConfig("http://localhost");
	}

	public static RemoteWallet remote(String host) {
		return new WalletServerConfig(host);
	}

	static class WalletServerConfig implements EmbeddedWallet, RemoteWallet, BlockchainConfig, NodeConfig, TopologyConfig, PortConfig, WalletPort, Node, Wallet {

		final String walletHost;
		int walletPort;
		int nodePort;
		File cardanoToolsDir;
		File blockchainDir;
		File nodeConfigurationFile;
		File nodeTopologyFile;


		public WalletServerConfig(String host) {
			this.walletHost = host;
		}

		@Override
		public EmbeddedWalletServer startAtPort(int walletPort) {
			this.walletPort = walletPort;
			return new EmbeddedWalletServer(this);
		}

		@Override
		public RemoteWalletServer connectToPort(int walletPort) {
			this.walletPort = walletPort;
			return new RemoteWalletServer(this);
		}

		@Override
		public PortConfig blockchain(String pathToBlockchain) {
			blockchainDir = toValidatedFile(pathToBlockchain, true);
			return this;
		}

		@Override
		public Node toolsIn(String pathToCardanoTools) {
			cardanoToolsDir = toValidatedFile(pathToCardanoTools, true);
			return this;
		}

		@Override
		public NodeConfig node() {
			return this;
		}

		@Override
		public TopologyConfig configuration(String pathToNodeConfiguration) {
			nodeConfigurationFile = toValidatedFile(pathToNodeConfiguration, false);
			return this;
		}

		@Override
		public Wallet port(int port) {
			nodePort = port;
			return this;
		}

		@Override
		public BlockchainConfig topology(String pathToTopologyFile) {
			nodeTopologyFile = toValidatedFile(pathToTopologyFile, false);
			return this;
		}

		@Override
		public WalletPort wallet() {
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


	public static void main(String... args) {
		String home = System.getProperty("user.home");
		String configRoot = home + "/dev/repository/free-commerce/src/main/resources/";

		EmbeddedWalletServer server = WalletServer.embedded()
				.toolsIn("/home/jbax/dev/repository/free-commerce/src/main/resources/cli/lin")
				.node()
					.configuration(configRoot + "mainnet-config.json")
					.topology(configRoot + "mainnet-topology.json")
					.blockchain(home + "/Downloads/blockchain")
					.port(3333)
				.wallet()
					.startAtPort(4444);

		//RemoteWalletServer remoteServer = WalletServer.remote("http://blah").connectToPort(1111);

	}


}
