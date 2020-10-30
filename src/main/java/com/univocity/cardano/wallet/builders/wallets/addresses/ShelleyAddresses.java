package com.univocity.cardano.wallet.builders.wallets.addresses;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.builders.*;
import com.univocity.cardano.wallet.builders.wallets.*;
import com.univocity.cardano.wallet.common.*;

import java.util.*;

public class ShelleyAddresses extends ApiWrapper implements Addresses {

	private final ShelleyWallet wallet;

	public ShelleyAddresses(ShelleyWallet wallet, WalletApi api) {
		super(api);
		this.wallet = wallet;
	}

	@Override
	public List<Address> used() {
		return listAddresses("used");
	}

	@Override
	public List<Address> unused() {
		return listAddresses("unused");
	}

	@Override
	public List<Address> all() {
		return listAddresses(null);
	}

	private List<Address> listAddresses(String state) {
		return Utils.convertList(api.sync().listAddresses(wallet.id(), state), (e) -> new ShelleyAddress(e, api));
	}
}
