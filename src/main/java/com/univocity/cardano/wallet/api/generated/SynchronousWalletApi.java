package com.univocity.cardano.wallet.api.generated;

import java.math.*;
import static com.univocity.cardano.wallet.api.service.InternalWalletApiServiceGenerator.*;
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
	 * List all assets associated with the wallet, and their metadata
	 * if known.
	 * An asset is _associated_ with a wallet if it is involved in a
	 * transaction of the wallet.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return the server response as a list of {@link ListAssetsResponseItem}
	 */
	public List<ListAssetsResponseItem> listAssets(String walletId){
		return executeSync(api.listAssets(walletId));
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
	 * @return the server response as an instance of {@link MintBurnAssetsResponse}
	 */
	public MintBurnAssetsResponse mintBurnAssets(String walletId, MintBurnAssetsRequest requestBody){
		return executeSync(api.mintBurnAssets(walletId, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as an instance of {@link GetAssetResponse}
	 */
	public GetAssetResponse getAsset(String walletId, String policyId, String assetName){
		return executeSync(api.getAsset(walletId, policyId, assetName));
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
	 * @return the server response as an instance of {@link GetAssetDefaultResponse}
	 */
	public GetAssetDefaultResponse getAssetDefault(String walletId, String policyId){
		return executeSync(api.getAssetDefault(walletId, policyId));
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
	 * 
	 * Generate a snapshot of the wallet's UTxO set.
	 * This endpoint is intended for debugging purposes.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return the server response as an instance of {@link GetWalletUtxoSnapshotResponse}
	 */
	public GetWalletUtxoSnapshotResponse getWalletUtxoSnapshot(String walletId){
		return executeSync(api.getWalletUtxoSnapshot(walletId));
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
	 * Create a transaction to be signed from the wallet.
	 * {@code status: under development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link ConstructTransactionRequest}
	 * @return the server response as an instance of {@link ConstructTransactionResponse}
	 */
	public ConstructTransactionResponse constructTransaction(String walletId, ConstructTransactionRequest requestBody){
		return executeSync(api.constructTransaction(walletId, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as an instance of {@link SignTransactionResponse}
	 */
	public SignTransactionResponse signTransaction(String walletId, SignTransactionRequest requestBody){
		return executeSync(api.signTransaction(walletId, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as an instance of {@link DecodeTransactionResponse}
	 */
	public DecodeTransactionResponse decodeTransaction(String walletId, DecodeTransactionRequest requestBody){
		return executeSync(api.decodeTransaction(walletId, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as an instance of {@link SubmitTransactionResponse}
	 */
	public SubmitTransactionResponse submitTransaction(String walletId, SubmitTransactionRequest requestBody){
		return executeSync(api.submitTransaction(walletId, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as an instance of {@link PostAccountKeyResponse}
	 */
	public PostAccountKeyResponse postAccountKey(String walletId, String index, PostAccountKeyRequest requestBody){
		return executeSync(api.postAccountKey(walletId, index, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as an instance of {@link GetAccountKeyResponse}
	 */
	public GetAccountKeyResponse getAccountKey(String walletId){
		return executeSync(api.getAccountKey(walletId));
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
	 * @return the server response as an instance of {@link GetWalletKeyResponse}
	 */
	public GetWalletKeyResponse getWalletKey(String walletId, String role, String index){
		return executeSync(api.getWalletKey(walletId, role, index));
	}

	/**
	 * 
	 * List stake-keys relevant to the wallet, and how much ada is associated with each.
	 * {@code status: Experimental}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return the server response as an instance of {@link ListStakeKeysResponse}
	 */
	public ListStakeKeysResponse listStakeKeys(String walletId){
		return executeSync(api.listStakeKeys(walletId));
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
	 * @param requestBody a request body containing the json representation of {@link SelectCoinsNormalPaymentRequest}
	 * @return the server response as an instance of {@link SelectCoinsResponse}
	 */
	public SelectCoinsResponse selectCoins(String walletId, SelectCoinsNormalPaymentRequest requestBody){
		return executeSync(api.selectCoins(walletId, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as an instance of {@link SelectCoinsResponse}
	 */
	public SelectCoinsResponse selectCoins(String walletId, SelectCoinsDelegationRequest requestBody){
		return executeSync(api.selectCoins(walletId, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as an instance of {@link SelectCoinsResponse}
	 */
	public SelectCoinsResponse selectCoins(String walletId, SelectCoinsRewardRedemptionRequest requestBody){
		return executeSync(api.selectCoins(walletId, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as a list of {@link MigrateShelleyWalletResponseItem}
	 */
	public List<MigrateShelleyWalletResponseItem> migrateShelleyWallet(String walletId, MigrateShelleyWalletRequest requestBody){
		return executeSync(api.migrateShelleyWallet(walletId, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as an instance of {@link CreateShelleyWalletMigrationPlanResponse}
	 */
	public CreateShelleyWalletMigrationPlanResponse createShelleyWalletMigrationPlan(String walletId, CreateShelleyWalletMigrationPlanRequest requestBody){
		return executeSync(api.createShelleyWalletMigrationPlan(walletId, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as an instance of {@link BalanceTransactionResponse}
	 */
	public BalanceTransactionResponse balanceTransaction(String walletId, BalanceTransactionRequest requestBody){
		return executeSync(api.balanceTransaction(walletId, Utils.createRequestBody(requestBody)));
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
	 * List all assets associated with the wallet, and their metadata
	 * if known.
	 * An asset is _associated_ with a wallet if it is involved in a
	 * transaction of the wallet.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return the server response as a list of {@link ListByronAssetsResponseItem}
	 */
	public List<ListByronAssetsResponseItem> listByronAssets(String walletId){
		return executeSync(api.listByronAssets(walletId));
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
	 * @return the server response as an instance of {@link GetByronAssetResponse}
	 */
	public GetByronAssetResponse getByronAsset(String walletId, String policyId, String assetName){
		return executeSync(api.getByronAsset(walletId, policyId, assetName));
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
	 * @return the server response as an instance of {@link GetByronAssetDefaultResponse}
	 */
	public GetByronAssetDefaultResponse getByronAssetDefault(String walletId, String policyId){
		return executeSync(api.getByronAssetDefault(walletId, policyId));
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
	 * Generate a snapshot of the wallet's UTxO set.
	 * This endpoint is intended for debugging purposes.
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return the server response as an instance of {@link GetByronWalletUtxoSnapshotResponse}
	 */
	public GetByronWalletUtxoSnapshotResponse getByronWalletUtxoSnapshot(String walletId){
		return executeSync(api.getByronWalletUtxoSnapshot(walletId));
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
	 * Create a transaction to be signed from the wallet.
	 * {@code status: unstable}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @param requestBody a request body containing the json representation of {@link ConstructByronTransactionRequest}
	 * @return the server response as an instance of {@link ConstructByronTransactionResponse}
	 */
	public ConstructByronTransactionResponse constructByronTransaction(String walletId, ConstructByronTransactionRequest requestBody){
		return executeSync(api.constructByronTransaction(walletId, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as an instance of {@link SignByronTransactionResponse}
	 */
	public SignByronTransactionResponse signByronTransaction(String walletId, SignByronTransactionRequest requestBody){
		return executeSync(api.signByronTransaction(walletId, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as a list of {@link MigrateByronWalletResponseItem}
	 */
	public List<MigrateByronWalletResponseItem> migrateByronWallet(String walletId, MigrateByronWalletRequest requestBody){
		return executeSync(api.migrateByronWallet(walletId, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as an instance of {@link CreateByronWalletMigrationPlanResponse}
	 */
	public CreateByronWalletMigrationPlanResponse createByronWalletMigrationPlan(String walletId, CreateByronWalletMigrationPlanRequest requestBody){
		return executeSync(api.createByronWalletMigrationPlan(walletId, Utils.createRequestBody(requestBody)));
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
	 * NOTE: Unlike the `submitTransaction` endpoint, there are no
	 * guarantees that a transaction accepted by this endpoint will
	 * actually be included in the chain. It's up to the caller to
	 * retry submission until the transaction is confirmed.
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

	/**
	 * 
	 * Create a shared wallet from either an account public key and script
	 * templates or mnemonic and script templates.
	 * {@code status: ⚠ under development}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostSharedWalletCreateSharedWalletFromMnemonicsRequest}
	 * @return the server response as an instance of {@link PostSharedWalletResponse}
	 */
	public PostSharedWalletResponse postSharedWallet(PostSharedWalletCreateSharedWalletFromMnemonicsRequest requestBody){
		return executeSync(api.postSharedWallet(Utils.createRequestBody(requestBody)));
	}

	/**
	 * 
	 * Create a shared wallet from either an account public key and script
	 * templates or mnemonic and script templates.
	 * {@code status: ⚠ under development}
	 * 
	 * @param requestBody a request body containing the json representation of {@link PostSharedWalletCreateSharedWalletFromAccountPublicKeyRequest}
	 * @return the server response as an instance of {@link PostSharedWalletResponse}
	 */
	public PostSharedWalletResponse postSharedWallet(PostSharedWalletCreateSharedWalletFromAccountPublicKeyRequest requestBody){
		return executeSync(api.postSharedWallet(Utils.createRequestBody(requestBody)));
	}

	/**
	 * 
	 * Return a list of known shared wallets, ordered from oldest to newest.
	 * {@code status: ⚠ under development}
	 * 
	 * @return the server response as a list of {@link ListSharedWalletsResponseItem}
	 */
	public List<ListSharedWalletsResponseItem> listSharedWallets(){
		return executeSync(api.listSharedWallets());
	}

	/**
	 * 
	 * Get a shared wallet for a given wallet id.
	 * {@code status: ⚠ under development}
	 * 
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * @return the server response as an instance of {@link GetSharedWalletResponse}
	 */
	public GetSharedWalletResponse getSharedWallet(String walletId){
		return executeSync(api.getSharedWallet(walletId));
	}

	/**
	 * status: stable
	 * @param walletId the walletId.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 */
	public void deleteSharedWallet(String walletId){
		executeSync(api.deleteSharedWallet(walletId));
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
	 * @return the server response as an instance of {@link PatchSharedWalletInPaymentResponse}
	 */
	public PatchSharedWalletInPaymentResponse patchSharedWalletInPayment(String walletId){
		return executeSync(api.patchSharedWalletInPayment(walletId));
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
	 * @return the server response as an instance of {@link PatchSharedWalletInDelegationResponse}
	 */
	public PatchSharedWalletInDelegationResponse patchSharedWalletInDelegation(String walletId){
		return executeSync(api.patchSharedWalletInDelegation(walletId));
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
	 * @return the server response as an instance of {@link PostAccountKeySharedResponse}
	 */
	public PostAccountKeySharedResponse postAccountKeyShared(String walletId, String index, PostAccountKeySharedRequest requestBody){
		return executeSync(api.postAccountKeyShared(walletId, index, Utils.createRequestBody(requestBody)));
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
	 * @return the server response as an instance of {@link GetAccountKeySharedResponse}
	 */
	public GetAccountKeySharedResponse getAccountKeyShared(String walletId){
		return executeSync(api.getAccountKeyShared(walletId));
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
	 * @return the server response as an instance of {@link GetSharedWalletKeyResponse}
	 */
	public GetSharedWalletKeyResponse getSharedWalletKey(String walletId, String role, String index, Boolean hash){
		return executeSync(api.getSharedWalletKey(walletId, role, index, hash));
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
	 * @return the server response as a list of {@link ListSharedAddressesResponseItem}
	 */
	public List<ListSharedAddressesResponseItem> listSharedAddresses(String walletId, String state){
		return executeSync(api.listSharedAddresses(walletId, state));
	}
}