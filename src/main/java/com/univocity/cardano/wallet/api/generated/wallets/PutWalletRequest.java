package com.univocity.cardano.wallet.api.generated.wallets;

import com.fasterxml.jackson.annotation.*;
import com.univocity.cardano.wallet.api.generated.*;


/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#putWallet(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PutWalletRequest {


	@JsonProperty("name")
	private String name;

	/**
	 * Returns the name (optional).
	 * - Length range from {@code 1} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Alan's Wallet}</pre>
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Defines the name (optional).
	 * - Length range from {@code 1} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Alan's Wallet}</pre>
	 * 
	 * @param name the name
	 */
	public void setName(String name){
		if (name == null) {
			this.name = name;
			return;
		}

		if (name.length() < 1) {
			throw new IllegalArgumentException("Length of name must have at least 1 characters");
		}

		if (name.length() > 255) {
			throw new IllegalArgumentException("Length of name cannot exceed 255 characters");
		}

		this.name = name;
	}
}
