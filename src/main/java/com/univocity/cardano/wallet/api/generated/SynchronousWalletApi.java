package com.univocity.cardano.wallet.api.generated;

import static com.univocity.cardano.wallet.api.service.InternalWalletApiServiceGenerator.*;
import okhttp3.*;

import com.univocity.cardano.wallet.api.generated.addresses.*;
import com.univocity.cardano.wallet.api.generated.byronaddresses.*;
import com.univocity.cardano.wallet.api.generated.byroncoinselections.*;
import com.univocity.cardano.wallet.api.generated.byronmigrations.*;
import com.univocity.cardano.wallet.api.generated.byrontransactions.*;
import com.univocity.cardano.wallet.api.generated.byronwallets.*;
import com.univocity.cardano.wallet.api.generated.coinselections.*;
import com.univocity.cardano.wallet.api.generated.migrations.*;
import com.univocity.cardano.wallet.api.generated.network.*;
import com.univocity.cardano.wallet.api.generated.proxy.*;
import com.univocity.cardano.wallet.api.generated.stakepools.*;
import com.univocity.cardano.wallet.api.generated.transactions.*;
import com.univocity.cardano.wallet.api.generated.utils.*;
import com.univocity.cardano.wallet.api.generated.wallets.*;
import java.util.*;


/**
 * Synchronous methods automatically generated for all endpoints available from the
 * Cardano Wallet Backend API:
 *
 * https://input-output-hk.github.io/cardano-wallet/api/edge/
 */
public class SynchronousWalletApi {

	private final InternalWalletApiService api;

	public SynchronousWalletApi(InternalWalletApiService api) {
		this.api = api;
	}


	/**
	 * 
	 * Create and restore a wallet from a mnemonic sentence or account public key.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostWalletRequest}
	 * @return the server response as an instance of {@link PostWalletResponse}
	 */
	public PostWalletResponse postWallet(RequestBody requestBody){
		return executeSync(api.postWallet(requestBody));
	}

	/**
	 * 
	 * Return a list of known wallets, ordered from oldest to newest.
	 * {@code status: stable}
	 * 
	 * @return the server response as a list of {@link ListWalletsResponseItem}
	 */
	public List<ListWalletsResponseItem> listWallets(){
		return executeSync(api.listWallets());
	}

