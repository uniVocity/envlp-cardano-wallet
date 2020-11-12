package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.byrontransactions.*;
import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.api.generated.transactions.*;
import com.univocity.cardano.wallet.builders.wallets.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.util.*;

public class ShelleyPayee implements Payee<ShelleyTransaction> {

	private final ShelleyWallet wallet;
	private final WalletApi api;
	private final ArrayList<PaymentsPayment> payments = new ArrayList<>();
	private final Map<Long, Object> metadata = new LinkedHashMap<>();

	private class Builder implements Authorization<ShelleyTransaction> {
		@Override
		public ShelleyTransaction authorize(String password) {
			PostTransactionPaymentRequest request = new PostTransactionPaymentRequest();
			request.setPayments(payments);
			if (!metadata.isEmpty()) {
				request.setMetadata(Utils.toMetadata(metadata));
			}
			request.setPassphrase(password);
			return new ShelleyTransaction(wallet, api.sync().postTransaction(wallet.id(), request), api);
		}

		@Override
		public Authorization<ShelleyTransaction> withMetadata(Object[] metadata) {
			for (int i = 0; i < metadata.length; i++) {
				ShelleyPayee.this.metadata.put((long)i, metadata[i]);
			}
			return this;
		}

		@Override
		public Authorization<ShelleyTransaction> withMetadata(Map<Long, Object> metadata) {
			Utils.notNull(metadata, "Transaction metadata");
			metadata.forEach(ShelleyPayee.this.metadata::put);
			return this;
		}

		@Override
		public Fees<ShelleyTransaction> estimateFees() {
			PostTransactionFeePaymentRequest request = new PostTransactionFeePaymentRequest();
			request.setPayments(payments);
			if (!metadata.isEmpty()) {
				request.setMetadata(Utils.toMetadata(metadata));
			}
			return new Fees<>(api.sync().postTransactionFee(wallet.id(), request), api, this::authorize);
		}

		@Override
		public Payee<ShelleyTransaction> and() {
			return ShelleyPayee.this;
		}
	}

	private final Builder builder;

	public ShelleyPayee(ShelleyWallet wallet, WalletApi api) {
		this.wallet = wallet;
		this.api = api;
		this.builder = new ShelleyPayee.Builder();

	}

	@Override
	public Authorization<ShelleyTransaction> to(String address, BigInteger amountInLovelace) {
		PaymentsPayment payment = new PaymentsPayment();
		payment.setAddress(address);

		Amount amount = new Amount();
		amount.setQuantity(amountInLovelace);
		amount.setUnit("lovelace");
		payment.setAmount(amount);

		payments.add(payment);
		return builder;
	}
}
