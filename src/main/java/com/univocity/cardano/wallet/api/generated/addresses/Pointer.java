package com.univocity.cardano.wallet.api.generated.addresses;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.math.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Pointer {


	@JsonProperty("slot_num")
	private BigInteger slotNum;

	@JsonProperty("transaction_index")
	private BigInteger transactionIndex;

	@JsonProperty("output_index")
	private BigInteger outputIndex;

	/**
	 * Returns the slot num.
	 * - Minimum value: {@code 0}.
	 * 
	 * @return the slot num
	 */
	public BigInteger getSlotNum(){
		return slotNum;
	}

	/**
	 * Defines the slot num.
	 * - Minimum value: {@code 0}.
	 * 
	 * @param slotNum the slot num
	 */
	public void setSlotNum(BigInteger slotNum){
		if (slotNum == null) {
			throw new IllegalArgumentException("Value of slotNum cannot be null");
		}

		if (slotNum.compareTo(new BigInteger("0")) < 0){
			throw new IllegalArgumentException("'" + slotNum + "': value of slotNum cannot be less than 0");
		}

		this.slotNum = slotNum;
	}

	/**
	 * Returns the transaction index.
	 * - Minimum value: {@code 0}.
	 * 
	 * @return the transaction index
	 */
	public BigInteger getTransactionIndex(){
		return transactionIndex;
	}

	/**
	 * Defines the transaction index.
	 * - Minimum value: {@code 0}.
	 * 
	 * @param transactionIndex the transaction index
	 */
	public void setTransactionIndex(BigInteger transactionIndex){
		if (transactionIndex == null) {
			throw new IllegalArgumentException("Value of transactionIndex cannot be null");
		}

		if (transactionIndex.compareTo(new BigInteger("0")) < 0){
			throw new IllegalArgumentException("'" + transactionIndex + "': value of transactionIndex cannot be less than 0");
		}

		this.transactionIndex = transactionIndex;
	}

	/**
	 * Returns the output index.
	 * - Minimum value: {@code 0}.
	 * 
	 * @return the output index
	 */
	public BigInteger getOutputIndex(){
		return outputIndex;
	}

	/**
	 * Defines the output index.
	 * - Minimum value: {@code 0}.
	 * 
	 * @param outputIndex the output index
	 */
	public void setOutputIndex(BigInteger outputIndex){
		if (outputIndex == null) {
			throw new IllegalArgumentException("Value of outputIndex cannot be null");
		}

		if (outputIndex.compareTo(new BigInteger("0")) < 0){
			throw new IllegalArgumentException("'" + outputIndex + "': value of outputIndex cannot be less than 0");
		}

		this.outputIndex = outputIndex;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
