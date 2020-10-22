package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.api.*;
import org.apache.commons.lang3.*;

public class CreateWallet implements WalletOptions {

	private enum WalletFormat {
		shelley, byron, icarus, trezor, ledger;
	}

	private final WalletApi api;
	private final String walletName;

	private String seedPhrase;
	private String walletPassword;
	private String secondFactorPhrase;
	private String publicKey;
	private String privateKey;
	private WalletFormat walletFormat;
	private int addressPoolGap = 20;


	CreateWallet(String walletName, WalletApi api) {
		if (StringUtils.isBlank(walletName)) {
			throw new IllegalArgumentException("Wallet name cannot be blank");
		}
		this.walletName = walletName;
		this.api = api;
	}

	@Override
	public ShelleyWalletRestoration shelley() {
		walletFormat = WalletFormat.shelley;
		return new ShelleyWalletBuilder();
	}

	@Override
	public ByronWalletRestoration byron() {
		walletFormat = WalletFormat.byron;
		return new WalletBuilder();
	}

	@Override
	public WalletRestoration icarus() {
		walletFormat = WalletFormat.icarus;
		return new WalletBuilder();
	}

	@Override
	public WalletRestoration ledger() {
		walletFormat = WalletFormat.ledger;
		return new WalletBuilder();
	}

	@Override
	public WalletRestoration trezor() {
		walletFormat = WalletFormat.trezor;
		return new WalletBuilder();
	}

	private class WalletBuilder implements WalletPassword, ByronWalletRestoration, WalletRestoration {

		@Override
		public WalletPassword fromPrivateKey(String privateKey) {
			return setPrivateKey(this, privateKey);
		}

		@Override
		public WalletPassword fromSeed(String seedPhrase) {
			return setSeed(this, seedPhrase);
		}

		@Override
		public Wallet fromPublicKey(String publicKey) {
			return setPublicKey(publicKey);
		}

		@Override
		public Wallet password(String walletPassword) {
			return setPassword(walletPassword);
		}
	}

	private class ShelleyWalletBuilder extends WalletBuilder implements ShelleyWalletRestoration, ShelleyWalletPassword {

		@Override
		public WalletPassword secondFactor(String secondFactorPhrase) {
			CreateWallet.this.secondFactorPhrase = secondFactorPhrase;
			return this;
		}

		@Override
		public ShelleyWalletPassword fromSeed(String seedPhrase) {
			return setSeed(this, seedPhrase);
		}

		@Override
		public Wallet fromPublicKey(String publicKey) {
			return setPublicKey(publicKey);
		}

		@Override
		public Wallet password(String walletPassword) {
			return setPassword(walletPassword);
		}
	}

	@Override
	public WalletType addressPoolGap(int gap) {
		addressPoolGap = gap;
		return this;
	}

	private <T> T setSeed(T parent, String seedPhrase) {
		this.seedPhrase = seedPhrase;
		return parent;
	}

	private Wallet setPassword(String walletPassword) {
		this.walletPassword = walletPassword;
		return createWallet();
	}

	private Wallet setPublicKey(String publicKey) {
		this.publicKey = publicKey;
		return createWallet();
	}

	private <T> T setPrivateKey(T parent, String privateKey) {
		this.privateKey = privateKey;
		return parent;
	}

	private Wallet createWallet() {
		return null;
	}

}
