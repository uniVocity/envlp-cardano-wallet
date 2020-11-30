package com.univocity.cardano.wallet.addresses;

import com.univocity.cardano.wallet.common.*;
import com.univocity.cardano.wallet.embedded.*;
import org.apache.commons.codec.*;
import org.apache.commons.codec.binary.*;

public class Bech32 extends CardanoToolWrapper {

	public Bech32(String toolDirectoryPath) {
		super(toolDirectoryPath, "bech32");
	}

	public String fromHex(String prefix, String hex) {
		try {
			Hex.decodeHex(hex);
		} catch (DecoderException e) {
			throw new IllegalArgumentException("Invalid hexadecimal value", e);
		}
		return execute("hex to bech32", hex, prefix, false);
	}

	public String fromBase58(String prefix, String base58) {
		Base58.decode(base58);
		return execute("base58 to bech32", base58, prefix, false);
	}

	public String toHex(String bech32) {
		com.univocity.cardano.wallet.common.Bech32.decode(bech32);
		return execute("bech32 to hex", bech32, "", false);
	}
}
