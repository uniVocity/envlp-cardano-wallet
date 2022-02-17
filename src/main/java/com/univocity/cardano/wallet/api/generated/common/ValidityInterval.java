package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * <p>status: <strong>⚠ under development</strong></p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ValidityInterval {


	@JsonProperty("invalid_before")
	private String invalidBefore;

	@JsonProperty("invalid_hereafter")
	private String invalidHereafter;

	/**
	 * Returns the invalid before.
	 * 
	 * @return the invalid before
	 */
	public String getInvalidBefore(){
		return invalidBefore;
	}

	/**
	 * Defines the invalid before.
	 * 
	 * @param invalidBefore the invalid before
	 */
	public void setInvalidBefore(String invalidBefore){
		if (invalidBefore == null) {
			throw new IllegalArgumentException("Value of invalidBefore cannot be null");
		}

		this.invalidBefore = invalidBefore;
	}

	/**
	 * Returns the invalid hereafter.
	 * 
	 * @return the invalid hereafter
	 */
	public String getInvalidHereafter(){
		return invalidHereafter;
	}

	/**
	 * Defines the invalid hereafter.
	 * 
	 * @param invalidHereafter the invalid hereafter
	 */
	public void setInvalidHereafter(String invalidHereafter){
		if (invalidHereafter == null) {
			throw new IllegalArgumentException("Value of invalidHereafter cannot be null");
		}

		this.invalidHereafter = invalidHereafter;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
