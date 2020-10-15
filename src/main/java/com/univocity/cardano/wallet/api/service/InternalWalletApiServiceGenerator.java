package com.univocity.cardano.wallet.api.service;


import com.univocity.cardano.wallet.api.*;
import com.univocity.cardano.wallet.api.generated.*;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.security.auth.login.*;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.concurrent.TimeUnit;

/**
 * Generates a Wallet API implementation based on @see {@link InternalWalletApiService}.
 */
public class InternalWalletApiServiceGenerator {

	private static final OkHttpClient sharedClient;
	private static final Converter.Factory converterFactory;
	private static final Converter<ResponseBody, WalletApiError> errorBodyConverter;

	static {
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.setMaxRequestsPerHost(500);
		dispatcher.setMaxRequests(500);
		converterFactory = JacksonConverterFactory.create();
		errorBodyConverter = (Converter) converterFactory.responseBodyConverter(WalletApiError.class, new Annotation[0], null);

		sharedClient = new OkHttpClient.Builder()
				.dispatcher(dispatcher)
				.pingInterval(20, TimeUnit.SECONDS)
				.build();
	}

	public static <S> S createService(WalletApiConfiguration configuration, Class<S> serviceClass) {
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
				throw new WalletApiException(apiError);
			}
		} catch (IOException e) {
			throw new WalletApiException(e);
		}
	}

	/**
	 * Extracts and converts the response error body into an object.
	 */
	public static WalletApiError getWalletApiError(Response<?> response) throws IOException, WalletApiException {
		return errorBodyConverter.convert(response.errorBody());
	}

	/**
	 * Returns the shared OkHttpClient instance.
	 */
	public static OkHttpClient getSharedClient() {
		return sharedClient;
	}
}
