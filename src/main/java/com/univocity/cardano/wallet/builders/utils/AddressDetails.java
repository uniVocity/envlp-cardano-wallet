package com.univocity.cardano.wallet.builders.utils;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.utils.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;

public class AddressDetails extends Wrapper<InspectAddressResponse> {

	public AddressDetails(InspectAddressResponse original, WalletApi api) {
		super(original, api);
	}

	public String style() {
		return original.getAddressStyle();
	}

	public String stakeReference() {
		return original.getStakeReference();
	}

	public BigInteger networkTag() {
		return original.getNetworkTag();
	}

	public String spendingKeyHash() {
		return original.getSpendingKeyHash();
	}

	public String stakeKeyHash() {
		return original.getStakeKeyHash();
	}


	public String scriptHash() {
		return original.getScriptHash();
	}

	public String root() {
		return original.getAddressRoot();
	}

	public String derivationPath() {
		return original.getDerivationPath();
	}

	private BigInteger slot() {
		return safeGet(() -> original.getPointer().getSlotNum());
	}

	private BigInteger transactionIndex() {
		return safeGet(() -> original.getPointer().getTransactionIndex());
	}

	private BigInteger outputIndex() {
		return safeGet(() -> original.getPointer().getOutputIndex());
	}

}
