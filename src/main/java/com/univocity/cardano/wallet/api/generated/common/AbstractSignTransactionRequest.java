package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

public abstract class AbstractSignTransactionRequest {


	@JsonProperty("passphrase")
	private String passphrase;

	@JsonProperty("transaction")
	private String transaction;

	/**
	 * Returns the wallet's master passphrase.
	 * - Length range from {@code 0} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @return the wallet's master passphrase.
	 */
	public String getPassphrase(){
		return passphrase;
	}

	/**
	 * Defines the wallet's master passphrase.
	 * - Length range from {@code 0} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @param passphrase the wallet's master passphrase.
	 */
	public void setPassphrase(String passphrase){
		if (passphrase == null) {
			throw new IllegalArgumentException("Value of passphrase cannot be null");
		}

		if (passphrase.codePointCount(0, passphrase.length()) < 0) {
			throw new IllegalArgumentException("Length of passphrase must have at least 0 characters, got '" + passphrase.codePointCount(0, passphrase.length()) + "'");
		}

		if (passphrase.codePointCount(0, passphrase.length()) > 255) {
			throw new IllegalArgumentException("Length of passphrase cannot exceed 255 characters, got '" + passphrase.codePointCount(0, passphrase.length()) + "'");
		}

		this.passphrase = passphrase;
	}

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
