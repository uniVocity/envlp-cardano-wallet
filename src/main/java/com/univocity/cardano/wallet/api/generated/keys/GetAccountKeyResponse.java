package com.univocity.cardano.wallet.api.generated.keys;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#getAccountKey(String)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class GetAccountKeyResponse {


	@JsonProperty("result")
	private String result;

	public GetAccountKeyResponse(){
		this(null);
	}

	public GetAccountKeyResponse(String result){
		this.result = result;
	}

	/**
	 * Returns the result.
	 * - Format: {@code bech32}.
	 * - Pattern: {@code ^((acct_xvk)|(acct_vk)|(acct_shared_xvk)|(acct_shared_vk))1[0-9a-z]*$}.
	 * 
	 * @return the result
	 */
	public String getResult(){
		return result;
	}

	/**
	 * Defines the result.
	 * - Format: {@code bech32}.
	 * - Pattern: {@code ^((acct_xvk)|(acct_vk)|(acct_shared_xvk)|(acct_shared_vk))1[0-9a-z]*$}.
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
