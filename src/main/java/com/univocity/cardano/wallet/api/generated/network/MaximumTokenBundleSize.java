package com.univocity.cardano.wallet.api.generated.network;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * The maximum size of a serialized `TokenBundle`. The concept was added in Mary where
 * it was hard-coded to 4000 bytes. In Alonzo it was made an updateable protocol parameter (`_maxValSize`).
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class MaximumTokenBundleSize {


	@JsonProperty("quantity")
	private Double quantity;

	@JsonProperty("unit")
	private String unit;

	/**
	 * Returns the quantity.
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 4000}</pre>
	 * 
	 * @return the quantity
	 */
	public Double getQuantity(){
		return quantity;
	}

	/**
	 * Defines the quantity.
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 4000}</pre>
	 * 
	 * @param quantity the quantity
	 */
	public void setQuantity(Double quantity){
		if (quantity == null) {
			throw new IllegalArgumentException("Value of quantity cannot be null");
		}

		if (quantity < 0) {
			throw new IllegalArgumentException("Value of quantity cannot be less than 0, got '" + quantity + "'");
		}

		this.quantity = quantity;
	}

	/**
	 * Returns the unit.
	 * - Accepted values: {@code [byte]}.
	 * 
	 * @return the unit
	 */
	public String getUnit(){
		return unit;
	}

	/**
	 * Defines the unit.
	 * - Accepted values: {@code [byte]}.
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
