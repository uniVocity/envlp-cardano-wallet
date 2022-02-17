package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * The absence of a stake key. The `stake` field shows how much of the wallet funds
 * are not associated with an identifiable stake key.
 * Most likely, these funds are associated with enterprise addresses lacking staking rights.
 * But they /could/ also be associate with more rare types of addreses like pointer addresses.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class None {


	@JsonProperty("stake")
	private Stake stake;

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

	@Override
	public String toString() {
		return printObject(this);
	}

}
