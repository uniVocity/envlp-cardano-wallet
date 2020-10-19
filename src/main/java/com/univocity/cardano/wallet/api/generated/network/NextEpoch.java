package com.univocity.cardano.wallet.api.generated.network;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class NextEpoch {


	@JsonProperty("epoch_number")
	private Integer epochNumber;

	@JsonProperty("epoch_start_time")
	private String epochStartTime;

	/**
	 * Returns the epoch is a time period which is divided into slots.
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 14}</pre>
	 * 
	 * @return the epoch is a time period which is divided into slots.
	 */
	public Integer getEpochNumber(){
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
	public void setEpochNumber(Integer epochNumber){
		if (epochNumber == null) {
			throw new IllegalArgumentException("Value of epochNumber cannot be null");
		}

		if (epochNumber < 0) {
			throw new IllegalArgumentException("Value of epochNumber cannot be less than 0");
		}

		this.epochNumber = epochNumber;
	}

	/**
	 * Returns the epoch start time.
	 * - Format: {@code iso-8601-date-and-time}.
	 * 
	 * - Example: 
	 *   <pre>{@code Thu Feb 28 01:16:45 ACDT 2019}</pre>
	 * 
	 * @return the epoch start time
	 */
	public String getEpochStartTime(){
		return epochStartTime;
	}

	/**
	 * Defines the epoch start time.
	 * - Format: {@code iso-8601-date-and-time}.
	 * 
	 * - Example: 
	 *   <pre>{@code Thu Feb 28 01:16:45 ACDT 2019}</pre>
	 * 
	 * @param epochStartTime the epoch start time
	 */
	public void setEpochStartTime(String epochStartTime){
		if (epochStartTime == null) {
			throw new IllegalArgumentException("Value of epochStartTime cannot be null");
		}

		this.epochStartTime = epochStartTime;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
