package com.univocity.cardano.wallet.builders.wallets.transactions;

import java.math.*;
import java.time.*;
import java.util.*;

public interface Transaction {

	enum Status {
		pending,
		in_ledger
	}

	enum Direction {
		outgoing,
		incoming
	}

	String id();

	BigDecimal amount();

	LocalDateTime insertedAt();

	LocalDateTime pendingSinceTime();

	BigInteger pendingSinceSlot();

	BigInteger pendingSinceEpoch();

	BigInteger pendingSinceSlotAbsolute();

	BigInteger depth();

	//TODO: probably refactor into transaction objects of different types
	Direction direction();

	List<TransactionInput> inputs();

	List<Payment> outputs();

	List<StakeWithdrawal> withdrawals();

	Status status();

	Map<Long, Object> metadata();

}
