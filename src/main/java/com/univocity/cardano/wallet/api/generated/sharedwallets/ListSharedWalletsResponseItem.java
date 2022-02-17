package com.univocity.cardano.wallet.api.generated.sharedwallets;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ListSharedWalletsResponseItem {


	@JsonProperty("")
	private SharedWallet sharedWallet;

	/**
	 * Returns the  (optional).
	 * 
	 * @return the 
	 */
	public SharedWallet getSharedWallet(){
		return sharedWallet;
	}

	/**
	 * Defines the  (optional).
	 * 
	 * @param sharedWallet the 
	 */
	public void setSharedWallet(SharedWallet sharedWallet){
		if (sharedWallet == null) {
			this.sharedWallet = sharedWallet;
			return;
		}

		this.sharedWallet = sharedWallet;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
