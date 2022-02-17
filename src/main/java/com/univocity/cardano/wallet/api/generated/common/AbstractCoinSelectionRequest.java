package com.univocity.cardano.wallet.api.generated.common;


import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

public abstract class AbstractCoinSelectionRequest {


	@JsonProperty("payments")
	private ArrayList<Payment> payments;

	/**
	 * Returns the list of target outputs with amount specified.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the list of target outputs with amount specified
	 */
	public ArrayList<Payment> getPayments(){
		return payments;
	}

	/**
	 * Defines a list of target outputs with amount specified.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param payments a list of target outputs with amount specified
	 */
	public void setPayments(ArrayList<Payment> payments){
		if (payments == null) {
			throw new IllegalArgumentException("Value of payments cannot be null");
		}

		this.payments = payments;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
