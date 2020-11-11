package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#getMaintenanceActions()}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class GetMaintenanceActionsResponse {


	@JsonProperty("gc_stake_pools")
	private GcStakePools gcStakePools;

	/**
	 * Returns the gc stake pools.
	 * 
	 * @return the gc stake pools
	 */
	public GcStakePools getGcStakePools(){
		return gcStakePools;
	}

	/**
	 * Defines the gc stake pools.
	 * 
	 * @param gcStakePools the gc stake pools
	 */
	public void setGcStakePools(GcStakePools gcStakePools){
		if (gcStakePools == null) {
			throw new IllegalArgumentException("Value of gcStakePools cannot be null");
		}

		this.gcStakePools = gcStakePools;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
