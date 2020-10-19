package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * Whether a wallet is ready to use or still syncing
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class State {


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

	@Override
	public String toString() {
		return printObject(this);
	}

}
