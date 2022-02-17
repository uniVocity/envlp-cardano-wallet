package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#listStakeKeys(String)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ListStakeKeysResponse {


	@JsonProperty("ours")
	private ArrayList<Our> ours;

	@JsonProperty("foreign")
	private ArrayList<Foreign> foreign;

	@JsonProperty("none")
	private None none;

	/**
	 * Returns the stake keys belonging to the wallet.
	 * 
	 * @return the stake keys belonging to the wallet.
	 */
	public ArrayList<Our> getOurs(){
		return ours;
	}

	/**
	 * Defines the stake keys belonging to the wallet.
	 * 
	 * @param ours the stake keys belonging to the wallet.
	 */
	public void setOurs(ArrayList<Our> ours){
		if (ours == null) {
			throw new IllegalArgumentException("Value of ours cannot be null");
		}

		this.ours = ours;
	}

	/**
	 * Returns the stake keys found in the wallet's UTxO, but does not belong to the wallet.
	 * 
	 * @return the stake keys found in the wallet's UTxO, but does not belong to the wallet.
	 */
	public ArrayList<Foreign> getForeign(){
		return foreign;
	}

	/**
	 * Defines the stake keys found in the wallet's UTxO, but does not belong to the wallet.
	 * 
	 * @param foreign the stake keys found in the wallet's UTxO, but does not belong to the wallet.
	 */
	public void setForeign(ArrayList<Foreign> foreign){
		if (foreign == null) {
			throw new IllegalArgumentException("Value of foreign cannot be null");
		}

		this.foreign = foreign;
	}

	/**
	 * Returns the none.
	 * 
	 * @return the none
	 */
	public None getNone(){
		return none;
	}

	/**
	 * Defines the none.
	 * 
	 * @param none the none
	 */
	public void setNone(None none){
		if (none == null) {
			throw new IllegalArgumentException("Value of none cannot be null");
		}

		this.none = none;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
