package com.univocity.cardano.wallet.api;

import com.univocity.cardano.wallet.api.service.exception.*;
import com.univocity.cardano.wallet.builders.server.*;
import com.univocity.cardano.wallet.builders.wallets.*;
import com.univocity.cardano.wallet.builders.wallets.addresses.*;
import org.testng.annotations.*;

import java.time.*;
import java.util.*;

import static org.testng.Assert.*;

/**
 * Basic wallet functionality tests running against the embedded server started in class
 * {@link TestEmbeddedServer}. Need to wait a while until it syncs with the blockchain
 * and allows the wallet to connect.
 */
public class BasicWalletFunctionalityTest {

	static final String PASSWORD = "qwertyqwerty";

	RemoteWalletServer server;
	ShelleyWallet shelleyWallet;
	ByronWallet byronWallet;
	Wallet icarusWallet;
	Wallet ledgerWallet;
	Wallet trezorWallet;

	Wallet byronWalletFromPrivateKey;

	Wallet shelleyReadOnlyWallet;
	Wallet icarusReadOnlyWallet;
	Wallet ledgerReadOnlyWallet;
	Wallet trezorReadOnlyWallet;

	String shelleySeed;
	String shelleyFactor;
	String byronSeed;
	String icarusSeed;
	String ledgerSeed;
	String trezorSeed;

	@BeforeClass
	public void startServer() {
		server = WalletServer.remote("http://localhost").connectToPort(4444);
	}

	@AfterClass
	public void exit() {

	}

	@Test
	public void testShelleyWalletCreation() {
		shelleySeed = "between faint debris journey happy bounce sword grow card suggest fury beach smoke paper employ thunder grocery uphold menu dad dutch alley coral noise";//Seed.generateEnglishSeedPhrase(24);
		shelleyFactor = "fiber urban wood thing fluid sibling hunt theme output";//Seed.generateEnglishSeedPhrase(9);

		testWallet(shelleyWallet = server.wallets().createOrGet("shelleyWallet").shelley()
				.fromSeed(shelleySeed)
				.secondFactor(shelleyFactor)
				.password(PASSWORD));
	}

	@Test(dependsOnMethods = "testShelleyWalletCreation")
	public void testShelleyWalletGetPublicKey() {
		assertEquals(shelleyWallet.keys().externalUtxoPublicKey(0), "addr_xvk1pqkrxdfnuq43gkjq5qdgfyuvj29569njywst5w46lkzzrjazy5rsxqu9r57y25xsygje380t0cke95uz7gdvrns39xr5rsungfqkwvce5782a");
		assertEquals(shelleyWallet.keys().internalUtxoPublicKey(0), "addr_xvk1k96j9609ukthxtnq0te87yh7jvhq2d6q6vkyzwmgdw84f9uk3xdtzt6n5ml427mzk8se9u4g8lvw08gf7xmewva9eh4ac0300mmgv4qkjh9jx");
		assertEquals(shelleyWallet.keys().mutableAccountPublicKey(0), "stake_xvk13s9ax57ttljfuak6w95lae2vvuwp29zhvvyu7t3uj343kv0zuhmv9cy8a39tza384mrvqx3jfa2d584evu6regshjm00ujl3h2hmdcgje367c");
		assertEquals(shelleyWallet.keys().multiSigPublicKey(0), "script_xvk129zt5mee3qdzjlf2980yw02aq9q7e2alpqzc8artwdnzt2mchj9h77lns9herw7dntf5fvg3kc97kc37yvs6at9a4tnedh9qrmdxujgkgx0gc");
	}

	@Test(dependsOnMethods = "testShelleyWalletCreation")
	public void testShelleyReadOnlyWallet() {
		testWallet(shelleyReadOnlyWallet = server.wallets().createOrGet("shelleyReadOnlyWallet").shelley()
				.fromPublicKey("invalid")); //TODO
	}

	@Test
	public void testByronWalletCreationFromSeed() {
		byronSeed = "when mosquito raccoon current resource shuffle shine bubble secret thumb fee pumpkin";//Seed.generateEnglishSeedPhrase(12);
		testWallet(byronWallet = server.wallets().createOrGet("byronWallet").byron()
				.fromSeed(byronSeed)
				.password(PASSWORD));
	}

	@Test
	public void testIcarusWalletCreation() {
		icarusSeed = "traffic fortune weapon strong renew edit snack glow infant super sadness repair spend dwarf arrange";//Seed.generateEnglishSeedPhrase(15);
		testWallet(icarusWallet = server.wallets().createOrGet("icarusWallet").icarus()
				.fromSeed(icarusSeed)
				.password(PASSWORD));
	}

	@Test
	public void testLedgerWalletCreation() {
		ledgerSeed = "retreat ill gold funny rent alpha swear fiber just spawn action maple business snake junior atom noise convince";//Seed.generateEnglishSeedPhrase(18);
		testWallet(ledgerWallet = server.wallets().createOrGet("ledgerWallet").ledger()
				.fromSeed(ledgerSeed)
				.password(PASSWORD));
	}

