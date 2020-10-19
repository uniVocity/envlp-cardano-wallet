package com.univocity.cardano.wallet.api.generated.byronaddresses;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#importAddresses(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ImportAddressesRequest {


	@JsonProperty("addresses")
	private ArrayList<String> addresses;

	/**
	 * Returns the imported addresses.
	 * - Format: {@code base58|bech32}.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * - Example: 
	 *   <pre>{@code addr1sjck9mdmfyhzvjhydcjllgj9vjvl522w0573ncustrrr2rg7h9azg4cyqd36yyd48t5ut72hgld0fg2xfvz82xgwh7wal6g2xt8n996s3xvu5g}</pre>
	 * 
	 * @return the imported addresses.
	 */
	public ArrayList<String> getAddresses(){
		return addresses;
	}

	/**
	 * Defines the imported addresses.
	 * - Format: {@code base58|bech32}.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * - Example: 
	 *   <pre>{@code addr1sjck9mdmfyhzvjhydcjllgj9vjvl522w0573ncustrrr2rg7h9azg4cyqd36yyd48t5ut72hgld0fg2xfvz82xgwh7wal6g2xt8n996s3xvu5g}</pre>
	 * 
	 * @param addresses the imported addresses.
	 */
	public void setAddresses(ArrayList<String> addresses){
		if (addresses == null) {
			throw new IllegalArgumentException("Value of addresses cannot be null");
		}

		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
