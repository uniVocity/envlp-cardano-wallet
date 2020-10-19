package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#quitStakePool(String)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class QuitStakePoolRequest {


	@JsonProperty("passphrase")
	private String passphrase;

	/**
	 * Returns the source Byron wallet's master passphrase.
	 * - Length range from {@code 0} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @return the source Byron wallet's master passphrase.
	 */
	public String getPassphrase(){
		return passphrase;
	}

	/**
	 * Defines the source Byron wallet's master passphrase.
	 * - Length range from {@code 0} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @param passphrase the source Byron wallet's master passphrase.
	 */
	public void setPassphrase(String passphrase){
		if (passphrase == null) {
			throw new IllegalArgumentException("Value of passphrase cannot be null");
		}

		if (passphrase.length() < 0) {
			throw new IllegalArgumentException("Length of passphrase must have at least 0 characters");
		}

		if (passphrase.length() > 255) {
			throw new IllegalArgumentException("Length of passphrase cannot exceed 255 characters");
		}

		this.passphrase = passphrase;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
