package com.univocity.cardano.wallet.common;

import java.math.*;
import java.text.*;

public abstract class Wrapper<T> {

	public static final BigInteger ONE_ADA_IN_LOVELACE = new BigInteger("1000000");
	public static final ThreadLocal<DecimalFormat> PERCENTAGE_FORMAT = ThreadLocal.withInitial(() -> new DecimalFormat("#,##0.00%"));
	protected final T original;

	public Wrapper(T original) {
		this.original = original;
	}

	public final T unwrap() {
		return original;
	}

	protected static double toPct(Double value) {
		if (value == null || Double.isNaN(value)) {
			return 0.0;
		}
		return value / 100.0;
	}

	protected static String toFormattedPct(Double value) {
		return toFormattedPct(toPct(value));
	}

	protected static String toFormattedPct(double value) {
		return PERCENTAGE_FORMAT.get().format(value);
	}

	protected static BigInteger lovelaceToAda(BigInteger quantity, String unit) {
		if ("lovelace".equalsIgnoreCase(unit)) {
			return quantity.divide(ONE_ADA_IN_LOVELACE);
		}
		return quantity;
	}

	public String toString() {
		return original.toString();
	}
}
