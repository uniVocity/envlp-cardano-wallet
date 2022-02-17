package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Gives an indication if metadata GC checking for delisted pools
 * has run and if so, when.
 * Possible values are:
 *   - not_applicable -> we're currently not querying a SMASH server for metadata
 *   - not_started -> the GC hasn't started yet, try again in a short while
 *   - restarting -> the GC thread is currently restarting, try again in short while
 *   - has_run -> the GC has run successfully
 * When 'status' is 'restarting' or 'has_run' then the field 'last_run'
 * is set to the last GC time in UTC.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class GcStakePools {


	@JsonProperty("status")
	private String status;

	@JsonProperty("last_run")
	private String lastRun;

	/**
	 * Returns the status.
	 * - Accepted values: {@code [not_applicable, not_started, restarting, has_run]}.
	 * 
	 * @return the status
	 */
	public String getStatus(){
		return status;
	}

	/**
	 * Defines the status.
	 * - Accepted values: {@code [not_applicable, not_started, restarting, has_run]}.
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
	 * Returns the last run (optional).
	 * - Format: {@code iso-8601-date-and-time}.
	 * 
	 * - Example: 
	 *   <pre>{@code Wed Feb 27 11:46:45 BRT 2019}</pre>
	 * 
	 * @return the last run
	 */
	public String getLastRun(){
		return lastRun;
	}

	/**
	 * Defines the last run (optional).
	 * - Format: {@code iso-8601-date-and-time}.
	 * 
	 * - Example: 
	 *   <pre>{@code Wed Feb 27 11:46:45 BRT 2019}</pre>
	 * 
	 * @param lastRun the last run
	 */
	public void setLastRun(String lastRun){
		if (lastRun == null) {
			this.lastRun = lastRun;
			return;
		}

		this.lastRun = lastRun;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
