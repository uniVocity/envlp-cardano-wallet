package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.byronwallets.*;
import com.univocity.cardano.wallet.api.generated.wallets.*;
import com.univocity.cardano.wallet.builders.*;
import com.univocity.cardano.wallet.common.*;

import java.util.*;

public class Wallets extends ApiWrapper {

	private final AsyncCallbackHandler<List<ListWalletsResponseItem>, List<ShelleyWallet>> shelleyWalletCallbackHandler;
	private final AsyncCallbackHandler<List<ListByronWalletsResponseItem>, List<ByronWallet>> byronWalletCallbackHandler;

	public Wallets(WalletApi api) {
		super(api);

		shelleyWalletCallbackHandler = new AsyncCallbackHandler<>(
				Collections.emptyList(),
				callback -> api.async().listWallets(callback),
				response -> Utils.convertList(response, ShelleyWallet::new)
		);

		byronWalletCallbackHandler = new AsyncCallbackHandler<>(
				Collections.emptyList(),
				callback -> api.async().listByronWallets(callback),
				response -> Utils.convertList(response, ByronWallet::new)
		);
	}

	public Wallet getById(String walletId){
//		ShelleyWallet shelleyWallet = new ShelleyWallet(api.sync().getWallet(walletId));

		return null;
	}

	public List<Wallet> list() {
		List<ShelleyWallet> shelleyWallets = shelleyWalletCallbackHandler.get();
		List<ByronWallet> byronWallets = byronWalletCallbackHandler.get();

		List<Wallet> out = new ArrayList<>(shelleyWallets.size() + byronWallets.size());
		out.addAll(shelleyWallets);
		out.addAll(byronWallets);

		return out;
	}

	public CreateWallet create(String walletName){
		return new CreateWallet(walletName, api);
	}
}
