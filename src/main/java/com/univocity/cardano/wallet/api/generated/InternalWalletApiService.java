package com.univocity.cardano.wallet.api.generated;

import java.math.*;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.http.*;
import retrofit2.http.Headers;

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
 * Interface automatically generated for all endpoints available from the
 * Cardano Wallet Backend API:
 *
 * https://input-output-hk.github.io/cardano-wallet/api/edge/
 */
public interface InternalWalletApiService {


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link SignMetadataResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets/{walletId}/signatures/{role}/{index}")
	Call<SignMetadataResponse> signMetadata(@Path("walletId") String walletId, @Path("role") String role, @Path("index") String index, @Body RequestBody requestBody);


	/**
	 * 
	 * Create and restore a wallet from a mnemonic sentence or account public key.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostWalletShelleyFromXpubRequest} or {@link PostWalletShelleyRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostWalletResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets")
	Call<PostWalletResponse> postWallet(@Body RequestBody requestBody);


	/**
	 * 
	 * Return a list of known wallets, ordered from oldest to newest.
	 * {@code status: stable}
	 * 
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListWalletsResponseItem}
	 */
	@GET("/v2/wallets")
	Call<List<ListWalletsResponseItem>> listWallets();


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetUTxOsStatisticsResponse}
	 */
	@GET("/v2/wallets/{walletId}/statistics/utxos")
	Call<GetUTxOsStatisticsResponse> getUTxOsStatistics(@Path("walletId") String walletId);


	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetWalletResponse}
	 */
	@GET("/v2/wallets/{walletId}")
	Call<GetWalletResponse> getWallet(@Path("walletId") String walletId);


	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} which is used as a handle for this HTTP request. No response body is expected.
	 */
	@DELETE("/v2/wallets/{walletId}")
	Call<Void> deleteWallet(@Path("walletId") String walletId);


	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PutWalletRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PutWalletResponse}
	 */
	@Headers("Content-Type: application/json")
	@PUT("/v2/wallets/{walletId}")
	Call<PutWalletResponse> putWallet(@Path("walletId") String walletId, @Body RequestBody requestBody);


	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PutWalletPassphraseRequest}
	 * @return a Retrofit {@link Call} which is used as a handle for this HTTP request. No response body is expected.
	 */
	@Headers("Content-Type: application/json")
	@PUT("/v2/wallets/{walletId}/passphrase")
	Call<Void> putWalletPassphrase(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	 * @param requestBody a request body containing the json representation of {@link PostTransactionFeeRedemptionRequest} or {@link PostTransactionFeePaymentRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostTransactionFeeResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets/{walletId}/payment-fees")
	Call<PostTransactionFeeResponse> postTransactionFee(@Path("walletId") String walletId, @Body RequestBody requestBody);


	/**
	 * 
	 * Create and send transaction from the wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PostTransactionRedemptionRequest} or {@link PostTransactionPaymentRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostTransactionResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets/{walletId}/transactions")
	Call<PostTransactionResponse> postTransaction(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListTransactionsResponseItem}
	 */
	@GET("/v2/wallets/{walletId}/transactions")
	Call<List<ListTransactionsResponseItem>> listTransactions(@Path("walletId") String walletId, @Query("start") String start, @Query("end") String end, @Query("order") String order, @Query("minWithdrawal") BigInteger minWithdrawal);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetTransactionResponse}
	 */
	@GET("/v2/wallets/{walletId}/transactions/{transactionId}")
	Call<GetTransactionResponse> getTransaction(@Path("walletId") String walletId, @Path("transactionId") String transactionId);


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
	 * @return a Retrofit {@link Call} which is used as a handle for this HTTP request. No response body is expected.
	 */
	@DELETE("/v2/wallets/{walletId}/transactions/{transactionId}")
	Call<Void> deleteTransaction(@Path("walletId") String walletId, @Path("transactionId") String transactionId);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListAddressesResponseItem}
	 */
	@GET("/v2/wallets/{walletId}/addresses")
	Call<List<ListAddressesResponseItem>> listAddresses(@Path("walletId") String walletId, @Query("state") String state);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetWalletKeyResponse}
	 */
	@GET("/v2/wallets/{walletId}/keys/{role}/{index}")
	Call<GetWalletKeyResponse> getWalletKey(@Path("walletId") String walletId, @Path("role") String role, @Path("index") String index);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListStakePoolsResponseItem}
	 */
	@GET("/v2/stake-pools")
	Call<List<ListStakePoolsResponseItem>> listStakePools(@Query("stake") Long stake);


	/**Returns the current status of the stake pools maintenance actions.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetMaintenanceActionsResponse}
	 */
	@GET("/v2/stake-pools/maintenance-actions")
	Call<GetMaintenanceActionsResponse> getMaintenanceActions();


	/**
	 * 
	 * Performs maintenance actions on stake pools, such
	 * as triggering metadata garbage collection.
	 * Actions may not be instantaneous.
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostMaintenanceActionRequest}
	 * @return a Retrofit {@link Call} which is used as a handle for this HTTP request. No response body is expected.
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/stake-pools/maintenance-actions")
	Call<Void> postMaintenanceAction(@Body RequestBody requestBody);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetDelegationFeeResponse}
	 */
	@GET("/v2/wallets/{walletId}/delegation-fees")
	Call<GetDelegationFeeResponse> getDelegationFee(@Path("walletId") String walletId);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link QuitStakePoolResponse}
	 */
	@Headers("Content-Type: application/json")
	@HTTP(method = "DELETE",path="/v2/stake-pools/*/wallets/{walletId}", hasBody = true)
	Call<QuitStakePoolResponse> quitStakePool(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link JoinStakePoolResponse}
	 */
	@Headers("Content-Type: application/json")
	@PUT("/v2/stake-pools/{stakePoolId}/wallets/{walletId}")
	Call<JoinStakePoolResponse> joinStakePool(@Path("stakePoolId") String stakePoolId, @Path("walletId") String walletId, @Body RequestBody requestBody);


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
	 * @param requestBody a request body containing the json representation of {@link SelectCoinsRequest} or {@link SelectCoinsRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link SelectCoinsResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets/{walletId}/coin-selections/random")
	Call<SelectCoinsResponse> selectCoins(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link MigrateShelleyWalletResponseItem}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets/{walletId}/migrations")
	Call<List<MigrateShelleyWalletResponseItem>> migrateShelleyWallet(@Path("walletId") String walletId, @Body RequestBody requestBody);


	/**
	 * 
	 * Calculate the exact cost of sending all funds from particular Shelley wallet
	 * to a set of addresses.
	 * {@code status: in development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetShelleyWalletMigrationInfoResponse}
	 */
	@GET("/v2/wallets/{walletId}/migrations")
	Call<GetShelleyWalletMigrationInfoResponse> getShelleyWalletMigrationInfo(@Path("walletId") String walletId);


	/**
	 * 
	 * Restore a Byron wallet from a mnemonic sentence or encrypted root private key (deprecated).
	 *   **⚠️ WARNING ⚠️**
	 *   The construction of random wallet in itself is **deprecated**, in particular the restoration from an encrypted root private key.
	 *   These endpoints exist to ease migrations from legacy software such as `cardano-sl` but should be avoided by new applications.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostByronWalletRandomFromXprvRequest}, {@link PostByronWalletIcarusTrezorLedgerFromXpubRequest}, {@link PostByronWalletLedgerRequest}, {@link PostByronWalletTrezorRequest}, {@link PostByronWalletIcarusRequest} or {@link PostByronWalletRandomRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostByronWalletResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/byron-wallets")
	Call<PostByronWalletResponse> postByronWallet(@Body RequestBody requestBody);


	/**
	 * 
	 * Return a list of known Byron wallets, ordered from oldest to newest.
	 * {@code status: stable}
	 * 
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListByronWalletsResponseItem}
	 */
	@GET("/v2/byron-wallets")
	Call<List<ListByronWalletsResponseItem>> listByronWallets();


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetByronUTxOsStatisticsResponse}
	 */
	@GET("/v2/byron-wallets/{walletId}/statistics/utxos")
	Call<GetByronUTxOsStatisticsResponse> getByronUTxOsStatistics(@Path("walletId") String walletId);


	/**
	 * 
	 * Return information about a Byron wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetByronWalletResponse}
	 */
	@GET("/v2/byron-wallets/{walletId}")
	Call<GetByronWalletResponse> getByronWallet(@Path("walletId") String walletId);


	/**
	 * 
	 * Delete a Byron wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} which is used as a handle for this HTTP request. No response body is expected.
	 */
	@DELETE("/v2/byron-wallets/{walletId}")
	Call<Void> deleteByronWallet(@Path("walletId") String walletId);


	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PutByronWalletRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PutByronWalletResponse}
	 */
	@Headers("Content-Type: application/json")
	@PUT("/v2/byron-wallets/{walletId}")
	Call<PutByronWalletResponse> putByronWallet(@Path("walletId") String walletId, @Body RequestBody requestBody);


	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PutByronWalletPassphraseRequest}
	 * @return a Retrofit {@link Call} which is used as a handle for this HTTP request. No response body is expected.
	 */
	@Headers("Content-Type: application/json")
	@PUT("/v2/byron-wallets/{walletId}/passphrase")
	Call<Void> putByronWalletPassphrase(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link CreateAddressResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/byron-wallets/{walletId}/addresses")
	Call<CreateAddressResponse> createAddress(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListByronAddressesResponseItem}
	 */
	@GET("/v2/byron-wallets/{walletId}/addresses")
	Call<List<ListByronAddressesResponseItem>> listByronAddresses(@Path("walletId") String walletId, @Query("state") String state);


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
	 * @return a Retrofit {@link Call} which is used as a handle for this HTTP request. No response body is expected.
	 */
	@Headers("Content-Type: application/json")
	@PUT("/v2/byron-wallets/{walletId}/addresses")
	Call<Void> importAddresses(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	 * @return a Retrofit {@link Call} which is used as a handle for this HTTP request. No response body is expected.
	 */
	@PUT("/v2/byron-wallets/{walletId}/addresses/{addressId}")
	Call<Void> importAddress(@Path("walletId") String walletId, @Path("addressId") String addressId);


	/**
	 * 
	 * Estimate fee for the transaction.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PostByronTransactionFeeRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostByronTransactionFeeResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/byron-wallets/{walletId}/payment-fees")
	Call<PostByronTransactionFeeResponse> postByronTransactionFee(@Path("walletId") String walletId, @Body RequestBody requestBody);


	/**
	 * 
	 * Create and send transaction from the wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PostByronTransactionRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostByronTransactionResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/byron-wallets/{walletId}/transactions")
	Call<PostByronTransactionResponse> postByronTransaction(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListByronTransactionsResponseItem}
	 */
	@GET("/v2/byron-wallets/{walletId}/transactions")
	Call<List<ListByronTransactionsResponseItem>> listByronTransactions(@Path("walletId") String walletId, @Query("start") String start, @Query("end") String end, @Query("order") String order);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetByronTransactionResponse}
	 */
	@GET("/v2/byron-wallets/{walletId}/transactions/{transactionId}")
	Call<GetByronTransactionResponse> getByronTransaction(@Path("walletId") String walletId, @Path("transactionId") String transactionId);


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
	 * @return a Retrofit {@link Call} which is used as a handle for this HTTP request. No response body is expected.
	 */
	@DELETE("/v2/byron-wallets/{walletId}/transactions/{transactionId}")
	Call<Void> deleteByronTransaction(@Path("walletId") String walletId, @Path("transactionId") String transactionId);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ByronSelectCoinsResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/byron-wallets/{walletId}/coin-selections/random")
	Call<ByronSelectCoinsResponse> byronSelectCoins(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link MigrateByronWalletResponseItem}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/byron-wallets/{walletId}/migrations")
	Call<List<MigrateByronWalletResponseItem>> migrateByronWallet(@Path("walletId") String walletId, @Body RequestBody requestBody);


	/**
	 * 
	 * Calculate the exact cost of sending all funds from particular Byron wallet to
	 * a set of addresses.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetByronWalletMigrationInfoResponse}
	 */
	@GET("/v2/byron-wallets/{walletId}/migrations")
	Call<GetByronWalletMigrationInfoResponse> getByronWalletMigrationInfo(@Path("walletId") String walletId);


	/**
	 * status: stable
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetNetworkInformationResponse}
	 */
	@GET("/v2/network/information")
	Call<GetNetworkInformationResponse> getNetworkInformation();


	/**
	 * status: stable
	 * @param forceNtpCheck the forceNtpCheck.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetNetworkClockResponse}
	 */
	@GET("/v2/network/clock")
	Call<GetNetworkClockResponse> getNetworkClock(@Query("forceNtpCheck") Boolean forceNtpCheck);


	/**
	 * 
	 * Returns the set of network parameters for the current epoch.
	 * {@code status: stable}
	 * 
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetNetworkParametersResponse}
	 */
	@GET("/v2/network/parameters")
	Call<GetNetworkParametersResponse> getNetworkParameters();


	/**
	 * 
	 * Submits a transaction that was created and signed outside of cardano-wallet.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing a {@code byte[]} loaded from a signed transaction message binary blob.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostExternalTransactionResponse}
	 */
	@Headers("Content-Type: application/octet-stream")
	@POST("/v2/proxy/transactions")
	Call<PostExternalTransactionResponse> postExternalTransaction(@Body RequestBody requestBody);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link InspectAddressResponse}
	 */
	@GET("/v2/addresses/{addressId}")
	Call<InspectAddressResponse> inspectAddress(@Path("addressId") String addressId);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostAnyAddressResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/addresses")
	Call<PostAnyAddressResponse> postAnyAddress(@Body RequestBody requestBody);


	/**
	 * 
	 * Overwrite current settings.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PutSettingsRequest}
	 * @return a Retrofit {@link Call} which is used as a handle for this HTTP request. No response body is expected.
	 */
	@Headers("Content-Type: application/json")
	@PUT("/v2/settings")
	Call<Void> putSettings(@Body RequestBody requestBody);


	/**
	 * 
	 * Return the current settings.
	 * {@code status: stable}
	 * 
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetSettingsResponse}
	 */
	@GET("/v2/settings")
	Call<GetSettingsResponse> getSettings();


	/**Get health status of the currently active SMASH server.
	 * @param url the base SMASH uri without endpoint path. (optional).
	 * - Pattern: {@code ^https?:\/\/[a-zA-Z0-9-_~.]+(:[0-9]+)?/?$}.
	 * 
	 * - Example: 
	 *   <pre>{@code https://smash.cardano-mainnet.iohk.io/}</pre>
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetCurrentSmashHealthResponse}
	 */
	@GET("/v2/smash/health")
	Call<GetCurrentSmashHealthResponse> getCurrentSmashHealth(@Query("url") String url);

}