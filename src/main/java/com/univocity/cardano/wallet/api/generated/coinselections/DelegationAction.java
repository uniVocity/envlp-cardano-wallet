package com.univocity.cardano.wallet.api.generated.coinselections;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * A delegation action.
 * Pool id is only required for "join".
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class DelegationAction {


	@JsonProperty("action")
	private String action;

	@JsonProperty("pool")
	private String pool;

	/**
	 * Returns the action.
	 * - Accepted values: {@code [quit, join]}.
	 * 
	 * @return the action
	 */
	public String getAction(){
		return action;
	}

	/**
	 * Defines the action.
	 * - Accepted values: {@code [quit, join]}.
	 * 
	 * @param action the action
	 */
	public void setAction(String action){
		if (action == null) {
			throw new IllegalArgumentException("Value of action cannot be null");
		}

		this.action = action;
	}

	/**
	 * Returns the unique identifier for the pool. (optional).
	 * - Format: {@code hex|bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code pool1wqaz0q0zhtxlgn0ewssevn2mrtm30fgh2g7hr7z9rj5856457mm}</pre>
	 * 
	 * @return the unique identifier for the pool.
	 */
	public String getPool(){
		return pool;
	}

	/**
	 * Defines a unique identifier for the pool. (optional).
	 * - Format: {@code hex|bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code pool1wqaz0q0zhtxlgn0ewssevn2mrtm30fgh2g7hr7z9rj5856457mm}</pre>
	 * 
	 * @param pool a unique identifier for the pool.
	 */
	public void setPool(String pool){
		if (pool == null) {
			this.pool = pool;
			return;
		}

		this.pool = pool;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
