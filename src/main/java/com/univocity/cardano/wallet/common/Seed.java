package com.univocity.cardano.wallet.common;

import org.apache.commons.lang3.*;

import java.nio.charset.*;
import java.security.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Adapted from BitcoinJ:
 *
 * https://raw.githubusercontent.com/bitcoinj/bitcoinj/2ec193f8479425c3a66bebf5f2d3493e39e88f7c/core/src/main/java/org/bitcoinj/crypto/MnemonicCode.java
 */

public class Seed {

	public static final Map<String, List<String>> mnemonicWords = Collections.unmodifiableMap(loadMnemonics());
	public static final String ENGLISH = "English";

	private static Map<String, List<String>> loadMnemonics() {
		Map<String, List<String>> out = new ConcurrentHashMap<>();

		List<String> lines = Utils.readLinesFromResource("/mnemonic/english.txt", StandardCharsets.UTF_8);
		lines.removeIf(StringUtils::isBlank);

		out.put(ENGLISH, Collections.unmodifiableList(lines));

		return out;
	}

	public static List<String> englishMnemonicWords() {
		return mnemonicWordsForLanguage(ENGLISH);
	}

	public static List<String> mnemonicWordsForLanguage(String language) {
		return mnemonicWords.get(language);
	}

	public static ArrayList<String> toMnemonicList(String seedPhrase) {
		return toMnemonicList(seedPhrase, -1, false);
	}

	public static ArrayList<String> toValidatedMnemonicList(String seedPhrase) throws InvalidMnemonicException {
		return toMnemonicList(seedPhrase, -1, true);
	}

	private static ArrayList<String> toMnemonicList(String seedPhrase, int expectedWordCount, boolean validate) throws InvalidMnemonicException {
		if(StringUtils.isBlank(seedPhrase)){
			throw new InvalidMnemonicException(expectedWordCount, 0);
		}
		Utils.notBlank(seedPhrase, "Seed phrase");
		seedPhrase = cleanSeedPhrase(seedPhrase);
		String[] words = seedPhrase.split(" ");

		if (validate) {
			List<String> unknown = new ArrayList<>();
			for (int i = 0; i < words.length; i++) {
				if (!englishMnemonicWords().contains(words[i])) {
					unknown.add(words[i]);
				}
			}

			if (!unknown.isEmpty()) {
				throw new InvalidMnemonicException(expectedWordCount, words.length, unknown);
			}
		}
		if (expectedWordCount != -1 && words.length != expectedWordCount) {
			throw new InvalidMnemonicException(expectedWordCount, words.length);
		}

		ArrayList<String> out = new ArrayList<>();
		Collections.addAll(out, words);
		return out;
	}

	public static ArrayList<String> toMnemonicList(String seedPhrase, int length) {
		return toMnemonicList(seedPhrase, length, false);
	}

	public static ArrayList<String> toValidatedMnemonicList(String seedPhrase, int length) throws InvalidMnemonicException {
		return toMnemonicList(seedPhrase, length, true);
	}

	public static String cleanSeedPhrase(String seed) {
		if (seed == null) {
			return null;
		}
		seed = seed.trim().toLowerCase();
		seed = seed.replaceAll("\\s+", " ");
		int len;
		do {
			len = seed.length();
			seed = StringUtils.replace(seed, "  ", " ");
		} while (seed.length() < len);
		return seed;
	}

