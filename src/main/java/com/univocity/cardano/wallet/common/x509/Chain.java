package com.univocity.cardano.wallet.common.x509;

import com.univocity.cardano.wallet.common.*;
import org.bouncycastle.asn1.x500.*;
import org.bouncycastle.openssl.jcajce.*;

import javax.net.ssl.*;
import java.io.*;
import java.math.*;
import java.nio.file.*;
import java.security.*;
import java.security.cert.*;
import java.util.*;

public class Chain {
	private BigInteger serialNumber = new BigInteger(Long.toString(Math.abs(new SecureRandom().nextLong())));

	KeyPair rootCAKeyPair;
	X500Name rootCAName;
	X509Certificate rootCACertificate;
	Path rootCACertificatePath;

	KeyPair serverKeyPair;
	X500Name serverName;
	X509Certificate serverCertificate;
	Path serverCertificatePath;
	Path serverKeyPath;

	TrustManagerFactory tmf;

	BigInteger getSerialNumber() {
		serialNumber = serialNumber.add(BigInteger.ONE);
		return serialNumber;
	}

	void verify() throws Exception {
		CertificateVerifier.verifyCertificate(getCertificates());
	}

	void generateFiles() {
		generateFiles(null);
	}

	static String getBaseDir(String baseDir) {
		if (baseDir == null) {
			baseDir = Utils.tempDir().toPath().resolve("chain").toFile().getAbsolutePath();
		}
		if (!new File(baseDir).exists()) {
			new File(baseDir).mkdirs();
		}
		return baseDir;
	}

	void generateFiles(String baseDir) {
		baseDir = getBaseDir(baseDir);
		Path p = new File(baseDir).toPath();

		dumpObjectsAsPem(rootCACertificatePath = p.resolve("rootCa.pem"), rootCACertificate);
		dumpObjectsAsPem(serverCertificatePath = p.resolve("server.pem"), serverCertificate);
		dumpObjectsAsPem(serverKeyPath = p.resolve("key.pem"), serverKeyPair.getPrivate());

		generateKeystore(p.resolve("keystore.tmp"), "changeit");
	}

	private static void dumpObjectsAsPem(Path path, Object... objects) {
		try (JcaPEMWriter writer = new JcaPEMWriter(new FileWriter(path.toFile()))) {
			Arrays.stream(objects).forEach(obj -> {
				try {
					writer.writeObject(obj);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
			writer.flush();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	X509Certificate[] getCertificates() {
		return new X509Certificate[]{serverCertificate, rootCACertificate};
	}

	void generateKeystore(Path pathToKeystore, String password) {
		try {
			char[] pwd = password.toCharArray();
			KeyStore store = KeyStore.getInstance("PKCS12");
			store.load(null, pwd);

			store.setKeyEntry("chain", serverKeyPair.getPrivate(), pwd, getCertificates());
			store.setCertificateEntry("rootCA", rootCACertificate);
			store.setCertificateEntry("serverCert", serverCertificate);

			try (FileOutputStream jks = new FileOutputStream(pathToKeystore.toFile().getAbsolutePath())) {
				store.store(jks, pwd);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public SSLContext getSSLContextFromKeystore(String pathToKeystore, String password) {
		return getSSLContextFromKeystore(new File(pathToKeystore).toPath(), password);
	}

	SSLContext getSSLContextFromKeystore(Path pathToKeystore, String password) {
		try {
			if (!pathToKeystore.toFile().exists()) {
				throw new IllegalStateException("Keystore doesn't exist: " + pathToKeystore);
			}

			final char[] pwd = password.toCharArray();
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			String path = pathToKeystore.toFile().getAbsolutePath();
			keyStore.load(new FileInputStream(path), pwd);

			if (!keyStore.aliases().hasMoreElements()) {
				throw new IllegalArgumentException("Keystore at " + path + " has no entries");
			}

			tmf = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			tmf.init(keyStore);

			SSLContext sslc = SSLContext.getInstance("TLS");
			sslc.init(null, tmf.getTrustManagers(), null);

			return sslc;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private SSLContext getSSLContext(X509Certificate certificate) {
		try {
			KeyStore store = KeyStore.getInstance("PKCS12");
			store.load(null);

			store.setCertificateEntry("cert", certificate);

			tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(store);

			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, tmf.getTrustManagers(), null);
			return ctx;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public X509TrustManager getTrustManager() {
		return (X509TrustManager) tmf.getTrustManagers()[0];
	}

	public String getRootCACertificatePath() {
		return rootCACertificatePath.toFile().getAbsolutePath();
	}

	public String getServerCertificatePath() {
		return serverCertificatePath.toFile().getAbsolutePath();
	}

	public String getServerKeyPath() {
		return serverKeyPath.toFile().getAbsolutePath();
	}

	public X509Certificate getRootCACertificate() {
		return rootCACertificate;
	}

	public X509Certificate getServerCertificate() {
		return serverCertificate;
	}
}