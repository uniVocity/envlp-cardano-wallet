package com.univocity.cardano.wallet.api.generated;

import java.math.*;
import com.univocity.cardano.wallet.api.service.*;
import com.univocity.cardano.wallet.common.*;

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
	 * List all assets associated with the wallet, and their metadata
	 * if known.
	 * An asset is _associated_ with a wallet if it is involved in a
	 * transaction of the wallet.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ListAssetsResponseItem}
	 */
	public void listAssets(String walletId, WalletApiCallback<List<ListAssetsResponseItem>> callback){
		api.listAssets(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link MintBurnAssetsResponse}
	 */
	public void mintBurnAssets(String walletId, MintBurnAssetsRequest requestBody, WalletApiCallback<MintBurnAssetsResponse> callback){
		api.mintBurnAssets(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetAssetResponse}
	 */
	public void getAsset(String walletId, String policyId, String assetName, WalletApiCallback<GetAssetResponse> callback){
		api.getAsset(walletId, policyId, assetName).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetAssetDefaultResponse}
	 */
	public void getAssetDefault(String walletId, String policyId, WalletApiCallback<GetAssetDefaultResponse> callback){
		api.getAssetDefault(walletId, policyId).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * 
	 * Generate a snapshot of the wallet's UTxO set.
	 * This endpoint is intended for debugging purposes.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetWalletUtxoSnapshotResponse}
	 */
	public void getWalletUtxoSnapshot(String walletId, WalletApiCallback<GetWalletUtxoSnapshotResponse> callback){
		api.getWalletUtxoSnapshot(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * Create a transaction to be signed from the wallet.
	 * {@code status: under development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link ConstructTransactionRequest}
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ConstructTransactionResponse}
	 */
	public void constructTransaction(String walletId, ConstructTransactionRequest requestBody, WalletApiCallback<ConstructTransactionResponse> callback){
		api.constructTransaction(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link SignTransactionResponse}
	 */
	public void signTransaction(String walletId, SignTransactionRequest requestBody, WalletApiCallback<SignTransactionResponse> callback){
		api.signTransaction(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Decode a serialized transaction.
	 * {@code status: unstable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link DecodeTransactionRequest}
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link DecodeTransactionResponse}
	 */
	public void decodeTransaction(String walletId, DecodeTransactionRequest requestBody, WalletApiCallback<DecodeTransactionResponse> callback){
		api.decodeTransaction(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link SubmitTransactionResponse}
	 */
	public void submitTransaction(String walletId, SubmitTransactionRequest requestBody, WalletApiCallback<SubmitTransactionResponse> callback){
		api.submitTransaction(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostAccountKeyResponse}
	 */
	public void postAccountKey(String walletId, String index, PostAccountKeyRequest requestBody, WalletApiCallback<PostAccountKeyResponse> callback){
		api.postAccountKey(walletId, index, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetAccountKeyResponse}
	 */
	public void getAccountKey(String walletId, WalletApiCallback<GetAccountKeyResponse> callback){
		api.getAccountKey(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetWalletKeyResponse}
	 */
	public void getWalletKey(String walletId, String role, String index, WalletApiCallback<GetWalletKeyResponse> callback){
		api.getWalletKey(walletId, role, index).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * List stake-keys relevant to the wallet, and how much ada is associated with each.
	 * {@code status: Experimental}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ListStakeKeysResponse}
	 */
	public void listStakeKeys(String walletId, WalletApiCallback<ListStakeKeysResponse> callback){
		api.listStakeKeys(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param requestBody a request body containing the json representation of {@link SelectCoinsNormalPaymentRequest}
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link SelectCoinsResponse}
	 */
	public void selectCoins(String walletId, SelectCoinsNormalPaymentRequest requestBody, WalletApiCallback<SelectCoinsResponse> callback){
		api.selectCoins(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param requestBody a request body containing the json representation of {@link SelectCoinsDelegationRequest}
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link SelectCoinsResponse}
	 */
	public void selectCoins(String walletId, SelectCoinsDelegationRequest requestBody, WalletApiCallback<SelectCoinsResponse> callback){
		api.selectCoins(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param requestBody a request body containing the json representation of {@link SelectCoinsRewardRedemptionRequest}
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link SelectCoinsResponse}
	 */
	public void selectCoins(String walletId, SelectCoinsRewardRedemptionRequest requestBody, WalletApiCallback<SelectCoinsResponse> callback){
		api.selectCoins(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link MigrateShelleyWalletResponseItem}
	 */
	public void migrateShelleyWallet(String walletId, MigrateShelleyWalletRequest requestBody, WalletApiCallback<List<MigrateShelleyWalletResponseItem>> callback){
		api.migrateShelleyWallet(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link CreateShelleyWalletMigrationPlanResponse}
	 */
	public void createShelleyWalletMigrationPlan(String walletId, CreateShelleyWalletMigrationPlanRequest requestBody, WalletApiCallback<CreateShelleyWalletMigrationPlanResponse> callback){
		api.createShelleyWalletMigrationPlan(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link BalanceTransactionResponse}
	 */
	public void balanceTransaction(String walletId, BalanceTransactionRequest requestBody, WalletApiCallback<BalanceTransactionResponse> callback){
		api.balanceTransaction(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * List all assets associated with the wallet, and their metadata
	 * if known.
	 * An asset is _associated_ with a wallet if it is involved in a
	 * transaction of the wallet.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ListByronAssetsResponseItem}
	 */
	public void listByronAssets(String walletId, WalletApiCallback<List<ListByronAssetsResponseItem>> callback){
		api.listByronAssets(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetByronAssetResponse}
	 */
	public void getByronAsset(String walletId, String policyId, String assetName, WalletApiCallback<GetByronAssetResponse> callback){
		api.getByronAsset(walletId, policyId, assetName).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetByronAssetDefaultResponse}
	 */
	public void getByronAssetDefault(String walletId, String policyId, WalletApiCallback<GetByronAssetDefaultResponse> callback){
		api.getByronAssetDefault(walletId, policyId).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * Generate a snapshot of the wallet's UTxO set.
	 * This endpoint is intended for debugging purposes.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetByronWalletUtxoSnapshotResponse}
	 */
	public void getByronWalletUtxoSnapshot(String walletId, WalletApiCallback<GetByronWalletUtxoSnapshotResponse> callback){
		api.getByronWalletUtxoSnapshot(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * Create a transaction to be signed from the wallet.
	 * {@code status: unstable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link ConstructByronTransactionRequest}
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ConstructByronTransactionResponse}
	 */
	public void constructByronTransaction(String walletId, ConstructByronTransactionRequest requestBody, WalletApiCallback<ConstructByronTransactionResponse> callback){
		api.constructByronTransaction(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link SignByronTransactionResponse}
	 */
	public void signByronTransaction(String walletId, SignByronTransactionRequest requestBody, WalletApiCallback<SignByronTransactionResponse> callback){
		api.signByronTransaction(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link MigrateByronWalletResponseItem}
	 */
	public void migrateByronWallet(String walletId, MigrateByronWalletRequest requestBody, WalletApiCallback<List<MigrateByronWalletResponseItem>> callback){
		api.migrateByronWallet(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link CreateByronWalletMigrationPlanResponse}
	 */
	public void createByronWalletMigrationPlan(String walletId, CreateByronWalletMigrationPlanRequest requestBody, WalletApiCallback<CreateByronWalletMigrationPlanResponse> callback){
		api.createByronWalletMigrationPlan(walletId, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * NOTE: Unlike the `submitTransaction` endpoint, there are no
	 * guarantees that a transaction accepted by this endpoint will
	 * actually be included in the chain. It's up to the caller to
	 * retry submission until the transaction is confirmed.
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

	/**
	 * 
	 * Create a shared wallet from either an account public key and script
	 * templates or mnemonic and script templates.
	 * {@code status: ⚠ under development}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostSharedWalletCreateSharedWalletFromMnemonicsRequest}
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostSharedWalletResponse}
	 */
	public void postSharedWallet(PostSharedWalletCreateSharedWalletFromMnemonicsRequest requestBody, WalletApiCallback<PostSharedWalletResponse> callback){
		api.postSharedWallet(Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Create a shared wallet from either an account public key and script
	 * templates or mnemonic and script templates.
	 * {@code status: ⚠ under development}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostSharedWalletCreateSharedWalletFromAccountPublicKeyRequest}
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostSharedWalletResponse}
	 */
	public void postSharedWallet(PostSharedWalletCreateSharedWalletFromAccountPublicKeyRequest requestBody, WalletApiCallback<PostSharedWalletResponse> callback){
		api.postSharedWallet(Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Return a list of known shared wallets, ordered from oldest to newest.
	 * {@code status: ⚠ under development}
	 * 
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ListSharedWalletsResponseItem}
	 */
	public void listSharedWallets(WalletApiCallback<List<ListSharedWalletsResponseItem>> callback){
		api.listSharedWallets().enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * 
	 * Get a shared wallet for a given wallet id.
	 * {@code status: ⚠ under development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetSharedWalletResponse}
	 */
	public void getSharedWallet(String walletId, WalletApiCallback<GetSharedWalletResponse> callback){
		api.getSharedWallet(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param callback code to be executed once a response is available. No response body is expected.
	 */
	public void deleteSharedWallet(String walletId, WalletApiCallback<Void> callback){
		api.deleteSharedWallet(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PatchSharedWalletInPaymentResponse}
	 */
	public void patchSharedWalletInPayment(String walletId, WalletApiCallback<PatchSharedWalletInPaymentResponse> callback){
		api.patchSharedWalletInPayment(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PatchSharedWalletInDelegationResponse}
	 */
	public void patchSharedWalletInDelegation(String walletId, WalletApiCallback<PatchSharedWalletInDelegationResponse> callback){
		api.patchSharedWalletInDelegation(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link PostAccountKeySharedResponse}
	 */
	public void postAccountKeyShared(String walletId, String index, PostAccountKeySharedRequest requestBody, WalletApiCallback<PostAccountKeySharedResponse> callback){
		api.postAccountKeyShared(walletId, index, Utils.createRequestBody(requestBody)).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetAccountKeySharedResponse}
	 */
	public void getAccountKeyShared(String walletId, WalletApiCallback<GetAccountKeySharedResponse> callback){
		api.getAccountKeyShared(walletId).enqueue(new WalletApiCallbackAdapter<>(callback));
	}

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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link GetSharedWalletKeyResponse}
	 */
	public void getSharedWalletKey(String walletId, String role, String index, Boolean hash, WalletApiCallback<GetSharedWalletKeyResponse> callback){
		api.getSharedWalletKey(walletId, role, index, hash).enqueue(new WalletApiCallbackAdapter<>(callback));
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
	 * @param callback code to be executed once a response is available. The response will be an instance of {@link ListSharedAddressesResponseItem}
	 */
	public void listSharedAddresses(String walletId, String state, WalletApiCallback<List<ListSharedAddressesResponseItem>> callback){
		api.listSharedAddresses(walletId, state).enqueue(new WalletApiCallbackAdapter<>(callback));
	}
}