package com.univocity.cardano.wallet.api;

import com.github.tomakehurst.wiremock.client.*;
import com.univocity.cardano.wallet.api.generated.*;
import org.apache.commons.lang3.*;
import retrofit2.http.*;

import java.lang.reflect.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class CardanoWalletServiceMock extends Mock {

	public CardanoWalletServiceMock(int port) {
		super(port);
	}

	@Override
	protected void setup() {
		Class<?> c = InternalWalletApiService.class;
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			String url = null;
			GET get = method.getAnnotation(GET.class);
			if (get != null) {
				url = get.value();
			} else {
				POST post = method.getAnnotation(POST.class);
				if (post != null) {
					url = post.value();
				} else {
					PUT put = method.getAnnotation(PUT.class);
					if (put != null) {
						url = put.value();
					} else {
						DELETE delete = method.getAnnotation(DELETE.class);
						if (delete != null) {
							url = delete.value();
						}
					}
				}
			}

			if (StringUtils.isBlank(url)) {
				throw new IllegalStateException("Cannot initialize unit tests using mocks for cardano-wallet. No URL defined in method " + methodName + " of " + c);
			}

			WireMock.stubFor(any(urlEqualTo(url))
					.willReturn(aResponse()
							.withStatus(200)
							.withBodyFile("api_responses/" + methodName + "/200.json")));
		}
	}
}
