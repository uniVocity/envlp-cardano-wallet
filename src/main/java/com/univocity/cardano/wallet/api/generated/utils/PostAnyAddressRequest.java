package com.univocity.cardano.wallet.api.generated.utils;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#postAnyAddress(okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostAnyAddressRequest {


	@JsonProperty("payment")
	private String payment;

	@JsonProperty("stake")
	private String stake;

	/**
	 * Returns the payment (optional).
	 * 
	 * @return the payment
	 */
	public String getPayment(){
		return payment;
	}

	/**
	 * Defines the payment (optional).
	 * 
	 * @param payment the payment
	 */
	public void setPayment(String payment){
		if (payment == null) {
			this.payment = payment;
			return;
		}

		this.payment = payment;
	}

	/**
	 * Returns the stake (optional).
	 * 
	 * @return the stake
	 */
	public String getStake(){
		return stake;
	}

	/**
	 * Defines the stake (optional).
	 * 
	 * @param stake the stake
	 */
	public void setStake(String stake){
		if (stake == null) {
			this.stake = stake;
			return;
		}

		this.stake = stake;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
