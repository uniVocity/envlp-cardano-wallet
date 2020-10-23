package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.byronwallets.*;
import com.univocity.cardano.wallet.api.generated.wallets.*;
import okhttp3.*;
import org.apache.commons.lang3.*;

import java.util.*;

public class CreateWallet implements WalletType {

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
	private ArrayList<String> mnemonicSentence;
	private ArrayList<String> mnemonicSecondFactor;


	CreateWallet(String walletName, WalletApi api) {
		if (StringUtils.isBlank(walletName)) {
			throw new IllegalArgumentException("Wallet name cannot be blank");
		}
		this.walletName = walletName;
		this.api = api;
	}

	@Override
	public ShelleyWalletRestorationOptions shelley() {
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

	private class ShelleyWalletBuilder extends WalletBuilder implements ShelleyWalletRestorationOptions, ShelleyWalletPassword {

		@Override
		public ShelleyWalletRestoration addressPoolGap(int gap) {
			addressPoolGap = gap;
			return this;
		}

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
		return walletFormat == WalletFormat.shelley ? createShelleyWallet() : createByronWallet();
	}

	private Wallet createByronWallet(){
		PostByronWalletRequest byronRequest = new PostByronWalletRequest();
		byronRequest.setStyle(walletFormat == WalletFormat.byron ? "random" : walletFormat.name());
		byronRequest.setName(walletName);

		if (mnemonicSentence != null) {
			byronRequest.setMnemonicSentence(mnemonicSentence);
			byronRequest.setPassphrase(walletPassword);
		} else if (publicKey == null) {
			//?
		}
		return null;// new ByronWallet(api.sync().postByronWallet(byronRequest));
	}

	private Wallet createShelleyWallet(){
		PostWalletRequest shelleyRequest = new PostWalletRequest();
		shelleyRequest.setName(walletName);
		shelleyRequest.setMnemonicSentence(mnemonicSentence);
		shelleyRequest.setPassphrase(walletPassword);
		shelleyRequest.setMnemonicSecondFactor(mnemonicSecondFactor);
		return null;//return new ShelleyWallet(api.sync().postWallet(shelleyRequest));
	}
}
