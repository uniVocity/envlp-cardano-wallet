package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


public abstract class AbstractWithdrawal {


	@JsonProperty("stake_address")
	private String stakeAddress;

	@JsonProperty("amount")
	private Amount amount;

	@JsonProperty("context")
	private String context;

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

	/**
	 * Returns the used when withdrawal or output is ours. (optional).
	 * - Accepted values: {@code [ours]}.
	 * 
	 * @return the used when withdrawal or output is ours.
	 */
	public String getContext(){
		return context;
	}

	/**
	 * Defines the used when withdrawal or output is ours. (optional).
	 * - Accepted values: {@code [ours]}.
	 * 
	 * @param context the used when withdrawal or output is ours.
	 */
	public void setContext(String context){
		if (context == null) {
			this.context = context;
			return;
		}

		this.context = context;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
