package com.univocity.cardano.wallet.api.generated.byronaddresses;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#createAddress(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CreateAddressRequest {


	@JsonProperty("passphrase")
	private String passphrase;

	@JsonProperty("address_index")
	private Double addressIndex;

	/**
	 * Returns the master passphrase to lock and protect the wallet for sensitive operation (e.g. sending funds).
	 * - Length range from {@code 0} to {@code 255}.
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
	 * - Length range from {@code 0} to {@code 255}.
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

		if (passphrase.length() < 0) {
			throw new IllegalArgumentException("Length of passphrase must have at least 0 characters");
		}

		if (passphrase.length() > 255) {
			throw new IllegalArgumentException("Length of passphrase cannot exceed 255 characters");
		}

		this.passphrase = passphrase;
	}

	/**
	 * Returns the address derivation index. (optional).
	 * - Value range from {@code 0} to {@code 4294967295}.
	 * 
	 * @return the address derivation index.
	 */
	public Double getAddressIndex(){
		return addressIndex;
	}

	/**
	 * Defines an address derivation index. (optional).
	 * - Value range from {@code 0} to {@code 4294967295}.
	 * 
	 * @param addressIndex an address derivation index.
	 */
	public void setAddressIndex(Double addressIndex){
		if (addressIndex == null) {
			this.addressIndex = addressIndex;
			return;
		}

		if (addressIndex < 0) {
			throw new IllegalArgumentException("Value of addressIndex cannot be less than 0");
		}

		if (addressIndex > 4294967295L) {
			throw new IllegalArgumentException("Value of addressIndex cannot be greater than 4294967295");
		}

		this.addressIndex = addressIndex;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
