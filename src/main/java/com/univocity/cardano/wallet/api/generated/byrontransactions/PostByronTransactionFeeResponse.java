package com.univocity.cardano.wallet.api.generated.byrontransactions;

import com.fasterxml.jackson.annotation.*;
import com.univocity.cardano.wallet.api.generated.*;
import com.univocity.cardano.wallet.api.generated.common.*;


/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#postByronTransactionFee(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostByronTransactionFeeResponse {


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
