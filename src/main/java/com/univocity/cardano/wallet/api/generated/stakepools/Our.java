package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Our {


	@JsonProperty("index")
	private Long index;

	@JsonProperty("key")
	private String key;

	@JsonProperty("stake")
	private Stake stake;

	@JsonProperty("reward_balance")
	private RewardBalance rewardBalance;

	@JsonProperty("delegation")
	private Delegation delegation;

	/**
	 * Returns the soft derivation index.
	 * - Value range from {@code 0} to {@code 2147483647}.
	 * 
	 * @return the soft derivation index.
	 */
	public Long getIndex(){
		return index;
	}

	/**
	 * Defines a soft derivation index.
	 * - Value range from {@code 0} to {@code 2147483647}.
	 * 
	 * @param index a soft derivation index.
	 */
	public void setIndex(Long index){
		if (index == null) {
			throw new IllegalArgumentException("Value of index cannot be null");
		}

		if (index < 0) {
			throw new IllegalArgumentException("Value of index cannot be less than 0, got '" + index + "'");
		}

		if (index > 2147483647L) {
			throw new IllegalArgumentException("Value of index cannot be greater than 2147483647, got '" + index + "'");
		}

		this.index = index;
	}

	/**
	 * Returns the key.
	 * - Format: {@code bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code stake1sjck9mdmfyhzvjhydcjllgj9vjvl522w0573ncustrrr2rg7h9azg4cyqd36yyd48t5ut72hgld0fg2x}</pre>
	 * 
	 * @return the key
	 */
	public String getKey(){
		return key;
	}

	/**
	 * Defines the key.
	 * - Format: {@code bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code stake1sjck9mdmfyhzvjhydcjllgj9vjvl522w0573ncustrrr2rg7h9azg4cyqd36yyd48t5ut72hgld0fg2x}</pre>
	 * 
	 * @param key the key
	 */
	public void setKey(String key){
		if (key == null) {
			throw new IllegalArgumentException("Value of key cannot be null");
		}

		this.key = key;
	}

	/**
	 * Returns the stake.
	 * 
	 * @return the stake
	 */
	public Stake getStake(){
		return stake;
	}

	/**
	 * Defines the stake.
	 * 
	 * @param stake the stake
	 */
	public void setStake(Stake stake){
		if (stake == null) {
			throw new IllegalArgumentException("Value of stake cannot be null");
		}

		this.stake = stake;
	}

	/**
	 * Returns the reward balance.
	 * 
	 * @return the reward balance
	 */
	public RewardBalance getRewardBalance(){
		return rewardBalance;
	}

	/**
	 * Defines the reward balance.
	 * 
	 * @param rewardBalance the reward balance
	 */
	public void setRewardBalance(RewardBalance rewardBalance){
		if (rewardBalance == null) {
			throw new IllegalArgumentException("Value of rewardBalance cannot be null");
		}

		this.rewardBalance = rewardBalance;
	}

	/**
	 * Returns the delegation.
	 * 
	 * @return the delegation
	 */
	public Delegation getDelegation(){
		return delegation;
	}

	/**
	 * Defines the delegation.
	 * 
	 * @param delegation the delegation
	 */
	public void setDelegation(Delegation delegation){
		if (delegation == null) {
			throw new IllegalArgumentException("Value of delegation cannot be null");
		}

		this.delegation = delegation;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
