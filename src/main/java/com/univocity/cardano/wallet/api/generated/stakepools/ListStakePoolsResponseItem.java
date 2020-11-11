package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ListStakePoolsResponseItem {


	@JsonProperty("id")
	private String id;

	@JsonProperty("metrics")
	private Metrics metrics;

	@JsonProperty("cost")
	private Cost cost;

	@JsonProperty("margin")
	private Margin margin;

	@JsonProperty("pledge")
	private Pledge pledge;

	@JsonProperty("metadata")
	private Metadata metadata;

	@JsonProperty("retirement")
	private Retirement retirement;

	@JsonProperty("flags")
	private ArrayList<String> flags;

	/**
	 * Returns the unique identifier for the pool.
	 * - Format: {@code hex|bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code pool1wqaz0q0zhtxlgn0ewssevn2mrtm30fgh2g7hr7z9rj5856457mm}</pre>
	 * 
	 * @return the unique identifier for the pool.
	 */
	public String getId(){
		return id;
	}

	/**
	 * Defines a unique identifier for the pool.
	 * - Format: {@code hex|bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code pool1wqaz0q0zhtxlgn0ewssevn2mrtm30fgh2g7hr7z9rj5856457mm}</pre>
	 * 
	 * @param id a unique identifier for the pool.
	 */
	public void setId(String id){
		if (id == null) {
			throw new IllegalArgumentException("Value of id cannot be null");
		}

		this.id = id;
	}

	/**
	 * Returns the metrics.
	 * 
	 * @return the metrics
	 */
	public Metrics getMetrics(){
		return metrics;
	}

	/**
	 * Defines the metrics.
	 * 
	 * @param metrics the metrics
	 */
	public void setMetrics(Metrics metrics){
		if (metrics == null) {
			throw new IllegalArgumentException("Value of metrics cannot be null");
		}

		this.metrics = metrics;
	}

	/**
	 * Returns the cost.
	 * 
	 * @return the cost
	 */
	public Cost getCost(){
		return cost;
	}

	/**
	 * Defines the cost.
	 * 
	 * @param cost the cost
	 */
	public void setCost(Cost cost){
		if (cost == null) {
			throw new IllegalArgumentException("Value of cost cannot be null");
		}

		this.cost = cost;
	}

	/**
	 * Returns the margin.
	 * 
	 * @return the margin
	 */
	public Margin getMargin(){
		return margin;
	}

	/**
	 * Defines the margin.
	 * 
	 * @param margin the margin
	 */
	public void setMargin(Margin margin){
		if (margin == null) {
			throw new IllegalArgumentException("Value of margin cannot be null");
		}

		this.margin = margin;
	}

	/**
	 * Returns the pledge.
	 * 
	 * @return the pledge
	 */
	public Pledge getPledge(){
		return pledge;
	}

	/**
	 * Defines the pledge.
	 * 
	 * @param pledge the pledge
	 */
	public void setPledge(Pledge pledge){
		if (pledge == null) {
			throw new IllegalArgumentException("Value of pledge cannot be null");
		}

		this.pledge = pledge;
	}

	/**
	 * Returns the metadata (optional).
	 * 
	 * @return the metadata
	 */
	public Metadata getMetadata(){
		return metadata;
	}

	/**
	 * Defines the metadata (optional).
	 * 
	 * @param metadata the metadata
	 */
	public void setMetadata(Metadata metadata){
		if (metadata == null) {
			this.metadata = metadata;
			return;
		}

		this.metadata = metadata;
	}

	/**
	 * Returns the retirement (optional).
	 * 
	 * @return the retirement
	 */
	public Retirement getRetirement(){
		return retirement;
	}

	/**
	 * Defines the retirement (optional).
	 * 
	 * @param retirement the retirement
	 */
	public void setRetirement(Retirement retirement){
		if (retirement == null) {
			this.retirement = retirement;
			return;
		}

		this.retirement = retirement;
	}

	/**
	 * Returns the flags.
	 * 
	 * Various flags applicable to stake pools. Possible flags:
	 * | flag     | description                                                                                                      |
	 * | ---      | ---                                                                                                              |
	 * | delisted | The pool is marked as delisted on a configured SMASH server; metadata for this pool have therefore been dropped. |
	 * 
	 * - Values: {@code [delisted]}.
	 * 
	 * @return the flags
	 */
	public ArrayList<String> getFlags(){
		return flags;
	}

	/**
	 * Defines the flags.
	 * 
	 * Various flags applicable to stake pools. Possible flags:
	 * | flag     | description                                                                                                      |
	 * | ---      | ---                                                                                                              |
	 * | delisted | The pool is marked as delisted on a configured SMASH server; metadata for this pool have therefore been dropped. |
	 * 
	 * - Values: {@code [delisted]}.
	 * 
	 * @param flags the flags
	 */
	public void setFlags(ArrayList<String> flags){
		if (flags == null) {
			throw new IllegalArgumentException("Value of flags cannot be null");
		}

		this.flags = flags;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
