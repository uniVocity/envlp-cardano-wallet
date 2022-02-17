package com.univocity.cardano.wallet.api.generated.common;


import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


public abstract class AbstractCoinSelection {


	@JsonProperty("stake_address")
	private String stakeAddress;

	@JsonProperty("derivation_path")
	private ArrayList<String> derivationPath;

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
	 * Returns the derivation path.
	 * 
	 * An individual segment within a derivation path.
	 * The `H` suffix indicates a _Hardened_ child private key, which
	 * means that children of this key cannot be derived from the public
	 * key. Indices without a `H` suffix are called _Soft_.
	 * 
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1852H}</pre>
	 * 
	 * @return the derivation path
	 */
	public ArrayList<String> getDerivationPath(){
		return derivationPath;
	}

	/**
	 * Defines the derivation path.
	 * 
	 * An individual segment within a derivation path.
	 * The `H` suffix indicates a _Hardened_ child private key, which
	 * means that children of this key cannot be derived from the public
	 * key. Indices without a `H` suffix are called _Soft_.
	 * 
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1852H}</pre>
	 * 
	 * @param derivationPath the derivation path
	 */
	public void setDerivationPath(ArrayList<String> derivationPath){
		if (derivationPath == null) {
			throw new IllegalArgumentException("Value of derivationPath cannot be null");
		}

		this.derivationPath = derivationPath;
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
