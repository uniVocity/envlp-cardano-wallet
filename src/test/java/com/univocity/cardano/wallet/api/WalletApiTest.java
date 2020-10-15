package com.univocity.cardano.wallet.api;

import com.github.tomakehurst.wiremock.*;
import com.github.tomakehurst.wiremock.core.*;
import com.univocity.cardano.wallet.api.generated.network.*;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class WalletApiTest {

	private WireMockServer wireMockServer;
	private CardanoWalletServiceMock cardanoWallet;

	WalletApiConfiguration configuration = new WalletApiConfiguration();
	private final WalletApi walletApi = new WalletApi(configuration);

	@BeforeClass
	public void setUp() {
		final int port = configuration.getWalletServicePort();
		wireMockServer = new WireMockServer(new WireMockConfiguration().port(port));
		wireMockServer.start();

		cardanoWallet = new CardanoWalletServiceMock(port);
		cardanoWallet.setup(wireMockServer);
	}

	@AfterClass
	public void tearDown() {
		wireMockServer.stop();
	}

	@Test
	public void testNetworkInformation() {
		GetNetworkInformationResponse networkInfoResponse = walletApi.sync().getNetworkInformation();
		assertEquals(networkInfoResponse.getNextEpoch().getEpochNumber().intValue(), 14);
	}

	@Test
	public void testNetworkClock() {
		GetNetworkClockResponse clock = walletApi.sync().getNetworkClock(null);
		assertEquals(clock.getStatus(), "available");
		assertEquals(clock.getOffset().getQuantity().longValue(), -11685L);
	}

}
