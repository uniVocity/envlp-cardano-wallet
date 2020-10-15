package com.univocity.cardano.wallet.api.generated.common;


import com.fasterxml.jackson.annotation.*;


/**
 * Coins, in Lovelace
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Amount {


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
