package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Metrics {


	@JsonProperty("non_myopic_member_rewards")
	private NonMyopicMemberRewards nonMyopicMemberRewards;

	@JsonProperty("relative_stake")
	private RelativeStake relativeStake;

	@JsonProperty("saturation")
	private Double saturation;

	@JsonProperty("produced_blocks")
	private ProducedBlocks producedBlocks;

	/**
	 * Returns the non myopic member rewards.
	 * 
	 * @return the non myopic member rewards
	 */
	public NonMyopicMemberRewards getNonMyopicMemberRewards(){
		return nonMyopicMemberRewards;
	}

	/**
	 * Defines the non myopic member rewards.
	 * 
	 * @param nonMyopicMemberRewards the non myopic member rewards
	 */
	public void setNonMyopicMemberRewards(NonMyopicMemberRewards nonMyopicMemberRewards){
		if (nonMyopicMemberRewards == null) {
			throw new IllegalArgumentException("Value of nonMyopicMemberRewards cannot be null");
		}

		this.nonMyopicMemberRewards = nonMyopicMemberRewards;
	}

	/**
	 * Returns the relative stake.
	 * 
	 * @return the relative stake
	 */
	public RelativeStake getRelativeStake(){
		return relativeStake;
	}

	/**
	 * Defines the relative stake.
	 * 
	 * @param relativeStake the relative stake
	 */
	public void setRelativeStake(RelativeStake relativeStake){
		if (relativeStake == null) {
			throw new IllegalArgumentException("Value of relativeStake cannot be null");
		}

		this.relativeStake = relativeStake;
	}

	/**
	 * Returns the saturation.
	 * 
	 * Saturation-level of the pool based on the desired number of pools aimed by the network.
	 * A value above `1` indicates that the pool is saturated.
	 * The `non_myopic_member_rewards` take oversaturation into account, as specified by the [specs](https://hydra.iohk.io/job/Cardano/cardano-ledger-specs/delegationDesignSpec/latest/download-by-type/doc-pdf/delegation_design_spec).
	 * The saturation is based on the live `relative_stake`. The saturation at the end of epoch e,
	 * will affect the rewards paid out at the end of epoch e+3.
	 * 
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 0.74}</pre>
	 * 
	 * @return the saturation
	 */
	public Double getSaturation(){
		return saturation;
	}

	/**
	 * Defines the saturation.
	 * 
	 * Saturation-level of the pool based on the desired number of pools aimed by the network.
	 * A value above `1` indicates that the pool is saturated.
	 * The `non_myopic_member_rewards` take oversaturation into account, as specified by the [specs](https://hydra.iohk.io/job/Cardano/cardano-ledger-specs/delegationDesignSpec/latest/download-by-type/doc-pdf/delegation_design_spec).
	 * The saturation is based on the live `relative_stake`. The saturation at the end of epoch e,
	 * will affect the rewards paid out at the end of epoch e+3.
	 * 
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 0.74}</pre>
	 * 
	 * @param saturation the saturation
	 */
	public void setSaturation(Double saturation){
		if (saturation == null) {
			throw new IllegalArgumentException("Value of saturation cannot be null");
		}

		if (saturation < 0) {
			throw new IllegalArgumentException("Value of saturation cannot be less than 0");
		}

		this.saturation = saturation;
	}

	/**
	 * Returns the produced blocks.
	 * 
	 * @return the produced blocks
	 */
	public ProducedBlocks getProducedBlocks(){
		return producedBlocks;
	}

	/**
	 * Defines the produced blocks.
	 * 
	 * @param producedBlocks the produced blocks
	 */
	public void setProducedBlocks(ProducedBlocks producedBlocks){
		if (producedBlocks == null) {
			throw new IllegalArgumentException("Value of producedBlocks cannot be null");
		}

		this.producedBlocks = producedBlocks;
	}
}
