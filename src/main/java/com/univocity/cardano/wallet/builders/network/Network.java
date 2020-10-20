package com.univocity.cardano.wallet.builders.network;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.builders.*;

public class Network extends ApiWrapper {
	public Network(WalletApi api) {
		super(api);
	}

	public NetworkInformation information(){
		return new NetworkInformation(api.sync().getNetworkInformation());
	}

	public NetworkClock clock(){
		return new NetworkClock(api.sync().getNetworkClock(null));
	}

	public NetworkParameters parameters(){
		return new NetworkParameters(api.sync().getNetworkParameters());
	}

}
