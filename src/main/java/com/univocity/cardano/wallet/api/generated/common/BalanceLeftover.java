package com.univocity.cardano.wallet.api.generated.common;


import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * The total balance of assets that **cannot** be migrated.
 * The **ada** component of this balance is the total sum of all dust
 * ada entries in the UTxO set. An ada entry is considered to be dust
 * if its value is not large enough to pay for the marginal cost of
 * including it in a transaction.
 * The **assets** component of this balance is the total sum of all
 * non-ada assets that cannot currently be migrated. Tokens of a
 * non-ada asset cannot be migrated if there is insufficient ada
 * available to pay for their inclusion in a transaction.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class BalanceLeftover {


	@JsonProperty("ada")
	private Ada ada;

	@JsonProperty("assets")
	private ArrayList<AssetsBalanceLeftover> assets;

	/**
	 * Returns the ada.
	 * 
	 * @return the ada
	 */
	public Ada getAda(){
		return ada;
	}

	/**
	 * Defines the ada.
	 * 
	 * @param ada the ada
	 */
	public void setAda(Ada ada){
		if (ada == null) {
			throw new IllegalArgumentException("Value of ada cannot be null");
		}

		this.ada = ada;
	}

	/**
	 * Returns the flat list of assets (possibly empty).
	 * 
	 * @return the flat list of assets (possibly empty).
	 */
	public ArrayList<AssetsBalanceLeftover> getAssets(){
		return assets;
	}

	/**
	 * Defines a flat list of assets (possibly empty).
	 * 
	 * @param assets a flat list of assets (possibly empty).
	 */
	public void setAssets(ArrayList<AssetsBalanceLeftover> assets){
		if (assets == null) {
			throw new IllegalArgumentException("Value of assets cannot be null");
		}

		this.assets = assets;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
