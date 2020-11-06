package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;

public class Payment extends Wrapper<AbstractPayment> {

	public Payment(AbstractPayment original) {
		super(original, null);
	}

	public String address() {
		return original.getAddress();
	}

	public BigDecimal amount() {
		return lovelaceToAda(original.getAmount());
	}
}
