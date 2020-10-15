package com.univocity.cardano.wallet.api.generated.common;


import java.util.*;
import com.fasterxml.jackson.annotation.*;


/**
 * Delegation settings
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Delegation {


	@JsonProperty("active")
	private Active active;

	@JsonProperty("next")
	private ArrayList<NextDelegation> next;

	/**
	 * Returns the active.
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "status" : "delegating",
	 *       "target" : "1423856bc91c49e928f6f30f4e8d665d53eb4ab6028bd0ac971809d514c92db1"
	 *     }
	 *   }</pre>
	 * 
	 * @return the active
	 */
	public Active getActive(){
		return active;
	}

	/**
	 * Defines the active.
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "status" : "delegating",
	 *       "target" : "1423856bc91c49e928f6f30f4e8d665d53eb4ab6028bd0ac971809d514c92db1"
	 *     }
	 *   }</pre>
	 * 
	 * @param active the active
	 */
	public void setActive(Active active){
		if (active == null) {
			throw new IllegalArgumentException("Value of active cannot be null");
		}

		this.active = active;
	}

	/**
	 * Returns the next.
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "status" : "not_delegating",
	 *       "changes_at" : {
	 *         "epoch_number" : 14,
	 *         "epoch_start_time" : 1579687599037
	 *       }
	 *     }
	 *   }</pre>
	 * 
	 * @return the next
	 */
	public ArrayList<NextDelegation> getNext(){
		return next;
	}

	/**
	 * Defines the next.
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "status" : "not_delegating",
	 *       "changes_at" : {
	 *         "epoch_number" : 14,
	 *         "epoch_start_time" : 1579687599037
	 *       }
	 *     }
	 *   }</pre>
	 * 
	 * @param next the next
	 */
	public void setNext(ArrayList<NextDelegation> next){
		if (next == null) {
			throw new IllegalArgumentException("Value of next cannot be null");
		}

		this.next = next;
	}
}
