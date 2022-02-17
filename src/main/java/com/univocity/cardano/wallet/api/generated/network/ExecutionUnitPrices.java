package com.univocity.cardano.wallet.api.generated.network;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * The price of time unit and memory unit used for calculating a fee of a script execution.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ExecutionUnitPrices {


	@JsonProperty("step_price")
	private String stepPrice;

	@JsonProperty("memory_unit_price")
	private String memoryUnitPrice;

	/**
	 * Returns the step price.
	 * 
	 * @return the step price
	 */
	public String getStepPrice(){
		return stepPrice;
	}

	/**
	 * Defines the step price.
	 * 
	 * @param stepPrice the step price
	 */
	public void setStepPrice(String stepPrice){
		if (stepPrice == null) {
			throw new IllegalArgumentException("Value of stepPrice cannot be null");
		}

		this.stepPrice = stepPrice;
	}

	/**
	 * Returns the memory unit price.
	 * 
	 * @return the memory unit price
	 */
	public String getMemoryUnitPrice(){
		return memoryUnitPrice;
	}

	/**
	 * Defines the memory unit price.
	 * 
	 * @param memoryUnitPrice the memory unit price
	 */
	public void setMemoryUnitPrice(String memoryUnitPrice){
		if (memoryUnitPrice == null) {
			throw new IllegalArgumentException("Value of memoryUnitPrice cannot be null");
		}

		this.memoryUnitPrice = memoryUnitPrice;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
