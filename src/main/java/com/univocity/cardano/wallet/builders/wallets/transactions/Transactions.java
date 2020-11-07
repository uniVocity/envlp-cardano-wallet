package com.univocity.cardano.wallet.builders.wallets.transactions;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;

import static com.univocity.cardano.wallet.common.AsyncCallbackHandler.*;

public interface Transactions<T extends Transaction> {

	T get(String transactionId);

	default List<T> list() {
		return list(null, null);
	}

	default List<T> list(LocalDateTime from, LocalDateTime to) {
		return sync(listAsync(from, to), Collections.emptyList());
	}


	default Future<List<T>> listAsync() {
		return listAsync(null, null);
	}

	Future<List<T>> listAsync(LocalDateTime from, LocalDateTime to);

}
