package com.univocity.cardano.wallet.api;

import com.univocity.cardano.wallet.builders.server.*;
import com.univocity.cardano.wallet.builders.wallets.*;
import org.testng.annotations.*;

import java.util.*;

import static org.testng.Assert.*;

public class TestRemoteServer {


	RemoteWalletServer server;
	Wallet shelleyWallet;
	Wallet byronWallet;
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
		System.exit(0);
	}

	@Test
	public void testShelleyWalletCreation() {
		shelleySeed = "between faint debris journey happy bounce sword grow card suggest fury beach smoke paper employ thunder grocery uphold menu dad dutch alley coral noise";//Seed.generateEnglishSeedPhrase(24);
		shelleyFactor = "fiber urban wood thing fluid sibling hunt theme output";//Seed.generateEnglishSeedPhrase(9);

		testWallet(shelleyWallet = server.wallets().create("shelleyWallet").shelley()
				.fromSeed(shelleySeed)
				.secondFactor(shelleyFactor)
				.password("qwertyqwerty"));
	}

	@Test(dependsOnMethods = "testShelleyWalletCreation")
	public void testShelleyReadOnlyWallet() {
		testWallet(shelleyReadOnlyWallet = server.wallets().create("shelleyReadOnlyWallet").shelley()
				.fromPublicKey("invalid")); //TODO
	}

	@Test
	public void testByronWalletCreationFromSeed() {
		byronSeed = "when mosquito raccoon current resource shuffle shine bubble secret thumb fee pumpkin";//Seed.generateEnglishSeedPhrase(12);
		testWallet(byronWallet = server.wallets().create("byronWallet").byron()
				.fromSeed(byronSeed)
				.password("qwertyqwerty"));
	}

	@Test(dependsOnMethods = "testByronWalletCreationFromSeed")
	public void testByronWalletCreationFromPrivateKey() {
		testWallet(byronWalletFromPrivateKey = server.wallets().create("byronWalletFromPrivateKey").byron()
				.fromPrivateKey("invalid") //TODO
				.password("qwertyqwerty"));
	}

	@Test
	public void testIcarusWalletCreation() {
		icarusSeed = "traffic fortune weapon strong renew edit snack glow infant super sadness repair spend dwarf arrange";//Seed.generateEnglishSeedPhrase(15);
		testWallet(icarusWallet = server.wallets().create("icarusWallet").icarus()
				.fromSeed(icarusSeed)
				.password("qwertyqwerty"));
	}

	@Test(dependsOnMethods = "testIcarusWalletCreation")
	public void testIcarusReadOnlyWallet() {
		testWallet(icarusReadOnlyWallet = server.wallets().create("icarusReadOnlyWallet").icarus()
				.fromPublicKey("invalid"));//TODO
	}

	@Test
	public void testLedgerWalletCreation() {
		ledgerSeed = "retreat ill gold funny rent alpha swear fiber just spawn action maple business snake junior atom noise convince";//Seed.generateEnglishSeedPhrase(18);
		testWallet(ledgerWallet = server.wallets().create("ledgerWallet").ledger()
				.fromSeed(ledgerSeed)
				.password("qwertyqwerty"));
	}

	@Test(dependsOnMethods = "testLedgerWalletCreation")
	public void testLedgerReadOnlyWalletCreation() {
		testWallet(ledgerReadOnlyWallet = server.wallets().create("ledgerReadOnlyWallet").ledger()
				.fromPublicKey("invalid"));//TODO
	}

	@Test
	public void testTrezorWalletCreation() {
		trezorSeed = "census dolphin follow cactus result vital beach zoo claw suffer drift ability voice ladder wedding sustain stomach kick mechanic save host trim cable arrest";//Seed.generateEnglishSeedPhrase(24);
		testWallet(trezorWallet = server.wallets().create("trezorWallet").trezor()
				.fromSeed(trezorSeed)
				.password("qwertyqwerty"));
	}

	@Test(dependsOnMethods = "testTrezorWalletCreation")
	public void testTrezorReadOnlyWalletCreation() {
		testWallet(trezorReadOnlyWallet = server.wallets().create("trezorReadOnlyWallet").trezor()
				.fromPublicKey("invalid"));//TODO
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

	@Test(dependsOnMethods = "testWalletListing")
	public void testByronWalletDeletion() {
		String walletId = byronWallet.id();
		byronWallet.delete();

		Wallet wallet = server.wallets().getById(walletId);
		assertNull(wallet);
	}

//	public static void main(String... args) {
//		try {
//
////		wallet.rename("new wallet name");
////		wallet.updatePassword("old", "new");
////
////		wallet.addresses().unused().list();
////		wallet.addresses().used().list();
////		wallet.addresses().all().list();
////
////		wallet.stakePool().quit();
////		wallet.stakePool().join(stakePool);
////
////		Transaction transaction = wallet.transfer().to("address", 50000L).andTo("address 2", 25000L).withMetadata("cardano", 1, "object[]").authorize("password");
////
////		wallet.transactions().from("date").to("date").ascending().list();
////		wallet.transactions().from("date").to("date").descending().minWithdrawal(100L).list();
////
////		Transaction transaction = wallet.transactions().get("id hex");
////		transaction.forget();
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			System.exit(0);
//		}
//	}
}
