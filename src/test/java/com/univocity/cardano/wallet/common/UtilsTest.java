package com.univocity.cardano.wallet.common;

import com.fasterxml.jackson.databind.*;
import org.apache.commons.codec.binary.*;
import org.testng.annotations.*;

import java.nio.charset.*;
import java.util.*;

import static org.testng.Assert.*;

public class UtilsTest {

	@Test
	public void fromMetadataTest() throws Exception {
		String json = Utils.readTextFromResource("transaction_metadata_example.json", StandardCharsets.UTF_8);
		Map original = new ObjectMapper().readerFor(Map.class).readValue(json);

		Map result = Utils.fromMetadata(original);

		assertEquals(result.get(0L), "cardano");
		assertEquals(result.get(1L), 14);
		assertEquals(result.get(2L), Hex.decodeHex("2512a00e9653fe49a44a5886202e24d77eeb998f"));

		List list = (List)result.get(3L);
		assertEquals(list.get(0), 14);
		assertEquals(list.get(1), 42);
		assertEquals(list.get(2), "1337");

		Map map = (Map)result.get(4L);
		assertEquals(map.get("key"), "value");
		assertEquals(map.get(14), 42);
	}

	@Test
	public void toMetadataTest() throws Exception {
		String json = Utils.readTextFromResource("transaction_metadata_example.json", StandardCharsets.UTF_8);
		Map original = new ObjectMapper().readerFor(Map.class).readValue(json);
		Map result = Utils.toMetadata(Utils.fromMetadata(original));

		assertEquals(original.toString(), result.toString());
	}
}