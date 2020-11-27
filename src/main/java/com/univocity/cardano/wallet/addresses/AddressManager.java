package com.univocity.cardano.wallet.addresses;

import com.univocity.cardano.wallet.common.*;
import com.univocity.cardano.wallet.embedded.*;
import org.apache.commons.lang3.*;
import org.slf4j.*;

import static com.univocity.cardano.wallet.addresses.AddressStyle.*;

public class AddressManager extends CardanoToolWrapper {

	private static final Logger log = LoggerFactory.getLogger(AddressManager.class);
	private AddressStyle style = Shelley;

	public AddressManager(String cardanoToolsDirPath) {
		super(cardanoToolsDirPath, "cardano-address");
	}

	public AddressStyle getStyle() {
		return style;
	}

	public AddressManager setStyle(AddressStyle style) {
		Utils.notNull(style, "Address style");
		AddressManager out = new AddressManager(this.toolDir.getAbsolutePath());
		out.style = style;
		return out;
	}

	public String generateSeed(int wordCount) {
		return executeNoInput("generate seed phrase", "recovery-phrase generate --size " + wordCount, false);
	}

	public String generateSeed() {
		return generateSeed(24);
	}

	public String generatePrivateKey(String seed) {
		if (seed == null) {
			seed = generateSeed();
		}
		if (seed != null) {
			String result = execute("generate private key", seed, "key from-recovery-phrase " + style.name(), false);
			if (result != null && result.toLowerCase().contains("error")) {
				log.warn("Error generating private key: " + result);
				return null;
			}
			return result;
		}
		return null;
	}

	public String generateHardenedPublicKeyPath(String privateKey, long accountIndex) {
		if (StringUtils.isBlank(privateKey)) {
			throw new IllegalArgumentException("Private key cannot be null/blank");
		}
		if (accountIndex < 0) {
			throw new IllegalArgumentException("Account can't be negative. Got " + accountIndex);
		}

		String derivationPath = "1852H/1815H/";
		return execute("generate hardened public key path", privateKey, "key child " + derivationPath + accountIndex + "H ", false);
	}

	public String generatePublicRootKeyFromPrivateKey(String privateKey, long accountIndex) {
		String hardenedPublicKeyPath = generateHardenedPublicKeyPath(privateKey, accountIndex);
		return execute("generate public root key", hardenedPublicKeyPath, "key public --with-chain-code", true);
	}

	public String generatePublicRootKeyFromSeed(String seed, long accountIndex) {
		if (StringUtils.isBlank(seed)) {
			throw new IllegalArgumentException("Seed phrase cannot be null/blank");
		}
		seed = seed.trim();
		if (style == Shelley && seed.split(" ").length != 24) {
			throw new IllegalArgumentException("Seed phrase must have 24 words");
		}
		return generatePublicRootKeyFromPrivateKey(generatePrivateKey(seed), accountIndex);
	}

	public String deriveKey(String publicRootKey, long sequentialDerivationIndex) {
		if (StringUtils.isBlank(publicRootKey)) {
			throw new IllegalArgumentException("Public root key cannot be null/blank");
		}
		if (sequentialDerivationIndex < 0) {
			throw new IllegalArgumentException("Sequential derivation index can't be negative. Got " + sequentialDerivationIndex);
		}
		return execute("derive key", publicRootKey, "key child 0/" + sequentialDerivationIndex, true);
	}

	public String generatePaymentAddress(String derivedKey) {
		if (StringUtils.isBlank(derivedKey)) {
			throw new IllegalArgumentException("Derived key must not be null/blank");
		}
		return execute("generate payment address", derivedKey, "address payment --network-tag 1", true);
	}

	public String generatePaymentAddressFromPublicRootKey(String publicRootKey, long sequentialDerivationIndex) {
		String derivedKey = deriveKey(publicRootKey, sequentialDerivationIndex);
		return generatePaymentAddress(derivedKey);
	}
}
