package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#postMaintenanceAction(okhttp3.RequestBody)}.
 * The maintenance action to carry out, current values are
 *   - gc_stake_pools -> trigger looking up delisted pools from the remote SMASH server
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostMaintenanceActionRequest {


	@JsonProperty("maintenance_action")
	private String maintenanceAction;

	/**
	 * Returns the maintenance action.
	 * - Accepted values: {@code [gc_stake_pools]}.
	 * 
	 * @return the maintenance action
	 */
	public String getMaintenanceAction(){
		return maintenanceAction;
	}

	/**
	 * Defines the maintenance action.
	 * - Accepted values: {@code [gc_stake_pools]}.
	 * 
	 * @param maintenanceAction the maintenance action
	 */
	public void setMaintenanceAction(String maintenanceAction){
		if (maintenanceAction == null) {
			throw new IllegalArgumentException("Value of maintenanceAction cannot be null");
		}

		this.maintenanceAction = maintenanceAction;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
