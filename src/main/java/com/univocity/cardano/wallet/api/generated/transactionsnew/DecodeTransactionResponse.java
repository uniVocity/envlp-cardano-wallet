package com.univocity.cardano.wallet.api.generated.transactionsnew;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.regex.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#decodeTransaction(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class DecodeTransactionResponse {


	@JsonProperty("id")
	private String id;

	@JsonProperty("fee")
	private Fee fee;

	@JsonProperty("inputs")
	private ArrayList<String> inputs;

	@JsonProperty("outputs")
	private ArrayList<String> outputs;

	@JsonProperty("collateral")
	private ArrayList<String> collateral;

	@JsonProperty("withdrawals")
	private ArrayList<Withdrawal> withdrawals;

	@JsonProperty("assets_minted")
	private ArrayList<AssetsMinted> assetsMinted;

	@JsonProperty("assets_burned")
	private ArrayList<AssetsBurned> assetsBurned;

	@JsonProperty("certificates")
	private ArrayList<String> certificates;

	@JsonProperty("metadata")
	private Object metadata;

	@JsonProperty("deposits_taken")
	private ArrayList<DepositsTaken> depositsTaken;

	@JsonProperty("deposits_returned")
	private ArrayList<DepositsReturned> depositsReturned;

	@JsonProperty("script_validity")
	private String scriptValidity;

	/**
	 * Returns the unique identifier for this transaction.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 64}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1423856bc91c49e928f6f30f4e8d665d53eb4ab6028bd0ac971809d514c92db1}</pre>
	 * 
	 * @return the unique identifier for this transaction
	 */
	public String getId(){
		return id;
	}

	/**
	 * Defines a unique identifier for this transaction.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 64}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1423856bc91c49e928f6f30f4e8d665d53eb4ab6028bd0ac971809d514c92db1}</pre>
	 * 
	 * @param id a unique identifier for this transaction
	 */
	public void setId(String id){
		if (id == null) {
			throw new IllegalArgumentException("Value of id cannot be null");
		}

		if (id.codePointCount(0, id.length()) < 64) {
			throw new IllegalArgumentException("Length of id must have at least 64 characters, got '" + id.codePointCount(0, id.length()) + "'");
		}

		if (id.codePointCount(0, id.length()) > 64) {
			throw new IllegalArgumentException("Length of id cannot exceed 64 characters, got '" + id.codePointCount(0, id.length()) + "'");
		}

	    if(!Pattern.compile("\\p{XDigit}+").matcher(id).matches()){
    		throw new IllegalArgumentException("Value provided for id does not represent a hexadecimal.");
    	}

		this.id = id;
	}

	/**
	 * Returns the fee.
	 * 
	 * @return the fee
	 */
	public Fee getFee(){
		return fee;
	}

	/**
	 * Defines the fee.
	 * 
	 * @param fee the fee
	 */
	public void setFee(Fee fee){
		if (fee == null) {
			throw new IllegalArgumentException("Value of fee cannot be null");
		}

		this.fee = fee;
	}

	/**
	 * Returns the inputs that could be external or belong to the wallet.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the inputs that could be external or belong to the wallet.
	 */
	public ArrayList<String> getInputs(){
		return inputs;
	}

	/**
	 * Defines the inputs that could be external or belong to the wallet.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param inputs the inputs that could be external or belong to the wallet.
	 */
	public void setInputs(ArrayList<String> inputs){
		if (inputs == null) {
			throw new IllegalArgumentException("Value of inputs cannot be null");
		}

		this.inputs = inputs;
	}

	/**
	 * Returns the outputs that could be external or belong to the wallet.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the outputs that could be external or belong to the wallet.
	 */
	public ArrayList<String> getOutputs(){
		return outputs;
	}

	/**
	 * Defines the outputs that could be external or belong to the wallet.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param outputs the outputs that could be external or belong to the wallet.
	 */
	public void setOutputs(ArrayList<String> outputs){
		if (outputs == null) {
			throw new IllegalArgumentException("Value of outputs cannot be null");
		}

		this.outputs = outputs;
	}

	/**
	 * Returns the inputs that could be external or belong to the wallet. (optional).
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the inputs that could be external or belong to the wallet.
	 */
	public ArrayList<String> getCollateral(){
		return collateral;
	}

	/**
	 * Defines the inputs that could be external or belong to the wallet. (optional).
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param collateral the inputs that could be external or belong to the wallet.
	 */
	public void setCollateral(ArrayList<String> collateral){
		if (collateral == null) {
			this.collateral = collateral;
			return;
		}

		this.collateral = collateral;
	}

	/**
	 * Returns the withdrawals that could be external or belong to the wallet.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the withdrawals that could be external or belong to the wallet.
	 */
	public ArrayList<Withdrawal> getWithdrawals(){
		return withdrawals;
	}

	/**
	 * Defines the withdrawals that could be external or belong to the wallet.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param withdrawals the withdrawals that could be external or belong to the wallet.
	 */
	public void setWithdrawals(ArrayList<Withdrawal> withdrawals){
		if (withdrawals == null) {
			throw new IllegalArgumentException("Value of withdrawals cannot be null");
		}

		this.withdrawals = withdrawals;
	}

	/**
	 * Returns the assets minted.
	 * 
	 * Assets minted (created).
	 * The act of minting assets provides value to a transaction.
	 * 
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the assets minted
	 */
	public ArrayList<AssetsMinted> getAssetsMinted(){
		return assetsMinted;
	}

	/**
	 * Defines the assets minted.
	 * 
	 * Assets minted (created).
	 * The act of minting assets provides value to a transaction.
	 * 
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param assetsMinted the assets minted
	 */
	public void setAssetsMinted(ArrayList<AssetsMinted> assetsMinted){
		if (assetsMinted == null) {
			throw new IllegalArgumentException("Value of assetsMinted cannot be null");
		}

		this.assetsMinted = assetsMinted;
	}

	/**
	 * Returns the assets burned.
	 * 
	 * Assets burned (destroyed).
	 * The act of burning assets consumes value from a transaction.
	 * 
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the assets burned
	 */
	public ArrayList<AssetsBurned> getAssetsBurned(){
		return assetsBurned;
	}

	/**
	 * Defines the assets burned.
	 * 
	 * Assets burned (destroyed).
	 * The act of burning assets consumes value from a transaction.
	 * 
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param assetsBurned the assets burned
	 */
	public void setAssetsBurned(ArrayList<AssetsBurned> assetsBurned){
		if (assetsBurned == null) {
			throw new IllegalArgumentException("Value of assetsBurned cannot be null");
		}

		this.assetsBurned = assetsBurned;
	}

	/**
	 * Returns the certificates.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the certificates
	 */
	public ArrayList<String> getCertificates(){
		return certificates;
	}

	/**
	 * Defines the certificates.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param certificates the certificates
	 */
	public void setCertificates(ArrayList<String> certificates){
		if (certificates == null) {
			throw new IllegalArgumentException("Value of certificates cannot be null");
		}

		this.certificates = certificates;
	}

	/**
	 * Returns the metadata (optional).
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "0" : {
	 *         "string" : "cardano"
	 *       },
	 *       "1" : {
	 *         "int" : 14
	 *       },
	 *       "2" : {
	 *         "bytes" : "2512a00e9653fe49a44a5886202e24d77eeb998f"
	 *       },
	 *       "3" : {
	 *         "list" : [ {
	 *           "int" : 14
	 *         }, {
	 *           "int" : 42
	 *         }, {
	 *           "string" : "1337"
	 *         } ]
	 *       },
	 *       "4" : {
	 *         "map" : [ {
	 *           "k" : {
	 *             "string" : "key"
	 *           },
	 *           "v" : {
	 *             "string" : "value"
	 *           }
	 *         }, {
	 *           "k" : {
	 *             "int" : 14
	 *           },
	 *           "v" : {
	 *             "int" : 42
	 *           }
	 *         } ]
	 *       }
	 *     }
	 *   }</pre>
	 * 
	 * @return the metadata
	 */
	public Object getMetadata(){
		return metadata;
	}

	/**
	 * Defines the metadata (optional).
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "0" : {
	 *         "string" : "cardano"
	 *       },
	 *       "1" : {
	 *         "int" : 14
	 *       },
	 *       "2" : {
	 *         "bytes" : "2512a00e9653fe49a44a5886202e24d77eeb998f"
	 *       },
	 *       "3" : {
	 *         "list" : [ {
	 *           "int" : 14
	 *         }, {
	 *           "int" : 42
	 *         }, {
	 *           "string" : "1337"
	 *         } ]
	 *       },
	 *       "4" : {
	 *         "map" : [ {
	 *           "k" : {
	 *             "string" : "key"
	 *           },
	 *           "v" : {
	 *             "string" : "value"
	 *           }
	 *         }, {
	 *           "k" : {
	 *             "int" : 14
	 *           },
	 *           "v" : {
	 *             "int" : 42
	 *           }
	 *         } ]
	 *       }
	 *     }
	 *   }</pre>
	 * 
	 * @param metadata the metadata
	 */
	public void setMetadata(Object metadata){
		if (metadata == null) {
			this.metadata = metadata;
			return;
		}

		this.metadata = metadata;
	}

	/**
	 * Returns the list of deposits associated with a transaction. (optional).
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the list of deposits associated with a transaction.
	 */
	public ArrayList<DepositsTaken> getDepositsTaken(){
		return depositsTaken;
	}

	/**
	 * Defines a list of deposits associated with a transaction. (optional).
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param depositsTaken a list of deposits associated with a transaction.
	 */
	public void setDepositsTaken(ArrayList<DepositsTaken> depositsTaken){
		if (depositsTaken == null) {
			this.depositsTaken = depositsTaken;
			return;
		}

		this.depositsTaken = depositsTaken;
	}

	/**
	 * Returns the list of deposits associated with a transaction. (optional).
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the list of deposits associated with a transaction.
	 */
	public ArrayList<DepositsReturned> getDepositsReturned(){
		return depositsReturned;
	}

	/**
	 * Defines a list of deposits associated with a transaction. (optional).
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param depositsReturned a list of deposits associated with a transaction.
	 */
	public void setDepositsReturned(ArrayList<DepositsReturned> depositsReturned){
		if (depositsReturned == null) {
			this.depositsReturned = depositsReturned;
			return;
		}

		this.depositsReturned = depositsReturned;
	}

	/**
	 * Returns the script validity (optional).
	 * 
	 * Indicates whether the phase-2 monetary policy script (e.g. Plutus script)
	 * used in the transaction validated or not. Validity may be null if this
	 * transaction was from an era that doesn't support phase-2 monetary policy
	 * scripts, or is a pending transaction (we don't know if validation passed or
	 * failed until the transaction hits the ledger).
	 * 
	 * - Accepted values: {@code [valid, invalid]}.
	 * 
	 * @return the script validity
	 */
	public String getScriptValidity(){
		return scriptValidity;
	}

	/**
	 * Defines the script validity (optional).
	 * 
	 * Indicates whether the phase-2 monetary policy script (e.g. Plutus script)
	 * used in the transaction validated or not. Validity may be null if this
	 * transaction was from an era that doesn't support phase-2 monetary policy
	 * scripts, or is a pending transaction (we don't know if validation passed or
	 * failed until the transaction hits the ledger).
	 * 
	 * - Accepted values: {@code [valid, invalid]}.
	 * 
	 * @param scriptValidity the script validity
	 */
	public void setScriptValidity(String scriptValidity){
		if (scriptValidity == null) {
			this.scriptValidity = scriptValidity;
			return;
		}

		this.scriptValidity = scriptValidity;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
