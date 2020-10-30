package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.byronwallets.*;
import com.univocity.cardano.wallet.builders.network.*;
import com.univocity.cardano.wallet.builders.wallets.addresses.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.time.*;

public class ByronWallet extends WrapperWithId<AbstractByronWalletResponse> implements Wallet {

	private final ByronAddresses addresses;

	ByronWallet(AbstractByronWalletResponse original, WalletApi api) {
		super(original, api);
		this.addresses = new ByronAddresses(this, api);
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
		return toDateTime(original.getPassphrase().getLastUpdatedAt());
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

	@Override
	public void delete() {
		api.sync().deleteByronWallet(this.id());
	}

	@Override
	public UTxOStatistics utxoStatistics() {
		return new UTxOStatistics(api.sync().getByronUTxOsStatistics(this.id()), null);
	}

	@Override
	public void rename(String newWalletName) {
		PutByronWalletRequest request = new PutByronWalletRequest();
		request.setName(newWalletName);
		PutByronWalletResponse response = api.sync().putByronWallet(this.id(), request);
		this.original.setName(response.getName());
	}

	@Override
	public void updatePassword(String currentPassword, String newPassword) {
		PutByronWalletPassphraseRequest request = new PutByronWalletPassphraseRequest();
		request.setOldPassphrase(currentPassword);
		request.setNewPassphrase(newPassword);
		api.sync().putByronWalletPassphrase(this.id(), request);
		original.setPassphrase(api.sync().getByronWallet(this.id()).getPassphrase());
	}

	@Override
	public ByronAddresses addresses() {
		return addresses;
	}
}
