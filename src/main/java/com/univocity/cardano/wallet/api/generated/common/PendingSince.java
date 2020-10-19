package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * The point in time at which a transaction became pending.
 * {@code if: status == pending}
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PendingSince {


	@JsonProperty("time")
	private String time;

	@JsonProperty("block")
	private Block block;

	/**
	 * Returns the time.
	 * - Format: {@code iso-8601-date-and-time}.
	 * 
	 * - Example: 
	 *   <pre>{@code Thu Feb 28 01:16:45 ACDT 2019}</pre>
	 * 
	 * @return the time
	 */
	public String getTime(){
		return time;
	}

	/**
	 * Defines the time.
	 * - Format: {@code iso-8601-date-and-time}.
	 * 
	 * - Example: 
	 *   <pre>{@code Thu Feb 28 01:16:45 ACDT 2019}</pre>
	 * 
	 * @param time the time
	 */
	public void setTime(String time){
		if (time == null) {
			throw new IllegalArgumentException("Value of time cannot be null");
		}

		this.time = time;
	}

	/**
	 * Returns the block.
	 * 
	 * @return the block
	 */
	public Block getBlock(){
		return block;
	}

	/**
	 * Defines the block.
	 * 
	 * @param block the block
	 */
	public void setBlock(Block block){
		if (block == null) {
			throw new IllegalArgumentException("Value of block cannot be null");
		}

		this.block = block;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
