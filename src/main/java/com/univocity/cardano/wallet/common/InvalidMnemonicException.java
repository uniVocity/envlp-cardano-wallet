package com.univocity.cardano.wallet.common;

import java.util.*;

public class InvalidMnemonicException extends IllegalArgumentException {

	private final Set<String> unknownWords = new LinkedHashSet<>();
	private final int actualWordCount;
	private final int expectedWordCount;

	public InvalidMnemonicException(int expectedWordCount, int actualWordCount) {
		super("Seed phrase length should be " + expectedWordCount + " words instead of " + actualWordCount);
		this.actualWordCount = actualWordCount;
		this.expectedWordCount = expectedWordCount;
	}

	public InvalidMnemonicException(int expectedWordCount, int actualWordCount, Collection<String> unknownWords) {
		super("Unknown mnemonic words: " + unknownWords);
		this.unknownWords.addAll(unknownWords);
		this.actualWordCount = actualWordCount;
		this.expectedWordCount = expectedWordCount;
	}

	public Set<String> getUnknownWords() {
		return Collections.unmodifiableSet(unknownWords);
	}

	public int getActualWordCount() {
		return actualWordCount;
	}

	public int getExpectedWordCount() {
		return expectedWordCount;
	}
}
