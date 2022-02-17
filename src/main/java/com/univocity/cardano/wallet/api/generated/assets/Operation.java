package com.univocity.cardano.wallet.api.generated.assets;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Operation {


	@JsonProperty("mint")
	private Mint mint;

	@JsonProperty("burn")
	private String burn;

	/**
	 * Returns the mint (optional).
	 * 
	 * @return the mint
	 */
	public Mint getMint(){
		return mint;
	}

	/**
	 * Defines the mint (optional).
	 * 
	 * @param mint the mint
	 */
	public void setMint(Mint mint){
		if (mint == null) {
			this.mint = mint;
			return;
		}

		this.mint = mint;
	}

	/**
	 * Returns the burn (optional).
	 * 
	 * @return the burn
	 */
	public String getBurn(){
		return burn;
	}

	/**
	 * Defines the burn (optional).
	 * 
	 * @param burn the burn
	 */
	public void setBurn(String burn){
		if (burn == null) {
			this.burn = burn;
			return;
		}

		this.burn = burn;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
