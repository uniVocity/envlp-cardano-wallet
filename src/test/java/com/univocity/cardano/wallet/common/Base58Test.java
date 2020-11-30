package com.univocity.cardano.wallet.common;

import org.testng.annotations.*;

import java.math.*;

import static org.junit.Assert.*;

public class Base58Test {

	@DataProvider
	public Object[][] encodeParams(){
		return new Object[][]{
				{"Hello World".getBytes(), "JxF12TrwUP45BMd"},
				{BigInteger.valueOf(3471844090L).toByteArray(), "16Ho7Hs"},
				{new byte[1], "1"},
				{new byte[7], "1111111"},
				{new byte[0], ""}
		};
	}


	@Test(dataProvider = "encodeParams")
	public void encode(byte[] input, String expected){
		assertEquals(Base58.encode(input), expected);
	}

	@DataProvider
	public Object[][] decodeParams(){
		return new Object[][]{
				{"JxF12TrwUP45BMd", "Hello World".getBytes()},
				{"1", new byte[1]},
				{"1111", new byte[4]}
		};
	}

	@Test(dataProvider = "decodeParams")
	public void decode(String expected, byte[] input){
		assertEquals(expected, Base58.encode(input));
	}
}