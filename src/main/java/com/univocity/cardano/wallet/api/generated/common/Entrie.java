package com.univocity.cardano.wallet.api.generated.common;


import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Entrie {


	@JsonProperty("ada")
	private Ada ada;

	@JsonProperty("ada_minimum")
	private AdaMinimum adaMinimum;

	@JsonProperty("assets")
	private ArrayList<AssetsEntrie> assets;

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
	 * Returns the ada minimum.
	 * 
	 * @return the ada minimum
	 */
	public AdaMinimum getAdaMinimum(){
		return adaMinimum;
	}

	/**
	 * Defines the ada minimum.
	 * 
	 * @param adaMinimum the ada minimum
	 */
	public void setAdaMinimum(AdaMinimum adaMinimum){
		if (adaMinimum == null) {
			throw new IllegalArgumentException("Value of adaMinimum cannot be null");
		}

		this.adaMinimum = adaMinimum;
	}

	/**
	 * Returns the set of non-ada assets associated with this UTxO entry.
	 * 
	 * @return the set of non-ada assets associated with this UTxO entry.
	 */
	public ArrayList<AssetsEntrie> getAssets(){
		return assets;
	}

	/**
	 * Defines the set of non-ada assets associated with this UTxO entry.
	 * 
	 * @param assets the set of non-ada assets associated with this UTxO entry.
	 */
	public void setAssets(ArrayList<AssetsEntrie> assets){
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
