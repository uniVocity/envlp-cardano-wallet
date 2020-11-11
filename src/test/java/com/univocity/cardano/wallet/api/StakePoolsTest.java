package com.univocity.cardano.wallet.api;

import com.univocity.cardano.wallet.builders.server.*;
import com.univocity.cardano.wallet.builders.stakepools.*;
import org.testng.annotations.*;

import java.util.*;
import java.util.concurrent.*;

import static org.testng.Assert.*;

public class StakePoolsTest {

	RemoteWalletServer server;

	@BeforeClass
	public void startServer() {
		server = WalletServer.remote("http://localhost").connectToPort(4444);
	}

	@AfterClass
	public void exit() {

	}

	@Test
	public void testStakePoolListing() throws Exception {
		Future<List<StakePool>> process = server.stakePools().listAsync();
		List<StakePool> stakePools = process.get();

		assertTrue(stakePools.size() > 0);

		long shopPoolCount = stakePools.stream().filter(pool -> "SHOP".equals(pool.ticker())).count();
		assertEquals(shopPoolCount, 1);
	}

}