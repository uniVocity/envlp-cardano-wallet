package com.univocity.cardano.wallet.api.generated.byronwallets;

import com.univocity.cardano.wallet.api.generated.common.*;
import com.fasterxml.jackson.annotation.*;


/**
 * Byron wallet's current balance(s)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Balance {


	@JsonProperty("available")
	private Available available;

	@JsonProperty("total")
	private Total total;

	/**
	 * Returns the available.
	 * 
	 * @return the available
	 */
	public Available getAvailable(){
		return available;
	}

	/**
	 * Defines the available.
	 * 
	 * @param available the available
	 */
	public void setAvailable(Available available){
		if (available == null) {
			throw new IllegalArgumentException("Value of available cannot be null");
		}

		this.available = available;
	}

	/**
	 * Returns the total.
	 * 
	 * @return the total
	 */
	public Total getTotal(){
		return total;
	}

	/**
	 * Defines the total.
	 * 
	 * @param total the total
	 */
	public void setTotal(Total total){
		if (total == null) {
			throw new IllegalArgumentException("Value of total cannot be null");
		}

		this.total = total;
	}
}
