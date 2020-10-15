package com.univocity.cardano.wallet.api.generated.wallets;

import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.api.generated.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#putWalletPassphrase(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PutWalletPassphraseRequest {


	@JsonProperty("old_passphrase")
	private String oldPassphrase;

	@JsonProperty("new_passphrase")
	private String newPassphrase;

	/**
	 * Returns the current passphrase.
	 * - Length range from {@code 10} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @return the current passphrase.
	 */
	public String getOldPassphrase(){
		return oldPassphrase;
	}

	/**
	 * Defines the current passphrase.
	 * - Length range from {@code 10} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @param oldPassphrase the current passphrase.
	 */
	public void setOldPassphrase(String oldPassphrase){
		if (oldPassphrase == null) {
			throw new IllegalArgumentException("Value of oldPassphrase cannot be null");
		}

		if (oldPassphrase.length() < 10) {
			throw new IllegalArgumentException("Length of oldPassphrase must have at least 10 characters");
		}

		if (oldPassphrase.length() > 255) {
			throw new IllegalArgumentException("Length of oldPassphrase cannot exceed 255 characters");
		}

		this.oldPassphrase = oldPassphrase;
	}

	/**
	 * Returns the master passphrase to lock and protect the wallet for sensitive operation (e.g. sending funds).
	 * - Length range from {@code 10} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @return the master passphrase to lock and protect the wallet for sensitive operation (e.g. sending funds).
	 */
	public String getNewPassphrase(){
		return newPassphrase;
	}

	/**
	 * Defines a master passphrase to lock and protect the wallet for sensitive operation (e.g. sending funds).
	 * - Length range from {@code 10} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @param newPassphrase a master passphrase to lock and protect the wallet for sensitive operation (e.g. sending funds).
	 */
	public void setNewPassphrase(String newPassphrase){
		if (newPassphrase == null) {
			throw new IllegalArgumentException("Value of newPassphrase cannot be null");
		}

		if (newPassphrase.length() < 10) {
			throw new IllegalArgumentException("Length of newPassphrase must have at least 10 characters");
		}

		if (newPassphrase.length() > 255) {
			throw new IllegalArgumentException("Length of newPassphrase cannot exceed 255 characters");
		}

		this.newPassphrase = newPassphrase;
	}
}
