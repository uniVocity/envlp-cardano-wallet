package com.univocity.cardano.wallet.addresses;


import org.testng.annotations.*;

import static org.testng.Assert.*;

public class AddressManagerTest {

	final String TOOL_PATH = System.getProperty("user.home") + "/Downloads" + "/cardano-wallet-linux64/";

	private final AddressManager addressManager = new AddressManager(TOOL_PATH);

	public static final String seed = "host calm shed bus chief cloud guide assist arrange upper embody crush party empower flag inside shove chef asset general vacuum buzz pink abstract";
	public static final String privateKey = "root_xsk12ry6ne5nx3lvdleymaddsqz5uvj77wzmjfs94smh4z3kjttlxaxvykja9g45mkz3jl0c6t7wz8srdr4k4ukvgfysrjd4t76rjae4stkjylzhw2sng257uf2vmtkyt9k0ps5rry7xrxgw4z0mda4rxskasgdd4cjg";
	public static final String hardenedPublicKeyPath_0 = "acct_xsk1yzz2tgwc9pz8tez6x3ah536nvw9e7ng4s3kn56vz75tquwtlxaxvfeq4ccdasw639j0qfewqf6vj029lx5kzv3xjtp958cc07rjlv2z6j328sewxyhk08fwz5x33c7825murwg4gw72aj0v3rp5h9nxf6yrrh58t";
	public static final String publicRootKey_0 = "acct_xvk1mg9krrgrgv5qd22s0cl67phallhhzndypw5l29eum8tqua9c05z949z50pjuvf0v7wju9gdrr3uw4fhcxu32sau4my7ezxrfwtxvn5gnnypxp";

	public static final String hardenedPublicKeyPath_10 = "acct_xsk1eruluuwr02s32ft6zj29x85m7c95hvucaran6tn6pdaz2wrlxaxfx2zetturxgduycry353xxammr5tcqjuqehnyjwu9slh5uxp62wqtrv8cwtjsphlhahqd2apclafea20tqxunj3e30u9a6z7r4d5kyc0u0j8g";
	public static final String publicRootKey_10 = "acct_xvk1nmx0cjdadnvhd34dctzsmu47s84p950dv7ckws7vyd057lyhxcjskxc0suh9qr0l0mwq646r3l6nn657kqde89rnzlctm59u82mfvfsd99xys";

	public static final String derivedKey0_0 = "addr_xvk1kzfm3ap909zrm8pdu8sxralpjcs2j74c4wwjuxum6s950xhxmtqs63gfp6y29e5ya2zfrxyyca7n6sx9prmquhwyf32jk054hhe5nschjwh7v";
	public static final String paymentAddress0_0 = "addr1v9fcy4q53km9dsnnkjhzsrl05f5z94p5n8r2h5hrkv0ucwchcc4af";

	public static final String derivedKey10_10 = "addr_xvk1w6rlcfj8pxu8990ll4uexq64v9gqe0y4qda6au6u88l6jmkt7zhjm94s6dyh0t5d2uhzqhsavu3u8qs7v08tla0cfwkd084dk6fk7rcmtuuhe";
	public static final String paymentAddress10_10 = "addr1v9vw4wv94aeqa2pjq2skvqpq8sesmqpcjh3cs3kfrce9qqgg6dr2u";

	@Test
	public void testGenerateSeed() {
		String seed = addressManager.generateSeed();
		assertNotNull(seed);
		assertEquals(seed.split(" ").length, 24);
	}

	@Test
	public void testGeneratePrivateKey() {
		String privateKeyFromSeed = addressManager.generatePrivateKey(seed);
		assertEquals(privateKeyFromSeed, privateKey);
	}

	@Test
	public void testGenerateHardenedPublicKeyPath() {
		String hardenedPublicKeyPathFromPrivateKey;
		hardenedPublicKeyPathFromPrivateKey = addressManager.generateHardenedPublicKeyPath(privateKey, 0);
		assertEquals(hardenedPublicKeyPathFromPrivateKey, hardenedPublicKeyPath_0);

		hardenedPublicKeyPathFromPrivateKey = addressManager.generateHardenedPublicKeyPath(privateKey, 10);
		assertEquals(hardenedPublicKeyPathFromPrivateKey, hardenedPublicKeyPath_10);
	}

	@Test
	public void testGeneratePublicRootKeyFromPrivateKey() {
		String publicRootKeyFromPrivateKey = addressManager.generatePublicRootKeyFromPrivateKey(privateKey, 0);
		assertEquals(publicRootKeyFromPrivateKey, publicRootKey_0);

		publicRootKeyFromPrivateKey = addressManager.generatePublicRootKeyFromPrivateKey(privateKey, 10);
		assertEquals(publicRootKeyFromPrivateKey, publicRootKey_10);
	}

	@Test
	public void testGeneratePublicRootKeyFromSeed() {
		String publicRootKeyFromSeed = addressManager.generatePublicRootKeyFromSeed(seed, 0);
		assertEquals(publicRootKeyFromSeed, publicRootKey_0);

		publicRootKeyFromSeed = addressManager.generatePublicRootKeyFromSeed(seed, 10);
		assertEquals(publicRootKeyFromSeed, publicRootKey_10);
	}

	@Test
	public void testDeriveKey() {
		String derivedKey = addressManager.deriveKey(publicRootKey_0, 0);
		assertEquals(derivedKey, derivedKey0_0);

		derivedKey = addressManager.deriveKey(publicRootKey_10, 10);
		assertEquals(derivedKey, derivedKey10_10);
	}

	@Test
	public void testGeneratePaymentAddress() {
		String paymentAddress = addressManager.generatePaymentAddress(derivedKey0_0);
		assertEquals(paymentAddress, paymentAddress0_0);

		paymentAddress = addressManager.generatePaymentAddress(derivedKey10_10);
		assertEquals(paymentAddress, paymentAddress10_10);
	}

	@Test
	public void testGeneratePaymentAddressFromPublicRootKey() {
		String paymentAddress = addressManager.generatePaymentAddressFromPublicRootKey(publicRootKey_0, 0);
		assertEquals(paymentAddress, paymentAddress0_0);

		paymentAddress = addressManager.generatePaymentAddressFromPublicRootKey(publicRootKey_10, 10);
		assertEquals(paymentAddress, paymentAddress10_10);
	}

	@Test
	public void testByronPrivateKey() {
		String privatekey = addressManager.setStyle(AddressStyle.Byron).generatePrivateKey("there anxiety vast trim family coast dismiss nut autumn detail record rule");
		assertEquals(privatekey, "root_xsk17rv42xqnvruwgatwp8x3gtc49e0mmqze58lxnlgpjtnhv6d5saqv86e6g2y4fapd2s0pt4m7vg279y0r94j9v07eny4xcyyyyudulw5rgvfm5veavz5svswqd4cng47ftzmmqa6kf8ma2028vscf4p33fv3kxw3d");
	}

	@Test
	public void testIcarusPublicKey() {
		String privatekey = addressManager.setStyle(AddressStyle.Icarus).generatePublicRootKeyFromSeed("mercy inside toilet topic fringe half pistol pioneer bunker sting grocery now", 0);
		assertEquals(privatekey, "acct_xvk19al3prfdnvyq3uq5nr93r7c3c24pajmht252pwvqm9zqlrn8vgsusjx8k0pzyy3c34z2t0vmecuxkpwrzk0wyj8ca56pzv0e5qrvlrq9gd6kq");
	}
}