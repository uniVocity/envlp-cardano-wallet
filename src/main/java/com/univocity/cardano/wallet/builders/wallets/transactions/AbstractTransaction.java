package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.builders.wallets.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.time.*;
import java.util.*;

abstract class AbstractTransaction<W extends Wallet, T extends AbstractTransaction<W, T>> extends WrapperWithId<com.univocity.cardano.wallet.api.generated.common.AbstractTransaction> implements Transaction {

	private Map<Long, Object> metadata = null;
	private final W wallet;

	public AbstractTransaction(W wallet, com.univocity.cardano.wallet.api.generated.common.AbstractTransaction original, WalletApi api) {
		super(original, api);
		this.wallet = wallet;
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
		return safeGet(() -> original.getPendingSince().getSlotNumber());
	}

	@Override
	public BigInteger pendingSinceEpoch() {
		return safeGet(() -> original.getPendingSince().getEpochNumber());
	}

	@Override
	public BigInteger pendingSinceSlotAbsolute() {
		return safeGet(() -> original.getPendingSince().getAbsoluteSlotNumber());
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

	public final W getWallet() {
		return wallet;
	}

	public final T update() {
		return (T) wallet.transactions().get(this.id());
	}

	public abstract void forget();


	public BigInteger feeInLovelace(){
		return original.getFee().getQuantity();
	}

	public BigDecimal feeInAda(){
		return lovelaceToAda(feeInLovelace());
	}

	public BigInteger depositInLovelace(){
		return original.getDeposit().getQuantity();
	}

	public BigDecimal getDepositInAda(){
		return lovelaceToAda(depositInLovelace());
	}
}
