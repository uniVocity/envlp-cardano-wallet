package com.univocity.cardano.wallet.common.x509;

/**
 * This class wraps an exception that could be thrown during
 * the certificate verification process.
 */
public class CertificateVerificationException extends Exception {
	private static final long serialVersionUID = 1L;

	public CertificateVerificationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CertificateVerificationException(String message) {
		super(message);
	}
}