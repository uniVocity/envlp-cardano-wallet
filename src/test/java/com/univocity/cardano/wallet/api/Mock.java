package com.univocity.cardano.wallet.api;

import com.github.tomakehurst.wiremock.*;
import com.github.tomakehurst.wiremock.client.*;

/**
 * Base wiremock mock configuration for local testing. All responses to be returned by the
 * mock server must be configured on the {@link #setup()} method.
 */
public abstract class Mock {

	private final String baseUrl;
	private WireMock mock;

	/**
	 * Associates this mock with the given port
	 * @param port the local port where this mock will be accessible from
	 */
	public Mock(int port) {
		baseUrl = "http://localhost:" + port;
		mock = new WireMock(port);
	}

	/**
	 * Adds a mock mapping to the mock server.
	 * @param mappingBuilder the wiremock mapping.
	 */
	protected void stubFor(MappingBuilder mappingBuilder) {
		mock.register(mappingBuilder);
	}

	/**
	 * Returns the base URL pointing to the localhost at the port number given in the constructor of this class.
	 *
	 * @return the "server" URL, i.e {@code "http://localhost:" + port}
	 */
	public final String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * Configures the mock endpoints. Subclasses will typically map URLs to files stored locally.
	 * @param wireMockServer the wiremock server instance to which endpoint stubs are to be setup
	 */
	protected abstract void setup(WireMockServer wireMockServer);
}
