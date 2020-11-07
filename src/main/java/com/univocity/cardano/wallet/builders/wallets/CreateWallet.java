package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.byronwallets.*;
import com.univocity.cardano.wallet.api.generated.wallets.*;
import com.univocity.cardano.wallet.api.service.exception.*;
import com.univocity.cardano.wallet.common.*;

import java.util.*;

public class CreateWallet implements WalletType {

	private enum WalletFormat {
		SHELLEY, BYRON, ICARUS, TREZOR, LEDGER;
	}

	private final WalletApi api;
	private final String walletName;

	private String walletPassword;
	private String publicKey;
	private String privateKey;
	private WalletFormat walletFormat;
	private int addressPoolGap = 20;
	private ArrayList<String> mnemonicSentence;
	private ArrayList<String> mnemonicSecondFactor;
	private final boolean onDuplicateGet;


	CreateWallet(String walletName, WalletApi api, boolean onDuplicateGet) {
		Utils.notBlank(walletName, "Wallet name");
		Utils.notNull(api, "Wallet API");
		this.walletName = walletName;
		this.api = api;
		this.onDuplicateGet = onDuplicateGet;
	}

	@Override
	public ShelleyWalletRestorationOptions shelley() {
		walletFormat = WalletFormat.SHELLEY;
		return new ShelleyWalletBuilder();
	}

	@Override
	public ByronWalletRestoration<ByronWallet> byron() {
		walletFormat = WalletFormat.BYRON;
		return new WalletBuilder<>();
	}

	@Override
	public WalletRestoration<ByronWallet> icarus() {
		walletFormat = WalletFormat.ICARUS;
		return new WalletBuilder<>();
	}

	@Override
	public WalletRestoration<ByronWallet> ledger() {
		walletFormat = WalletFormat.LEDGER;
		return new WalletBuilder<>();
	}

	@Override
	public WalletRestoration<ByronWallet> trezor() {
		walletFormat = WalletFormat.TREZOR;
		return new WalletBuilder<>();
	}

	private class WalletBuilder<T extends Wallet> implements WalletPassword<T>, ByronWalletRestoration<T>, WalletRestoration<T> {

		@Override
		public WalletPassword<T> fromPrivateKey(String privateKey) {
			return setPrivateKey(this, privateKey);
		}

		@Override
		public WalletPassword<T> fromSeed(String seedPhrase) {
			return setSeed(this, seedPhrase);
		}

		@Override
		public T fromPublicKey(String publicKey) {
			return (T) setPublicKey(publicKey);
		}

		@Override
		public T password(String walletPassword) {
			return (T) setPassword(walletPassword);
		}
	}

	private class ShelleyWalletBuilder extends WalletBuilder<ShelleyWallet> implements ShelleyWalletRestorationOptions, ShelleyWalletPassword {

		@Override
		public ShelleyWalletRestoration addressPoolGap(int gap) {
			addressPoolGap = gap;
			return this;
		}

		@Override
		public WalletPassword<ShelleyWallet> secondFactor(String secondFactorPhrase) {
			Utils.notBlank(secondFactorPhrase, "Second factor phrase");
			CreateWallet.this.mnemonicSecondFactor = new ArrayList<>(Arrays.asList(secondFactorPhrase.split(" ")));
			return this;
		}

		@Override
		public ShelleyWalletPassword fromSeed(String seedPhrase) {
			return setSeed(this, seedPhrase);
		}

		@Override
		public ShelleyWallet fromPublicKey(String publicKey) {
			return (ShelleyWallet) setPublicKey(publicKey);
		}

		@Override
		public ShelleyWallet password(String walletPassword) {
			return (ShelleyWallet) setPassword(walletPassword);
		}
	}

	private <T> T setSeed(T parent, String seedPhrase) {
		this.mnemonicSentence = Seed.toMnemonicList(seedPhrase);
		return parent;
	}

	private Wallet setPassword(String walletPassword) {
		Utils.notBlank(walletPassword, "Wallet password");
		this.walletPassword = walletPassword;
		return createOrGetWallet();
	}

