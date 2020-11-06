package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;

public class StakeWithdrawal extends Wrapper<Withdrawal> {

	public StakeWithdrawal(Withdrawal original) {
		super(original, null);
	}

	public String stakeAddress() {
		return original.getStakeAddress();
	}

	public BigDecimal amount() {
		return lovelaceToAda(original.getAmount());
	}
}
