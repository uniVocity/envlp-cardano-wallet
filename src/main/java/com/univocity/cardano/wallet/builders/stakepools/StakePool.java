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
		return original.getMetadata().getTicker();
	}

	public String description() {
		return original.getMetadata().getDescription();
	}

	public String homePage() {
		return original.getMetadata().getHomepage();
	}

	public double relativeStake() {
		return toPct(original.getMetrics().getRelativeStake().getQuantity());
	}

	public String formattedRelativeStake() {
		return toFormattedPct(relativeStake());
	}

	public double saturation() {
		return toPct(original.getMetrics().getSaturation());
	}

	public String formattedSaturation() {
		return toFormattedPct(saturation());
	}

	public long blocksProduced() {
		return original.getMetrics().getProducedBlocks().getQuantity().longValue();
	}

	public double margin() {
		return toPct(original.getMargin().getQuantity());
	}

	public String formattedMargin() {
		return toFormattedPct(margin());
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


