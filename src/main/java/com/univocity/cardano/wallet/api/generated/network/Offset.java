package com.univocity.cardano.wallet.api.generated.network;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Drift offset of the local clock.
 * {@code if: status == available}
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Offset {


	@JsonProperty("quantity")
	private Integer quantity;

	@JsonProperty("unit")
	private String unit;

	/**
	 * Returns the quantity.
	 * 
	 * - Example: 
	 *   <pre>{@code 14}</pre>
	 * 
	 * @return the quantity
	 */
	public Integer getQuantity(){
		return quantity;
	}

	/**
	 * Defines the quantity.
	 * 
	 * - Example: 
	 *   <pre>{@code 14}</pre>
	 * 
	 * @param quantity the quantity
	 */
	public void setQuantity(Integer quantity){
		if (quantity == null) {
			throw new IllegalArgumentException("Value of quantity cannot be null");
		}

		this.quantity = quantity;
	}

	/**
	 * Returns the unit.
	 * - Accepted values: {@code [microsecond]}.
	 * 
	 * @return the unit
	 */
	public String getUnit(){
		return unit;
	}

	/**
	 * Defines the unit.
	 * - Accepted values: {@code [microsecond]}.
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
