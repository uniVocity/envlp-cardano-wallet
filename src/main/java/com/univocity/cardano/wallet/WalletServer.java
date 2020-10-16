package com.univocity.cardano.wallet;

import com.univocity.cardano.wallet.builders.*;

import java.io.*;

public class WalletServer {

	private final WalletServerConfig config;

	private WalletServer(WalletServerConfig config) {
		this.config = config;
	}

	public static EmbeddedWallet embedded() {
		return new WalletServerConfig(null);
	}

	public static RemoteWallet remote(String host) {
		return new WalletServerConfig(host);
	}

	private static class WalletServerConfig implements EmbeddedWallet, RemoteWallet, BlockchainConfig, NodeConfig, TopologyConfig, PortConfig, WalletPort, Node, Wallet {

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
		public WalletServer startAtPort(int walletPort) {
			this.walletPort = walletPort;
			return new WalletServer(this);
		}

		@Override
		public WalletServer connectToPort(int walletPort) {
			this.walletPort = walletPort;
			return new WalletServer(this);
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
		WalletServer server = WalletServer.embedded()
				.toolsIn("/path/to/cardano/tools")
				.node().configuration("/path/to/node/config").topology("/path/to/topology").blockchain("/path/to/blockchain").port(3333)
				.wallet().startAtPort(4444);

		server = WalletServer.remote("http://blah").connectToPort(1111);

	}


}