	@Test
	public void testTrezorWalletCreation() {
		trezorSeed = "census dolphin follow cactus result vital beach zoo claw suffer drift ability voice ladder wedding sustain stomach kick mechanic save host trim cable arrest";//Seed.generateEnglishSeedPhrase(24);
		testWallet(trezorWallet = server.wallets().createOrGet("trezorWallet").trezor()
				.fromSeed(trezorSeed)
				.password(PASSWORD));
	}

	private void testWallet(Wallet wallet) {
		assertNotNull(wallet);
		Wallet fromServer = server.wallets().getById(wallet.id());
		assertNotNull(fromServer);
		assertEquals(fromServer.id(), wallet.id());

		UTxOStatistics statistics = wallet.utxoStatistics();
		assertNotNull(statistics);
		assertFalse(statistics.distribution().isEmpty());
	}

	@Test(dependsOnMethods = {"testShelleyWalletCreation", "testByronWalletCreationFromSeed"})
	public void testWalletListing() {
		List<Wallet> wallets = server.wallets().list();
		System.out.println(wallets);
		assertTrue(wallets.contains(shelleyWallet));
		assertTrue(wallets.contains(byronWallet));
	}

	@Test(dependsOnMethods = "testWalletListing")
	public void testWalletRename() {
		rename(shelleyWallet);
		rename(byronWallet);
		rename(icarusWallet);
	}

	private void rename(Wallet wallet) {
		String originalName = wallet.name();
		wallet.rename("UPDATED-" + originalName);
		assertEquals(wallet.name(), "UPDATED-" + originalName);
		assertEquals(server.wallets().getById(wallet.id()).name(), "UPDATED-" + originalName);

		wallet.rename(originalName);
		assertEquals(wallet.name(), originalName);
		assertEquals(server.wallets().getById(wallet.id()).name(), originalName);
	}

	@Test(dependsOnMethods = "testWalletListing")
	public void testWalletPasswordUpdate() {
		updatePassword(shelleyWallet);
		updatePassword(byronWallet);
		updatePassword(icarusWallet);
		updatePassword(trezorWallet);
	}

	private void updatePassword(Wallet wallet) {
		long lastChangeTime = wallet.lastPasswordChange().toInstant(ZoneOffset.UTC).toEpochMilli();

		try {
			wallet.updatePassword(PASSWORD, "1234512345");
		} catch (InvalidWalletPasswordException e) {
			wallet.delete();
			return;
		}
		long changeTime = wallet.lastPasswordChange().toInstant(ZoneOffset.UTC).toEpochMilli();
		assertTrue(changeTime > lastChangeTime);

		wallet.updatePassword("1234512345", PASSWORD);
		lastChangeTime = wallet.lastPasswordChange().toInstant(ZoneOffset.UTC).toEpochMilli();
		assertTrue(lastChangeTime > changeTime);
	}

	@Test(dependsOnMethods = "testWalletListing")
	public void testNonByronRandomWalletAddresses() {
		testNonRandomWalletAddresses(shelleyWallet);
		testNonRandomWalletAddresses(icarusWallet);
		testNonRandomWalletAddresses(ledgerWallet);
		testNonRandomWalletAddresses(trezorWallet);
	}

	@Test(dependsOnMethods = "testWalletListing")
	public void testByronRandomWalletAddresses() {
		Address address = byronWallet.addresses().next(PASSWORD);

		List<Address> addresses = byronWallet.addresses().all();
		assertTrue(addresses.contains(address));

		assertTrue(byronWallet.addresses().unused().contains(address));
		assertFalse(byronWallet.addresses().used().contains(address));
	}

	private void testNonRandomWalletAddresses(Wallet wallet) {
		List<Address> unused = wallet.addresses().unused();
		List<Address> used = wallet.addresses().used();
		List<Address> all = wallet.addresses().all();

		assertFalse(all.isEmpty());
		assertFalse(unused.isEmpty());
		assertTrue(all.containsAll(unused));
		if (!used.isEmpty()) {
			assertTrue(all.containsAll(unused));
			used.forEach(u -> assertFalse(unused.contains(u)));
		}
	}

	@Test(dependsOnMethods = "testWalletRename", enabled = false)
	public void testShelleyWalletDeletion() {
		List<Error> errors = new ArrayList<>();
		deleteTestWallet(shelleyWallet, errors);
		deleteTestWallet(byronWallet, errors);
		deleteTestWallet(icarusWallet, errors);
		deleteTestWallet(ledgerWallet, errors);
		deleteTestWallet(trezorWallet, errors);
		deleteTestWallet(byronWalletFromPrivateKey, errors);
		deleteTestWallet(shelleyReadOnlyWallet, errors);
		deleteTestWallet(icarusReadOnlyWallet, errors);
		deleteTestWallet(ledgerReadOnlyWallet, errors);
		deleteTestWallet(trezorReadOnlyWallet, errors);

		errors.forEach(Throwable::printStackTrace);
		assertTrue(errors.isEmpty());
	}

	private void deleteTestWallet(Wallet wallet, List<Error> errors) {
		if (wallet == null) {
			return;
		}
		try {
			String walletId = wallet.id();
			wallet.delete();

			Wallet result = server.wallets().getById(walletId);
			assertNull(result);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Error e) {
			errors.add(e);
		}
	}
}
