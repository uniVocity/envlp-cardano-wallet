package com.univocity.cardano.wallet.api.generated.addresses;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#listAddresses(String, String)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ListAddressesResponse {


	@JsonProperty("id")
	private String id;

	@JsonProperty("state")
	private String state;

	/**
	 * Returns the id.
	 * - Format: {@code base58|bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code addr1sjck9mdmfyhzvjhydcjllgj9vjvl522w0573ncustrrr2rg7h9azg4cyqd36yyd48t5ut72hgld0fg2xfvz82xgwh7wal6g2xt8n996s3xvu5g}</pre>
	 * 
	 * @return the id
	 */
	public String getId(){
		return id;
	}

	/**
	 * Defines the id.
	 * - Format: {@code base58|bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code addr1sjck9mdmfyhzvjhydcjllgj9vjvl522w0573ncustrrr2rg7h9azg4cyqd36yyd48t5ut72hgld0fg2xfvz82xgwh7wal6g2xt8n996s3xvu5g}</pre>
	 * 
	 * @param id the id
	 */
	public void setId(String id){
		if (id == null) {
			throw new IllegalArgumentException("Value of id cannot be null");
		}

		this.id = id;
	}

	/**
	 * Returns the state.
	 * - Accepted values: {@code [used, unused]}.
	 * 
	 * @return the state
	 */
	public String getState(){
		return state;
	}

	/**
	 * Defines the state.
	 * - Accepted values: {@code [used, unused]}.
	 * 
	 * @param state the state
	 */
	public void setState(String state){
		if (state == null) {
			throw new IllegalArgumentException("Value of state cannot be null");
		}

		this.state = state;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
