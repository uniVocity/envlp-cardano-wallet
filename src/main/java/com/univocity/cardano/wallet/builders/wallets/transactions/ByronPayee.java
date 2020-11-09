package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.byrontransactions.*;
import com.univocity.cardano.wallet.api.generated.common.Payment;
import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.builders.wallets.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.util.*;

public class ByronPayee implements Payee<ByronTransaction> {

	private final ByronWallet wallet;
	private final WalletApi api;
	private final ArrayList<Payment> payments = new ArrayList<>();
	private final Map<Long, Object> metadata = new LinkedHashMap<>();

	private class Builder implements Authorization<ByronTransaction> {
		@Override
		public ByronTransaction authorize(String password) {
			PostByronTransactionRequest request = new PostByronTransactionRequest();
			request.setPayments(payments);
			if (!metadata.isEmpty()) {
				request.setMetadata(Utils.toMetadata(metadata));
			}
			request.setPassphrase(password);
			return new ByronTransaction(wallet, api.sync().postByronTransaction(wallet.id(), request), api);
		}

		@Override
		public Fees<ByronTransaction> estimateFees() {
			PostByronTransactionFeeRequest request = new PostByronTransactionFeeRequest();
			request.setPayments(payments);
			return new Fees<>(api.sync().postByronTransactionFee(wallet.id(), request), api, this::authorize);
		}

		@Override
		public Authorization<ByronTransaction> withMetadata(Object[] metadata) {
			for (int i = 0; i < metadata.length; i++) {
				ByronPayee.this.metadata.put((long)i, metadata[i]);
			}
			return this;
		}

		@Override
		public Authorization<ByronTransaction> withMetadata(Map<Long, Object> metadata) {
			Utils.notNull(metadata, "Transaction metadata");
			metadata.forEach(ByronPayee.this.metadata::put);
			return this;
		}
	}

	private final Builder builder;

	public ByronPayee(ByronWallet wallet, WalletApi api) {
		this.wallet = wallet;
		this.api = api;
		this.builder = new Builder();

	}

	@Override
	public Authorization<ByronTransaction> to(String address, BigInteger amountInLovelace) {
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
