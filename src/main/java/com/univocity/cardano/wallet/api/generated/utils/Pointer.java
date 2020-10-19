package com.univocity.cardano.wallet.api.generated.utils;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Pointer {


	@JsonProperty("slot_num")
	private Integer slotNum;

	@JsonProperty("transaction_index")
	private Integer transactionIndex;

	@JsonProperty("output_index")
	private Integer outputIndex;

	/**
	 * Returns the slot num.
	 * - Minimum value: {@code 0}.
	 * 
	 * @return the slot num
	 */
	public Integer getSlotNum(){
		return slotNum;
	}

	/**
	 * Defines the slot num.
	 * - Minimum value: {@code 0}.
	 * 
	 * @param slotNum the slot num
	 */
	public void setSlotNum(Integer slotNum){
		if (slotNum == null) {
			throw new IllegalArgumentException("Value of slotNum cannot be null");
		}

		if (slotNum < 0) {
			throw new IllegalArgumentException("Value of slotNum cannot be less than 0");
		}

		this.slotNum = slotNum;
	}

	/**
	 * Returns the transaction index.
	 * - Minimum value: {@code 0}.
	 * 
	 * @return the transaction index
	 */
	public Integer getTransactionIndex(){
		return transactionIndex;
	}

	/**
	 * Defines the transaction index.
	 * - Minimum value: {@code 0}.
	 * 
	 * @param transactionIndex the transaction index
	 */
	public void setTransactionIndex(Integer transactionIndex){
		if (transactionIndex == null) {
			throw new IllegalArgumentException("Value of transactionIndex cannot be null");
		}

		if (transactionIndex < 0) {
			throw new IllegalArgumentException("Value of transactionIndex cannot be less than 0");
		}

		this.transactionIndex = transactionIndex;
	}

	/**
	 * Returns the output index.
	 * - Minimum value: {@code 0}.
	 * 
	 * @return the output index
	 */
	public Integer getOutputIndex(){
		return outputIndex;
	}

	/**
	 * Defines the output index.
	 * - Minimum value: {@code 0}.
	 * 
	 * @param outputIndex the output index
	 */
	public void setOutputIndex(Integer outputIndex){
		if (outputIndex == null) {
			throw new IllegalArgumentException("Value of outputIndex cannot be null");
		}

		if (outputIndex < 0) {
			throw new IllegalArgumentException("Value of outputIndex cannot be less than 0");
		}

		this.outputIndex = outputIndex;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
