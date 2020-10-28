package com.univocity.cardano.wallet.builders.network;

import com.univocity.cardano.wallet.api.generated.network.*;
import com.univocity.cardano.wallet.common.*;

public class NetworkClock extends Wrapper<GetNetworkClockResponse> {

	NetworkClock(GetNetworkClockResponse response) {
		super(response, null);
	}

	public ClockStatus status() {
		return ClockStatus.valueOf(original.getStatus());
	}

	public long offsetInMicroseconds() {
		if ("microsecond".equalsIgnoreCase(original.getOffset().getUnit())) {
			return original.getOffset().getQuantity().longValue();
		}
		throw new IllegalStateException("No conversion from " + original.getOffset().getUnit() + " to microsecond has been implemented");
	}

	public long offsetInNanoseconds() {
		return offsetInMicroseconds() * 1000L;
	}

	public long offsetInMilliseconds() {
		return offsetInMicroseconds() / 1000L;
	}
}


