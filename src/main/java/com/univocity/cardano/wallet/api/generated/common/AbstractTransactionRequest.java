package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

public abstract class AbstractTransactionRequest {


	@JsonProperty("transaction")
	private String transaction;

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

	@Override
	public String toString() {
		return printObject(this);
	}

}
