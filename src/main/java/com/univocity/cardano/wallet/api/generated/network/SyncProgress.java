package com.univocity.cardano.wallet.api.generated.network;

import com.fasterxml.jackson.annotation.*;
import com.univocity.cardano.wallet.api.generated.common.*;


/**
 * 
 * Estimated synchronization progress of the node with the underlying network. Note that this may
 * change quite arbitrarily as the node may switch to shorter or longer chain forks.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SyncProgress {


	@JsonProperty("status")
	private String status;

	@JsonProperty("progress")
	private Progress progress;

	/**
	 * Returns the status.
	 * - Accepted values: {@code [ready, syncing, not_responding]}.
	 * 
	 * @return the status
	 */
	public String getStatus(){
		return status;
	}

	/**
	 * Defines the status.
	 * - Accepted values: {@code [ready, syncing, not_responding]}.
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
	 * Returns the progress (optional).
	 * 
	 * @return the progress
	 */
	public Progress getProgress(){
		return progress;
	}

	/**
	 * Defines the progress (optional).
	 * 
	 * @param progress the progress
	 */
	public void setProgress(Progress progress){
		if (progress == null) {
			this.progress = progress;
			return;
		}

		this.progress = progress;
	}
}
