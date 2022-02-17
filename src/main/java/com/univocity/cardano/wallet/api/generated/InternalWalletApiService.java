package com.univocity.cardano.wallet.api.generated;

import java.math.*;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.http.*;
import retrofit2.http.Headers;

import com.univocity.cardano.wallet.api.generated.addresses.*;
import com.univocity.cardano.wallet.api.generated.assets.*;
import com.univocity.cardano.wallet.api.generated.byronaddresses.*;
import com.univocity.cardano.wallet.api.generated.byronassets.*;
import com.univocity.cardano.wallet.api.generated.byroncoinselections.*;
import com.univocity.cardano.wallet.api.generated.byronmigrations.*;
import com.univocity.cardano.wallet.api.generated.byrontransactions.*;
import com.univocity.cardano.wallet.api.generated.byrontransactionsnew.*;
import com.univocity.cardano.wallet.api.generated.byronwallets.*;
import com.univocity.cardano.wallet.api.generated.coinselections.*;
import com.univocity.cardano.wallet.api.generated.experimental.*;
import com.univocity.cardano.wallet.api.generated.keys.*;
import com.univocity.cardano.wallet.api.generated.migrations.*;
import com.univocity.cardano.wallet.api.generated.network.*;
import com.univocity.cardano.wallet.api.generated.proxy.*;
import com.univocity.cardano.wallet.api.generated.settings.*;
import com.univocity.cardano.wallet.api.generated.sharedaddresses.*;
import com.univocity.cardano.wallet.api.generated.sharedkeys.*;
import com.univocity.cardano.wallet.api.generated.sharedwallets.*;
import com.univocity.cardano.wallet.api.generated.stakepools.*;
import com.univocity.cardano.wallet.api.generated.transactions.*;
import com.univocity.cardano.wallet.api.generated.transactionsnew.*;
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
	 * - Accepted values: {@code [utxo_external, utxo_internal, mutable_account]}.
	 * @param index the index.
	 * 
	 * An individual segment within a derivation path.
	 * The `H` suffix indicates a _Hardened_ child private key, which
	 * means that children of this key cannot be derived from the public
	 * key. Indices without a `H` suffix are called _Soft_.
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
	 * List all assets associated with the wallet, and their metadata
	 * if known.
	 * An asset is _associated_ with a wallet if it is involved in a
	 * transaction of the wallet.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListAssetsResponseItem}
	 */
	@GET("/v2/wallets/{walletId}/assets")
	Call<List<ListAssetsResponseItem>> listAssets(@Path("walletId") String walletId);


	/**
	 * 
	 * Mint and burn assets from the wallet.
	 * We only support the simplest of scripts: those which require a signature
	 * from a single key (known as the policy key). The policy key is generated
	 * from the HD wallet according to to draft CIP-1855
	 * (https://github.com/cardano-foundation/CIPs/blob/b2e9d02cb9a71ba9e754a432c78197428abf7e4c/CIP-1855/CIP-1855.md).
	 * Once the policy key is generated, cardano-wallet creates a script from
	 * that key, which we then mint or burn assets under.
	 * **⚠️ WARNING ⚠️**
	 * Please note that due to the fact that there is no physical access to
	 * policy keys under which assets are minted from the wallet it is
	 * currently not possible to add metadata of such assets into [Cardano Token Registry](https://github.com/cardano-foundation/cardano-token-registry).
	 * {@code status: under development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link MintBurnAssetsRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link MintBurnAssetsResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets/{walletId}/assets")
	Call<MintBurnAssetsResponse> mintBurnAssets(@Path("walletId") String walletId, @Body RequestBody requestBody);


	/**
	 * 
	 * Fetch a single asset from its `policy_id` and `asset_name`,
	 * with its metadata if any.
	 * The asset must be associated with the wallet.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param policyId the policyId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 56}.
	 * @param assetName the assetName.
	 * - Format: {@code hex}.
	 * - Maximum length: {@code 64}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetAssetResponse}
	 */
	@GET("/v2/wallets/{walletId}/assets/{policyId}/{assetName}")
	Call<GetAssetResponse> getAsset(@Path("walletId") String walletId, @Path("policyId") String policyId, @Path("assetName") String assetName);


	/**
	 * 
	 * Fetch the asset from `policy_id` with an empty name.
	 * The asset must be associated with the wallet.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param policyId the policyId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 56}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetAssetDefaultResponse}
	 */
	@GET("/v2/wallets/{walletId}/assets/{policyId}")
	Call<GetAssetDefaultResponse> getAssetDefault(@Path("walletId") String walletId, @Path("policyId") String policyId);


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
	 * 
	 * Generate a snapshot of the wallet's UTxO set.
	 * This endpoint is intended for debugging purposes.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetWalletUtxoSnapshotResponse}
	 */
	@GET("/v2/wallets/{walletId}/utxo")
	Call<GetWalletUtxoSnapshotResponse> getWalletUtxoSnapshot(@Path("walletId") String walletId);


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
	 * Create a transaction to be signed from the wallet.
	 * {@code status: under development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link ConstructTransactionRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ConstructTransactionResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets/{walletId}/transactions-construct")
	Call<ConstructTransactionResponse> constructTransaction(@Path("walletId") String walletId, @Body RequestBody requestBody);


	/**
	 * 
	 * Signs a serialised transaction, returning the modified
	 * transaction.
	 * This endpoint will add new witnesses using the keys available
	 * to this wallet. Any existing witnesses will remain in the
	 * witness set.
	 * {@code status: under development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link SignTransactionRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link SignTransactionResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets/{walletId}/transactions-sign")
	Call<SignTransactionResponse> signTransaction(@Path("walletId") String walletId, @Body RequestBody requestBody);


	/**
	 * 
	 * Decode a serialized transaction.
	 * {@code status: unstable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link DecodeTransactionRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link DecodeTransactionResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets/{walletId}/transactions-decode")
	Call<DecodeTransactionResponse> decodeTransaction(@Path("walletId") String walletId, @Body RequestBody requestBody);


	/**
	 * 
	 * Submit a transaction that was already created and signed.
	 * Fails for foreign transactions that is transactions which lack
	 * the wallet's inputs and withdrawals.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link SubmitTransactionRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link SubmitTransactionResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets/{walletId}/transactions-submit")
	Call<SubmitTransactionResponse> submitTransaction(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	 * Derive an account public key for any account index. For this key derivation to be possible,
	 * the wallet must have been created from mnemonic.
	 * It is possible to use the optional `purpose` field to override that branch of the derivation path
	 * with different hardened derivation index. If that field is omitted, the default purpose
	 * for Cardano wallets (`1852H`) will be used.
	 * Note: Only _Hardened_ indexes are supported by this endpoint.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param index the index.
	 * 
	 * An individual segment within a derivation path.
	 * The `H` suffix indicates a _Hardened_ child private key, which
	 * means that children of this key cannot be derived from the public
	 * key. Indices without a `H` suffix are called _Soft_.
	 * 
	 * 
	 * - Example: 
	 *   <pre>{@code 1852H}</pre>
	 * @param requestBody a request body containing the json representation of {@link PostAccountKeyRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostAccountKeyResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets/{walletId}/keys/{index}")
	Call<PostAccountKeyResponse> postAccountKey(@Path("walletId") String walletId, @Path("index") String index, @Body RequestBody requestBody);


	/**
	 * 
	 * Retrieve the account public key of this wallet.
	 * To get an extended public key, instead of the public key,
	 * use query parameter `format=extended`. For non-extended public key
	 * use `format=non_extended` or omit query parameter.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetAccountKeyResponse}
	 */
	@GET("/v2/wallets/{walletId}/keys")
	Call<GetAccountKeyResponse> getAccountKey(@Path("walletId") String walletId);


	/**
	 * 
	 * Return a public key for a given role and derivation index.
	 * To get a hash of the public key, instead of the public key,
	 * use query parameter `hash=true`.
	 * Note: Only `Soft` indexes are supported by this endpoint.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param role the role.
	 * - Accepted values: {@code [utxo_external, utxo_internal, mutable_account]}.
	 * @param index the index.
	 * 
	 * An individual segment within a derivation path.
	 * The `H` suffix indicates a _Hardened_ child private key, which
	 * means that children of this key cannot be derived from the public
	 * key. Indices without a `H` suffix are called _Soft_.
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
	 * List stake-keys relevant to the wallet, and how much ada is associated with each.
	 * {@code status: Experimental}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListStakeKeysResponse}
	 */
	@GET("/v2/wallets/{walletId}/stake-keys")
	Call<ListStakeKeysResponse> listStakeKeys(@Path("walletId") String walletId);


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
	 * Any current rewards will automatically withdrawn.
	 * > ⚠️  Disclaimer ⚠️
	 * >
	 * > This endpoint historically use to take a stake pool id as a path parameter.
	 * > However, retiring from delegation is ubiquitous and not tied to a particular
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
	 * @param requestBody a request body containing the json representation of {@link SelectCoinsRewardRedemptionRequest}, {@link SelectCoinsDelegationRequest} or {@link SelectCoinsNormalPaymentRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link SelectCoinsResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets/{walletId}/coin-selections/random")
	Call<SelectCoinsResponse> selectCoins(@Path("walletId") String walletId, @Body RequestBody requestBody);


	/**
	 * 
	 * Migrate the UTxO balance of this wallet to the given set of addresses.
	 * This operation will attempt to transfer as much of the wallet's balance
	 * as possible to the given set of addresses, by creating and submitting
	 * as many transactions as may be necessary to migrate the entire balance.
	 * In order to minimize the total transaction fee required, UTxO entries
	 * are coalesced together to the greatest extent possible in the resulting
	 * transactions. No attempt is made to preserve the wallet's UTxO
	 * distribution.
	 * This operation is performed on a best-effort basis. If there is
	 * insufficient ada available to pay for the entire UTxO set to be
	 * migrated, then only a subset of the wallet's UTxO set will be migrated.
	 * A typical use of this operation would be to move all funds from an old
	 * wallet to a new wallet, by providing addresses that belong to the new
	 * wallet.
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
	 * Generate a plan for migrating the UTxO balance of this wallet to
	 * another wallet, without executing the plan.
	 * This operation generates a plan that transfers as much of the wallet's
	 * balance as possible, by creating as many selections as may be necessary
	 * to migrate the entire balance.  Each selection created is the basis for
	 * a transaction.
	 * In order to minimize the total transaction fee required, UTxO entries
	 * are coalesced together to the greatest extent possible in the resulting
	 * selections. No attempt is made to preserve the wallet's UTxO
	 * distribution.
	 * The plan is generated on a best-effort basis. If there is insufficient
	 * ada available to pay for the entire UTxO set to be migrated, then only
	 * a subset of the wallet's UTxO set will be included in the resultant
	 * plan.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link CreateShelleyWalletMigrationPlanRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link CreateShelleyWalletMigrationPlanResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets/{walletId}/migrations/plan")
	Call<CreateShelleyWalletMigrationPlanResponse> createShelleyWalletMigrationPlan(@Path("walletId") String walletId, @Body RequestBody requestBody);


	/**
	 * 
	 * Balance a transaction body of a given transaction, add needed inputs/outputs,
	 * so as the transaction can be signed from the wallet.
	 * {@code status: under development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link BalanceTransactionRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link BalanceTransactionResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/wallets/{walletId}/transactions-balance")
	Call<BalanceTransactionResponse> balanceTransaction(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	 * List all assets associated with the wallet, and their metadata
	 * if known.
	 * An asset is _associated_ with a wallet if it is involved in a
	 * transaction of the wallet.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListByronAssetsResponseItem}
	 */
	@GET("/v2/byron-wallets/{walletId}/assets")
	Call<List<ListByronAssetsResponseItem>> listByronAssets(@Path("walletId") String walletId);


	/**
	 * 
	 * Fetch a single asset from its `policy_id` and `asset_name`,
	 * with its metadata if any.
	 * The asset must be associated with the wallet.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param policyId the policyId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 56}.
	 * @param assetName the assetName.
	 * - Format: {@code hex}.
	 * - Maximum length: {@code 64}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetByronAssetResponse}
	 */
	@GET("/v2/byron-wallets/{walletId}/assets/{policyId}/{assetName}")
	Call<GetByronAssetResponse> getByronAsset(@Path("walletId") String walletId, @Path("policyId") String policyId, @Path("assetName") String assetName);


	/**
	 * 
	 * Fetch the asset from `policy_id` with an empty name.
	 * The asset must be associated with the wallet.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param policyId the policyId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 56}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetByronAssetDefaultResponse}
	 */
	@GET("/v2/byron-wallets/{walletId}/assets/{policyId}")
	Call<GetByronAssetDefaultResponse> getByronAssetDefault(@Path("walletId") String walletId, @Path("policyId") String policyId);


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
	 * Generate a snapshot of the wallet's UTxO set.
	 * This endpoint is intended for debugging purposes.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetByronWalletUtxoSnapshotResponse}
	 */
	@GET("/v2/byron-wallets/{walletId}/utxo")
	Call<GetByronWalletUtxoSnapshotResponse> getByronWalletUtxoSnapshot(@Path("walletId") String walletId);


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
	 * Create a transaction to be signed from the wallet.
	 * {@code status: unstable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link ConstructByronTransactionRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ConstructByronTransactionResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/byron-wallets/{walletId}/transactions-construct")
	Call<ConstructByronTransactionResponse> constructByronTransaction(@Path("walletId") String walletId, @Body RequestBody requestBody);


	/**
	 * 
	 * Signs a serialised transaction, returning the modified
	 * transaction.
	 * This endpoint will add new witnesses using the keys available
	 * to this wallet. Any existing witnesses will remain in the
	 * witness set.
	 * {@code status: under development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link SignByronTransactionRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link SignByronTransactionResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/byron-wallets/{walletId}/transactions-sign")
	Call<SignByronTransactionResponse> signByronTransaction(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	 * Migrate the UTxO balance of this wallet to the given set of addresses.
	 * This operation will attempt to transfer as much of the wallet's balance
	 * as possible to the given set of addresses, by creating and submitting
	 * as many transactions as may be necessary to migrate the entire balance.
	 * In order to minimize the total transaction fee required, UTxO entries
	 * are coalesced together to the greatest extent possible in the resulting
	 * transactions. No attempt is made to preserve the wallet's UTxO
	 * distribution.
	 * This operation is performed on a best-effort basis. If there is
	 * insufficient ada available to pay for the entire UTxO set to be
	 * migrated, then only a subset of the wallet's UTxO set will be migrated.
	 * A typical use of this operation would be to move all funds from an old
	 * wallet to a new wallet, by providing addresses that belong to the new
	 * wallet.
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
	 * Generate a plan for migrating the UTxO balance of this wallet to
	 * another wallet, without executing the plan.
	 * This operation generates a plan that transfers as much of the wallet's
	 * balance as possible, by creating as many selections as may be necessary
	 * to migrate the entire balance.  Each selection created is the basis for
	 * a transaction.
	 * In order to minimize the total transaction fee required, UTxO entries
	 * are coalesced together to the greatest extent possible in the resulting
	 * selections. No attempt is made to preserve the wallet's UTxO
	 * distribution.
	 * The plan is generated on a best-effort basis. If there is insufficient
	 * ada available to pay for the entire UTxO set to be migrated, then only
	 * a subset of the wallet's UTxO set will be included in the resultant
	 * plan.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link CreateByronWalletMigrationPlanRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link CreateByronWalletMigrationPlanResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/byron-wallets/{walletId}/migrations/plan")
	Call<CreateByronWalletMigrationPlanResponse> createByronWalletMigrationPlan(@Path("walletId") String walletId, @Body RequestBody requestBody);


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
	 * NOTE: Unlike the `submitTransaction` endpoint, there are no
	 * guarantees that a transaction accepted by this endpoint will
	 * actually be included in the chain. It's up to the caller to
	 * retry submission until the transaction is confirmed.
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
	 * Construct any address by specyfying credential for payment or delegation.
	 * In Cardano, Addresses are made of three parts:
	 *   <pre>{@code 
	 * *---------*---------*-----------*
	 * | NETWORK | PAYMENT | DELEGATION |
	 * *---------*---------*-----------*
	 *   }</pre>
	 * The `NETWORK` part allows for distinguishing addresses between different networks like the mainnet or the testnet. It is implicitly
	 * handled by the server without you having to worry about it. The `PAYMENT` and `DELEGATION` parts however can be constructed similarly, using
	 * either:
	 * - A public key
	 * - A script
	 * The script itself is either constructed out of a public key, one of two timelocks, or one of the three following primitives:
	 * - all
	 * - any
	 * - some
	 * The timelock can determine validity as respect to the slot. `active_from slot` means the script is valid from the specified slot
	 * and onward. `active_until slot` means the script is valid until (not included) the specified slot.
	 * Each of which contains one or more script(s) that can be either keys or primitives, and so on. Schematically:
	 *   <pre>{@code 
	 *                                    ┏─────────┓
	 * SCRIPT = ──┬───────────────────────┤ pub key ├─────────────────────┬──
	 *            │                       ┗─────────┛                     │
	 *            │                       ┏──────────────────┓            │
	 *            ├───────────────────────┤ ACTIVE_FROM slot ├──── ───────┤
	 *            │                       ┗──────────────────┛            │
	 *            │                       ┏───────────────────┓           │
	 *            ├───────────────────────┤ ACTIVE_UNTIL slot ├───────────┤
	 *            │                       ┗───────────────────┛           │
	 *            │                                                       │
	 *            │  ╭─────╮   ╭────────╮                                 │
	 *            ├──┤ ALL ├───┤ SCRIPT ├─┬───────────────────────────────┤
	 *            │  ╰─────╯ ^ ╰────────╯ │                               │
	 *            │          │   ╭───╮    │                               │
	 *            │          └───┤ , ├────┘                               │
	 *            │              ╰───╯                                    │
	 *            │  ╭─────╮   ╭────────╮                                 │
	 *            ├──┤ ANY ├───┤ SCRIPT ├─┬───────────────────────────────┤
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


	/**
	 * 
	 * Create a shared wallet from either an account public key and script
	 * templates or mnemonic and script templates.
	 * {@code status: ⚠ under development}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostSharedWalletCreateSharedWalletFromAccountPublicKeyRequest} or {@link PostSharedWalletCreateSharedWalletFromMnemonicsRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostSharedWalletResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/shared-wallets")
	Call<PostSharedWalletResponse> postSharedWallet(@Body RequestBody requestBody);


	/**
	 * 
	 * Return a list of known shared wallets, ordered from oldest to newest.
	 * {@code status: ⚠ under development}
	 * 
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListSharedWalletsResponseItem}
	 */
	@GET("/v2/shared-wallets")
	Call<List<ListSharedWalletsResponseItem>> listSharedWallets();


	/**
	 * 
	 * Get a shared wallet for a given wallet id.
	 * {@code status: ⚠ under development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetSharedWalletResponse}
	 */
	@GET("/v2/shared-wallets/{walletId}")
	Call<GetSharedWalletResponse> getSharedWallet(@Path("walletId") String walletId);


	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} which is used as a handle for this HTTP request. No response body is expected.
	 */
	@DELETE("/v2/shared-wallets/{walletId}")
	Call<Void> deleteSharedWallet(@Path("walletId") String walletId);


	/**
	 * 
	 * Update payment script template for a given shared wallet by
	 * updating/adding account public key for cosigner. Updating the
	 * shared wallet account key results in an error. Also updating is
	 * enabled only for pending shared wallet, ie., the wallet that has
	 * a missing account public key for any cosigner.
	 * {@code status: ⚠ under development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PatchSharedWalletInPaymentResponse}
	 */
	@Headers("Content-Type: application/json")
	@PATCH("/v2/shared-wallets/{walletId}/payment-script-template")
	Call<PatchSharedWalletInPaymentResponse> patchSharedWalletInPayment(@Path("walletId") String walletId);


	/**
	 * 
	 * Update delegation script template for a given shared wallet by
	 * updating/adding account public key for cosigner. Updating the
	 * shared wallet account key results in an error. Also updating is
	 * enabled only for pending shared wallet, ie., the wallet that has
	 * a missing account public key for any cosigner.
	 * {@code status: ⚠ under development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PatchSharedWalletInDelegationResponse}
	 */
	@Headers("Content-Type: application/json")
	@PATCH("/v2/shared-wallets/{walletId}/delegation-script-template")
	Call<PatchSharedWalletInDelegationResponse> patchSharedWalletInDelegation(@Path("walletId") String walletId);


	/**
	 * 
	 * Derive an account public key for any account index. For this key derivation to be possible,
	 * the wallet must have been created from mnemonic.
	 * Note: Only _Hardened_ indexes are supported by this endpoint.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param index the index.
	 * 
	 * An individual segment within a derivation path.
	 * The `H` suffix indicates a _Hardened_ child private key, which
	 * means that children of this key cannot be derived from the public
	 * key. Indices without a `H` suffix are called _Soft_.
	 * 
	 * 
	 * - Example: 
	 *   <pre>{@code 1852H}</pre>
	 * @param requestBody a request body containing the json representation of {@link PostAccountKeySharedRequest}
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link PostAccountKeySharedResponse}
	 */
	@Headers("Content-Type: application/json")
	@POST("/v2/shared-wallets/{walletId}/keys/{index}")
	Call<PostAccountKeySharedResponse> postAccountKeyShared(@Path("walletId") String walletId, @Path("index") String index, @Body RequestBody requestBody);


	/**
	 * 
	 * Retrieve the account public key of this shared wallet.
	 * To get an extended public key, instead of the public key,
	 * use query parameter `format=extended`. For non-extended public key
	 * use `format=non_extended` or omit query parameter.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetAccountKeySharedResponse}
	 */
	@GET("/v2/shared-wallets/{walletId}/keys")
	Call<GetAccountKeySharedResponse> getAccountKeyShared(@Path("walletId") String walletId);


	/**
	 * 
	 * Return a public key for a given role and derivation index for
	 * a shared wallet.
	 * To get a hash of the public key, instead of the public key,
	 * use query parameter `hash=true`.
	 * Note: Only `Soft` indexes are supported by this endpoint.
	 * {@code status: stable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param role the role.
	 * - Accepted values: {@code [utxo_external, utxo_internal, mutable_account]}.
	 * @param index the index.
	 * 
	 * An individual segment within a derivation path.
	 * The `H` suffix indicates a _Hardened_ child private key, which
	 * means that children of this key cannot be derived from the public
	 * key. Indices without a `H` suffix are called _Soft_.
	 * 
	 * 
	 * - Example: 
	 *   <pre>{@code 1852H}</pre>
	 * @param hash the hash.
	 * - Defaults to: {@code false}.
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link GetSharedWalletKeyResponse}
	 */
	@GET("/v2/shared-wallets/{walletId}/keys/{role}/{index}")
	Call<GetSharedWalletKeyResponse> getSharedWalletKey(@Path("walletId") String walletId, @Path("role") String role, @Path("index") String index, @Query("hash") Boolean hash);


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
	 * @return a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ListSharedAddressesResponseItem}
	 */
	@GET("/v2/shared-wallets/{walletId}/addresses")
	Call<List<ListSharedAddressesResponseItem>> listSharedAddresses(@Path("walletId") String walletId, @Query("state") String state);

}