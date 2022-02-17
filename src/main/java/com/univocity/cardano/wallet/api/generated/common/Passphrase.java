package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * Information about the wallet's passphrase
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Passphrase {


	@JsonProperty("last_updated_at")
	private String lastUpdatedAt;

	/**
	 * Returns the last updated at.
	 * - Format: {@code iso-8601-date-and-time}.
	 * 
	 * - Example: 
	 *   <pre>{@code Wed Feb 27 11:46:45 BRT 2019}</pre>
	 * 
	 * @return the last updated at
	 */
	public String getLastUpdatedAt(){
		return lastUpdatedAt;
	}

	/**
	 * Defines the last updated at.
	 * - Format: {@code iso-8601-date-and-time}.
	 * 
	 * - Example: 
	 *   <pre>{@code Wed Feb 27 11:46:45 BRT 2019}</pre>
	 * 
	 * @param lastUpdatedAt the last updated at
	 */
	public void setLastUpdatedAt(String lastUpdatedAt){
		if (lastUpdatedAt == null) {
			throw new IllegalArgumentException("Value of lastUpdatedAt cannot be null");
		}

		this.lastUpdatedAt = lastUpdatedAt;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
