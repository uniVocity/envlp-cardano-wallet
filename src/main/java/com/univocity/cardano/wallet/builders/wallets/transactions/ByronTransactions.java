package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.byrontransactions.*;
import com.univocity.cardano.wallet.builders.wallets.*;
import com.univocity.cardano.wallet.common.*;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class ByronTransactions implements Transactions<ByronTransaction> {

	private final ByronWallet wallet;
	private final WalletApi api;

	public ByronTransactions(ByronWallet wallet, WalletApi api) {
		this.wallet = wallet;
		this.api = api;

	}

	@Override
	public ByronTransaction get(String transactionId) {
		return new ByronTransaction(wallet, api.sync().getTransaction(wallet.id(), transactionId), api);
	}

	@Override
	public Future<List<ByronTransaction>> listAsync(LocalDateTime from, LocalDateTime to) {
		String start = Utils.toFormattedISO8601Date(from);
		String end = Utils.toFormattedISO8601Date(to);
		String order = null;

		return new AsyncCallbackHandler<List<ListByronTransactionsResponseItem>, List<ByronTransaction>>(
				Collections.emptyList(),
				callback -> api.async().listByronTransactions(wallet.id(), start, end, order, callback),
				result -> Utils.convertList(result, (r) -> new ByronTransaction(wallet, r, api))
		).getAsync();
	}
}
