package com.univocity.cardano.wallet.common;

import java.math.*;
import java.text.*;
import java.util.function.*;

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

	protected static double toPercentage(Double value) {
		if (value == null || Double.isNaN(value)) {
			return 0.0;
		}
		return value / 100.0;
	}

	protected static String toFormattedPercentage(Double value) {
		return toFormattedPercentage(toPercentage(value));
	}

	protected static String toFormattedPercentage(double value) {
		return PERCENTAGE_FORMAT.get().format(value);
	}

	protected static BigInteger lovelaceToAda(BigInteger quantity, String unit) {
		if ("lovelace".equalsIgnoreCase(unit)) {
			return quantity.divide(ONE_ADA_IN_LOVELACE);
		}
		return quantity;
	}

	protected static BigDecimal lovelaceToAda(BigInteger quantity) {
		return new BigDecimal(quantity).divide(new BigDecimal(ONE_ADA_IN_LOVELACE), 6, RoundingMode.HALF_UP);
	}

	public String safeGet(Supplier<String> supplier){
		try {
			return supplier.get();
		} catch (Exception e){
			return null;
		}
	}

	public final String toString() {
		return original.toString();
	}
}
