package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.transactions.*;
import com.univocity.cardano.wallet.builders.wallets.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class ShelleyTransactions implements Transactions<ShelleyTransaction> {

	private final ShelleyWallet wallet;
	private final WalletApi api;

	public ShelleyTransactions(ShelleyWallet wallet, WalletApi api) {
		this.wallet = wallet;
		this.api = api;

	}

	@Override
	public ShelleyTransaction get(String transactionId) {
		return new ShelleyTransaction(wallet, api.sync().getTransaction(wallet.id(), transactionId), api);
	}

	@Override
	public Future<List<ShelleyTransaction>> listAsync(LocalDateTime from, LocalDateTime to) {
		String start = Utils.toFormattedISO8601Date(from);
		String end = Utils.toFormattedISO8601Date(to);
		String order = null;
		BigInteger minWithdrawal = null;

		return new AsyncCallbackHandler<List<ListTransactionsResponseItem>, List<ShelleyTransaction>>(
				Collections.emptyList(),
				callback -> api.async().listTransactions(wallet.id(), start, end, order, minWithdrawal, callback),
				result -> Utils.convertList(result, (r) -> new ShelleyTransaction(wallet, r, api))
		).getAsync();
	}
}
