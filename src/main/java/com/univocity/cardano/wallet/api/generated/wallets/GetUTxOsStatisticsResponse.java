package com.univocity.cardano.wallet.api.generated.wallets;

import com.fasterxml.jackson.annotation.*;
import com.univocity.cardano.wallet.api.generated.*;
import com.univocity.cardano.wallet.api.generated.common.*;


/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#getUTxOsStatistics(String)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class GetUTxOsStatisticsResponse {


	@JsonProperty("total")
	private Total total;

	@JsonProperty("scale")
	private String scale;

	@JsonProperty("distribution")
	private Object distribution;

	/**
	 * Returns the total.
	 * 
	 * @return the total
	 */
	public Total getTotal(){
		return total;
	}

	/**
	 * Defines the total.
	 * 
	 * @param total the total
	 */
	public void setTotal(Total total){
		if (total == null) {
			throw new IllegalArgumentException("Value of total cannot be null");
		}

		this.total = total;
	}

	/**
	 * Returns the scale.
	 * - Accepted values: {@code [log10]}.
	 * 
	 * @return the scale
	 */
	public String getScale(){
		return scale;
	}

	/**
	 * Defines the scale.
	 * - Accepted values: {@code [log10]}.
	 * 
	 * @param scale the scale
	 */
	public void setScale(String scale){
		if (scale == null) {
			throw new IllegalArgumentException("Value of scale cannot be null");
		}

		this.scale = scale;
	}

	/**
	 * Returns the distribution.
	 * 
	 * @return the distribution
	 */
	public Object getDistribution(){
		return distribution;
	}

	/**
	 * Defines the distribution.
	 * 
	 * @param distribution the distribution
	 */
	public void setDistribution(Object distribution){
		if (distribution == null) {
			throw new IllegalArgumentException("Value of distribution cannot be null");
		}

		this.distribution = distribution;
	}
}
