package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.api.generated.byronwallets.*;
import com.univocity.cardano.wallet.builders.network.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.time.*;

public class ByronWallet extends Wrapper<AbstractByronWalletResponse> implements Wallet {

	public ByronWallet(AbstractByronWalletResponse original) {
		super(original);
	}

	@Override
	public String id() {
		return original.getId();
	}

	@Override
	public String name() {
		return original.getName();
	}

	@Override
	public LocalDateTime lastPasswordChange() {
		return LocalDateTime.parse(original.getPassphrase().getLastUpdatedAt());
	}

	@Override
	public int addressPoolGap() {
		return 20;
	}

	@Override
	public BigDecimal totalBalance() {
		return lovelaceToAda(original.getBalance().getTotal().getQuantity());
	}

	@Override
	public BigDecimal availableBalance() {
		return lovelaceToAda(original.getBalance().getAvailable().getQuantity());
	}

	@Override
	public BigDecimal rewardsBalance() {
		return BigDecimal.ZERO;
	}

	@Override
	public String currentStakePoolId() {
		return null;
	}

	@Override
	public String nextStakePoolId() {
		return null;
	}

	@Override
	public SynchronizationStatus synchronizationStatus() {
		return SynchronizationStatus.valueOf(original.getState().getStatus());
	}

	@Override
	public double synchronizationProgressPercentage() {
		return toPercentage(original.getState().getProgress().getQuantity());
	}

	@Override
	public String formattedSynchronizationProgressPercentage() {
		return toFormattedPercentage(synchronizationProgressPercentage());
	}
}
