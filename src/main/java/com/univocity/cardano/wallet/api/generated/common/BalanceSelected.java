package com.univocity.cardano.wallet.api.generated.common;


import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * The total balance of assets that **can** be migrated.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class BalanceSelected {


	@JsonProperty("ada")
	private Ada ada;

	@JsonProperty("assets")
	private ArrayList<AssetsBalanceSelected> assets;

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
	public ArrayList<AssetsBalanceSelected> getAssets(){
		return assets;
	}

	/**
	 * Defines a flat list of assets (possibly empty).
	 * 
	 * @param assets a flat list of assets (possibly empty).
	 */
	public void setAssets(ArrayList<AssetsBalanceSelected> assets){
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
