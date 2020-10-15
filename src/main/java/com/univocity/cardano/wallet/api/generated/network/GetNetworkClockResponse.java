package com.univocity.cardano.wallet.api.generated.network;

import com.fasterxml.jackson.annotation.*;
import com.univocity.cardano.wallet.api.generated.*;


/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#getNetworkClock(Boolean)}.
 * [Network Time Protocol](https://en.wikipedia.org/wiki/Network_Time_Protocol) information of the server.
 * **Important:** This piece of information only makes sense when the server runs on the same host machine as the node.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class GetNetworkClockResponse {


	@JsonProperty("status")
	private String status;

	@JsonProperty("offset")
	private Offset offset;

	/**
	 * Returns the status.
	 * - Accepted values: {@code [available, unavailable, pending]}.
	 * 
	 * @return the status
	 */
	public String getStatus(){
		return status;
	}

	/**
	 * Defines the status.
	 * - Accepted values: {@code [available, unavailable, pending]}.
	 * 
	 * @param status the status
	 */
	public void setStatus(String status){
		if (status == null) {
			throw new IllegalArgumentException("Value of status cannot be null");
		}

		this.status = status;
	}

	/**
	 * Returns the offset (optional).
	 * 
	 * @return the offset
	 */
	public Offset getOffset(){
		return offset;
	}

	/**
	 * Defines the offset (optional).
	 * 
	 * @param offset the offset
	 */
	public void setOffset(Offset offset){
		if (offset == null) {
			this.offset = offset;
			return;
		}

		this.offset = offset;
	}
}
