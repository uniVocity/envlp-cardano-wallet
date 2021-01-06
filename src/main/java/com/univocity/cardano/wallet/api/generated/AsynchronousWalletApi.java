package com.univocity.cardano.wallet.api.generated;

import java.math.*;
import com.univocity.cardano.wallet.api.service.*;
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
 * Asynchronous methods automatically generated for all endpoints available from the
 * Cardano Wallet Backend API:
 *
 * https://input-output-hk.github.io/cardano-wallet/api/edge/
 */
public class AsynchronousWalletApi {

	private final InternalWalletApiService api;

	public AsynchronousWalletApi(InternalWalletApiService api) {
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link SignMetadataResponse}
	 */
	public void signMetadata(String walletId, String role, String index, SignMetadataRequest requestBody, WalletApiCallback<SignMetadataResponse> callback){
		api.signMetadata(walletId, role, index, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Create and restore a wallet from a mnemonic sentence or account public key.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostWalletShelleyRequest}
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostWalletResponse}
	 */
	public void postWallet(PostWalletShelleyRequest requestBody, WalletApiCallback<PostWalletResponse> callback){
		api.postWallet(Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Create and restore a wallet from a mnemonic sentence or account public key.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostWalletShelleyFromXpubRequest}
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostWalletResponse}
	 */
	public void postWallet(PostWalletShelleyFromXpubRequest requestBody, WalletApiCallback<PostWalletResponse> callback){
		api.postWallet(Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Return a list of known wallets, ordered from oldest to newest.
	 * {@code status: stable}
	 * 
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ListWalletsResponseItem}
	 */
	public void listWallets(WalletApiCallback<List<ListWalletsResponseItem>> callback){
		api.listWallets().enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetUTxOsStatisticsResponse}
	 */
	public void getUTxOsStatistics(String walletId, WalletApiCallback<GetUTxOsStatisticsResponse> callback){
		api.getUTxOsStatistics(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetWalletResponse}
	 */
	public void getWallet(String walletId, WalletApiCallback<GetWalletResponse> callback){
		api.getWallet(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param callback code to be executed once a response is available. No response body is expected.
	 */
	public void deleteWallet(String walletId, WalletApiCallback<Void> callback){
		api.deleteWallet(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PutWalletRequest}
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PutWalletResponse}
	 */
	public void putWallet(String walletId, PutWalletRequest requestBody, WalletApiCallback<PutWalletResponse> callback){
		api.putWallet(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PutWalletPassphraseRequest}
	 * @param callback code to be executed once a response is available. No response body is expected.
	 */
	public void putWalletPassphrase(String walletId, PutWalletPassphraseRequest requestBody, WalletApiCallback<Void> callback){
		api.putWalletPassphrase(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostTransactionFeeResponse}
	 */
	public void postTransactionFee(String walletId, PostTransactionFeePaymentRequest requestBody, WalletApiCallback<PostTransactionFeeResponse> callback){
		api.postTransactionFee(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostTransactionFeeResponse}
	 */
	public void postTransactionFee(String walletId, PostTransactionFeeRedemptionRequest requestBody, WalletApiCallback<PostTransactionFeeResponse> callback){
		api.postTransactionFee(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostTransactionResponse}
	 */
	public void postTransaction(String walletId, PostTransactionPaymentRequest requestBody, WalletApiCallback<PostTransactionResponse> callback){
		api.postTransaction(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostTransactionResponse}
	 */
	public void postTransaction(String walletId, PostTransactionRedemptionRequest requestBody, WalletApiCallback<PostTransactionResponse> callback){
		api.postTransaction(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ListTransactionsResponseItem}
	 */
	public void listTransactions(String walletId, String start, String end, String order, BigInteger minWithdrawal, WalletApiCallback<List<ListTransactionsResponseItem>> callback){
		api.listTransactions(walletId, start, end, order, minWithdrawal).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetTransactionResponse}
	 */
	public void getTransaction(String walletId, String transactionId, WalletApiCallback<GetTransactionResponse> callback){
		api.getTransaction(walletId, transactionId).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. No response body is expected.
	 */
	public void deleteTransaction(String walletId, String transactionId, WalletApiCallback<Void> callback){
		api.deleteTransaction(walletId, transactionId).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ListAddressesResponseItem}
	 */
	public void listAddresses(String walletId, String state, WalletApiCallback<List<ListAddressesResponseItem>> callback){
		api.listAddresses(walletId, state).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetWalletKeyResponse}
	 */
	public void getWalletKey(String walletId, String role, String index, WalletApiCallback<GetWalletKeyResponse> callback){
		api.getWalletKey(walletId, role, index).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ListStakePoolsResponseItem}
	 */
	public void listStakePools(Long stake, WalletApiCallback<List<ListStakePoolsResponseItem>> callback){
		api.listStakePools(stake).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**Returns the current status of the stake pools maintenance actions.
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetMaintenanceActionsResponse}
	 */
	public void getMaintenanceActions(WalletApiCallback<GetMaintenanceActionsResponse> callback){
		api.getMaintenanceActions().enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Performs maintenance actions on stake pools, such
	 * as triggering metadata garbage collection.
	 * Actions may not be instantaneous.
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostMaintenanceActionRequest}
	 * @param callback code to be executed once a response is available. No response body is expected.
	 */
	public void postMaintenanceAction(PostMaintenanceActionRequest requestBody, WalletApiCallback<Void> callback){
		api.postMaintenanceAction(Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetDelegationFeeResponse}
	 */
	public void getDelegationFee(String walletId, WalletApiCallback<GetDelegationFeeResponse> callback){
		api.getDelegationFee(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link QuitStakePoolResponse}
	 */
	public void quitStakePool(String walletId, QuitStakePoolRequest requestBody, WalletApiCallback<QuitStakePoolResponse> callback){
		api.quitStakePool(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link JoinStakePoolResponse}
	 */
	public void joinStakePool(String stakePoolId, String walletId, JoinStakePoolRequest requestBody, WalletApiCallback<JoinStakePoolResponse> callback){
		api.joinStakePool(stakePoolId, walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link SelectCoinsResponse}
	 */
	public void selectCoins(String walletId, SelectCoinsRequest requestBody, WalletApiCallback<SelectCoinsResponse> callback){
		api.selectCoins(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link MigrateShelleyWalletResponseItem}
	 */
	public void migrateShelleyWallet(String walletId, MigrateShelleyWalletRequest requestBody, WalletApiCallback<List<MigrateShelleyWalletResponseItem>> callback){
		api.migrateShelleyWallet(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetShelleyWalletMigrationInfoResponse}
	 */
	public void getShelleyWalletMigrationInfo(String walletId, WalletApiCallback<GetShelleyWalletMigrationInfoResponse> callback){
		api.getShelleyWalletMigrationInfo(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostByronWalletResponse}
	 */
	public void postByronWallet(PostByronWalletRandomRequest requestBody, WalletApiCallback<PostByronWalletResponse> callback){
		api.postByronWallet(Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostByronWalletResponse}
	 */
	public void postByronWallet(PostByronWalletIcarusRequest requestBody, WalletApiCallback<PostByronWalletResponse> callback){
		api.postByronWallet(Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostByronWalletResponse}
	 */
	public void postByronWallet(PostByronWalletTrezorRequest requestBody, WalletApiCallback<PostByronWalletResponse> callback){
		api.postByronWallet(Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostByronWalletResponse}
	 */
	public void postByronWallet(PostByronWalletLedgerRequest requestBody, WalletApiCallback<PostByronWalletResponse> callback){
		api.postByronWallet(Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostByronWalletResponse}
	 */
	public void postByronWallet(PostByronWalletIcarusTrezorLedgerFromXpubRequest requestBody, WalletApiCallback<PostByronWalletResponse> callback){
		api.postByronWallet(Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostByronWalletResponse}
	 */
	public void postByronWallet(PostByronWalletRandomFromXprvRequest requestBody, WalletApiCallback<PostByronWalletResponse> callback){
		api.postByronWallet(Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Return a list of known Byron wallets, ordered from oldest to newest.
	 * {@code status: stable}
	 * 
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ListByronWalletsResponseItem}
	 */
	public void listByronWallets(WalletApiCallback<List<ListByronWalletsResponseItem>> callback){
		api.listByronWallets().enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetByronUTxOsStatisticsResponse}
	 */
	public void getByronUTxOsStatistics(String walletId, WalletApiCallback<GetByronUTxOsStatisticsResponse> callback){
		api.getByronUTxOsStatistics(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Return information about a Byron wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetByronWalletResponse}
	 */
	public void getByronWallet(String walletId, WalletApiCallback<GetByronWalletResponse> callback){
		api.getByronWallet(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Delete a Byron wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param callback code to be executed once a response is available. No response body is expected.
	 */
	public void deleteByronWallet(String walletId, WalletApiCallback<Void> callback){
		api.deleteByronWallet(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PutByronWalletRequest}
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PutByronWalletResponse}
	 */
	public void putByronWallet(String walletId, PutByronWalletRequest requestBody, WalletApiCallback<PutByronWalletResponse> callback){
		api.putByronWallet(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PutByronWalletPassphraseRequest}
	 * @param callback code to be executed once a response is available. No response body is expected.
	 */
	public void putByronWalletPassphrase(String walletId, PutByronWalletPassphraseRequest requestBody, WalletApiCallback<Void> callback){
		api.putByronWalletPassphrase(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link CreateAddressResponse}
	 */
	public void createAddress(String walletId, CreateAddressRequest requestBody, WalletApiCallback<CreateAddressResponse> callback){
		api.createAddress(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ListByronAddressesResponseItem}
	 */
	public void listByronAddresses(String walletId, String state, WalletApiCallback<List<ListByronAddressesResponseItem>> callback){
		api.listByronAddresses(walletId, state).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. No response body is expected.
	 */
	public void importAddresses(String walletId, ImportAddressesRequest requestBody, WalletApiCallback<Void> callback){
		api.importAddresses(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. No response body is expected.
	 */
	public void importAddress(String walletId, String addressId, WalletApiCallback<Void> callback){
		api.importAddress(walletId, addressId).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostByronTransactionFeeResponse}
	 */
	public void postByronTransactionFee(String walletId, PostByronTransactionFeeRequest requestBody, WalletApiCallback<PostByronTransactionFeeResponse> callback){
		api.postByronTransactionFee(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostByronTransactionResponse}
	 */
	public void postByronTransaction(String walletId, PostByronTransactionRequest requestBody, WalletApiCallback<PostByronTransactionResponse> callback){
		api.postByronTransaction(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ListByronTransactionsResponseItem}
	 */
	public void listByronTransactions(String walletId, String start, String end, String order, WalletApiCallback<List<ListByronTransactionsResponseItem>> callback){
		api.listByronTransactions(walletId, start, end, order).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetByronTransactionResponse}
	 */
	public void getByronTransaction(String walletId, String transactionId, WalletApiCallback<GetByronTransactionResponse> callback){
		api.getByronTransaction(walletId, transactionId).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. No response body is expected.
	 */
	public void deleteByronTransaction(String walletId, String transactionId, WalletApiCallback<Void> callback){
		api.deleteByronTransaction(walletId, transactionId).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ByronSelectCoinsResponse}
	 */
	public void byronSelectCoins(String walletId, ByronSelectCoinsRequest requestBody, WalletApiCallback<ByronSelectCoinsResponse> callback){
		api.byronSelectCoins(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link MigrateByronWalletResponseItem}
	 */
	public void migrateByronWallet(String walletId, MigrateByronWalletRequest requestBody, WalletApiCallback<List<MigrateByronWalletResponseItem>> callback){
		api.migrateByronWallet(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetByronWalletMigrationInfoResponse}
	 */
	public void getByronWalletMigrationInfo(String walletId, WalletApiCallback<GetByronWalletMigrationInfoResponse> callback){
		api.getByronWalletMigrationInfo(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * status: stable
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetNetworkInformationResponse}
	 */
	public void getNetworkInformation(WalletApiCallback<GetNetworkInformationResponse> callback){
		api.getNetworkInformation().enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * status: stable
	 * @param forceNtpCheck the forceNtpCheck.
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetNetworkClockResponse}
	 */
	public void getNetworkClock(Boolean forceNtpCheck, WalletApiCallback<GetNetworkClockResponse> callback){
		api.getNetworkClock(forceNtpCheck).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Returns the set of network parameters for the current epoch.
	 * {@code status: stable}
	 * 
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetNetworkParametersResponse}
	 */
	public void getNetworkParameters(WalletApiCallback<GetNetworkParametersResponse> callback){
		api.getNetworkParameters().enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Submits a transaction that was created and signed outside of cardano-wallet.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing a {@code byte[]} loaded from a signed transaction message binary blob.
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostExternalTransactionResponse}
	 */
	public void postExternalTransaction(byte[] requestBody, WalletApiCallback<PostExternalTransactionResponse> callback){
		api.postExternalTransaction(Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link InspectAddressResponse}
	 */
	public void inspectAddress(String addressId, WalletApiCallback<InspectAddressResponse> callback){
		api.inspectAddress(addressId).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostAnyAddressResponse}
	 */
	public void postAnyAddress(PostAnyAddressRequest requestBody, WalletApiCallback<PostAnyAddressResponse> callback){
		api.postAnyAddress(Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Overwrite current settings.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PutSettingsRequest}
	 * @param callback code to be executed once a response is available. No response body is expected.
	 */
	public void putSettings(PutSettingsRequest requestBody, WalletApiCallback<Void> callback){
		api.putSettings(Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Return the current settings.
	 * {@code status: stable}
	 * 
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetSettingsResponse}
	 */
	public void getSettings(WalletApiCallback<GetSettingsResponse> callback){
		api.getSettings().enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**Get health status of the currently active SMASH server.
	 * @param url the base SMASH uri without endpoint path. (optional).
	 * - Pattern: {@code ^https?:\/\/[a-zA-Z0-9-_~.]+(:[0-9]+)?/?$}.
	 * 
	 * - Example: 
	 *   <pre>{@code https://smash.cardano-mainnet.iohk.io/}</pre>
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetCurrentSmashHealthResponse}
	 */
	public void getCurrentSmashHealth(String url, WalletApiCallback<GetCurrentSmashHealthResponse> callback){
		api.getCurrentSmashHealth(url).enqueue(new WalletApiCallbackAdapter<>(callback));
	}
}