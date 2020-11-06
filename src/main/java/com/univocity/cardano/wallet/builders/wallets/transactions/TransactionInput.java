package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;

public class TransactionInput extends Wrapper<Input> {

	public TransactionInput(Input original) {
		super(original, null);
	}

	public String address() {
		return original.getAddress();
	}

	public BigDecimal amount() {
		return lovelaceToAda(original.getAmount());
	}

	public String id() {
		return original.getId();
	}

	public BigInteger index() {
		return original.getIndex();
	}

}
