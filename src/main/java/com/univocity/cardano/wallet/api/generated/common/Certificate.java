package com.univocity.cardano.wallet.api.generated.common;


import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * A delegation certificate
 * Only for 'join_pool' the 'pool' property is required.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Certificate {


	@JsonProperty("certificate_type")
	private String certificateType;

	@JsonProperty("pool")
	private String pool;

	@JsonProperty("reward_account_path")
	private ArrayList<String> rewardAccountPath;

	/**
	 * Returns the certificate type.
	 * - Accepted values: {@code [join_pool, quit_pool, register_reward_account]}.
	 * 
	 * @return the certificate type
	 */
	public String getCertificateType(){
		return certificateType;
	}

	/**
	 * Defines the certificate type.
	 * - Accepted values: {@code [join_pool, quit_pool, register_reward_account]}.
	 * 
	 * @param certificateType the certificate type
	 */
	public void setCertificateType(String certificateType){
		if (certificateType == null) {
			throw new IllegalArgumentException("Value of certificateType cannot be null");
		}

		this.certificateType = certificateType;
	}

	/**
	 * Returns the unique identifier for the pool. (optional).
	 * - Format: {@code hex|bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code pool1wqaz0q0zhtxlgn0ewssevn2mrtm30fgh2g7hr7z9rj5856457mm}</pre>
	 * 
	 * @return the unique identifier for the pool.
	 */
	public String getPool(){
		return pool;
	}

	/**
	 * Defines a unique identifier for the pool. (optional).
	 * - Format: {@code hex|bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code pool1wqaz0q0zhtxlgn0ewssevn2mrtm30fgh2g7hr7z9rj5856457mm}</pre>
	 * 
	 * @param pool a unique identifier for the pool.
	 */
	public void setPool(String pool){
		if (pool == null) {
			this.pool = pool;
			return;
		}

		this.pool = pool;
	}

	/**
	 * Returns the reward account path.
	 * - Number of elements must be exactly {@code 5}.
	 * 
	 * @return the reward account path
	 */
	public ArrayList<String> getRewardAccountPath(){
		return rewardAccountPath;
	}

	/**
	 * Defines the reward account path.
	 * - Number of elements must be exactly {@code 5}.
	 * 
	 * @param rewardAccountPath the reward account path
	 */
	public void setRewardAccountPath(ArrayList<String> rewardAccountPath){
		if (rewardAccountPath == null) {
			throw new IllegalArgumentException("Value of rewardAccountPath cannot be null");
		}

		this.rewardAccountPath = rewardAccountPath;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
