package com.univocity.cardano.wallet.api.generated;

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
import okhttp3.*;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.*;


/**
 * Interface automatically generated for all endpoints available from the
 * Cardano Wallet Backend API:
 *
 * https://input-output-hk.github.io/cardano-wallet/api/edge/
 */
public interface InternalWalletApiService {


	/**
	 * 
	 * Create and restore a wallet from a mnemonic sentence or account public key.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostWalletRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostWalletResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/wallets")
	Call<PostWalletResponse> postWallet(@Body RequestBody requestBody);


	/**
	 * 
	 * Return a list of known wallets, ordered from oldest to newest.
	 * {@code status: stable}
	 * 
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListWalletsResponse}
	 */
	@GET("/wallets")
	Call<ListWalletsResponse> listWallets();


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
	@GET("/wallets/{walletId}/statistics/utxos")
	Call<GetUTxOsStatisticsResponse> getUTxOsStatistics(@Path("walletId") String walletId);


	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetWalletResponse}
	 */
	@GET("/wallets/{walletId}")
	Call<GetWalletResponse> getWallet(@Path("walletId") String walletId);


	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} which is used as a handle for this HTTP request. No response body is expected.
	 */
	@DELETE("/wallets/{walletId}")
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
	@PUT("/wallets/{walletId}")
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
	@PUT("/wallets/{walletId}/passphrase")
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
	 * @param requestBody a request body containing the json representation of {@link PostTransactionFeeRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostTransactionFeeResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/wallets/{walletId}/payment-fees")
	Call<PostTransactionFeeResponse> postTransactionFee(@Path("walletId") String walletId, @Body RequestBody requestBody);


	/**
	 * 
	 * Create and send transaction from the wallet.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link PostTransactionRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostTransactionResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/wallets/{walletId}/transactions")
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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListTransactionsResponse}
	 */
	@GET("/wallets/{walletId}/transactions")
	Call<ListTransactionsResponse> listTransactions(@Path("walletId") String walletId, @Query("start") String start, @Query("end") String end, @Query("order") String order, @Query("minWithdrawal") Integer minWithdrawal);


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
	@GET("/wallets/{walletId}/transactions/{transactionId}")
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
	@DELETE("/wallets/{walletId}/transactions/{transactionId}")
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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListAddressesResponse}
	 */
	@GET("/wallets/{walletId}/addresses")
	Call<ListAddressesResponse> listAddresses(@Path("walletId") String walletId, @Query("state") String state);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListStakePoolsResponse}
	 */
	@GET("/stake-pools")
	Call<ListStakePoolsResponse> listStakePools(@Query("stake") Integer stake);


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
	@GET("/wallets/{walletId}/delegation-fees")
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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link QuitStakePoolResponse}
	 */
	@Headers("Content-Type: application/json")
	@DELETE("/stake-pools/*/wallets/{walletId}")
	Call<QuitStakePoolResponse> quitStakePool(@Path("walletId") String walletId);


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
	@PUT("/stake-pools/{stakePoolId}/wallets/{walletId}")
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
	 * @param requestBody a request body containing the json representation of {@link SelectCoinsRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link SelectCoinsResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/wallets/{walletId}/coin-selections/random")
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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link MigrateShelleyWalletResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/wallets/{walletId}/migrations")
	Call<MigrateShelleyWalletResponse> migrateShelleyWallet(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	@GET("/wallets/{walletId}/migrations")
	Call<GetShelleyWalletMigrationInfoResponse> getShelleyWalletMigrationInfo(@Path("walletId") String walletId);


	/**
	 * 
	 * Restore a Byron wallet from a mnemonic sentence or encrypted root private key.
	 * {@code status: stable}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostByronWalletRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostByronWalletResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/byron-wallets")
	Call<PostByronWalletResponse> postByronWallet(@Body RequestBody requestBody);


	/**
	 * 
	 * Return a list of known Byron wallets, ordered from oldest to newest.
	 * {@code status: stable}
	 * 
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListByronWalletsResponse}
	 */
	@GET("/byron-wallets")
	Call<ListByronWalletsResponse> listByronWallets();


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
	@GET("/byron-wallets/{walletId}/statistics/utxos")
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
	@GET("/byron-wallets/{walletId}")
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
	@DELETE("/byron-wallets/{walletId}")
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
	@PUT("/byron-wallets/{walletId}")
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
	@PUT("/byron-wallets/{walletId}/passphrase")
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
	@POST("/byron-wallets/{walletId}/addresses")
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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListByronAddressesResponse}
	 */
	@GET("/byron-wallets/{walletId}/addresses")
	Call<ListByronAddressesResponse> listByronAddresses(@Path("walletId") String walletId, @Query("state") String state);


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
	@PUT("/byron-wallets/{walletId}/addresses")
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
	@PUT("/byron-wallets/{walletId}/addresses/{addressId}")
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
	@POST("/byron-wallets/{walletId}/payment-fees")
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
	@POST("/byron-wallets/{walletId}/transactions")
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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListByronTransactionsResponse}
	 */
	@GET("/byron-wallets/{walletId}/transactions")
	Call<ListByronTransactionsResponse> listByronTransactions(@Path("walletId") String walletId, @Query("start") String start, @Query("end") String end, @Query("order") String order);


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
	@GET("/byron-wallets/{walletId}/transactions/{transactionId}")
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
	@DELETE("/byron-wallets/{walletId}/transactions/{transactionId}")
	Call<Void> deleteByronTransaction(@Path("walletId") String walletId, @Path("transactionId") String transactionId);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ByronSelectCoinsResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/byron-wallets/{walletId}/coin-selections/random")
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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link MigrateByronWalletResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/byron-wallets/{walletId}/migrations")
	Call<MigrateByronWalletResponse> migrateByronWallet(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	@GET("/byron-wallets/{walletId}/migrations")
	Call<GetByronWalletMigrationInfoResponse> getByronWalletMigrationInfo(@Path("walletId") String walletId);


	/**
	 * status: stable
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetNetworkInformationResponse}
	 */
	@GET("/network/information")
	Call<GetNetworkInformationResponse> getNetworkInformation();


	/**
	 * status: stable
	 * @param forceNtpCheck the forceNtpCheck.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetNetworkClockResponse}
	 */
	@GET("/network/clock")
	Call<GetNetworkClockResponse> getNetworkClock(@Query("forceNtpCheck") Boolean forceNtpCheck);


	/**
	 * 
	 * Returns the set of network parameters for the current epoch.
	 * {@code status: stable}
	 * 
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetNetworkParametersResponse}
	 */
	@GET("/network/parameters")
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
	@POST("/proxy/transactions")
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
	@GET("/addresses/{addressId}")
	Call<InspectAddressResponse> inspectAddress(@Path("addressId") String addressId);

}