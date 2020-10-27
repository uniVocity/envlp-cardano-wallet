package com.univocity.cardano.wallet.builders.stakepools;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.stakepools.*;
import com.univocity.cardano.wallet.builders.*;
import com.univocity.cardano.wallet.common.*;

import java.util.*;
import java.util.concurrent.*;

public class StakePools extends ApiWrapper {

	private final AsyncCallbackHandler<List<ListStakePoolsResponseItem>, List<StakePool>> asyncCallbackHandler;

	public StakePools(WalletApi api) {
		super(api);

		this.asyncCallbackHandler = new AsyncCallbackHandler<>(
				Collections.emptyList(),
				callback -> api.async().listStakePools(0L, callback),
				result -> Utils.convertList(result, StakePool::new)
		);
	}

	public List<StakePool> list() {
		return asyncCallbackHandler.getEventually();
	}

	public Future<List<StakePool>> listAsync() {
		return asyncCallbackHandler.getAsync();
	}
}
