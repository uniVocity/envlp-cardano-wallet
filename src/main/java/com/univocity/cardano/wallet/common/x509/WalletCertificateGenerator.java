package com.univocity.cardano.wallet.common.x509;

import com.univocity.cardano.wallet.common.*;
import okhttp3.*;
import okhttp3.tls.*;
import org.bouncycastle.openssl.jcajce.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class WalletCertificateGenerator {

	private static final WalletCertificateGenerator instance = new WalletCertificateGenerator();

	private HandshakeCertificates clientCertificates;
	private Path rootCACertificatePath;
	private Path serverCertificatePath;
	private Path serverKeyPath;

	private WalletCertificateGenerator() {

	}

	public static WalletCertificateGenerator getInstance() {
		return instance;
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


	public void generate(String baseDir) {
		HeldCertificate rootCertificate = new HeldCertificate.Builder()
				.certificateAuthority(0)
				.duration(3650, TimeUnit.DAYS)
				.rsa2048()
				.build();

		HeldCertificate serverCertificate = new HeldCertificate.Builder()
				.commonName("server")
				.duration(365, TimeUnit.DAYS)
				.rsa2048()
				.addSubjectAlternativeName("localhost")
				.signedBy(rootCertificate)
				.build();


		baseDir = getBaseDir(baseDir);
		Path p = new File(baseDir).toPath();

		dumpObjectsAsPem(rootCACertificatePath = p.resolve("rootCa.pem"), rootCertificate.certificate());
		dumpObjectsAsPem(serverCertificatePath = p.resolve("server.pem"), serverCertificate.certificate());
		dumpObjectsAsPem(serverKeyPath = p.resolve("key.pem"), serverCertificate.keyPair().getPrivate());

		HeldCertificate clientCertificate = new HeldCertificate.Builder()
				.commonName("client")
				.duration(365, TimeUnit.DAYS)
				.rsa2048()
				.signedBy(rootCertificate)
				.build();

		clientCertificates = new HandshakeCertificates.Builder()
				.addTrustedCertificate(rootCertificate.certificate())
				.heldCertificate(clientCertificate)
				.build();
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

	public String getRootCACertificatePath() {
		return rootCACertificatePath.toFile().getAbsolutePath();
	}

	public String getServerCertificatePath() {
		return serverCertificatePath.toFile().getAbsolutePath();
	}

	public String getServerKeyPath() {
		return serverKeyPath.toFile().getAbsolutePath();
	}

	public OkHttpClient.Builder configureSSL(OkHttpClient.Builder builder) {
		if (clientCertificates != null) {
			builder.sslSocketFactory(clientCertificates.sslSocketFactory(), clientCertificates.trustManager());
		}
		return builder;
	}

	public static void main(String[] args) throws Exception {
		OkHttpClient client = WalletCertificateGenerator.getInstance().configureSSL(new OkHttpClient.Builder()).build();
		Request request = new Request.Builder()
				.url("https://localhost:4444/v2/network/clock")
				.build();

		Response response = client.newCall(request).execute();
		System.out.println(response.body().string());

	}
}
