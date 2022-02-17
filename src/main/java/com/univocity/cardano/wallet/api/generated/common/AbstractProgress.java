package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


public abstract class AbstractProgress {


	@JsonProperty("status")
	private String status;

	/**
	 * Returns the status.
	 * 
	 * A pending shared wallet does not have a complete set
	 * of keys, so the only possible status is `incomplete`.
	 * 
	 * - Accepted values: {@code [incomplete]}.
	 * 
	 * @return the status
	 */
	public String getStatus(){
		return status;
	}

	/**
	 * Defines the status.
	 * 
	 * A pending shared wallet does not have a complete set
	 * of keys, so the only possible status is `incomplete`.
	 * 
	 * - Accepted values: {@code [incomplete]}.
	 * 
	 * @param status the status
	 */
	public void setStatus(String status){
		if (status == null) {
			throw new IllegalArgumentException("Value of status cannot be null");
		}

		this.status = status;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
