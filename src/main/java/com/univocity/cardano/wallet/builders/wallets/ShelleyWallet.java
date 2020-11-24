package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.coinselections.*;
import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.api.generated.stakepools.*;
import com.univocity.cardano.wallet.api.generated.transactions.*;
import com.univocity.cardano.wallet.api.generated.wallets.*;
import com.univocity.cardano.wallet.builders.network.*;
import com.univocity.cardano.wallet.builders.stakepools.*;
import com.univocity.cardano.wallet.builders.wallets.addresses.*;
import com.univocity.cardano.wallet.builders.wallets.transactions.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.time.*;
import java.util.*;

public class ShelleyWallet extends WrapperWithId<AbstractWalletResponse> implements Wallet {

	private final Addresses addresses;
	private final Keys keys;

	public ShelleyWallet(AbstractWalletResponse original, WalletApi api) {
		super(original, api);
		this.addresses = new ShelleyAddresses(this, api);
		this.keys = new Keys(this, api);
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
		return original.getAddressPoolGap();
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
		return original.getBalance().getReward().getQuantity();
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
		api.sync().deleteWallet(this.id());
	}

	@Override
	public UTxOStatistics utxoStatistics() {
		return new UTxOStatistics(api.sync().getUTxOsStatistics(this.id()), null);
	}

	@Override
	public void rename(String newWalletName) {
		PutWalletRequest request = new PutWalletRequest();
		request.setName(newWalletName);
		PutWalletResponse response = api.sync().putWallet(this.id(), request);
		this.original.setName(response.getName());
	}

	@Override
	public void updatePassword(String currentPassword, String newPassword) {
		PutWalletPassphraseRequest request = new PutWalletPassphraseRequest();
		request.setOldPassphrase(currentPassword);
		request.setNewPassphrase(newPassword);
		api.sync().putWalletPassphrase(this.id(), request);
		original.setPassphrase(api.sync().getWallet(id()).getPassphrase());
	}

	@Override
	public Addresses addresses() {
		return addresses;
	}

	@Override
	public ShelleyPayee transfer() {
		return new ShelleyPayee(this, api);
	}

	@Override
	public Transactions<ShelleyTransaction> transactions() {
		return new ShelleyTransactions(this, api);
	}

	public ShelleyWallet update() {
		return new ShelleyWallet(api.sync().getWallet(id()), api);
	}

	public Keys keys() {
		return keys;
	}

	public ShelleyTransaction redeemITNStakingRewards(String password, String itnWalletSeed) {
		PostTransactionRedemptionRequest request = new PostTransactionRedemptionRequest();
		request.setPassphrase(password);

		ArrayList<PaymentsRedemption> payments = new ArrayList<>();
		PaymentsRedemption redemption = new PaymentsRedemption();
		redemption.setAddress(this.addresses.next().id());

		Amount amount = new Amount();
		amount.setQuantity(this.rewardsBalanceInLovelace());
		amount.setUnit("lovelace");
		redemption.setAmount(amount);

		request.setPassphrase(itnWalletSeed);

		payments.add(redemption);

		request.setPayments(payments);
		return new ShelleyTransaction(this, api.sync().postTransaction(id(), request), api);
	}

	public ShelleyTransaction redeemStakingRewards(String password) {
		PostTransactionPaymentRequest request = new PostTransactionPaymentRequest();
		request.setPassphrase(password);

		ArrayList<PaymentsPayment> payments = new ArrayList<>();
		PaymentsPayment redemption = new PaymentsPayment();
		redemption.setAddress(this.addresses.next().id());

		Amount amount = new Amount();
		amount.setQuantity(this.rewardsBalanceInLovelace());
		amount.setUnit("lovelace");
		redemption.setAmount(amount);

		payments.add(redemption);

		request.setPayments(payments);

		request.setWithdrawal("self");

		return new ShelleyTransaction(this, api.sync().postTransaction(id(), request), api);
	}

	public ShelleyTransaction delegate(StakePool stakePool, String password) {
		JoinStakePoolRequest request = new JoinStakePoolRequest();
		request.setPassphrase(password);
		return new ShelleyTransaction(this, api.sync().joinStakePool(stakePool.id(), this.id(), request), api);
	}

	public QuitPoolTransaction undelegate(String password) {
		QuitStakePoolRequest request = new QuitStakePoolRequest();
		request.setPassphrase(password);
		return new QuitPoolTransaction(this, api.sync().quitStakePool(this.id(), request), api);
	}

	@Override
	public CoinSelection selectCoins(Map<String, BigDecimal> payments) {
		SelectCoinsRequest request = new SelectCoinsRequest();
		request.setPayments(CoinSelection.toPaymentList(payments));
		return new CoinSelection(this.api.sync().selectCoins(id(), request), api);
	}


}
