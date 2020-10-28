package com.univocity.cardano.wallet.builders.network;

import com.univocity.cardano.wallet.api.generated.network.*;
import com.univocity.cardano.wallet.common.*;

import java.time.*;

import static com.univocity.cardano.wallet.builders.network.SynchronizationStatus.*;

public class NetworkInformation extends Wrapper<GetNetworkInformationResponse> {


	NetworkInformation(GetNetworkInformationResponse response) {
		super(response, null);
	}

	public SynchronizationStatus synchronizationStatus() {
		return SynchronizationStatus.valueOf(original.getSyncProgress().getStatus());
	}

	public double synchronizationProgressPercentage() {
		if (synchronizationStatus() == syncing) {
			return toPercentage(original.getSyncProgress().getProgress().getQuantity());
		}
		return Double.NaN;
	}

	public String formattedSynchronizationProgressPercentage() {
		if (synchronizationStatus() == syncing) {
			return toFormattedPercentage(original.getSyncProgress().getProgress().getQuantity());
		}
		return "N/A";
	}

	public long nodeEpoch() {
		return original.getNodeTip().getEpochNumber().longValue();
	}

	public long nodeSlot() {
		return original.getNodeTip().getSlotNumber().longValue();
	}

	public long absoluteNodeSlot() {
		return original.getNodeTip().getAbsoluteSlotNumber().longValue();
	}

	public long blockHeight() {
		return original.getNodeTip().getHeight().getQuantity().longValue();
	}

	public long networkEpoch() {
		return original.getNetworkTip().getEpochNumber().longValue();
	}

	public long networkSlot() {
		return original.getNetworkTip().getSlotNumber().longValue();
	}

	public long absoluteNetworkSlot() {
		return original.getNetworkTip().getAbsoluteSlotNumber().longValue();
	}

	public long nextEpoch(){
		return original.getNextEpoch().getEpochNumber().longValue();
	}

	public LocalDateTime nextEpochStart(){
		return LocalDateTime.parse(original.getNextEpoch().getEpochStartTime());
	}
}


