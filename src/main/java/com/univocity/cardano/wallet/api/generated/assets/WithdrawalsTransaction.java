package com.univocity.cardano.wallet.api.generated.assets;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class WithdrawalsTransaction {


	@JsonProperty("stake_address")
	private String stakeAddress;

	@JsonProperty("amount")
	private Amount amount;

	/**
	 * Returns the stake address.
	 * - Format: {@code bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code stake1sjck9mdmfyhzvjhydcjllgj9vjvl522w0573ncustrrr2rg7h9azg4cyqd36yyd48t5ut72hgld0fg2x}</pre>
	 * 
	 * @return the stake address
	 */
	public String getStakeAddress(){
		return stakeAddress;
	}

	/**
	 * Defines the stake address.
	 * - Format: {@code bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code stake1sjck9mdmfyhzvjhydcjllgj9vjvl522w0573ncustrrr2rg7h9azg4cyqd36yyd48t5ut72hgld0fg2x}</pre>
	 * 
	 * @param stakeAddress the stake address
	 */
	public void setStakeAddress(String stakeAddress){
		if (stakeAddress == null) {
			throw new IllegalArgumentException("Value of stakeAddress cannot be null");
		}

		this.stakeAddress = stakeAddress;
	}

	/**
	 * Returns the amount.
	 * 
	 * @return the amount
	 */
	public Amount getAmount(){
		return amount;
	}

	/**
	 * Defines the amount.
	 * 
	 * @param amount the amount
	 */
	public void setAmount(Amount amount){
		if (amount == null) {
			throw new IllegalArgumentException("Value of amount cannot be null");
		}

		this.amount = amount;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
