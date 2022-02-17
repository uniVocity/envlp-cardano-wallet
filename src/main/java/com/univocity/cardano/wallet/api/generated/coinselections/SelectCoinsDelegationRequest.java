package com.univocity.cardano.wallet.api.generated.coinselections;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#selectCoins(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SelectCoinsDelegationRequest {


	@JsonProperty("delegation_action")
	private DelegationAction delegationAction;

	/**
	 * Returns the delegation action.
	 * 
	 * @return the delegation action
	 */
	public DelegationAction getDelegationAction(){
		return delegationAction;
	}

	/**
	 * Defines the delegation action.
	 * 
	 * @param delegationAction the delegation action
	 */
	public void setDelegationAction(DelegationAction delegationAction){
		if (delegationAction == null) {
			throw new IllegalArgumentException("Value of delegationAction cannot be null");
		}

		this.delegationAction = delegationAction;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
