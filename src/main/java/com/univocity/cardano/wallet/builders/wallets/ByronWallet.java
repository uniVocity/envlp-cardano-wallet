package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.byroncoinselections.*;
import com.univocity.cardano.wallet.api.generated.byronwallets.*;
import com.univocity.cardano.wallet.builders.network.*;
import com.univocity.cardano.wallet.builders.wallets.addresses.*;
import com.univocity.cardano.wallet.builders.wallets.transactions.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.time.*;
import java.util.*;

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
		return toDateTime(safeGet(() -> original.getPassphrase().getLastUpdatedAt()));
	}

	@Override
	public int addressPoolGap() {
		return 20;
	}

	@Override
	public BigInteger totalBalanceInLovelace() {
		return original.getBalance().getTotal().getQuantity();
	}

	@Override
	public BigInteger availableBalanceInLovelace() {
		return original.getBalance().getAvailable().getQuantity();
	}

	@Override
	public BigInteger rewardsBalanceInLovelace() {
		return BigInteger.ZERO;
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
		return SynchronizationStatus.valueOf(original.getState().getStatus().toUpperCase());
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


	@Override
	public ByronPayee transfer() {
		return new ByronPayee(this, api);
	}

	@Override
	public Transactions<ByronTransaction> transactions() {
		return new ByronTransactions(this, api);
	}

	public ByronWallet update() {
		return new ByronWallet(api.sync().getByronWallet(id()), api);
	}

	@Override
	public CoinSelection selectCoins(Map<String, BigDecimal> payments) {
		ByronSelectCoinsRequest request = new ByronSelectCoinsRequest();
		request.setPayments(CoinSelection.toPaymentList(payments));
		return new CoinSelection(this.api.sync().byronSelectCoins(id(), request), api);
	}
}
