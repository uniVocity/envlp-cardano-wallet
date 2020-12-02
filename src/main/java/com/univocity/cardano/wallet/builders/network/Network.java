package com.univocity.cardano.wallet.builders.network;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.service.*;
import com.univocity.cardano.wallet.builders.*;

import java.util.function.*;

public class Network extends ApiWrapper {
	public Network(WalletApi api) {
		super(api);
	}

	public NetworkInformation information() {
		return new NetworkInformation(api.sync().getNetworkInformation());
	}

	public void information(Consumer<NetworkInformation> consumer) {
		api.async().getNetworkInformation(response -> consumer.accept(new NetworkInformation(response)));
	}

	public void information(Consumer<NetworkInformation> consumer, Consumer<Throwable> errorHandler) {
		api.async().getNetworkInformation(new WalletApiConsumer<>(NetworkInformation::new, consumer, errorHandler));
	}

	public NetworkClock clock() {
		return new NetworkClock(api.sync().getNetworkClock(null));
	}

	public NetworkParameters parameters() {
		return new NetworkParameters(api.sync().getNetworkParameters());
	}
}
