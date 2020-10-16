package com.univocity.cardano.wallet.embedded;

import com.univocity.cardano.wallet.embedded.services.*;

import java.io.*;

public class EmbeddedWalletProcess {

	private final CardanoNodeManager cardanoNode;
	private final CardanoWalletManager cardanoWallet;

	private EmbeddedWalletProcess(File cardanoToolsDir, File blockchainDir){
		String toolDirsPath = cardanoToolsDir.getAbsolutePath();
		String blockchainDirPath = blockchainDir.getAbsolutePath();


		cardanoNode = new CardanoNodeManager(toolDirsPath);
		cardanoWallet = new CardanoWalletManager(toolDirsPath);
	}

//	.setStartupCommand(
//				"run" +
//						" --topology {config.dir}/mainnet-topology.json" +
//						" --database-path {blockchain.dir}" +
//						" --socket-path {blockchain.dir}/node.socket" +
//						" --host-addr 0.0.0.0" +
//						" --port {cardano.node.port}" +
//						" --config {config.dir}/mainnet-config.json"
//	));
//	cardanoWalletControlPanel = intializeProcess(new CardanoWalletManager(toolsDir).setStartupCommand(
//				"serve" +
//						" --mainnet" +
//						" --database {blockchain.dir}" +
//						" --node-socket {blockchain.dir}/node.socket" +
//						" --port {cardano.wallet.port}")
//		);

}
