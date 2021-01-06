package com.univocity.cardano.wallet.api.generated;

import java.math.*;
import static com.univocity.cardano.wallet.api.service.InternalWalletApiServiceGenerator.*;
import com.univocity.cardano.wallet.common.*;

import com.univocity.cardano.wallet.api.generated.addresses.*;
import com.univocity.cardano.wallet.api.generated.byronaddresses.*;
import com.univocity.cardano.wallet.api.generated.byroncoinselections.*;
import com.univocity.cardano.wallet.api.generated.byronmigrations.*;
import com.univocity.cardano.wallet.api.generated.byrontransactions.*;
import com.univocity.cardano.wallet.api.generated.byronwallets.*;
import com.univocity.cardano.wallet.api.generated.coinselections.*;
import com.univocity.cardano.wallet.api.generated.experimental.*;
import com.univocity.cardano.wallet.api.generated.keys.*;
import com.univocity.cardano.wallet.api.generated.migrations.*;
import com.univocity.cardano.wallet.api.generated.network.*;
import com.univocity.cardano.wallet.api.generated.proxy.*;
import com.univocity.cardano.wallet.api.generated.settings.*;
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
	 * **⚠️ WARNING ⚠️**
	 * This endpoint is experimental and for internal use in the Catalyst project. This
	 * functionality will be refined in the forthcoming future and the interface is likely
	 * to change in **NON-BACKWARD COMPATIBLE WAYS**.
	 * Note: Only `Soft` indexes are supported by this endpoint.
	 * {@code status: experimental}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param role the role.
	 * - Accepted values: {@code [utxo_external, utxo_internal, mutable_account, multisig_script]}.
	 * @param index the index.
	 * 
	 * An individual segment within a derivation path.
	 * Indexes without `H` suffix are called `Soft`.
	 * Indexes with `H` suffix are called `Hardened`.
	 * 
	 * 
	 * - Example: 
	 *   <pre>{@code 1852H}</pre>
	 * @param requestBody a request body containing the json representation of {@link SignMetadataRequest}
	 * @return the server response as an instance of {@link SignMetadataResponse}
	 */
	public SignMetadataResponse signMetadata(String walletId, String role, String index, SignMetadataRequest requestBody){
		return executeSync(api.signMetadata(walletId, role, index, Utils.createRequestBody(requestBody)));
	}

	/**
	 * 
	 * Create and restore a wallet from a mnemonic sentence or account public key.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostWalletShelleyRequest}
	 * @return the server response as an instance of {@link PostWalletResponse}
	 */
	public PostWalletResponse postWallet(PostWalletShelleyRequest requestBody){
		return executeSync(api.postWallet(Utils.createRequestBody(requestBody)));
	}

	/**
	 * 
	 * Create and restore a wallet from a mnemonic sentence or account public key.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostWalletShelleyFromXpubRequest}
	 * @return the server response as an instance of {@link PostWalletResponse}
	 */
	public PostWalletResponse postWallet(PostWalletShelleyFromXpubRequest requestBody){
		return executeSync(api.postWallet(Utils.createRequestBody(requestBody)));
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
	public PutWalletResponse putWallet(String walletId, PutWalletRequest requestBody){
		return executeSync(api.putWallet(walletId, Utils.createRequestBody(requestBody)));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PutWalletPassphraseRequest}
	 */
	public void putWalletPassphrase(String walletId, PutWalletPassphraseRequest requestBody){
		executeSync(api.putWalletPassphrase(walletId, Utils.createRequestBody(requestBody)));
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
	 * @param requestBody a request body containing the json representation of {@link PostTransactionFeePaymentRequest}
	 * @return the server response as an instance of {@link PostTransactionFeeResponse}
	 */
	public PostTransactionFeeResponse postTransactionFee(String walletId, PostTransactionFeePaymentRequest requestBody){
		return executeSync(api.postTransactionFee(walletId, Utils.createRequestBody(requestBody)));
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
	 * @param requestBody a request body containing the json representation of {@link PostTransactionFeeRedemptionRequest}
	 * @return the server response as an instance of {@link PostTransactionFeeResponse}
	 */
	public PostTransactionFeeResponse postTransactionFee(String walletId, PostTransactionFeeRedemptionRequest requestBody){
		return executeSync(api.postTransactionFee(walletId, Utils.createRequestBody(requestBody)));
	}

	/**
	 * 
	 * Create and send transaction from the wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PostTransactionPaymentRequest}
	 * @return the server response as an instance of {@link PostTransactionResponse}
	 */
	public PostTransactionResponse postTransaction(String walletId, PostTransactionPaymentRequest requestBody){
		return executeSync(api.postTransaction(walletId, Utils.createRequestBody(requestBody)));
	}

	/**
	 * 
	 * Create and send transaction from the wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PostTransactionRedemptionRequest}
	 * @return the server response as an instance of {@link PostTransactionResponse}
	 */
	public PostTransactionResponse postTransaction(String walletId, PostTransactionRedemptionRequest requestBody){
		return executeSync(api.postTransaction(walletId, Utils.createRequestBody(requestBody)));
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
	public List<ListTransactionsResponseItem> listTransactions(String walletId, String start, String end, String order, BigInteger minWithdrawal){
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
	 * Return a public key for a given role and derivation index.
	 * Note: Only `Soft` indexes are supported by this endpoint.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param role the role.
	 * - Accepted values: {@code [utxo_external, utxo_internal, mutable_account, multisig_script]}.
	 * @param index the index.
	 * 
	 * An individual segment within a derivation path.
	 * Indexes without `H` suffix are called `Soft`.
	 * Indexes with `H` suffix are called `Hardened`.
	 * 
	 * 
	 * - Example: 
	 *   <pre>{@code 1852H}</pre>
	 * @return the server response as an instance of {@link GetWalletKeyResponse}
	 */
	public GetWalletKeyResponse getWalletKey(String walletId, String role, String index){
		return executeSync(api.getWalletKey(walletId, role, index));
	}

	/**
	 * 
	 * List all known stake pools ordered by descending `non_myopic_member_rewards`.
	 * The `non_myopic_member_rewards` — and thus the ordering — depends on the `?stake` query
	 * parameter.
	 * Some pools _may_ also have metadata attached to them.
	 * {@code status: stable}
	 * 
	 * @param stake the stake.
	 * - Value range from {@code 0} to {@code 45000000000000000}.
	 * @return the server response as a list of {@link ListStakePoolsResponseItem}
	 */
	public List<ListStakePoolsResponseItem> listStakePools(Long stake){
		return executeSync(api.listStakePools(stake));
	}

	/**Returns the current status of the stake pools maintenance actions.
	 * @return the server response as an instance of {@link GetMaintenanceActionsResponse}
	 */
	public GetMaintenanceActionsResponse getMaintenanceActions(){
		return executeSync(api.getMaintenanceActions());
	}

	/**
	 * 
	 * Performs maintenance actions on stake pools, such
	 * as triggering metadata garbage collection.
	 * Actions may not be instantaneous.
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostMaintenanceActionRequest}
	 */
	public void postMaintenanceAction(PostMaintenanceActionRequest requestBody){
		executeSync(api.postMaintenanceAction(Utils.createRequestBody(requestBody)));
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
	 * @param requestBody a request body containing the json representation of {@link QuitStakePoolRequest}
	 * @return the server response as an instance of {@link QuitStakePoolResponse}
	 */
	public QuitStakePoolResponse quitStakePool(String walletId, QuitStakePoolRequest requestBody){
		return executeSync(api.quitStakePool(walletId, Utils.createRequestBody(requestBody)));
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
	public JoinStakePoolResponse joinStakePool(String stakePoolId, String walletId, JoinStakePoolRequest requestBody){
		return executeSync(api.joinStakePool(stakePoolId, walletId, Utils.createRequestBody(requestBody)));
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
	public SelectCoinsResponse selectCoins(String walletId, SelectCoinsRequest requestBody){
		return executeSync(api.selectCoins(walletId, Utils.createRequestBody(requestBody)));
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
	public List<MigrateShelleyWalletResponseItem> migrateShelleyWallet(String walletId, MigrateShelleyWalletRequest requestBody){
		return executeSync(api.migrateShelleyWallet(walletId, Utils.createRequestBody(requestBody)));
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
	 * Restore a Byron wallet from a mnemonic sentence or encrypted root private key (deprecated).
	 *   **⚠️ WARNING ⚠️**
	 *   The construction of random wallet in itself is **deprecated**, in particular the restoration from an encrypted root private key.
	 *   These endpoints exist to ease migrations from legacy software such as `cardano-sl` but should be avoided by new applications.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostByronWalletRandomRequest}
	 * @return the server response as an instance of {@link PostByronWalletResponse}
	 */
	public PostByronWalletResponse postByronWallet(PostByronWalletRandomRequest requestBody){
		return executeSync(api.postByronWallet(Utils.createRequestBody(requestBody)));
	}

	/**
	 * 
	 * Restore a Byron wallet from a mnemonic sentence or encrypted root private key (deprecated).
	 *   **⚠️ WARNING ⚠️**
	 *   The construction of random wallet in itself is **deprecated**, in particular the restoration from an encrypted root private key.
	 *   These endpoints exist to ease migrations from legacy software such as `cardano-sl` but should be avoided by new applications.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostByronWalletIcarusRequest}
	 * @return the server response as an instance of {@link PostByronWalletResponse}
	 */
	public PostByronWalletResponse postByronWallet(PostByronWalletIcarusRequest requestBody){
		return executeSync(api.postByronWallet(Utils.createRequestBody(requestBody)));
	}

	/**
	 * 
	 * Restore a Byron wallet from a mnemonic sentence or encrypted root private key (deprecated).
	 *   **⚠️ WARNING ⚠️**
	 *   The construction of random wallet in itself is **deprecated**, in particular the restoration from an encrypted root private key.
	 *   These endpoints exist to ease migrations from legacy software such as `cardano-sl` but should be avoided by new applications.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostByronWalletTrezorRequest}
	 * @return the server response as an instance of {@link PostByronWalletResponse}
	 */
	public PostByronWalletResponse postByronWallet(PostByronWalletTrezorRequest requestBody){
		return executeSync(api.postByronWallet(Utils.createRequestBody(requestBody)));
	}

	/**
	 * 
	 * Restore a Byron wallet from a mnemonic sentence or encrypted root private key (deprecated).
	 *   **⚠️ WARNING ⚠️**
	 *   The construction of random wallet in itself is **deprecated**, in particular the restoration from an encrypted root private key.
	 *   These endpoints exist to ease migrations from legacy software such as `cardano-sl` but should be avoided by new applications.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostByronWalletLedgerRequest}
	 * @return the server response as an instance of {@link PostByronWalletResponse}
	 */
	public PostByronWalletResponse postByronWallet(PostByronWalletLedgerRequest requestBody){
		return executeSync(api.postByronWallet(Utils.createRequestBody(requestBody)));
	}

	/**
	 * 
	 * Restore a Byron wallet from a mnemonic sentence or encrypted root private key (deprecated).
	 *   **⚠️ WARNING ⚠️**
	 *   The construction of random wallet in itself is **deprecated**, in particular the restoration from an encrypted root private key.
	 *   These endpoints exist to ease migrations from legacy software such as `cardano-sl` but should be avoided by new applications.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostByronWalletIcarusTrezorLedgerFromXpubRequest}
	 * @return the server response as an instance of {@link PostByronWalletResponse}
	 */
	public PostByronWalletResponse postByronWallet(PostByronWalletIcarusTrezorLedgerFromXpubRequest requestBody){
		return executeSync(api.postByronWallet(Utils.createRequestBody(requestBody)));
	}

	/**
	 * 
	 * Restore a Byron wallet from a mnemonic sentence or encrypted root private key (deprecated).
	 *   **⚠️ WARNING ⚠️**
	 *   The construction of random wallet in itself is **deprecated**, in particular the restoration from an encrypted root private key.
	 *   These endpoints exist to ease migrations from legacy software such as `cardano-sl` but should be avoided by new applications.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostByronWalletRandomFromXprvRequest}
	 * @return the server response as an instance of {@link PostByronWalletResponse}
	 */
	public PostByronWalletResponse postByronWallet(PostByronWalletRandomFromXprvRequest requestBody){
		return executeSync(api.postByronWallet(Utils.createRequestBody(requestBody)));
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
	public PutByronWalletResponse putByronWallet(String walletId, PutByronWalletRequest requestBody){
		return executeSync(api.putByronWallet(walletId, Utils.createRequestBody(requestBody)));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PutByronWalletPassphraseRequest}
	 */
	public void putByronWalletPassphrase(String walletId, PutByronWalletPassphraseRequest requestBody){
		executeSync(api.putByronWalletPassphrase(walletId, Utils.createRequestBody(requestBody)));
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
	public CreateAddressResponse createAddress(String walletId, CreateAddressRequest requestBody){
		return executeSync(api.createAddress(walletId, Utils.createRequestBody(requestBody)));
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
	public void importAddresses(String walletId, ImportAddressesRequest requestBody){
		executeSync(api.importAddresses(walletId, Utils.createRequestBody(requestBody)));
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
	public PostByronTransactionFeeResponse postByronTransactionFee(String walletId, PostByronTransactionFeeRequest requestBody){
		return executeSync(api.postByronTransactionFee(walletId, Utils.createRequestBody(requestBody)));
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
	public PostByronTransactionResponse postByronTransaction(String walletId, PostByronTransactionRequest requestBody){
		return executeSync(api.postByronTransaction(walletId, Utils.createRequestBody(requestBody)));
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
	 * Note:  Not supported for Byron random wallets.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link ByronSelectCoinsRequest}
	 * @return the server response as an instance of {@link ByronSelectCoinsResponse}
	 */
	public ByronSelectCoinsResponse byronSelectCoins(String walletId, ByronSelectCoinsRequest requestBody){
		return executeSync(api.byronSelectCoins(walletId, Utils.createRequestBody(requestBody)));
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
	public List<MigrateByronWalletResponseItem> migrateByronWallet(String walletId, MigrateByronWalletRequest requestBody){
		return executeSync(api.migrateByronWallet(walletId, Utils.createRequestBody(requestBody)));
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
	public PostExternalTransactionResponse postExternalTransaction(byte[] requestBody){
		return executeSync(api.postExternalTransaction(Utils.createRequestBody(requestBody)));
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

	/**
	 * 
	 * Construct any address by specyfying credential for payment or stake.
	 * In Cardano, Addresses are made of three parts:
	 *   <pre>{@code 
	 * *---------*---------*-------*
	 * | NETWORK | PAYMENT | STAKE |
	 * *---------*---------*-------*
	 *   }</pre>
	 * The `NETWORK` part allows for distinguishing addresses between different networks like the mainnet or the testnet. It is implicitly
	 * handled by the server without you having to worry about it. The `PAYMENT` and `STAKE` parts however can be constructed similarly, using
	 * either:
	 * - A public key
	 * - A script
	 * The script itself is either constructed out of a public key, or one of the three following primitives:
	 * - all
	 * - any
	 * - some
	 * Each of which contains one or more script(s) that can be either keys or primitives, and so on. Schematically:
	 *   <pre>{@code 
	 *                                    ┏─────────┓
	 * SCRIPT = ──┬───────────────────────┤ pub key ├─────────────────────┬──
	 *            │                       ┗─────────┛                     │
	 *            │  ╭─────╮   ╭────────╮                                 │
	 *            ├──┤ ALL ├───┤ SCRIPT ├─┬───────────────────────────────┤
	 *            │  ╰─────╯ ^ ╰────────╯ │                               │
	 *            │          │   ╭───╮    │                               │
	 *            │          └───┤ , ├────┘                               │
	 *            │              ╰───╯                                    │
	 *            │  ╭─────╮   ╭────────╮                                 │
	 *            ├──┤ ALL ├───┤ SCRIPT ├─┬───────────────────────────────┤
	 *            │  ╰─────╯ ^ ╰────────╯ │                               │
	 *            │          │   ╭───╮    │                               │
	 *            │          └───┤ , ├────┘                               │
	 *            │              ╰───╯                                    │
	 *            │  ╭──────╮ ╭──────────╮ ┏───┓ ╭──────╮   ╭────────╮    │
	 *            └──┤ SOME ├─┤ AT_LEAST ├─┤ n ├─┤ FROM ├───┤ SCRIPT ├─┬──┘
	 *               ╰──────╯ ╰──────────╯ ┗───┛ ╰──────╯ ^ ╰────────╯ │
	 *                                                    │   ╭───╮    │
	 *                                                    └───┤ , ├────┘
	 *                                                        ╰───╯
	 *   }</pre>
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostAnyAddressRequest}
	 * @return the server response as an instance of {@link PostAnyAddressResponse}
	 */
	public PostAnyAddressResponse postAnyAddress(PostAnyAddressRequest requestBody){
		return executeSync(api.postAnyAddress(Utils.createRequestBody(requestBody)));
	}

	/**
	 * 
	 * Overwrite current settings.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PutSettingsRequest}
	 */
	public void putSettings(PutSettingsRequest requestBody){
		executeSync(api.putSettings(Utils.createRequestBody(requestBody)));
	}

	/**
	 * 
	 * Return the current settings.
	 * {@code status: stable}
	 * 
	 * @return the server response as an instance of {@link GetSettingsResponse}
	 */
	public GetSettingsResponse getSettings(){
		return executeSync(api.getSettings());
	}

	/**Get health status of the currently active SMASH server.
	 * @param url the base SMASH uri without endpoint path. (optional).
	 * - Pattern: {@code ^https?:\/\/[a-zA-Z0-9-_~.]+(:[0-9]+)?/?$}.
	 * 
	 * - Example: 
	 *   <pre>{@code https://smash.cardano-mainnet.iohk.io/}</pre>
	 * @return the server response as an instance of {@link GetCurrentSmashHealthResponse}
	 */
	public GetCurrentSmashHealthResponse getCurrentSmashHealth(String url){
		return executeSync(api.getCurrentSmashHealth(url));
	}
}