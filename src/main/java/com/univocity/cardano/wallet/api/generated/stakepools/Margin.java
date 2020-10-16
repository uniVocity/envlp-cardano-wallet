package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Variable margin on the total reward given to an operator before splitting rewards between stakeholders.
 * May be omitted if the wallet hasn't found the pool's registration cerificate yet.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Margin {


	@JsonProperty("quantity")
	private Double quantity;

	@JsonProperty("unit")
	private String unit;

	/**
	 * Returns the quantity.
	 * - Value range from {@code 0} to {@code 100}.
	 * 
	 * - Example: 
	 *   <pre>{@code 42}</pre>
	 * 
	 * @return the quantity
	 */
	public Double getQuantity(){
		return quantity;
	}

	/**
	 * Defines the quantity.
	 * - Value range from {@code 0} to {@code 100}.
	 * 
	 * - Example: 
	 *   <pre>{@code 42}</pre>
	 * 
	 * @param quantity the quantity
	 */
	public void setQuantity(Double quantity){
		if (quantity == null) {
			throw new IllegalArgumentException("Value of quantity cannot be null");
		}

		if (quantity < 0) {
			throw new IllegalArgumentException("Value of quantity cannot be less than 0");
		}

		if (quantity > 100L) {
			throw new IllegalArgumentException("Value of quantity cannot be greater than 100");
		}

		this.quantity = quantity;
	}

	/**
	 * Returns the unit.
	 * - Accepted values: {@code [percent]}.
	 * 
	 * @return the unit
	 */
	public String getUnit(){
		return unit;
	}

	/**
	 * Defines the unit.
	 * - Accepted values: {@code [percent]}.
	 * 
	 * @param unit the unit
	 */
	public void setUnit(String unit){
		if (unit == null) {
			throw new IllegalArgumentException("Value of unit cannot be null");
		}

		this.unit = unit;
	}
}