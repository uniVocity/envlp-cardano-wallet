package com.univocity.cardano.wallet.api.generated.wallets;

import com.univocity.cardano.wallet.api.generated.common.*;
import com.fasterxml.jackson.annotation.*;


/**
 * Wallet current balance(s)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Balance {


	@JsonProperty("available")
	private Available available;

	@JsonProperty("reward")
	private Reward reward;

	@JsonProperty("total")
	private Total total;

	/**
	 * Returns the available.
	 * 
	 * @return the available
	 */
	public Available getAvailable(){
		return available;
	}

	/**
	 * Defines the available.
	 * 
	 * @param available the available
	 */
	public void setAvailable(Available available){
		if (available == null) {
			throw new IllegalArgumentException("Value of available cannot be null");
		}

		this.available = available;
	}

	/**
	 * Returns the reward.
	 * 
	 * @return the reward
	 */
	public Reward getReward(){
		return reward;
	}

	/**
	 * Defines the reward.
	 * 
	 * @param reward the reward
	 */
	public void setReward(Reward reward){
		if (reward == null) {
			throw new IllegalArgumentException("Value of reward cannot be null");
		}

		this.reward = reward;
	}

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
}
