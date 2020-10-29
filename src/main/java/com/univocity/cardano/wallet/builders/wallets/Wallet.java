package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.builders.network.*;
import org.jetbrains.annotations.*;

import java.math.*;
import java.time.*;

public interface Wallet extends Comparable<Wallet>{

	String id();

	String name();

	LocalDateTime lastPasswordChange();

	int addressPoolGap();

	BigDecimal totalBalance();

	BigDecimal availableBalance();

	BigDecimal rewardsBalance();

	String currentStakePoolId();

	String nextStakePoolId();

	SynchronizationStatus synchronizationStatus();

	double synchronizationProgressPercentage();

	String formattedSynchronizationProgressPercentage();

	void delete();

	UTxOStatistics utxoStatistics();

	void rename(String newWalletName);

	@Override
	default int compareTo(Wallet o) {
		return this.id().compareTo(o.id());
	}
}