	/**
	 * 
	 * Return the UTxOs distribution across the whole wallet, in the form of a histogram.
	 *   <pre>{@code 
	 *      │
	 *  100 ─
	 *      │
	 *      │                                 ┌───┐
	 *   10 ─                         ┌───┐   │   │                   ┌───┐
	 *      │                 ┌───┐   │   │   │   │                   │   │
	 *      │                 │   │   │   │   │   │   ┌───┐           │   │
	 *    1 ─ ┌───┐           │   │   │   │   │   │   │   │           │   │
	 *      │ │   │           │   │   │   │   │   │   │   │           │   │
	 *      │ │   │ │       │ │   │ │ │   │ ╷ │   │ ╷ │   │ ╷       ╷ │   │
	 *      └─┘   └─│───────│─┘   └─│─┘   └─│─┘   └─│─┘   └─│───────│─┘   └────
	 *            10μ₳    100μ₳   1000μ₳   0.1₳    1₳      10₳     100₳
	 *   }</pre>
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return the server response as an instance of {@link GetUTxOsStatisticsResponse}
	 */
	public GetUTxOsStatisticsResponse getUTxOsStatistics(String walletId){
		return executeSync(api.getUTxOsStatistics(walletId));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return the server response as an instance of {@link GetWalletResponse}
	 */
	public GetWalletResponse getWallet(String walletId){
		return executeSync(api.getWallet(walletId));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 */
	public void deleteWallet(String walletId){
		executeSync(api.deleteWallet(walletId));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PutWalletRequest}
	 * @return the server response as an instance of {@link PutWalletResponse}
	 */
	public PutWalletResponse putWallet(String walletId, RequestBody requestBody){
		return executeSync(api.putWallet(walletId, requestBody));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PutWalletPassphraseRequest}
	 */
	public void putWalletPassphrase(String walletId, RequestBody requestBody){
		executeSync(api.putWalletPassphrase(walletId, requestBody));
	}

	/**
	 * 
	 * Estimate fee for the transaction. The estimate is made by
	 * assembling multiple transactions and analyzing the
	 * distribution of their fees. The estimated_max is the highest
	 * fee observed, and the estimated_min is the fee which is lower
	 * than at least 90% of the fees observed.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PostTransactionFeeRequest}
	 * @return the server response as an instance of {@link PostTransactionFeeResponse}
	 */
	public PostTransactionFeeResponse postTransactionFee(String walletId, RequestBody requestBody){
		return executeSync(api.postTransactionFee(walletId, requestBody));
	}

	/**
	 * 
	 * Create and send transaction from the wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PostTransactionRequest}
	 * @return the server response as an instance of {@link PostTransactionResponse}
	 */
	public PostTransactionResponse postTransaction(String walletId, RequestBody requestBody){
		return executeSync(api.postTransaction(walletId, requestBody));
	}

	/**
	 * 
	 * Lists all incoming and outgoing wallet's transactions.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param start the start.
	 * - Format: {@code ISO 8601}.
	 * @param end the end.
	 * - Format: {@code ISO 8601}.
	 * @param order the order.
	 * - Accepted values: {@code [ascending, descending]}.
	 * - Defaults to: {@code descending}.
	 * @param minWithdrawal the minWithdrawal.
	 * - Minimum value: {@code 1}.
	 * @return the server response as a list of {@link ListTransactionsResponseItem}
	 */
	public List<ListTransactionsResponseItem> listTransactions(String walletId, String start, String end, String order, Integer minWithdrawal){
		return executeSync(api.listTransactions(walletId, start, end, order, minWithdrawal));
	}

	/**
	 * 
	 * Get transaction by id.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param transactionId the transactionId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 64}.
	 * @return the server response as an instance of {@link GetTransactionResponse}
	 */
	public GetTransactionResponse getTransaction(String walletId, String transactionId){
		return executeSync(api.getTransaction(walletId, transactionId));
	}

	/**
	 * 
	 * Forget pending transaction. Importantly, a transaction, when sent,
	 * cannot be cancelled. One can only request forgetting about it
	 * in order to try spending (concurrently) the same UTxO in another
	 * transaction. But, the transaction may still show up later in a block
	 * and therefore, appear in the wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param transactionId the transactionId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 64}.
	 */
	public void deleteTransaction(String walletId, String transactionId){
		executeSync(api.deleteTransaction(walletId, transactionId));
	}

	/**
	 * 
	 * Return a list of known addresses, ordered from newest to oldest
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param state the state.
	 * - Accepted values: {@code [used, unused]}.
	 * @return the server response as a list of {@link ListAddressesResponseItem}
	 */
	public List<ListAddressesResponseItem> listAddresses(String walletId, String state){
		return executeSync(api.listAddresses(walletId, state));
	}

	/**
	 * 
	 * List all known stake pools ordered by descending `non_myopic_member_rewards`.
	 * The `non_myopic_member_rewards` — and thus the ordering — depends on the `?stake` query
	 * parameter.
	 * > ⚠️  On the incentivized testnet, pools are instead ordered by
	 * descending `desirability`.
	 * Some pools _may_ also have metadata attached to them.
	 * {@code status: stable}
	 * 
	 * @param stake the stake (optional).
	 * - Value range from {@code 0} to {@code 45000000000000000}.
	 * @return the server response as a list of {@link ListStakePoolsResponseItem}
	 */
	public List<ListStakePoolsResponseItem> listStakePools(Integer stake){
		return executeSync(api.listStakePools(stake));
	}

	/**
	 * 
	 * Estimate fee for joining or leaving a stake pool. Note that it is an
	 * estimation because a delegation induces a transaction for which coins
	 * have to be selected randomly within the wallet. Because of this randomness,
	 * fees can only be estimated.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return the server response as an instance of {@link GetDelegationFeeResponse}
	 */
	public GetDelegationFeeResponse getDelegationFee(String walletId){
		return executeSync(api.getDelegationFee(walletId));
	}

	/**
	 * 
	 * Stop delegating completely. The wallet's stake will become inactive.
	 * > ⚠️  Disclaimer ⚠️
	 * >
	 * > This endpoint historically use to take a stake pool id as a path parameter.
	 * > However, retiring from delegation is ubiquitous and not tight to a particular
	 * > stake pool. For backward-compatibility reasons, sending stake pool ids as path
	 * > parameter will still be accepted by the server but new integrations are
	 * > encouraged to provide a placeholder asterisk `*` instead.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return the server response as an instance of {@link QuitStakePoolResponse}
	 */
	public QuitStakePoolResponse quitStakePool(String walletId){
		return executeSync(api.quitStakePool(walletId));
	}

	/**
	 * 
	 * Delegate all (current and future) addresses from the given wallet to the given stake pool.
	 * Note: Bech32-encoded stake pool identifiers can vary in length.
	 * {@code status: stable}
	 * 
	 * @param stakePoolId the stakePoolId.
	 * - Format: {@code hex|bech32}.
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link JoinStakePoolRequest}
	 * @return the server response as an instance of {@link JoinStakePoolResponse}
	 */
	public JoinStakePoolResponse joinStakePool(String stakePoolId, String walletId, RequestBody requestBody){
		return executeSync(api.joinStakePool(stakePoolId, walletId, requestBody));
	}

	/**
	 * 
	 * Select coins to cover the given set of payments.
	 * Uses the 
	 * Random-Improve coin selection algorithm.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link SelectCoinsRequest}
	 * @return the server response as an instance of {@link SelectCoinsResponse}
	 */
	public SelectCoinsResponse selectCoins(String walletId, RequestBody requestBody){
		return executeSync(api.selectCoins(walletId, requestBody));
	}

	/**
	 * 
	 * Submit one or more transactions which transfers all funds from a Shelley
	 * wallet to a set of addresses.
	 * This operation attempts to preserve the UTxO "shape" of a wallet as far as possible.
	 * That is, coins will not be agglomerated. Therefore, if the wallet has
	 * a large UTxO set, several transactions may be needed.
	 * A typical usage would be when one wants to move all funds from an old wallet to another
	 * by providing addresses coming from the new wallet.
	 * {@code status: in development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link MigrateShelleyWalletRequest}
	 * @return the server response as a list of {@link MigrateShelleyWalletResponseItem}
	 */
	public List<MigrateShelleyWalletResponseItem> migrateShelleyWallet(String walletId, RequestBody requestBody){
		return executeSync(api.migrateShelleyWallet(walletId, requestBody));
	}

	/**
	 * 
	 * Calculate the exact cost of sending all funds from particular Shelley wallet
	 * to a set of addresses.
	 * {@code status: in development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return the server response as an instance of {@link GetShelleyWalletMigrationInfoResponse}
	 */
	public GetShelleyWalletMigrationInfoResponse getShelleyWalletMigrationInfo(String walletId){
		return executeSync(api.getShelleyWalletMigrationInfo(walletId));
	}

	/**
	 * 
	 * Restore a Byron wallet from a mnemonic sentence or encrypted root private key.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostByronWalletRequest}
	 * @return the server response as an instance of {@link PostByronWalletResponse}
	 */
	public PostByronWalletResponse postByronWallet(RequestBody requestBody){
		return executeSync(api.postByronWallet(requestBody));
	}

	/**
	 * 
	 * Return a list of known Byron wallets, ordered from oldest to newest.
	 * {@code status: stable}
	 * 
	 * @return the server response as a list of {@link ListByronWalletsResponseItem}
	 */
	public List<ListByronWalletsResponseItem> listByronWallets(){
		return executeSync(api.listByronWallets());
	}

	/**
	 * 
	 * Return the UTxOs distribution across the whole wallet, in the form of a histogram.
	 *   <pre>{@code 
	 *      │
	 *  100 ─
	 *      │
	 *      │                                 ┌───┐
	 *   10 ─                         ┌───┐   │   │                   ┌───┐
	 *      │                 ┌───┐   │   │   │   │                   │   │
	 *      │                 │   │   │   │   │   │   ┌───┐           │   │
	 *    1 ─ ┌───┐           │   │   │   │   │   │   │   │           │   │
	 *      │ │   │           │   │   │   │   │   │   │   │           │   │
	 *      │ │   │ │       │ │   │ │ │   │ ╷ │   │ ╷ │   │ ╷       ╷ │   │
	 *      └─┘   └─│───────│─┘   └─│─┘   └─│─┘   └─│─┘   └─│───────│─┘   └────
	 *            10μ₳    100μ₳   1000μ₳   0.1₳    1₳      10₳     100₳
	 *   }</pre>
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return the server response as an instance of {@link GetByronUTxOsStatisticsResponse}
	 */
	public GetByronUTxOsStatisticsResponse getByronUTxOsStatistics(String walletId){
		return executeSync(api.getByronUTxOsStatistics(walletId));
	}

	/**
	 * 
	 * Return information about a Byron wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return the server response as an instance of {@link GetByronWalletResponse}
	 */
	public GetByronWalletResponse getByronWallet(String walletId){
		return executeSync(api.getByronWallet(walletId));
	}

	/**
	 * 
	 * Delete a Byron wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 */
	public void deleteByronWallet(String walletId){
		executeSync(api.deleteByronWallet(walletId));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PutByronWalletRequest}
	 * @return the server response as an instance of {@link PutByronWalletResponse}
	 */
	public PutByronWalletResponse putByronWallet(String walletId, RequestBody requestBody){
		return executeSync(api.putByronWallet(walletId, requestBody));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PutByronWalletPassphraseRequest}
	 */
	public void putByronWalletPassphrase(String walletId, RequestBody requestBody){
		executeSync(api.putByronWalletPassphrase(walletId, requestBody));
	}

	/**
	 * 
	 * ⚠️  This endpoint is available for `random` wallets only. Any
	 * attempt to call this endpoint on another type of wallet will result in
	 * a `403 Forbidden` error from the server.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link CreateAddressRequest}
	 * @return the server response as an instance of {@link CreateAddressResponse}
	 */
	public CreateAddressResponse createAddress(String walletId, RequestBody requestBody){
		return executeSync(api.createAddress(walletId, requestBody));
	}

	/**
	 * 
	 * Return a list of known addresses, ordered from newest to oldest for sequential wallets.
	 * Arbitrarily ordered for random wallets.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param state the state.
	 * - Accepted values: {@code [used, unused]}.
	 * @return the server response as a list of {@link ListByronAddressesResponseItem}
	 */
	public List<ListByronAddressesResponseItem> listByronAddresses(String walletId, String state){
		return executeSync(api.listByronAddresses(walletId, state));
	}

	/**
	 * 
	 * ⚠️  This endpoint is available for `random` wallets only. Any
	 * attempt to call this endpoint on another type of wallet will result in
	 * a `403 Forbidden` error from the server.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link ImportAddressesRequest}
	 */
	public void importAddresses(String walletId, RequestBody requestBody){
		executeSync(api.importAddresses(walletId, requestBody));
	}

	/**
	 * 
	 * ⚠️  This endpoint is available for `random` wallets only. Any
	 * attempt to call this endpoint on another type of wallet will result in
	 * a `403 Forbidden` error from the server.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param addressId the addressId.
	 * - Format: {@code base58}.
	 * 
	 * - Example: 
	 *   <pre>{@code DdzFFzCqrhtCNjPk5Lei7E1FxnoqMoAYtJ8VjAWbFmDb614nNBWBwv3kt6QHJa59cGezzf6piMWsbK7sWRB5sv325QqWdRuusMqqLdMt}</pre>
	 */
	public void importAddress(String walletId, String addressId){
		executeSync(api.importAddress(walletId, addressId));
	}

	/**
	 * 
	 * Estimate fee for the transaction.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PostByronTransactionFeeRequest}
	 * @return the server response as an instance of {@link PostByronTransactionFeeResponse}
	 */
	public PostByronTransactionFeeResponse postByronTransactionFee(String walletId, RequestBody requestBody){
		return executeSync(api.postByronTransactionFee(walletId, requestBody));
	}

	/**
	 * 
	 * Create and send transaction from the wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PostByronTransactionRequest}
	 * @return the server response as an instance of {@link PostByronTransactionResponse}
	 */
	public PostByronTransactionResponse postByronTransaction(String walletId, RequestBody requestBody){
		return executeSync(api.postByronTransaction(walletId, requestBody));
	}

	/**
	 * 
	 * List all incoming and outgoing transactions for the given wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param start the start.
	 * - Format: {@code ISO 8601}.
	 * @param end the end.
	 * - Format: {@code ISO 8601}.
	 * @param order the order.
	 * - Accepted values: {@code [ascending, descending]}.
	 * - Defaults to: {@code descending}.
	 * @return the server response as a list of {@link ListByronTransactionsResponseItem}
	 */
	public List<ListByronTransactionsResponseItem> listByronTransactions(String walletId, String start, String end, String order){
		return executeSync(api.listByronTransactions(walletId, start, end, order));
	}

	/**
	 * 
	 * Get transaction by id.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param transactionId the transactionId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 64}.
	 * @return the server response as an instance of {@link GetByronTransactionResponse}
	 */
	public GetByronTransactionResponse getByronTransaction(String walletId, String transactionId){
		return executeSync(api.getByronTransaction(walletId, transactionId));
	}

	/**
	 * 
	 * Forget pending Byron transaction. Importantly, a transaction, when sent,
	 * cannot be cancelled. One can only request forgetting about it
	 * in order to try spending (concurrently) the same UTxO in another
	 * transaction. But, the transaction may still show up later in a block
	 * and therefore, appear in the wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param transactionId the transactionId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 64}.
	 */
	public void deleteByronTransaction(String walletId, String transactionId){
		executeSync(api.deleteByronTransaction(walletId, transactionId));
	}

	/**
	 * 
	 * Select coins to cover the given set of payments.
	 * Uses the 
	 * Random-Improve coin selection algorithm.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link ByronSelectCoinsRequest}
	 * @return the server response as an instance of {@link ByronSelectCoinsResponse}
	 */
	public ByronSelectCoinsResponse byronSelectCoins(String walletId, RequestBody requestBody){
		return executeSync(api.byronSelectCoins(walletId, requestBody));
	}

	/**
	 * 
	 * Submit one or more transactions which transfers all funds from a Byron
	 * wallet to a set of addresses.
	 * This operation attempts to preserve the UTxO "shape" of a wallet as far as possible.
	 * That is, coins will not be agglomerated. Therefore, if the wallet has
	 * a large UTxO set, several transactions may be needed.
	 * A typical usage would be when one wants to move all funds from an old wallet to another (Shelley
	 * or Byron) by providing addresses coming from the new wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link MigrateByronWalletRequest}
	 * @return the server response as a list of {@link MigrateByronWalletResponseItem}
	 */
	public List<MigrateByronWalletResponseItem> migrateByronWallet(String walletId, RequestBody requestBody){
		return executeSync(api.migrateByronWallet(walletId, requestBody));
	}

	/**
	 * 
	 * Calculate the exact cost of sending all funds from particular Byron wallet to
	 * a set of addresses.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return the server response as an instance of {@link GetByronWalletMigrationInfoResponse}
	 */
	public GetByronWalletMigrationInfoResponse getByronWalletMigrationInfo(String walletId){
		return executeSync(api.getByronWalletMigrationInfo(walletId));
	}

	/**
	 * status: stable
	 * @return the server response as an instance of {@link GetNetworkInformationResponse}
	 */
	public GetNetworkInformationResponse getNetworkInformation(){
		return executeSync(api.getNetworkInformation());
	}

	/**
	 * status: stable
	 * @param forceNtpCheck the forceNtpCheck.
	 * @return the server response as an instance of {@link GetNetworkClockResponse}
	 */
	public GetNetworkClockResponse getNetworkClock(Boolean forceNtpCheck){
		return executeSync(api.getNetworkClock(forceNtpCheck));
	}

	/**
	 * 
	 * Returns the set of network parameters for the current epoch.
	 * {@code status: stable}
	 * 
	 * @return the server response as an instance of {@link GetNetworkParametersResponse}
	 */
	public GetNetworkParametersResponse getNetworkParameters(){
		return executeSync(api.getNetworkParameters());
	}

	/**
	 * 
	 * Submits a transaction that was created and signed outside of cardano-wallet.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing a {@code byte[]} loaded from a signed transaction message binary blob.
	 * @return the server response as an instance of {@link PostExternalTransactionResponse}
	 */
	public PostExternalTransactionResponse postExternalTransaction(RequestBody requestBody){
		return executeSync(api.postExternalTransaction(requestBody));
	}

	/**
	 * 
	 * Give useful information about the structure of a given address.
	 * {@code status: stable}
	 * 
	 * @param addressId the addressId.
	 * - Format: {@code base58}.
	 * 
	 * - Example: 
	 *   <pre>{@code DdzFFzCqrhtCNjPk5Lei7E1FxnoqMoAYtJ8VjAWbFmDb614nNBWBwv3kt6QHJa59cGezzf6piMWsbK7sWRB5sv325QqWdRuusMqqLdMt}</pre>
	 * @return the server response as an instance of {@link InspectAddressResponse}
	 */
	public InspectAddressResponse inspectAddress(String addressId){
		return executeSync(api.inspectAddress(addressId));
	}
}