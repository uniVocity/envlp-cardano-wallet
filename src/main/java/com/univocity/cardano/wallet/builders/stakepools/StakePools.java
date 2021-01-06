package com.univocity.cardano.wallet.builders.stakepools;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.settings.*;
import com.univocity.cardano.wallet.api.generated.stakepools.*;
import com.univocity.cardano.wallet.api.generated.utils.*;
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

	public void garbageCollect() {
		PostMaintenanceActionRequest request = new PostMaintenanceActionRequest();
		request.setMaintenanceAction("gc_stake_pools");
		api.async().postMaintenanceAction(request, response -> {
		});
	}

	public ServerHealth smashServerHealth(String smashServerUrl) {
		GetCurrentSmashHealthResponse response = api.sync().getCurrentSmashHealth(smashServerUrl);
		return ServerHealth.valueOf(response.getHealth().toUpperCase());
	}

	public String metadataSource() {
		return api.sync().getSettings().getPoolMetadataSource();
	}

	public void metadataSource(String metadataSource) {
		PutSettingsRequest request = new PutSettingsRequest();
		Settings settings = new Settings();
		settings.setPoolMetadataSource(metadataSource);
		request.setSettings(settings);
		api.sync().putSettings(request);
	}
}
