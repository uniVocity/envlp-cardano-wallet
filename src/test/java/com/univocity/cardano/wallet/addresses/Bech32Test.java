package com.univocity.cardano.wallet.addresses;

import org.testng.annotations.*;

import static com.univocity.cardano.wallet.addresses.AddressManagerTest.*;
import static org.testng.Assert.*;

public class Bech32Test {

	private final Bech32 bech32 = new Bech32(TOOL_PATH);

	@Test
	public void testBase16ToBech32(){
		assertEquals(bech32.fromHex("base16_","706174617465"), "base16_1wpshgct5v5r5mxh0");
	}

	@Test
	public void testBase58ToBech32(){
		assertEquals(bech32.fromBase58("base58_","Ae2tdPwUPEYy"), "base58_1p58rejhd9592uusa8pzj2");
	}

	@Test
	public void testBech32ToHex(){
		assertEquals(bech32.toHex("base16_1wpshgct5v5r5mxh0"), "706174617465");
	}

}