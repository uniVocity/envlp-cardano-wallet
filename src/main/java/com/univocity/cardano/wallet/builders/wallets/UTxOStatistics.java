package com.univocity.cardano.wallet.builders.wallets;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.common.*;
import com.univocity.cardano.wallet.common.*;

import java.math.*;
import java.util.*;

public class UTxOStatistics extends Wrapper<AbstractUTxOStatistic> {

	private TreeMap<BigInteger, BigInteger> distribution = null;

	public UTxOStatistics(AbstractUTxOStatistic original, WalletApi api) {
		super(original, api);
	}

	public BigDecimal total() {
		return lovelaceToAda(original.getTotal());
	}

	public BigInteger totalInLovelace() {
		return original.getTotal().getQuantity();
	}

	public String scaleDescription() {
		return original.getScale();
	}

	public TreeMap<BigInteger, BigInteger> distribution() {
		if (this.distribution == null) {
			this.distribution = new TreeMap<>();

			Map tmp = (Map) original.getDistribution();

			tmp.forEach((k, v) -> {
				distribution.put(new BigInteger(String.valueOf(k)), new BigInteger(String.valueOf(v)));
			});

			original.setDistribution(this.distribution);
		}
		return distribution;
	}
}
