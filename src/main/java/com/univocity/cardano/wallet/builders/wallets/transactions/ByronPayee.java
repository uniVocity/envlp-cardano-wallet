package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.byrontransactions.*;
import com.univocity.cardano.wallet.api.generated.common.Payment;
import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.builders.wallets.*;

import java.math.*;
import java.util.*;

public class ByronPayee implements Payee<ByronAuthorization> {

	private final ByronWallet wallet;
	private final WalletApi api;
	private final ArrayList<Payment> payments = new ArrayList<>();

	private class Builder implements ByronAuthorization {
		@Override
		public ByronTransaction authorize(String password) {
			PostByronTransactionRequest request = new PostByronTransactionRequest();
			request.setPayments(payments);
			request.setPassphrase(password);
			return new ByronTransaction(wallet, api.sync().postByronTransaction(wallet.id(), request), api);
		}

		@Override
		public Fees estimateFees() {
			PostByronTransactionFeeRequest request = new PostByronTransactionFeeRequest();
			request.setPayments(payments);
			return new Fees(api.sync().postByronTransactionFee(wallet.id(), request), api);
		}
	}

	private final Builder builder;

	public ByronPayee(ByronWallet wallet, WalletApi api) {
		this.wallet = wallet;
		this.api = api;
		this.builder = new Builder();

	}

	@Override
	public ByronAuthorization to(String address, BigInteger amountInLovelace) {
		Payment payment = new Payment();
		payment.setAddress(address);

		Amount amount = new Amount();
		amount.setQuantity(amountInLovelace);
		amount.setUnit("lovelace");
		payment.setAmount(amount);

		payments.add(payment);
		return builder;
	}
}
