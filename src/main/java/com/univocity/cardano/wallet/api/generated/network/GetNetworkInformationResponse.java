package com.univocity.cardano.wallet.api.generated.network;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#getNetworkInformation()}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class GetNetworkInformationResponse {


	@JsonProperty("sync_progress")
	private SyncProgress syncProgress;

	@JsonProperty("node_tip")
	private NodeTip nodeTip;

	@JsonProperty("network_tip")
	private NetworkTip networkTip;

	@JsonProperty("next_epoch")
	private NextEpoch nextEpoch;

	/**
	 * Returns the sync progress.
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "status" : "ready"
	 *     }
	 *   }</pre>
	 * 
	 * @return the sync progress
	 */
	public SyncProgress getSyncProgress(){
		return syncProgress;
	}

	/**
	 * Defines the sync progress.
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "status" : "ready"
	 *     }
	 *   }</pre>
	 * 
	 * @param syncProgress the sync progress
	 */
	public void setSyncProgress(SyncProgress syncProgress){
		if (syncProgress == null) {
			throw new IllegalArgumentException("Value of syncProgress cannot be null");
		}

		this.syncProgress = syncProgress;
	}

	/**
	 * Returns the node tip.
	 * 
	 * @return the node tip
	 */
	public NodeTip getNodeTip(){
		return nodeTip;
	}

	/**
	 * Defines the node tip.
	 * 
	 * @param nodeTip the node tip
	 */
	public void setNodeTip(NodeTip nodeTip){
		if (nodeTip == null) {
			throw new IllegalArgumentException("Value of nodeTip cannot be null");
		}

		this.nodeTip = nodeTip;
	}

	/**
	 * Returns the network tip (optional).
	 * 
	 * @return the network tip
	 */
	public NetworkTip getNetworkTip(){
		return networkTip;
	}

	/**
	 * Defines the network tip (optional).
	 * 
	 * @param networkTip the network tip
	 */
	public void setNetworkTip(NetworkTip networkTip){
		if (networkTip == null) {
			this.networkTip = networkTip;
			return;
		}

		this.networkTip = networkTip;
	}

	/**
	 * Returns the next epoch (optional).
	 * 
	 * @return the next epoch
	 */
	public NextEpoch getNextEpoch(){
		return nextEpoch;
	}

	/**
	 * Defines the next epoch (optional).
	 * 
	 * @param nextEpoch the next epoch
	 */
	public void setNextEpoch(NextEpoch nextEpoch){
		if (nextEpoch == null) {
			this.nextEpoch = nextEpoch;
			return;
		}

		this.nextEpoch = nextEpoch;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
