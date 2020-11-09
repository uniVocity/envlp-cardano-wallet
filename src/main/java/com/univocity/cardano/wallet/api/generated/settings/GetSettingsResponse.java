package com.univocity.cardano.wallet.api.generated.settings;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#getSettings()}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class GetSettingsResponse {


	@JsonProperty("pool_metadata_source")
	private String poolMetadataSource;

	/**
	 * Returns the pool metadata source.
	 * 
	 * Pool metadata source. This sets the metadata fetching strategy.
	 * Possible values are
	 *   * null -> no fetching
	 *   * direct -> direct fetching
	 *   * uri -> use SMASH server
	 * 
	 * 
	 * @return the pool metadata source
	 */
	public String getPoolMetadataSource(){
		return poolMetadataSource;
	}

	/**
	 * Defines the pool metadata source.
	 * 
	 * Pool metadata source. This sets the metadata fetching strategy.
	 * Possible values are
	 *   * null -> no fetching
	 *   * direct -> direct fetching
	 *   * uri -> use SMASH server
	 * 
	 * 
	 * @param poolMetadataSource the pool metadata source
	 */
	public void setPoolMetadataSource(String poolMetadataSource){
		if (poolMetadataSource == null) {
			throw new IllegalArgumentException("Value of poolMetadataSource cannot be null");
		}

		this.poolMetadataSource = poolMetadataSource;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
