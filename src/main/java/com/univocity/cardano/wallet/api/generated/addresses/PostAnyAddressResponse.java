package com.univocity.cardano.wallet.api.generated.addresses;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#postAnyAddress(okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostAnyAddressResponse {


	@JsonProperty("address")
	private String address;

	/**
	 * Returns the Shelley address representing either enterprise, reward account or delegating address.
	 * - Format: {@code bech32}.
	 * - Pattern: {@code ^((addr)|(stake)|(addr_test)|(stake_test))1[0-9a-z]*$}.
	 * 
	 * - Example: 
	 *   <pre>{@code [stake17xt2z3pa7etaxp7jurdg0m8jhsmtp4r2z56pd3a5q3jhxycdxzmx9, addr1wy5np0m5x03tax3kcdh6e2cet98qcfs80wtv4cyvl5taclc6dnd8e, addr1xy5np0m5x03tax3kcdh6e2cet98qcfs80wtv4cyvl5tacluk59zrmajh6vra9cx6slk090pkkr2x59f5zmrmgpr9wvfs37hjk4]}</pre>
	 * 
	 * @return the Shelley address representing either enterprise, reward account or delegating address
	 */
	public String getAddress(){
		return address;
	}

	/**
	 * Defines a Shelley address representing either enterprise, reward account or delegating address.
	 * - Format: {@code bech32}.
	 * - Pattern: {@code ^((addr)|(stake)|(addr_test)|(stake_test))1[0-9a-z]*$}.
	 * 
	 * - Example: 
	 *   <pre>{@code [stake17xt2z3pa7etaxp7jurdg0m8jhsmtp4r2z56pd3a5q3jhxycdxzmx9, addr1wy5np0m5x03tax3kcdh6e2cet98qcfs80wtv4cyvl5taclc6dnd8e, addr1xy5np0m5x03tax3kcdh6e2cet98qcfs80wtv4cyvl5tacluk59zrmajh6vra9cx6slk090pkkr2x59f5zmrmgpr9wvfs37hjk4]}</pre>
	 * 
	 * @param address a Shelley address representing either enterprise, reward account or delegating address
	 */
	public void setAddress(String address){
		if (address == null) {
			throw new IllegalArgumentException("Value of address cannot be null");
		}

		this.address = address;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
