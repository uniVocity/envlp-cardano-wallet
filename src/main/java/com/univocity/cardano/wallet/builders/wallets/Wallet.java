package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.builders.network.*;
import com.univocity.cardano.wallet.builders.wallets.addresses.*;
import com.univocity.cardano.wallet.builders.wallets.transactions.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.time.*;

public interface Wallet extends ObjectWithId {

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

	void updatePassword(String currentPassword, String newPassword);

	Addresses addresses();

	Payee<?> transfer();

	Transactions<?> transactions();
}
