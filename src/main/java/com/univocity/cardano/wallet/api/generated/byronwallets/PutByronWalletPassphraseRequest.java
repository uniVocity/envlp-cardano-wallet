package com.univocity.cardano.wallet.api.generated.byronwallets;

import com.fasterxml.jackson.annotation.*;
import com.univocity.cardano.wallet.api.generated.*;


/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#putByronWalletPassphrase(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PutByronWalletPassphraseRequest {


	@JsonProperty("old_passphrase")
	private String oldPassphrase;

	@JsonProperty("new_passphrase")
	private String newPassphrase;

	/**
	 * Returns the current passphrase if present. (optional).
	 * - Length range from {@code 0} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @return the current passphrase if present.
	 */
	public String getOldPassphrase(){
		return oldPassphrase;
	}

	/**
	 * Defines the current passphrase if present. (optional).
	 * - Length range from {@code 0} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @param oldPassphrase the current passphrase if present.
	 */
	public void setOldPassphrase(String oldPassphrase){
		if (oldPassphrase == null) {
			this.oldPassphrase = oldPassphrase;
			return;
		}

		if (oldPassphrase.length() < 0) {
			throw new IllegalArgumentException("Length of oldPassphrase must have at least 0 characters");
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
