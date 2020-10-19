package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * Next delegation status becomes active at the start of the second epoch after the corresponding delegation certificate was discovered. The exact moment is specified by changes_at
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class NextDelegation {


	@JsonProperty("status")
	private String status;

	@JsonProperty("target")
	private String target;

	@JsonProperty("changes_at")
	private ChangesAt changesAt;

	/**
	 * Returns the status.
	 * - Accepted values: {@code [not_delegating, delegating]}.
	 * 
	 * @return the status
	 */
	public String getStatus(){
		return status;
	}

	/**
	 * Defines the status.
	 * - Accepted values: {@code [not_delegating, delegating]}.
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
	 * Returns the unique Stake-Pool identifier (present only if status = `delegating`) (optional).
	 * - Format: {@code hex|bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code pool1wqaz0q0zhtxlgn0ewssevn2mrtm30fgh2g7hr7z9rj5856457mm}</pre>
	 * 
	 * @return the unique Stake-Pool identifier (present only if status = `delegating`)
	 */
	public String getTarget(){
		return target;
	}

	/**
	 * Defines a unique Stake-Pool identifier (present only if status = `delegating`) (optional).
	 * - Format: {@code hex|bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code pool1wqaz0q0zhtxlgn0ewssevn2mrtm30fgh2g7hr7z9rj5856457mm}</pre>
	 * 
	 * @param target a unique Stake-Pool identifier (present only if status = `delegating`)
	 */
	public void setTarget(String target){
		if (target == null) {
			this.target = target;
			return;
		}

		this.target = target;
	}

	/**
	 * Returns the changes at.
	 * 
	 * @return the changes at
	 */
	public ChangesAt getChangesAt(){
		return changesAt;
	}

	/**
	 * Defines the changes at.
	 * 
	 * @param changesAt the changes at
	 */
	public void setChangesAt(ChangesAt changesAt){
		if (changesAt == null) {
			throw new IllegalArgumentException("Value of changesAt cannot be null");
		}

		this.changesAt = changesAt;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
