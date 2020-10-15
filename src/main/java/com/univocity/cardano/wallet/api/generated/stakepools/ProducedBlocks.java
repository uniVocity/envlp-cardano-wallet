package com.univocity.cardano.wallet.api.generated.stakepools;

import com.fasterxml.jackson.annotation.*;


/**
 * Number of blocks produced by a given stake pool in its lifetime.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ProducedBlocks {


	@JsonProperty("quantity")
	private Integer quantity;

	@JsonProperty("unit")
	private String unit;

	/**
	 * Returns the quantity.
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1337}</pre>
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
	 *   <pre>{@code 1337}</pre>
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
	 * - Accepted values: {@code [block]}.
	 * 
	 * - Example: 
	 *   <pre>{@code block}</pre>
	 * 
	 * @return the unit
	 */
	public String getUnit(){
		return unit;
	}

	/**
	 * Defines the unit.
	 * - Accepted values: {@code [block]}.
	 * 
	 * - Example: 
	 *   <pre>{@code block}</pre>
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