	private static byte[] hash(byte[] input) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("SHA-256 encryption not available", e);
		}
		return digest.digest(input);
	}

	public static String generateEnglishSeedPhrase(int wordCount) {
		return generateSeedPhrase(wordCount, ENGLISH);
	}

	public static String generateSeedPhrase(int wordCount, String language) {
		int byteCount;
		if (wordCount == 9) {
			byteCount = 12;
		} else if (wordCount == 12) {
			byteCount = 16;
		} else if (wordCount == 15) {
			byteCount = 20;
		} else if (wordCount == 18) {
			byteCount = 24;
		} else if (wordCount == 21) {
			byteCount = 28;
		} else if (wordCount == 24) {
			byteCount = 32;
		} else {
			throw new IllegalArgumentException("Word count must be 9, 12, 15, 18, 21 or 24");
		}

		byte[] entropy = new byte[byteCount];
		Random rnd = new SecureRandom();
		rnd.nextBytes(entropy);

		return generateSeedPhrase(entropy, language);
	}

	public static String generateEnglishSeedPhrase(byte[] entropy) {
		return generateSeedPhrase(entropy, ENGLISH);
	}

	/**
	 * Convert entropy data to mnemonic word list.
	 */
	public static String generateSeedPhrase(byte[] entropy, String language) {
		if (entropy.length % 4 > 0) {
			throw new IllegalArgumentException("Entropy length not multiple of 32 bits.");
		}
		if (entropy.length == 0) {
			throw new IllegalArgumentException("Entropy is empty.");
		}

		// We take initial entropy of ENT bits and compute its
		// checksum by taking first ENT / 32 bits of its SHA256 hash.
		byte[] hash = hash(entropy);
		boolean[] hashBits = bytesToBits(hash);

		boolean[] entropyBits = bytesToBits(entropy);
		int checksumLengthBits = entropyBits.length / 32;

		// We append these bits to the end of the initial entropy.
		boolean[] concatBits = new boolean[entropyBits.length + checksumLengthBits];
		System.arraycopy(entropyBits, 0, concatBits, 0, entropyBits.length);
		System.arraycopy(hashBits, 0, concatBits, entropyBits.length, checksumLengthBits);

		// Next we take these concatenated bits and split them into
		// groups of 11 bits. Each group encodes number from 0-2047
		// which is a position in a wordlist.  We convert numbers into
		// words and use joined words as mnemonic sentence.

		StringBuilder out = new StringBuilder();
		List<String> wordList = mnemonicWordsForLanguage(language);
		int nwords = concatBits.length / 11;
		for (int i = 0; i < nwords; ++i) {
			int index = 0;
			for (int j = 0; j < 11; ++j) {
				index <<= 1;
				if (concatBits[(i * 11) + j]) {
					index |= 0x1;
				}
			}
			out.append(wordList.get(index)).append(' ');
		}

		out.deleteCharAt(out.length() - 1);
		return out.toString();
	}


	public static byte[] checkEnglishSeedPhrase(String seedPhrase) {
		return checkSeedPhrase(seedPhrase, ENGLISH);
	}

	public static byte[] checkSeedPhrase(String seedPhrase, String language) {
		return checkSeedPhrase(toMnemonicList(seedPhrase), language);
	}

	/**
	 * Check to see if a mnemonic word list is valid.
	 */
	public static byte[] checkSeedPhrase(List<String> words, String language) {
		List<String> wordList = mnemonicWordsForLanguage(language);
		if (words.size() % 3 > 0) {
			throw new IllegalArgumentException("Word list size must be multiple of three words.");
		}
		if (words.size() == 0) {
			throw new IllegalArgumentException("Word list is empty.");
		}

		// Look up all the words in the list and construct the
		// concatenation of the original entropy and the checksum.
		//
		int concatLenBits = words.size() * 11;
		boolean[] concatBits = new boolean[concatLenBits];
		int wordindex = 0;
		for (String word : words) {
			// Find the words index in the wordlist.
			int ndx = wordList.indexOf(word);
			if (ndx < 0) {
				throw new IllegalArgumentException("Unknown mnemonic word: '" + word + "'");
			}

			// Set the next 11 bits to the value of the index.
			for (int ii = 0; ii < 11; ++ii) {
				concatBits[(wordindex * 11) + ii] = (ndx & (1 << (10 - ii))) != 0;
			}
			++wordindex;
		}

		int checksumLengthBits = concatLenBits / 33;
		int entropyLengthBits = concatLenBits - checksumLengthBits;

		// Extract original entropy as bytes.
		byte[] entropy = new byte[entropyLengthBits / 8];
		for (int ii = 0; ii < entropy.length; ++ii) {
			for (int jj = 0; jj < 8; ++jj) {
				if (concatBits[(ii * 8) + jj]) {
					entropy[ii] |= 1 << (7 - jj);
				}
			}
		}

		// Take the digest of the entropy.
		byte[] hash = hash(entropy);
		boolean[] hashBits = bytesToBits(hash);

		// Check all the checksum bits.
		for (int i = 0; i < checksumLengthBits; ++i) {
			if (concatBits[entropyLengthBits + i] != hashBits[i]) {
				throw new IllegalArgumentException("Mnemonic checksum failed");
			}
		}
		return entropy;
	}

	private static boolean[] bytesToBits(byte[] data) {
		boolean[] bits = new boolean[data.length * 8];
		for (int i = 0; i < data.length; ++i) {
			for (int j = 0; j < 8; ++j) {
				bits[(i * 8) + j] = (data[i] & (1 << (7 - j))) != 0;
			}
		}
		return bits;
	}
}
