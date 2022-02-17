package com.univocity.cardano.wallet.api.generated.sharedkeys;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#postAccountKeyShared(String, String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostAccountKeySharedRequest {


	@JsonProperty("passphrase")
	private String passphrase;

	@JsonProperty("format")
	private String format;

	/**
	 * Returns the master passphrase to lock and protect the wallet for sensitive operation (e.g. sending funds).
	 * - Length range from {@code 10} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @return the master passphrase to lock and protect the wallet for sensitive operation (e.g. sending funds)
	 */
	public String getPassphrase(){
		return passphrase;
	}

	/**
	 * Defines a master passphrase to lock and protect the wallet for sensitive operation (e.g. sending funds).
	 * - Length range from {@code 10} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @param passphrase a master passphrase to lock and protect the wallet for sensitive operation (e.g. sending funds)
	 */
	public void setPassphrase(String passphrase){
		if (passphrase == null) {
			throw new IllegalArgumentException("Value of passphrase cannot be null");
		}

		if (passphrase.codePointCount(0, passphrase.length()) < 10) {
			throw new IllegalArgumentException("Length of passphrase must have at least 10 characters, got '" + passphrase.codePointCount(0, passphrase.length()) + "'");
		}

		if (passphrase.codePointCount(0, passphrase.length()) > 255) {
			throw new IllegalArgumentException("Length of passphrase cannot exceed 255 characters, got '" + passphrase.codePointCount(0, passphrase.length()) + "'");
		}

		this.passphrase = passphrase;
	}

	/**
	 * Returns the determines whether extended (with chain code) or normal (without chain code) key is requested.
	 * - Accepted values: {@code [extended, non_extended]}.
	 * 
	 * @return the determines whether extended (with chain code) or normal (without chain code) key is requested
	 */
	public String getFormat(){
		return format;
	}

	/**
	 * Defines the determines whether extended (with chain code) or normal (without chain code) key is requested.
	 * - Accepted values: {@code [extended, non_extended]}.
	 * 
	 * @param format the determines whether extended (with chain code) or normal (without chain code) key is requested
	 */
	public void setFormat(String format){
		if (format == null) {
			throw new IllegalArgumentException("Value of format cannot be null");
		}

		this.format = format;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
