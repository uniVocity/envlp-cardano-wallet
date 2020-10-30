package com.univocity.cardano.wallet.builders.wallets.addresses;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.byronaddresses.*;
import com.univocity.cardano.wallet.builders.*;
import com.univocity.cardano.wallet.builders.wallets.*;
import com.univocity.cardano.wallet.common.*;

import java.util.*;

public class ByronAddresses extends ApiWrapper implements Addresses {

	private final ByronWallet wallet;

	public ByronAddresses(ByronWallet wallet, WalletApi api) {
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
		return Utils.convertList(api.sync().listByronAddresses(wallet.id(), state), (e) -> new ByronAddress(e, api));
	}

	public Address next(String walletPassword) {
		CreateAddressRequest request = new CreateAddressRequest();
		request.setPassphrase(walletPassword);
		return new ByronAddress(api.sync().createAddress(wallet.id(), request), null);
	}
}
