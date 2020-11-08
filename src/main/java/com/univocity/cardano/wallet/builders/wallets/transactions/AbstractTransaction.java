package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.time.*;
import java.util.*;

abstract class AbstractTransaction extends WrapperWithId<com.univocity.cardano.wallet.api.generated.common.AbstractTransaction> implements Transaction {

	private Map<Long, Object> metadata = null;

	public AbstractTransaction(com.univocity.cardano.wallet.api.generated.common.AbstractTransaction original, WalletApi api) {
		super(original, api);
	}

	@Override
	public String id() {
		return original.getId();
	}

	@Override
	public BigDecimal amount() {
		return lovelaceToAda(original.getAmount());
	}

	@Override
	public LocalDateTime insertedAt() {
		return toDateTime(safeGet(() -> original.getInsertedAt().getTime()));
	}

	@Override
	public LocalDateTime pendingSinceTime() {
		return toDateTime(safeGet(() -> original.getPendingSince().getTime()));
	}

	@Override
	public BigInteger pendingSinceSlot() {
		return safeGet(() -> original.getPendingSince().getBlock().getSlotNumber());
	}

	@Override
	public BigInteger pendingSinceEpoch() {
		return safeGet(() -> original.getPendingSince().getBlock().getEpochNumber());
	}

	@Override
	public BigInteger pendingSinceSlotAbsolute() {
		return safeGet(() -> original.getPendingSince().getBlock().getAbsoluteSlotNumber());
	}

	@Override
	public BigInteger depth() {
		return safeGet(() -> original.getDepth().getQuantity());
	}

	@Override
	public Direction direction() {
		return Direction.valueOf(original.getDirection().toUpperCase());
	}

	@Override
	public List<TransactionInput> inputs() {
		return Utils.convertList(original.getInputs(), TransactionInput::new);
	}

	@Override
	public List<Payment> outputs() {
		return Utils.convertList(original.getOutputs(), Payment::new);
	}

	@Override
	public List<StakeWithdrawal> withdrawals() {
		return Utils.convertList(original.getWithdrawals(), StakeWithdrawal::new);
	}

	@Override
	public Status status() {
		return Status.valueOf(original.getStatus().toUpperCase());
	}

	@Override
	public Map<Long, Object> metadata() {
		if (this.metadata == null) {
			this.metadata = Collections.unmodifiableMap(Utils.fromMetadata(original.getMetadata()));
		}
		return this.metadata;
	}
}
