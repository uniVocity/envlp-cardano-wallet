package com.univocity.cardano.wallet.api.generated.settings;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * Settings
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Settings {


	@JsonProperty("pool_metadata_source")
	private String poolMetadataSource;

	/**
	 * Returns the pool metadata source.
	 * 
	 * Select stake pool metadata fetching strategy:
	 *   - `none` - metadata is not fetched at all,
	 *   - `direct` - metadata is fetched directly URLs registered on chain,
	 *   - `uri` - metadata is fetched from an external Stake-Pool Metadata Aggregation Server (SMASH)
	 * After update existing metadata will be dropped forcing it to re-sync automatically with the new setting.
	 * 
	 * - Pattern: {@code ^(none|direct|https?:\/\/[a-zA-Z0-9-_~.]+(:[0-9]+)?/?)$}.
	 * 
	 * - Example: 
	 *   <pre>{@code https://smash.cardano-mainnet.iohk.io/}</pre>
	 * 
	 * @return the pool metadata source
	 */
	public String getPoolMetadataSource(){
		return poolMetadataSource;
	}

	/**
	 * Defines the pool metadata source.
	 * 
	 * Select stake pool metadata fetching strategy:
	 *   - `none` - metadata is not fetched at all,
	 *   - `direct` - metadata is fetched directly URLs registered on chain,
	 *   - `uri` - metadata is fetched from an external Stake-Pool Metadata Aggregation Server (SMASH)
	 * After update existing metadata will be dropped forcing it to re-sync automatically with the new setting.
	 * 
	 * - Pattern: {@code ^(none|direct|https?:\/\/[a-zA-Z0-9-_~.]+(:[0-9]+)?/?)$}.
	 * 
	 * - Example: 
	 *   <pre>{@code https://smash.cardano-mainnet.iohk.io/}</pre>
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
