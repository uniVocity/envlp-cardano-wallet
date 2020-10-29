package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.byronwallets.*;
import com.univocity.cardano.wallet.api.generated.wallets.*;
import com.univocity.cardano.wallet.builders.*;
import com.univocity.cardano.wallet.common.*;

import java.util.*;
import java.util.concurrent.*;

import static com.univocity.cardano.wallet.common.AsyncCallbackHandler.*;

public class Wallets extends ApiWrapper {

	private final AsyncCallbackHandler<List<ListWalletsResponseItem>, List<ShelleyWallet>> shelleyWalletCallbackHandler;
	private final AsyncCallbackHandler<List<ListByronWalletsResponseItem>, List<ByronWallet>> byronWalletCallbackHandler;

	public Wallets(WalletApi api) {
		super(api);

		shelleyWalletCallbackHandler = new AsyncCallbackHandler<>(
				Collections.emptyList(),
				callback -> api.async().listWallets(callback),
				response -> Utils.convertList(response, (r) -> new ShelleyWallet(r, api))
		);

		byronWalletCallbackHandler = new AsyncCallbackHandler<>(
				Collections.emptyList(),
				callback -> api.async().listByronWallets(callback),
				response -> Utils.convertList(response, (r) -> new ByronWallet(r, api))
		);
	}

	public Wallet getById(String walletId) {
		try {
			return new ShelleyWallet(api.sync().getWallet(walletId), api);
		} catch (Exception e) {
			return new ByronWallet(api.sync().getByronWallet(walletId), api);
		}
	}

	public List<Wallet> list() {
		Future<List<ShelleyWallet>> shelleyList = shelleyWalletCallbackHandler.getAsync();
		Future<List<ByronWallet>> byronList = byronWalletCallbackHandler.getAsync();

		List<ShelleyWallet> shelleyWallets = sync(shelleyList, Collections.emptyList());
		List<ByronWallet> byronWallets = sync(byronList, Collections.emptyList());

		List<Wallet> out = new ArrayList<>(shelleyWallets.size() + byronWallets.size());
		out.addAll(shelleyWallets);
		out.addAll(byronWallets);

		return out;
	}

	public CreateWallet create(String walletName) {
		return new CreateWallet(walletName, api, false);
	}

	public CreateWallet createOrGet(String walletName) {
		return new CreateWallet(walletName, api, true);
	}
}
