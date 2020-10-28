package com.univocity.cardano.wallet.builders.network;

import com.univocity.cardano.wallet.api.generated.network.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.time.*;

public class NetworkParameters extends Wrapper<GetNetworkParametersResponse> {

	NetworkParameters(GetNetworkParametersResponse response) {
		super(response, null);
	}

	public String genesisBlockHash() {
		return original.getGenesisBlockHash();
	}

	public LocalDateTime blockchainStartTime() {
		return LocalDateTime.parse(original.getBlockchainStartTime());
	}

	public long slotLengthInSeconds() {
		if ("second".equalsIgnoreCase(original.getSlotLength().getUnit())) {
			return original.getSlotLength().getQuantity().longValue();
		}
		throw new IllegalStateException("No conversion from " + original.getSlotLength().getUnit() + " to seconds has been implemented");
	}

	public long epochLengthInSlots() {
		if ("slot".equalsIgnoreCase(original.getEpochLength().getUnit())) {
			return original.getEpochLength().getQuantity().longValue();
		}
		throw new IllegalStateException("No conversion from " + original.getEpochLength().getUnit() + " to slot has been implemented");
	}

	public long epochStabilityInBlocks() {
		if ("block".equalsIgnoreCase(original.getEpochStability().getUnit())) {
			return original.getEpochStability().getQuantity().longValue();
		}
		throw new IllegalStateException("No conversion from " + original.getEpochStability().getUnit() + " to block has been implemented");
	}

	public double activeSlotCoefficient() {
		return toPercentage(original.getActiveSlotCoefficient().getQuantity());
	}

	public String formattedActiveSlotCoefficient() {
		return toFormattedPercentage(activeSlotCoefficient());
	}

	public double decentralizationPercentage() {
		return toPercentage(original.getDecentralizationLevel().getQuantity());
	}

	public String formattedDecentralizationPercentage() {
		return toFormattedPercentage(decentralizationPercentage());
	}

	public long desiredPoolNumber() {
		return original.getDesiredPoolNumber().longValue();
	}

	public BigInteger minimumUTxOValue() {
		return original.getMinimumUtxoValue().getQuantity();
	}

	public boolean isHardforkPlanned(){
		return original.getHardforkAt() != null;
	}

	public long nextHardforkEpoch() {
		if (isHardforkPlanned()) {
			return original.getHardforkAt().getEpochNumber().longValue();
		}
		return -1L;
	}

	public LocalDateTime nextHardforkDate() {
		if (isHardforkPlanned()) {
			return LocalDateTime.parse(original.getHardforkAt().getEpochStartTime());
		}
		return null;
	}
}


