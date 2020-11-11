package com.univocity.cardano.wallet.api.generated.experimental;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#signMetadata(String, String, String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SignMetadataResponse {


	@JsonProperty("result")
	private String result;

	public SignMetadataResponse(){
		this(null);
	}

	public SignMetadataResponse(String result){
		this.result = result;
	}

	/**
	 * Returns the result.
	 * - Format: {@code binary}.
	 * 
	 * @return the result
	 */
	public String getResult(){
		return result;
	}

	/**
	 * Defines the result.
	 * - Format: {@code binary}.
	 * 
	 * @param result the result
	 */
	public void setResult(String result){
		if (result == null) {
			throw new IllegalArgumentException("Value of result cannot be null");
		}

		this.result = result;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
