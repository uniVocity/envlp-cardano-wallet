package com.univocity.cardano.wallet.builders.stakepools;

import com.univocity.cardano.wallet.api.generated.stakepools.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.time.*;

public class StakePool extends Wrapper<ListStakePoolsResponseItem> {

	StakePool(ListStakePoolsResponseItem pool) {
		super(pool);
	}

	public String id() {
		return original.getId();
	}

	public String ticker() {
		return safeGet(() -> original.getMetadata().getTicker());
	}

	public String description() {
		return safeGet(() -> original.getMetadata().getDescription());
	}

	public String homePage() {
		return safeGet(() -> original.getMetadata().getHomepage());
	}

	public double relativeStakePercentage() {
		return toPercentage(original.getMetrics().getRelativeStake().getQuantity());
	}

	public String formattedRelativeStakePercentage() {
		return toFormattedPercentage(relativeStakePercentage());
	}

	public double saturationPercentage() {
		return toPercentage(original.getMetrics().getSaturation());
	}

	public String formattedSaturationPercentage() {
		return toFormattedPercentage(saturationPercentage());
	}

	public long blocksProduced() {
		return original.getMetrics().getProducedBlocks().getQuantity().longValue();
	}

	public double marginPercentage() {
		return toPercentage(original.getMargin().getQuantity());
	}

	public String formattedMarginPercentage() {
		return toFormattedPercentage(marginPercentage());
	}

	public BigInteger fixedFee() {
		return lovelaceToAda(original.getCost().getQuantity(), original.getCost().getUnit());
	}

	public BigInteger pledge() {
		return lovelaceToAda(original.getPledge().getQuantity(), original.getPledge().getUnit());
	}

	public boolean isRetiring() {
		return original.getRetirement() != null;
	}

	public LocalDateTime retirementDate() {
		if (isRetiring()) {
			return LocalDateTime.parse(original.getRetirement().getEpochStartTime());
		}
		return null;
	}

	@Override
	public int hashCode() {
		return id().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StakePool) {
			return id().equals(((StakePool) obj).id());
		}
		return false;
	}
}


