package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.builders.network.*;
import com.univocity.cardano.wallet.builders.wallets.addresses.*;
import com.univocity.cardano.wallet.builders.wallets.transactions.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.time.*;
import java.util.*;

import static com.univocity.cardano.wallet.common.Wrapper.*;

public interface Wallet extends ObjectWithId {

	String name();

	LocalDateTime lastPasswordChange();

	int addressPoolGap();

	default BigDecimal totalBalanceInAda(){
		return lovelaceToAda(totalBalanceInLovelace());
	}

	default BigDecimal availableBalanceInAda() {
		return lovelaceToAda(availableBalanceInLovelace());
	}

	default BigDecimal rewardsBalanceInAda() {
		return lovelaceToAda(rewardsBalanceInLovelace());
	}

	BigInteger totalBalanceInLovelace();

	BigInteger availableBalanceInLovelace();

	BigInteger rewardsBalanceInLovelace();

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

	Payee<? extends Transaction> transfer();

	Transactions<? extends Transaction> transactions();

	Wallet update();

	CoinSelection selectCoins(Map<String, BigDecimal> payments);
}
