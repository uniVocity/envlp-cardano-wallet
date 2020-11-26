package com.univocity.cardano.wallet.api.service;


import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.*;
import com.univocity.cardano.wallet.api.service.exception.*;
import com.univocity.cardano.wallet.common.x509.*;
import okhttp3.*;
import org.apache.commons.lang3.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.*;
import retrofit2.converter.jackson.*;

import java.io.*;
import java.lang.annotation.*;
import java.util.concurrent.*;

/**
 * Generates a Wallet API implementation based on @see {@link InternalWalletApiService}.
 */
public class InternalWalletApiServiceGenerator {

	private static final OkHttpClient sharedClient;
	private static final Converter.Factory converterFactory;
	private static final Converter<ResponseBody, WalletApiError> errorBodyConverter;

	private static final Interceptor interceptor = chain -> {
		Request request = chain.request();
		Request.Builder builder = request.newBuilder().addHeader("Cache-Control", "no-cache");
		request = builder.build();
		return chain.proceed(request);
	};

	static {
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.setMaxRequestsPerHost(500);
		dispatcher.setMaxRequests(500);
		converterFactory = JacksonConverterFactory.create();
		errorBodyConverter = (Converter) converterFactory.responseBodyConverter(WalletApiError.class, new Annotation[0], null);

		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		WalletCertificateGenerator.getInstance().configureSSL(builder);

		builder
				.dispatcher(dispatcher)
				.cache(null)
				.addInterceptor(interceptor)
				.callTimeout(2, TimeUnit.MINUTES)
				.connectTimeout(2, TimeUnit.MINUTES)
				.readTimeout(2, TimeUnit.MINUTES)
				.writeTimeout(2, TimeUnit.MINUTES)
				.pingInterval(20, TimeUnit.SECONDS);

		sharedClient = builder.build();
	}

	public static <S> S createService(ApiConfiguration configuration, Class<S> serviceClass) {
		String walletServiceUrl = configuration.getWalletServiceBaseUrl();
		Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
				.baseUrl(walletServiceUrl)
				.addConverterFactory(converterFactory)
				.validateEagerly(true);

		Retrofit retrofit = retrofitBuilder.build();

		return retrofit.create(serviceClass);
	}

	/**
	 * Execute a REST call and block until the response is received.
	 */
	public static <T> T executeSync(Call<T> call) {
		try {
			Response<T> response = call.execute();
			if (response.isSuccessful()) {
				return response.body();
			} else {
				WalletApiError apiError = getWalletApiError(response);
				throw WalletApiException.translateError(apiError);
			}
		} catch (IOException e) {
			throw new WalletApiException(e);
		}
	}

	/**
	 * Extracts and converts the response error body into an object.
	 */
	public static WalletApiError getWalletApiError(Response<?> response) throws IOException, WalletApiException {
		if ("text/plain".equals(response.headers().get("Content-type"))) {
			WalletApiError out = new WalletApiError();
			StringBuilder tmp = new StringBuilder();
			String message = response.message();
			if (StringUtils.isNotBlank(message)) {
				tmp.append(message.trim());
			}
			ResponseBody body = response.errorBody();
			if (body != null) {
				String string = body.string();
				if (StringUtils.isNotBlank(string)) {
					if (tmp.length() > 0) {
						tmp.append(": ");
					}
					tmp.append(string);
				}
			}
			out.setMessage(tmp.toString());

			return out;
		}
		return errorBodyConverter.convert(response.errorBody());
	}

	/**
	 * Returns the shared OkHttpClient instance.
	 */
	public static OkHttpClient getSharedClient() {
		return sharedClient;
	}
}
