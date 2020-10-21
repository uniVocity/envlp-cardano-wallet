package com.univocity.cardano.wallet.api.generated.common;


import java.math.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


public abstract class AbstractSlotDetails {


	@JsonProperty("slot_number")
	private BigInteger slotNumber;

	@JsonProperty("epoch_number")
	private BigInteger epochNumber;

	@JsonProperty("height")
	private Height height;

	@JsonProperty("absolute_slot_number")
	private BigInteger absoluteSlotNumber;

	/**
	 * Returns the slot index within an epoch.
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1337}</pre>
	 * 
	 * @return the slot index within an epoch.
	 */
	public BigInteger getSlotNumber(){
		return slotNumber;
	}

	/**
	 * Defines the slot index within an epoch.
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1337}</pre>
	 * 
	 * @param slotNumber the slot index within an epoch.
	 */
	public void setSlotNumber(BigInteger slotNumber){
		if (slotNumber == null) {
			throw new IllegalArgumentException("Value of slotNumber cannot be null");
		}

		if (slotNumber.compareTo(new BigInteger("0")) < 0){
			throw new IllegalArgumentException("'" + slotNumber + "': value of slotNumber cannot be less than 0");
		}

		this.slotNumber = slotNumber;
	}

	/**
	 * Returns the epoch is a time period which is divided into slots.
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 14}</pre>
	 * 
	 * @return the epoch is a time period which is divided into slots.
	 */
	public BigInteger getEpochNumber(){
		return epochNumber;
	}

	/**
	 * Defines an epoch is a time period which is divided into slots.
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 14}</pre>
	 * 
	 * @param epochNumber an epoch is a time period which is divided into slots.
	 */
	public void setEpochNumber(BigInteger epochNumber){
		if (epochNumber == null) {
			throw new IllegalArgumentException("Value of epochNumber cannot be null");
		}

		if (epochNumber.compareTo(new BigInteger("0")) < 0){
			throw new IllegalArgumentException("'" + epochNumber + "': value of epochNumber cannot be less than 0");
		}

		this.epochNumber = epochNumber;
	}

	/**
	 * Returns the height.
	 * 
	 * @return the height
	 */
	public Height getHeight(){
		return height;
	}

	/**
	 * Defines the height.
	 * 
	 * @param height the height
	 */
	public void setHeight(Height height){
		if (height == null) {
			throw new IllegalArgumentException("Value of height cannot be null");
		}

		this.height = height;
	}

	/**
	 * Returns the 0-based slot index starting from genesis of the blockchain.
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 8086}</pre>
	 * 
	 * @return the 0-based slot index starting from genesis of the blockchain.
	 */
	public BigInteger getAbsoluteSlotNumber(){
		return absoluteSlotNumber;
	}

	/**
	 * Defines the 0-based slot index starting from genesis of the blockchain.
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 8086}</pre>
	 * 
	 * @param absoluteSlotNumber the 0-based slot index starting from genesis of the blockchain.
	 */
	public void setAbsoluteSlotNumber(BigInteger absoluteSlotNumber){
		if (absoluteSlotNumber == null) {
			throw new IllegalArgumentException("Value of absoluteSlotNumber cannot be null");
		}

		if (absoluteSlotNumber.compareTo(new BigInteger("0")) < 0){
			throw new IllegalArgumentException("'" + absoluteSlotNumber + "': value of absoluteSlotNumber cannot be less than 0");
		}

		this.absoluteSlotNumber = absoluteSlotNumber;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
