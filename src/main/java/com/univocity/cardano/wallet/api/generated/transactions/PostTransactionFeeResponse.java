package com.univocity.cardano.wallet.api.generated.transactions;

import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.api.generated.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#postTransactionFee(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostTransactionFeeResponse {


	@JsonProperty("estimated_min")
	private EstimatedMin estimatedMin;

	@JsonProperty("estimated_max")
	private EstimatedMax estimatedMax;

	/**
	 * Returns the estimated min.
	 * 
	 * @return the estimated min
	 */
	public EstimatedMin getEstimatedMin(){
		return estimatedMin;
	}

	/**
	 * Defines the estimated min.
	 * 
	 * @param estimatedMin the estimated min
	 */
	public void setEstimatedMin(EstimatedMin estimatedMin){
		if (estimatedMin == null) {
			throw new IllegalArgumentException("Value of estimatedMin cannot be null");
		}

		this.estimatedMin = estimatedMin;
	}

	/**
	 * Returns the estimated max.
	 * 
	 * @return the estimated max
	 */
	public EstimatedMax getEstimatedMax(){
		return estimatedMax;
	}

	/**
	 * Defines the estimated max.
	 * 
	 * @param estimatedMax the estimated max
	 */
	public void setEstimatedMax(EstimatedMax estimatedMax){
		if (estimatedMax == null) {
			throw new IllegalArgumentException("Value of estimatedMax cannot be null");
		}

		this.estimatedMax = estimatedMax;
	}
}
