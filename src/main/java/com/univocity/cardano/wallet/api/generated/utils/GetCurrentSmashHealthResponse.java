package com.univocity.cardano.wallet.api.generated.utils;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#getCurrentSmashHealth(String)}.
 * The status of the SMASH server. Possible values are:
 * health                  | description
 * ---                     | ---
 * `"available"`           | server is awaiting your requests
 * `"unavailable"`         | server is running, but currently unavailable, try again in a short time
 * `"unreachable"`         | server could not be reached or didn't return a health status
 * `"no_smash_configured"` | SMASH is currently not configured, adjust the Settings first
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class GetCurrentSmashHealthResponse {


	@JsonProperty("health")
	private String health;

	/**
	 * Returns the health.
	 * - Accepted values: {@code [available, unavailable, unreachable, no_smash_configured]}.
	 * 
	 * @return the health
	 */
	public String getHealth(){
		return health;
	}

	/**
	 * Defines the health.
	 * - Accepted values: {@code [available, unavailable, unreachable, no_smash_configured]}.
	 * 
	 * @param health the health
	 */
	public void setHealth(String health){
		if (health == null) {
			throw new IllegalArgumentException("Value of health cannot be null");
		}

		this.health = health;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
