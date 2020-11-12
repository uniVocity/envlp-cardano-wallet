package com.univocity.cardano.wallet.builders.server;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.builders.network.*;
import com.univocity.cardano.wallet.builders.stakepools.*;
import com.univocity.cardano.wallet.builders.utils.*;
import com.univocity.cardano.wallet.builders.wallets.*;
import org.apache.commons.codec.*;
import org.apache.commons.codec.binary.*;
import org.apache.commons.io.*;

import java.io.*;

public class RemoteWalletServer {

	private final WalletApi api;
	private final StakePools stakePools;
	private final Network network;
	private final Wallets wallets;

	public RemoteWalletServer(WalletServer.WalletServerConfig config) {
		ApiConfiguration configuration = new ApiConfiguration();
		configuration.setWalletServiceHost(config.walletHost);
		configuration.setWalletServicePort(config.walletPort);

		api = new WalletApi(configuration);

		stakePools = new StakePools(api);
		network = new Network(api);
		wallets = new Wallets(api);
	}

	public WalletApi api() {
		return api;
	}

	public StakePools stakePools() {
		return stakePools;
	}

	public Network network() {
		return network;
	}

	public Wallets wallets() {
		return wallets;
	}

	public AddressDetails inspectAddress(String address) {
		return new AddressDetails(api.sync().inspectAddress(address), api);
	}

	public String submitTransaction(File signedTransactionFile) {
		byte[] bytes;
		try {
			bytes = FileUtils.readFileToByteArray(signedTransactionFile);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to read signed transaction from file: " + signedTransactionFile, e);
		}
		return submitTransaction(bytes);
	}

	public String submitTransaction(String signedTransactionBytes) {
		byte[] bytes;
		try {
			bytes = Hex.decodeHex(signedTransactionBytes);
		} catch (DecoderException e) {
			throw new IllegalStateException("Unable to decode signed transaction from: " + signedTransactionBytes, e);
		}
		return submitTransaction(bytes);
	}

	public String submitTransaction(byte[] signedTransactionBytes) {
		return api.sync().postExternalTransaction(signedTransactionBytes).getId();
	}

}
