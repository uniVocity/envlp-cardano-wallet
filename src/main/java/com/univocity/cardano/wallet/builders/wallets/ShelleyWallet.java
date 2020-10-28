package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.wallets.*;
import com.univocity.cardano.wallet.builders.network.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.time.*;

public class ShelleyWallet extends Wrapper<AbstractWalletResponse> implements Wallet {

	public ShelleyWallet(AbstractWalletResponse original, WalletApi api) {
		super(original, api);
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
		return original.getAddressPoolGap();
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
		return lovelaceToAda(original.getBalance().getReward().getQuantity());
	}

	@Override
	public String currentStakePoolId() {
		return safeGet(() -> original.getDelegation().getActive().getTarget());
	}

	@Override
	public String nextStakePoolId() {
		return safeGet(() -> original.getDelegation().getNext().get(0).getTarget());
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

	@Override
	public void delete() {
		api.sync().deleteWallet(this.id());
	}
}
