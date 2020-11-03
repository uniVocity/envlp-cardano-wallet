package com.univocity.cardano.wallet.common;

import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.common.*;

import java.math.*;
import java.text.*;
import java.time.*;
import java.util.function.*;

public abstract class Wrapper<T> {

	public static final BigInteger ONE_ADA_IN_LOVELACE = new BigInteger("1000000");
	public static final ThreadLocal<DecimalFormat> PERCENTAGE_FORMAT = ThreadLocal.withInitial(() -> new DecimalFormat("#,##0.00%"));
	protected final T original;
	protected final WalletApi api;

	public Wrapper(T original, WalletApi api) {
		this.original = original;
		this.api = api;
	}

	public final T unwrap() {
		return original;
	}

	protected Instant toInstant(String value){
		return Instant.parse(value);
	}

	protected LocalDateTime toDateTime(String value){
		return LocalDateTime.ofInstant(toInstant(value), ZoneId.systemDefault());
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

	protected static BigDecimal lovelaceToAda(AbstractAmount amount) {
		if("lovelace".equalsIgnoreCase(amount.getUnit())){
			return lovelaceToAda(amount.getQuantity());
		}
		return new BigDecimal(amount.getQuantity());
	}

	public static BigInteger adaToLovelace(BigDecimal amountInAda){
		BigInteger amountInLovelace = amountInAda.multiply(new BigDecimal(ONE_ADA_IN_LOVELACE)).toBigInteger();
		return amountInLovelace;
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
