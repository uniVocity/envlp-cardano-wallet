package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

public abstract class AbstractFeeResponse {


	@JsonProperty("estimated_min")
	private EstimatedMin estimatedMin;

	@JsonProperty("estimated_max")
	private EstimatedMax estimatedMax;

	@JsonProperty("deposit")
	private Deposit deposit;

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

	/**
	 * Returns the deposit.
	 * 
	 * @return the deposit
	 */
	public Deposit getDeposit(){
		return deposit;
	}

	/**
	 * Defines the deposit.
	 * 
	 * @param deposit the deposit
	 */
	public void setDeposit(Deposit deposit){
		if (deposit == null) {
			throw new IllegalArgumentException("Value of deposit cannot be null");
		}

		this.deposit = deposit;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
