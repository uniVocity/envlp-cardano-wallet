package com.univocity.cardano.wallet.builders.network;

import com.univocity.cardano.wallet.api.generated.network.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.time.*;

import static com.univocity.cardano.wallet.common.Utils.*;

public class NetworkParameters extends Wrapper<GetNetworkParametersResponse> {

	NetworkParameters(GetNetworkParametersResponse response) {
		super(response, null);
	}

	public String genesisBlockHash() {
		return original.getGenesisBlockHash();
	}

	public LocalDateTime blockchainStartTime() {
		return parseISO8601Date(original.getBlockchainStartTime());
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

	public long securityParameterInBlocks() {
		if ("block".equalsIgnoreCase(original.getSecurityParameter().getUnit())) {
			return original.getSecurityParameter().getQuantity().longValue();
		}
		throw new IllegalStateException("No conversion from " + original.getSecurityParameter().getUnit() + " to block has been implemented");
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
			return parseISO8601Date(original.getHardforkAt().getEpochStartTime());
		}
		return null;
	}
}


