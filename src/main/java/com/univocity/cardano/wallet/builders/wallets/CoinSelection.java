package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.util.*;
import java.util.stream.*;

public class CoinSelection extends Wrapper<AbstractCoinSelectionResponse> {
	public CoinSelection(AbstractCoinSelectionResponse original, WalletApi api) {
		super(original, api);
	}

	static ArrayList<Payment> toPaymentList(Map<String, BigDecimal> payments) {
		ArrayList<Payment> out = new ArrayList<>();
		payments.forEach((address, adaAmount) -> {
			Payment payment = new Payment();
			payment.setAddress(address);
			Amount amount = new Amount();
			amount.setUnit("lovelace");
			amount.setQuantity(adaToLovelace(adaAmount));
			payment.setAmount(amount);
			out.add(payment);
		});
		return out;
	}

	public List<BigInteger> depositsInLovelace() {
		return original.getDeposits().stream().map(AbstractAmount::getQuantity).collect(Collectors.toList());
	}

	public List<BigDecimal> getDepositsInAda() {
		return original.getDeposits().stream().map(e -> lovelaceToAda(e.getQuantity())).collect(Collectors.toList());
	}
}
