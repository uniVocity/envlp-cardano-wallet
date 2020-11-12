package com.univocity.cardano.wallet.builders.wallets.transactions;

import java.util.*;

public interface Authorization<T extends Transaction> {

	T authorize(String password);

	Fees<T> estimateFees();

	Authorization<T> withMetadata(Object[] metadata);

	Authorization<T> withMetadata(Map<Long, Object> metadata);

	Payee<T> and();
}