	private Wallet setPublicKey(String publicKey) {
		Utils.notBlank(publicKey, "Public key");
		this.publicKey = publicKey;
		return createOrGetWallet();
	}

	private <T> T setPrivateKey(T parent, String privateKey) {
		Utils.notBlank(privateKey, "Public key");
		this.privateKey = privateKey;
		return parent;
	}

	private Wallet createOrGetWallet() {
		try {
			return createWallet();
		} catch (DuplicateWalletException e) {
			if (onDuplicateGet) {
				if (walletFormat == WalletFormat.SHELLEY) {
					return new ShelleyWallet(api.sync().getWallet(e.getWalletId()), api);
				} else {
					return new ByronWallet(api.sync().getByronWallet(e.getWalletId()), api);
				}
			} else {
				throw e;
			}
		}
	}

	private Wallet createWallet() {
		if (mnemonicSentence != null) {
			switch (walletFormat) {
				case SHELLEY: {
					PostWalletShelleyRequest req = new PostWalletShelleyRequest();
					req.setName(walletName);
					req.setMnemonicSentence(mnemonicSentence);
					req.setPassphrase(walletPassword);
					req.setMnemonicSecondFactor(mnemonicSecondFactor);
					req.setAddressPoolGap(addressPoolGap);
					return new ShelleyWallet(api.sync().postWallet(req), api);
				}
				case BYRON: {
					PostByronWalletRandomRequest req = new PostByronWalletRandomRequest();
					req.setStyle("random");
					req.setName(walletName);
					req.setMnemonicSentence(mnemonicSentence);
					req.setPassphrase(walletPassword);
					return new ByronWallet(api.sync().postByronWallet(req), api);
				}
				case TREZOR: {
					PostByronWalletTrezorRequest req = new PostByronWalletTrezorRequest();
					req.setStyle("trezor");
					req.setName(walletName);
					req.setMnemonicSentence(mnemonicSentence);
					req.setPassphrase(walletPassword);
					return new ByronWallet(api.sync().postByronWallet(req), api);
				}
				case LEDGER: {
					PostByronWalletLedgerRequest req = new PostByronWalletLedgerRequest();
					req.setStyle("ledger");
					req.setName(walletName);
					req.setMnemonicSentence(mnemonicSentence);
					req.setPassphrase(walletPassword);
					return new ByronWallet(api.sync().postByronWallet(req), api);
				}
				case ICARUS: {
					PostByronWalletIcarusRequest req = new PostByronWalletIcarusRequest();
					req.setStyle("icarus");
					req.setName(walletName);
					req.setMnemonicSentence(mnemonicSentence);
					req.setPassphrase(walletPassword);
					return new ByronWallet(api.sync().postByronWallet(req), api);
				}
			}
		} else if (publicKey != null) {
			switch (walletFormat) {
				case SHELLEY: {
					PostWalletShelleyFromXpubRequest req = new PostWalletShelleyFromXpubRequest();
					req.setName(walletName);
					req.setAccountPublicKey(publicKey);
					req.setAddressPoolGap(addressPoolGap);
					return new ShelleyWallet(api.sync().postWallet(req), api);
				}
				case ICARUS:
				case LEDGER:
				case TREZOR: { //TODO: this doesn't look right (no style parameter plus address pool gap).
					PostByronWalletIcarusTrezorLedgerFromXpubRequest req = new PostByronWalletIcarusTrezorLedgerFromXpubRequest();
					req.setName(walletName);
					req.setAccountPublicKey(publicKey);
					req.setAddressPoolGap(addressPoolGap);
					return new ByronWallet(api.sync().postByronWallet(req), api);
				}
			}
		} else if (privateKey != null) {
			switch (walletFormat) {
				case BYRON: {
					PostByronWalletRandomFromXprvRequest req = new PostByronWalletRandomFromXprvRequest();
					req.setStyle("random");
					req.setName(walletName);
					req.setEncryptedRootPrivateKey(privateKey);
					req.setPassphraseHash(walletPassword);
					return new ByronWallet(api.sync().postByronWallet(req), api);
				}
			}
		}
		throw new IllegalStateException("Incomplete shelley wallet details");
	}
}
