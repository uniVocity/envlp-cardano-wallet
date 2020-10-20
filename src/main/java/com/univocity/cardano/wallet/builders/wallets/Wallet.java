package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.builders.network.*;

import java.math.*;
import java.time.*;

public interface Wallet {

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

}
