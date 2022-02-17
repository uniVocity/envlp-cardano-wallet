package com.univocity.cardano.wallet.api.generated.transactionsnew;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#balanceTransaction(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class BalanceTransactionRequest {


	@JsonProperty("transaction")
	private String transaction;

	@JsonProperty("inputs")
	private ArrayList<Input> inputs;

	@JsonProperty("redeemers")
	private ArrayList<String> redeemers;

	/**
	 * Returns the transaction.
	 * 
	 * The CBOR-encoded transaction, represented in either hex or base64 encoding.
	 * This always includes the transaction body and the witness set, even if the
	 * latter is empty.
	 * 
	 * - Format: {@code base64|base16}.
	 * 
	 * @return the transaction
	 */
	public String getTransaction(){
		return transaction;
	}

	/**
	 * Defines the transaction.
	 * 
	 * The CBOR-encoded transaction, represented in either hex or base64 encoding.
	 * This always includes the transaction body and the witness set, even if the
	 * latter is empty.
	 * 
	 * - Format: {@code base64|base16}.
	 * 
	 * @param transaction the transaction
	 */
	public void setTransaction(String transaction){
		if (transaction == null) {
			throw new IllegalArgumentException("Value of transaction cannot be null");
		}

		this.transaction = transaction;
	}

	/**
	 * Returns the list of additional transaction inputs foreign to the wallet. (optional).
	 * 
	 * @return the list of additional transaction inputs foreign to the wallet.
	 */
	public ArrayList<Input> getInputs(){
		return inputs;
	}

	/**
	 * Defines a list of additional transaction inputs foreign to the wallet. (optional).
	 * 
	 * @param inputs a list of additional transaction inputs foreign to the wallet.
	 */
	public void setInputs(ArrayList<Input> inputs){
		if (inputs == null) {
			this.inputs = inputs;
			return;
		}

		this.inputs = inputs;
	}

	/**
	 * Returns the list of redeemers data with their purpose. (optional).
	 * 
	 * @return the list of redeemers data with their purpose.
	 */
	public ArrayList<String> getRedeemers(){
		return redeemers;
	}

	/**
	 * Defines a list of redeemers data with their purpose. (optional).
	 * 
	 * @param redeemers a list of redeemers data with their purpose.
	 */
	public void setRedeemers(ArrayList<String> redeemers){
		if (redeemers == null) {
			this.redeemers = redeemers;
			return;
		}

		this.redeemers = redeemers;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
