package com.univocity.cardano.wallet.api.generated.proxy;

import com.fasterxml.jackson.annotation.*;
import com.univocity.cardano.wallet.api.generated.*;

import java.util.regex.*;


/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#postExternalTransaction(okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostExternalTransactionResponse {


	@JsonProperty("id")
	private String id;

	/**
	 * Returns the unique identifier for this transaction.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 64}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1423856bc91c49e928f6f30f4e8d665d53eb4ab6028bd0ac971809d514c92db1}</pre>
	 * 
	 * @return the unique identifier for this transaction
	 */
	public String getId(){
		return id;
	}

	/**
	 * Defines a unique identifier for this transaction.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 64}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1423856bc91c49e928f6f30f4e8d665d53eb4ab6028bd0ac971809d514c92db1}</pre>
	 * 
	 * @param id a unique identifier for this transaction
	 */
	public void setId(String id){
		if (id == null) {
			throw new IllegalArgumentException("Value of id cannot be null");
		}

		if (id.length() < 64) {
			throw new IllegalArgumentException("Length of id must have at least 64 characters");
		}

		if (id.length() > 64) {
			throw new IllegalArgumentException("Length of id cannot exceed 64 characters");
		}

	    if(!Pattern.compile("\\p{XDigit}+").matcher(id).matches()){
    		throw new IllegalArgumentException("Value provided for id does not represent a hexadecimal");
    	}

		this.id = id;
	}
}
