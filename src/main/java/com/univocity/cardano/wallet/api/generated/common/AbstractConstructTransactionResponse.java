package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

public abstract class AbstractConstructTransactionResponse {


	@JsonProperty("transaction")
	private String transaction;

	@JsonProperty("coin_selection")
	private CoinSelection coinSelection;

	@JsonProperty("fee")
	private Fee fee;

	/**
	 * Returns the transaction.
	 * 
	 * The CBOR-encoded transaction, represented in base64 encoding.
	 * This always includes the transaction body and the witness set, even if the
	 * latter is empty.
	 * 
	 * - Format: {@code base64}.
	 * 
	 * @return the transaction
	 */
	public String getTransaction(){
		return transaction;
	}

	/**
	 * Defines the transaction.
	 * 
	 * The CBOR-encoded transaction, represented in base64 encoding.
	 * This always includes the transaction body and the witness set, even if the
	 * latter is empty.
	 * 
	 * - Format: {@code base64}.
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
	 * Returns the coin selection.
	 * 
	 * @return the coin selection
	 */
	public CoinSelection getCoinSelection(){
		return coinSelection;
	}

	/**
	 * Defines the coin selection.
	 * 
	 * @param coinSelection the coin selection
	 */
	public void setCoinSelection(CoinSelection coinSelection){
		if (coinSelection == null) {
			throw new IllegalArgumentException("Value of coinSelection cannot be null");
		}

		this.coinSelection = coinSelection;
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

	@Override
	public String toString() {
		return printObject(this);
	}

}
