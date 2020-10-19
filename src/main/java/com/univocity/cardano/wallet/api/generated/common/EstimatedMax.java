package com.univocity.cardano.wallet.api.generated.common;


import java.math.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * Coins, in Lovelace
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class EstimatedMax {


	@JsonProperty("quantity")
	private BigInteger quantity;

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
	public BigInteger getQuantity(){
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
	public void setQuantity(BigInteger quantity){
		if (quantity == null) {
			throw new IllegalArgumentException("Value of quantity cannot be null");
		}

		if (quantity.compareTo(new BigInteger("0")) < 0){
			throw new IllegalArgumentException("'" + quantity + "': value of quantity cannot be less than 0");
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

	@Override
	public String toString() {
		return printObject(this);
	}

}
