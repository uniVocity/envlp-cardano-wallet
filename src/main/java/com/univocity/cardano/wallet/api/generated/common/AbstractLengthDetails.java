package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


public abstract class AbstractLengthDetails {


	@JsonProperty("quantity")
	private Double quantity;

	@JsonProperty("unit")
	private String unit;

	/**
	 * Returns the quantity.
	 * - Minimum value: {@code 0.0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 10.0}</pre>
	 * 
	 * @return the quantity
	 */
	public Double getQuantity(){
		return quantity;
	}

	/**
	 * Defines the quantity.
	 * - Minimum value: {@code 0.0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 10.0}</pre>
	 * 
	 * @param quantity the quantity
	 */
	public void setQuantity(Double quantity){
		if (quantity == null) {
			throw new IllegalArgumentException("Value of quantity cannot be null");
		}

		if (quantity < 0.0) {
			throw new IllegalArgumentException("Value of quantity cannot be less than 0.0, got '" + quantity + "'");
		}

		this.quantity = quantity;
	}

	/**
	 * Returns the unit.
	 * - Accepted values: {@code [second]}.
	 * 
	 * @return the unit
	 */
	public String getUnit(){
		return unit;
	}

	/**
	 * Defines the unit.
	 * - Accepted values: {@code [second]}.
	 * 
	 * @param unit the unit
	 */
	public void setUnit(String unit){
		if (unit == null) {
			throw new IllegalArgumentException("Value of unit cannot be null");
		}

		this.unit = unit;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
