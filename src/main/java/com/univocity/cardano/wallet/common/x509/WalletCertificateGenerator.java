package com.univocity.cardano.wallet.common.x509;

import com.univocity.cardano.wallet.builders.server.*;
import okhttp3.*;
import okhttp3.tls.*;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x500.*;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.cert.*;
import org.bouncycastle.cert.jcajce.*;
import org.bouncycastle.jce.provider.*;
import org.bouncycastle.operator.*;
import org.bouncycastle.operator.jcajce.*;
import org.bouncycastle.pkcs.*;
import org.bouncycastle.pkcs.jcajce.*;

import java.security.*;
import java.security.cert.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class WalletCertificateGenerator {

	private static final int RSA_KEY_SIZE = 4096;
	private static final String ALGO = "RSA";
	private static final String SIGNATURE_ALGO = "SHA256With" + ALGO;
	private static final String ROOT_CN = "Root CA";
	private static final String SERVER_CN = "Server Cert";

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	private WalletCertificateGenerator() {

	}

	public static Chain generate() {
		return generate(null);
	}

	public static Chain generate(String baseDir) {
		Chain details = new Chain();
		try {
			generateCA(details);
			generateServerCertificate(details);

			details.verify();

			details.generateFiles(baseDir);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return details;
	}

	private static void generateCA(Chain chain) throws Exception {
		KeyPair keypair = generateKeyPair();

		OffsetDateTime now = OffsetDateTime.now();
		X500Name cnName = x500Name(ROOT_CN);
		JcaX509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(
				cnName,
				chain.getSerialNumber(),
				Date.from(now.toInstant()),
				Date.from(now.plusDays(365 * 10).toInstant()),
				cnName,
				keypair.getPublic()
		);
		JcaX509ExtensionUtils extensionUtils = new JcaX509ExtensionUtils();
		builder.addExtension(Extension.basicConstraints, true, new BasicConstraints(0));
		builder.addExtension(Extension.authorityKeyIdentifier, false, extensionUtils.createAuthorityKeyIdentifier(keypair.getPublic()));
		builder.addExtension(Extension.subjectKeyIdentifier, false, extensionUtils.createSubjectKeyIdentifier(keypair.getPublic()));
		builder.addExtension(Extension.keyUsage, false, new KeyUsage(KeyUsage.cRLSign | KeyUsage.keyCertSign));

		ContentSigner signer = new JcaContentSignerBuilder(SIGNATURE_ALGO).build(keypair.getPrivate());
		X509CertificateHolder holder = builder.build(signer);
		X509Certificate cert = new JcaX509CertificateConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME).getCertificate(holder);

		cert.checkValidity();

		chain.rootCACertificate = cert;
		chain.rootCAKeyPair = keypair;
		chain.rootCAName = cnName;
	}

	private static void generateServerCertificate(Chain chain) throws Exception {
		KeyPair keyPair = generateKeyPair();

		X500Name subjectName = x500Name(SERVER_CN);
		PKCS10CertificationRequestBuilder p10Builder = new JcaPKCS10CertificationRequestBuilder(subjectName, keyPair.getPublic());
		JcaContentSignerBuilder csBuilder = new JcaContentSignerBuilder(SIGNATURE_ALGO).setProvider(BouncyCastleProvider.PROVIDER_NAME);
		ContentSigner signer = csBuilder.build(chain.rootCAKeyPair.getPrivate());
		PKCS10CertificationRequest csr = p10Builder.build(signer);

		OffsetDateTime now = OffsetDateTime.now();
		X509v3CertificateBuilder builder = new X509v3CertificateBuilder(
				chain.rootCAName,
				chain.getSerialNumber(),
				Date.from(now.toInstant()),
				Date.from(now.plusDays(365).toInstant()),
				csr.getSubject(),
				csr.getSubjectPublicKeyInfo()
		);

		JcaX509ExtensionUtils extensionUtils = new JcaX509ExtensionUtils();
		builder.addExtension(Extension.basicConstraints, true, new BasicConstraints(false));
		builder.addExtension(Extension.authorityKeyIdentifier, false, extensionUtils.createAuthorityKeyIdentifier(chain.rootCACertificate));
		builder.addExtension(Extension.subjectKeyIdentifier, false, extensionUtils.createSubjectKeyIdentifier(csr.getSubjectPublicKeyInfo()));
		builder.addExtension(Extension.extendedKeyUsage, false, new ExtendedKeyUsage(new KeyPurposeId[]{KeyPurposeId.id_kp_serverAuth}));
		builder.addExtension(Extension.keyUsage, false, new KeyUsage(KeyUsage.keyEncipherment | KeyUsage.digitalSignature));

		builder.addExtension(Extension.subjectAlternativeName, false, new GeneralNames(new GeneralName[]{
				new GeneralName(GeneralName.dNSName, "localhost"),
				new GeneralName(GeneralName.dNSName, "localhost.localdomain"),
				new GeneralName(GeneralName.iPAddress, "127.0.0.1"),
				new GeneralName(GeneralName.iPAddress, "::1"),
		}));

		X509CertificateHolder issuedCertHolder = builder.build(signer);
		X509Certificate issuedCert = new JcaX509CertificateConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME).getCertificate(issuedCertHolder);

		issuedCert.checkValidity();
		issuedCert.verify(chain.rootCACertificate.getPublicKey(), BouncyCastleProvider.PROVIDER_NAME);

		chain.serverCertificate = issuedCert;
		chain.serverKeyPair = keyPair;
		chain.serverName = subjectName;

	}

	private static KeyPair generateKeyPair() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGO, BouncyCastleProvider.PROVIDER_NAME);
			keyPairGenerator.initialize(RSA_KEY_SIZE);
			return keyPairGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			throw new IllegalStateException(e);
		}
	}

	private static X500Name x500Name(String cn) {
		RDN[] rDNs = new RDN[]{
				new RDN(new AttributeTypeAndValue(X509ObjectIdentifiers.commonName, new DERUTF8String(cn))),
				new RDN(new AttributeTypeAndValue(X509ObjectIdentifiers.countryName, new DERUTF8String("AU"))),
				new RDN(new AttributeTypeAndValue(X509ObjectIdentifiers.stateOrProvinceName, new DERUTF8String("SA"))),
				new RDN(new AttributeTypeAndValue(X509ObjectIdentifiers.localityName, new DERUTF8String("Adelaide"))),
				new RDN(new AttributeTypeAndValue(X509ObjectIdentifiers.organization, new DERUTF8String("univocity"))),
				new RDN(new AttributeTypeAndValue(X509ObjectIdentifiers.organizationalUnitName, new DERUTF8String("NA"))),
		};
		return new X500Name(rDNs);
	}

	public static void main(String[] args) throws Exception {
		Security.addProvider(new BouncyCastleProvider());

		Dispatcher dispatcher = new Dispatcher();
		dispatcher.setMaxRequestsPerHost(500);
		dispatcher.setMaxRequests(500);

		OkHttpClient.Builder builder = new OkHttpClient.Builder();

		Chain chain = EmbeddedWalletServer.getCertificateChain();
		if (chain != null) {
			HandshakeCertificates certificates = new HandshakeCertificates.Builder()
					.addTrustedCertificate(chain.getServerCertificate())
					.addTrustedCertificate(chain.getRootCACertificate())
					.addPlatformTrustedCertificates()
					.build();

//			builder.sslSocketFactory(chain.getSSLContextFromKeystore("/tmp/chain/keystore.tmp", "changeit").getSocketFactory(), chain.getTrustManager());
			builder.sslSocketFactory(certificates.sslSocketFactory(), certificates.trustManager());
		}

		builder
				.dispatcher(dispatcher)
				.cache(null)
				.callTimeout(2, TimeUnit.MINUTES)
				.connectTimeout(2, TimeUnit.MINUTES)
				.readTimeout(2, TimeUnit.MINUTES)
				.writeTimeout(2, TimeUnit.MINUTES)
				.pingInterval(20, TimeUnit.SECONDS);

		OkHttpClient client = builder.build();


		Request request = new Request.Builder()
				.url("https://localhost:4444/v2/network/clock")
				.build();

		Response response = client.newCall(request).execute();
		System.out.println(response.body().toString());

	}
}
