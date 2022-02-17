package com.univocity.cardano.wallet.api.generated.common;


import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Current non-Ada asset holdings of the wallet.
 * The amount of assets available to spend may be less than the total
 * unspent assets due to transaction change amounts which are yet to
 * be fully confirmed (pending).
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Assets {


	@JsonProperty("available")
	private ArrayList<AvailableAsset> available;

	@JsonProperty("total")
	private ArrayList<TotalAsset> total;

	/**
	 * Returns the available.
	 * 
	 * Available UTxO asset balances (funds that can be spent without
	 * condition).
	 * 
	 * 
	 * @return the available
	 */
	public ArrayList<AvailableAsset> getAvailable(){
		return available;
	}

	/**
	 * Defines the available.
	 * 
	 * Available UTxO asset balances (funds that can be spent without
	 * condition).
	 * 
	 * 
	 * @param available the available
	 */
	public void setAvailable(ArrayList<AvailableAsset> available){
		if (available == null) {
			throw new IllegalArgumentException("Value of available cannot be null");
		}

		this.available = available;
	}

	/**
	 * Returns the total asset balances (available balances plus pending change balances).
	 * 
	 * @return the total asset balances (available balances plus pending change balances).
	 */
	public ArrayList<TotalAsset> getTotal(){
		return total;
	}

	/**
	 * Defines the total asset balances (available balances plus pending change balances).
	 * 
	 * @param total the total asset balances (available balances plus pending change balances).
	 */
	public void setTotal(ArrayList<TotalAsset> total){
		if (total == null) {
			throw new IllegalArgumentException("Value of total cannot be null");
		}

		this.total = total;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
