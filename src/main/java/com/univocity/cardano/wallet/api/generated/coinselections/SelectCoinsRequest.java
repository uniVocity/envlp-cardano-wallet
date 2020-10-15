package com.univocity.cardano.wallet.api.generated.coinselections;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import com.univocity.cardano.wallet.api.generated.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#selectCoins(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SelectCoinsRequest {


	@JsonProperty("payments")
	private ArrayList<Payment> payments;

	/**
	 * Returns the list of target outputs.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * @return the list of target outputs
	 */
	public ArrayList<Payment> getPayments(){
		return payments;
	}

	/**
	 * Defines a list of target outputs.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * @param payments a list of target outputs
	 */
	public void setPayments(ArrayList<Payment> payments){
		if (payments == null) {
			throw new IllegalArgumentException("Value of payments cannot be null");
		}

		this.payments = payments;
	}
}
