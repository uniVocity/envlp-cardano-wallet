package com.univocity.cardano.wallet.common;

import org.bouncycastle.util.encoders.*;
import org.testng.annotations.*;

import java.util.*;

import static org.testng.Assert.*;

public class Bech32Test {
	static final String publicKeyBech32 = "root_xvk15qhndxry94h2sw72jxm0wwcnpl8henuy5xref39hjmawkx5u7ywvqw50rtcl5tnup44g8hya6na4dgljwsnukyp3fck062wgy3x6e0cxaqcr3";
	static final String publicKeyHex = "a02f3698642d6ea83bca91b6f73b130fcf7ccf84a18794c4b796faeb1a9cf11cc03a8f1af1fa2e7c0d6a83dc9dd4fb56a3f27427cb10314e2cfd29c8244dacbf";


//	@Test
//	public void testEncodingBase58() {
//		String out = Bech32.encode("base58", Base58.decode("Ae2tdPwUPEYy"));
//		System.out.println(out);
//	}

	@Test
	public void testRoundtrip(){
		String original = "base16_1wpshgct5v5r5mxh0";
		byte[] bytes = Bech32.decode(original).data;

//		System.out.println(Hex.toHexString(bytes));
//		System.out.println(Base58.encode(bytes));

		assertEquals(Bech32.encode("base16_", bytes), original);
	}

	@Test
	public void valid() {
		for (String valid : VALID) {
			Bech32.Bech32Data bechData = Bech32.decode(valid);
			String recode = Bech32.encode(bechData);
			assertEquals(recode.toLowerCase(Locale.ROOT), valid.toLowerCase(Locale.ROOT), String.format("Failed to roundtrip '%s' -> '%s'", valid, recode));
			// Test encoding with an uppercase HRP
			recode = Bech32.encode(bechData.hrp.toUpperCase(Locale.ROOT), bechData.data);
			assertEquals(recode.toLowerCase(Locale.ROOT), valid.toLowerCase(Locale.ROOT), String.format("Failed to roundtrip '%s' -> '%s'", valid, recode));
		}
	}

	private static final String[] VALID = {
			"A12UEL5L",
			"a12uel5l",
			"an83characterlonghumanreadablepartthatcontainsthenumber1andtheexcludedcharactersbio1tt5tgs",
			"abcdef1qpzry9x8gf2tvdw0s3jn54khce6mua7lmqqqxw",
			"11qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqc8247j",
			"split1checkupstagehandshakeupstreamerranterredcaperred2y9e3w",
			"?1ezyfcl",
			"A12UEL5L"
			, "a12uel5l"
			, "an83characterlonghumanreadablepartthatcontainsthenumber1andtheexcludedcharactersbio1tt5tgs"
			, "abcdef1qpzry9x8gf2tvdw0s3jn54khce6mua7lmqqqxw"
			, "11qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqc8247j"
			, "split1checkupstagehandshakeupstreamerranterredcaperred2y9e3w"
			, "?1ezyfcl"
			, "BC1SW50QA3JX3S"
			, "bc1zw508d6qejxtdg4y5r3zarvaryvg6kdaj"
			, "bc1qw508d6qejxtdg4y5r3zarvary0c5xw7kv8f3t4"
			, "BC1QW508D6QEJXTDG4Y5R3ZARVARY0C5XW7KV8F3T4"
			, "tb1qrp33g0q5c5txsp9arysrx4k6zdkfs4nce4xj0gdcccefvpysxf3q0sl5k7"
			, "tb1qqqqqp399et2xygdj5xreqhjjvcmzhxw4aywxecjdzew6hylgvsesrxh6hy"
			, "bc1pw508d6qejxtdg4y5r3zarvary0c5xw7kw508d6qejxtdg4y5r3zarvary0c5xw7k7grplx"
			, "tc1qw508d6qejxtdg4y5r3zarvary0c5xw7kg3g4ty"
			, "BC13W508D6QEJXTDG4Y5R3ZARVARY0C5XW7KN40WF2"
			, "bc1rw5uspcuh"
			, "bc10w508d6qejxtdg4y5r3zarvary0c5xw7kw508d6qejxtdg4y5r3zarvary0c5xw7kw5rljs90"
			, "BC1QR508D6QEJXTDG4Y5R3ZARVARYV98GJ9P"
			, "bc1zw508d6qejxtdg4y5r3zarvaryvqyzf3du"
			, "tb1qrp33g0q5c5txsp9arysrx4k6zdkfs4nce4xj0gdcccefvpysxf3pjxtptv"
			, "bc1gmk9yu"
	};

	@Test
	public void invalid() {
		for (String invalid : INVALID) {
			try {
				Bech32.decode(invalid);
				throw new IllegalStateException(String.format("Parsed an invalid code: '%s'", invalid));
			} catch (IllegalArgumentException x) {
				/* expected */
			}
		}
	}

	private static final String[] INVALID = {
			" 1nwldj5", // HRP character out of range
			new String(new char[]{0x7f}) + "1axkwrx", // HRP character out of range
			new String(new char[]{0x80}) + "1eym55h", // HRP character out of range
			"an84characterslonghumanreadablepartthatcontainsthenumber1andtheexcludedcharactersbio1569pvx", // overall max length exceeded
			"pzry9x0s0muk", // No separator character
			"1pzry9x0s0muk", // Empty HRP
			"x1b4n0q5v", // Invalid data character
			"li1dgmt3", // Too short checksum
			"de1lg7wt" + new String(new char[]{0xff}), // Invalid character in checksum
			"A1G7SGD8", // checksum calculated with uppercase form of HRP
			"10a06t8", // empty HRP
			"1qzzfhee", // empty HRP
	};

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void decode_invalidCharacter_notInAlphabet() {
		Bech32.decode("A12OUEL5X");
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void decode_invalidCharacter_upperLowerMix() {
		Bech32.decode("A12UeL5X");
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void decode_invalidNetwork() {
		Bech32.decode("A12UEL5X");
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void decode_invalidHrp() {
		Bech32.decode("1pzry9x0s0muk");
	}
}