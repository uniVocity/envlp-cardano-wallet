package com.univocity.cardano.wallet.builders.stakepools;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.stakepools.*;
import com.univocity.cardano.wallet.api.service.*;
import com.univocity.cardano.wallet.builders.*;
import com.univocity.cardano.wallet.common.*;

import java.util.*;

public class StakePools extends ApiWrapper {

	private boolean fetchingPools;
	private List<StakePool> lastResult = new ArrayList<>();

	private final WalletApiCallback<List<ListStakePoolsResponseItem>> callback = new WalletApiCallback<List<ListStakePoolsResponseItem>>() {
		@Override
		public void onResponse(List<ListStakePoolsResponseItem> response) {
			try {
				lastResult = Utils.convertList(response, StakePool::new);
			} finally {
				fetchingPools = false;
			}

		}

		@Override
		public void onFailure(Throwable error) {
			fetchingPools = false;
			WalletApiCallback.super.onFailure(error);
		}
	};

	public StakePools(WalletApi api) {
		super(api);
	}

	public List<StakePool> list() {
		if (!fetchingPools) {
			synchronized (this) {
				if (!fetchingPools) {
					fetchingPools = true;
					api.async().listStakePools(0L, callback);
				}
			}
		}

		return lastResult;
	}
}
