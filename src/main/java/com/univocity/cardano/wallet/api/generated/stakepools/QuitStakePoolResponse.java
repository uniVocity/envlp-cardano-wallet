package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.regex.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#quitStakePool(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class QuitStakePoolResponse {


	@JsonProperty("id")
	private String id;

	@JsonProperty("amount")
	private Amount amount;

	@JsonProperty("fee")
	private Fee fee;

	@JsonProperty("deposit")
	private Deposit deposit;

	@JsonProperty("inserted_at")
	private InsertedAt insertedAt;

	@JsonProperty("expires_at")
	private ExpiresAt expiresAt;

	@JsonProperty("pending_since")
	private PendingSince pendingSince;

	@JsonProperty("depth")
	private Depth depth;

	@JsonProperty("direction")
	private String direction;

	@JsonProperty("inputs")
	private ArrayList<InputsDelete> inputs;

	@JsonProperty("outputs")
	private ArrayList<OutputsDelete> outputs;

	@JsonProperty("withdrawals")
	private ArrayList<WithdrawalsDelete> withdrawals;

	@JsonProperty("status")
	private String status;

	@JsonProperty("metadata")
	private Object metadata;

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
	 * Returns the amount.
	 * 
	 * @return the amount
	 */
	public Amount getAmount(){
		return amount;
	}

	/**
	 * Defines the amount.
	 * 
	 * @param amount the amount
	 */
	public void setAmount(Amount amount){
		if (amount == null) {
			throw new IllegalArgumentException("Value of amount cannot be null");
		}

		this.amount = amount;
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
	 * Returns the deposit.
	 * 
	 * @return the deposit
	 */
	public Deposit getDeposit(){
		return deposit;
	}

	/**
	 * Defines the deposit.
	 * 
	 * @param deposit the deposit
	 */
	public void setDeposit(Deposit deposit){
		if (deposit == null) {
			throw new IllegalArgumentException("Value of deposit cannot be null");
		}

		this.deposit = deposit;
	}

	/**
	 * Returns the inserted at (optional).
	 * 
	 * @return the inserted at
	 */
	public InsertedAt getInsertedAt(){
		return insertedAt;
	}

	/**
	 * Defines the inserted at (optional).
	 * 
	 * @param insertedAt the inserted at
	 */
	public void setInsertedAt(InsertedAt insertedAt){
		if (insertedAt == null) {
			this.insertedAt = insertedAt;
			return;
		}

		this.insertedAt = insertedAt;
	}

	/**
	 * Returns the expires at (optional).
	 * 
	 * @return the expires at
	 */
	public ExpiresAt getExpiresAt(){
		return expiresAt;
	}

	/**
	 * Defines the expires at (optional).
	 * 
	 * @param expiresAt the expires at
	 */
	public void setExpiresAt(ExpiresAt expiresAt){
		if (expiresAt == null) {
			this.expiresAt = expiresAt;
			return;
		}

		this.expiresAt = expiresAt;
	}

	/**
	 * Returns the pending since (optional).
	 * 
	 * @return the pending since
	 */
	public PendingSince getPendingSince(){
		return pendingSince;
	}

	/**
	 * Defines the pending since (optional).
	 * 
	 * @param pendingSince the pending since
	 */
	public void setPendingSince(PendingSince pendingSince){
		if (pendingSince == null) {
			this.pendingSince = pendingSince;
			return;
		}

		this.pendingSince = pendingSince;
	}

	/**
	 * Returns the depth (optional).
	 * 
	 * @return the depth
	 */
	public Depth getDepth(){
		return depth;
	}

	/**
	 * Defines the depth (optional).
	 * 
	 * @param depth the depth
	 */
	public void setDepth(Depth depth){
		if (depth == null) {
			this.depth = depth;
			return;
		}

		this.depth = depth;
	}

	/**
	 * Returns the direction.
	 * - Accepted values: {@code [outgoing, incoming]}.
	 * 
	 * @return the direction
	 */
	public String getDirection(){
		return direction;
	}

	/**
	 * Defines the direction.
	 * - Accepted values: {@code [outgoing, incoming]}.
	 * 
	 * @param direction the direction
	 */
	public void setDirection(String direction){
		if (direction == null) {
			throw new IllegalArgumentException("Value of direction cannot be null");
		}

		this.direction = direction;
	}

	/**
	 * Returns the list of transaction inputs.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the list of transaction inputs
	 */
	public ArrayList<InputsDelete> getInputs(){
		return inputs;
	}

	/**
	 * Defines a list of transaction inputs.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param inputs a list of transaction inputs
	 */
	public void setInputs(ArrayList<InputsDelete> inputs){
		if (inputs == null) {
			throw new IllegalArgumentException("Value of inputs cannot be null");
		}

		this.inputs = inputs;
	}

	/**
	 * Returns the list of target outputs.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the list of target outputs
	 */
	public ArrayList<OutputsDelete> getOutputs(){
		return outputs;
	}

	/**
	 * Defines a list of target outputs.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param outputs a list of target outputs
	 */
	public void setOutputs(ArrayList<OutputsDelete> outputs){
		if (outputs == null) {
			throw new IllegalArgumentException("Value of outputs cannot be null");
		}

		this.outputs = outputs;
	}

	/**
	 * Returns the list of withdrawals from stake addresses.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the list of withdrawals from stake addresses.
	 */
	public ArrayList<WithdrawalsDelete> getWithdrawals(){
		return withdrawals;
	}

	/**
	 * Defines a list of withdrawals from stake addresses.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param withdrawals a list of withdrawals from stake addresses.
	 */
	public void setWithdrawals(ArrayList<WithdrawalsDelete> withdrawals){
		if (withdrawals == null) {
			throw new IllegalArgumentException("Value of withdrawals cannot be null");
		}

		this.withdrawals = withdrawals;
	}

	/**
	 * Returns the status.
	 * 
	 * Current transaction status.
	 *   ```
	 *          *---------*          *-----------*
	 *          |         |---------->  EXPIRED  |
	 *          |         |  (ttl)   *-----------*
	 *   -------> PENDING |
	 *          |         <----------------*
	 *          |         |                |
	 *          *---------*            (rollback)
	 *               |                     |
	 *          (in ledger)          *-----------*
	 *               |               |           |
	 *               *---------------> IN_LEDGER |
	 *                               |           |
	 *                               *-----------*
	 *   ```
	 * 
	 * - Accepted values: {@code [pending, in_ledger, expired]}.
	 * 
	 * @return the status
	 */
	public String getStatus(){
		return status;
	}

	/**
	 * Defines the status.
	 * 
	 * Current transaction status.
	 *   ```
	 *          *---------*          *-----------*
	 *          |         |---------->  EXPIRED  |
	 *          |         |  (ttl)   *-----------*
	 *   -------> PENDING |
	 *          |         <----------------*
	 *          |         |                |
	 *          *---------*            (rollback)
	 *               |                     |
	 *          (in ledger)          *-----------*
	 *               |               |           |
	 *               *---------------> IN_LEDGER |
	 *                               |           |
	 *                               *-----------*
	 *   ```
	 * 
	 * - Accepted values: {@code [pending, in_ledger, expired]}.
	 * 
	 * @param status the status
	 */
	public void setStatus(String status){
		if (status == null) {
			throw new IllegalArgumentException("Value of status cannot be null");
		}

		this.status = status;
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

	@Override
	public String toString() {
		return printObject(this);
	}

}
