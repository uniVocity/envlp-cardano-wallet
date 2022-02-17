package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Foreign {


	@JsonProperty("key")
	private String key;

	@JsonProperty("stake")
	private Stake stake;

	@JsonProperty("reward_balance")
	private RewardBalance rewardBalance;

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

	@Override
	public String toString() {
		return printObject(this);
	}

}
