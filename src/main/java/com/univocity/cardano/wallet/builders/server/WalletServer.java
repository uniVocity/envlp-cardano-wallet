package com.univocity.cardano.wallet.builders.server;

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

		class NodeServerBuilder implements NodeConfig, BlockchainConfig, TopologyConfig, PortConfig<WalletBuilder>, ProcessOutput<WalletBuilder>, WalletBuilder {
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
			public ProcessOutput<WalletBuilder> port(int port) {
				nodePort = port;
				return this;
			}

			@Override
			public PortConfig storeBlockchainIn(String pathToBlockchain) {
				blockchainDir = toValidatedFile(pathToBlockchain, true);
				return this;
			}

			@Override
			public WalletBuilder consumeOutput(Consumer<String> outputConsumer) {
				nodeOutputConsumer = outputConsumer;
				return this;
			}

			@Override
			public WalletBuilder ignoreOutput() {
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
		public Node binariesIn(String pathToCardanoTools) {
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
}
