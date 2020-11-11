package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.builders.wallets.addresses.*;

public class Keys {

	enum Role {
		UTxO_EXTERNAL,
		UTxO_INTERNAL,
		MUTABLE_ACCOUNT,
		MULTISIG_SCRIPT
	}

	private final ShelleyWallet wallet;
	private final WalletApi api;

	Keys(ShelleyWallet wallet, WalletApi api) {
		this.wallet = wallet;
		this.api = api;
	}

	private String publicKey(Role role, long index) {
		return api.sync().getWalletKey(wallet.id(), role.toString().toLowerCase(), String.valueOf(index)).getResult();
	}

	public String externalUtxoPublicKey(long index) {
		return publicKey(Role.UTxO_EXTERNAL, index);
	}

	public String internalUtxoPublicKey(long index) {
		return publicKey(Role.UTxO_INTERNAL, index);
	}

	public String mutableAccountPublicKey(long index) {
		return publicKey(Role.MUTABLE_ACCOUNT, index);
	}

	public String multiSigPublicKey(long index) {
		return publicKey(Role.MULTISIG_SCRIPT, index);
	}
}
