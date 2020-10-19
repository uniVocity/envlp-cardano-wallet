package com.univocity.cardano.wallet.api.service;

import org.apache.commons.lang3.builder.*;

/**
 * A container for errors returned by the cardano-wallet.
 */
public class WalletApiError {

	private String code;
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("code", code)
				.append("message", message)
				.toString();
	}
}
