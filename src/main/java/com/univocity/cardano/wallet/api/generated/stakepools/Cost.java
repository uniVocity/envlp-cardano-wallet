package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Estimated cost set by the pool operator when registering his pool.
 * This fixed cost is taken from each reward earned by the pool before splitting rewards between stakeholders.
 * May be omitted if the wallet hasn't found the pool's registration cerificate yet.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Cost {


	@JsonProperty("quantity")
	private Integer quantity;

	@JsonProperty("unit")
	private String unit;

	/**
	 * Returns the quantity.
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 42000000}</pre>
	 * 
	 * @return the quantity
	 */
	public Integer getQuantity(){
		return quantity;
	}

	/**
	 * Defines the quantity.
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 42000000}</pre>
	 * 
	 * @param quantity the quantity
	 */
	public void setQuantity(Integer quantity){
		if (quantity == null) {
			throw new IllegalArgumentException("Value of quantity cannot be null");
		}

		if (quantity < 0) {
			throw new IllegalArgumentException("Value of quantity cannot be less than 0");
		}

		this.quantity = quantity;
	}

	/**
	 * Returns the unit.
	 * - Accepted values: {@code [lovelace]}.
	 * 
	 * @return the unit
	 */
	public String getUnit(){
		return unit;
	}

	/**
	 * Defines the unit.
	 * - Accepted values: {@code [lovelace]}.
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