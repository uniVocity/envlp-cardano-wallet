package com.univocity.cardano.wallet.api;

import com.univocity.cardano.wallet.builders.server.*;
import com.univocity.cardano.wallet.builders.wallets.*;
import com.univocity.cardano.wallet.builders.wallets.transactions.*;
import org.testng.annotations.*;

import java.math.*;

/**
 * All tests in this class work based on fees, transfers and anything that involves transfer of value.
 *
 * This depends on having a test blockchain available, which can be started automatically with the
 * `shelley-test-cluster` available from the cardano-wallet repository.
 *
 * The setup steps are quite simple:
 *
 * clone: `cardano-wallet`
 *
 * execute: `stack install cardano-wallet:exe:shelley-test-cluster`
 *
 * After it is compiled, execute:
 * `~/.local/bin/shelley-test-cluster`
 *
 * Make sure all other binaries are also available in your `~/.local/bin` folder, and that this folder is in your PATH.
 * Also `export CARDANO_WALLET_PORT=7654` so the wallet always start at the same port.
 *
 * Documentation on this tool is currently available here:
 * https://github.com/input-output-hk/cardano-wallet/blob/master/lib/shelley/exe/shelley-test-cluster.hs#L73-L191
 */
public class TransactionTests {

	static final String PASSWORD = "qwertyqwerty";
	public static final int WALLET_PORT = 7654;
	RemoteWalletServer server;

	String emptyShelleyWalletFactor;
	ShelleyWallet emptyShelleyWallet;

	/**
	 * (Shelley) Has a pre-registered stake key but no delegation.
	 */
	ShelleyWallet undelegatedShelleyWallet;


	/**
	 * (Shelley) Contains only small coins (but greater than the minUTxOValue)
	 */
	ShelleyWallet smallCoinShelleyWallet;

	/**
	 * (Shelley) Contains 100 UTxO of 100_000 Ada, and 100 UTxO of 1 Ada
	 */
	ShelleyWallet shelleyWallet;

	/**
	 * (Icarus) Has only addresses that start at index 500
	 */
	ByronWallet icarusWallet;

	/**
	 * (Byron) Has only 5 UTxOs of 1,2,3,4,5 Lovelace
	 */
	ByronWallet smallCoinByronWallet;

	/**
	 * (Byron) Has 200 UTxO, 100 are worth 1 Lovelace, 100 are woth 100_000 Ada.
	 */
	ByronWallet byronWallet;

	/**
	 * (Ledger) Created via the Ledger method for master key generation
	 */
	Wallet ledgerWallet;

	/**
	 * (Ledger) Created via the Ledger method for master key generation
	 */
	Wallet ledgerWallet2;

	@BeforeClass
	public void startServer() {
		server = WalletServer.remote("http://localhost").connectToPort(WALLET_PORT);

		emptyShelleyWalletFactor = "fiber urban wood thing fluid sibling hunt theme output";//Seed.generateEnglishSeedPhrase(9);
		emptyShelleyWallet = server.wallets().createOrGet("emptyShelleyWallet").shelley()
				.fromSeed("over decorate flock badge beauty stamp chest owner excess omit bid raccoon spin reduce rival")
				.secondFactor(emptyShelleyWalletFactor)
				.password(PASSWORD);

		undelegatedShelleyWallet = server.wallets().createOrGet("undelegatedShelleyWallet").shelley()
				.fromSeed("over decorate flock badge beauty stamp chest owner excess omit bid raccoon spin reduce rival")
				.password(PASSWORD);

		smallCoinShelleyWallet = server.wallets().createOrGet("smallCoinShelleyWallet").shelley()
				.fromSeed("either flip maple shift dismiss bridge sweet reveal green tornado need patient wall stamp pass")
				.password(PASSWORD);

		shelleyWallet = server.wallets().createOrGet("shelleyWallet").shelley()
				.fromSeed("radar scare sense winner little jeans blue spell mystery sketch omit time tiger leave load")
				.password(PASSWORD);

		icarusWallet = server.wallets().createOrGet("icarusWallet").icarus()
				.fromSeed("erosion ahead vibrant air day timber thunder general dice into chest enrich social neck shine")
				.password(PASSWORD);

		smallCoinByronWallet = server.wallets().createOrGet("smallCoinByronWallet").icarus()
				.fromSeed("suffer decorate head opera yellow debate visa fire salute hybrid stone smart")
				.password(PASSWORD);

		byronWallet = server.wallets().createOrGet("byronWallet").icarus()
				.fromSeed("collect fold file clown injury sun brass diet exist spike behave clip")
				.password(PASSWORD);

		ledgerWallet = server.wallets().createOrGet("ledgerWallet").ledger()
				.fromSeed("struggle section scissors siren garbage yellow maximum finger duty require mule earn")
				.password(PASSWORD);

		ledgerWallet2 = server.wallets().createOrGet("ledgerWallet2").ledger()
				.fromSeed("vague wrist poet crazy danger dinner grace home naive unfold april exile relief rifle ranch tone betray wrong")
				.password(PASSWORD);

	}

	@AfterClass
	public void exit() {
		System.exit(0);
	}

	@Test
	public void transferTestShelleyToShelley() {
		BigDecimal payerBalance = undelegatedShelleyWallet.totalBalance();
		BigDecimal amountToTransfer = payerBalance.multiply(new BigDecimal("0.01"));
		BigDecimal payeeBalance = emptyShelleyWallet.totalBalance();

		ShelleyTransaction transaction = undelegatedShelleyWallet.transfer().to(emptyShelleyWallet.addresses().next(), new BigDecimal(1)).authorize(PASSWORD);
		System.out.println(transaction);

//		ByronTransaction byronTransaction = byronWallet.transfer().to(shelleyWallet, new BigInteger(1000000)).authorize(PASSWORD);
	}

	@Test
	public void transferTestShelleyToShelleyWithMetadata() {
		//FIXME need to encode metadata as CBOR
		
		BigDecimal payerBalance = undelegatedShelleyWallet.totalBalance();
		BigDecimal amountToTransfer = payerBalance.multiply(new BigDecimal("0.01"));
		BigDecimal payeeBalance = emptyShelleyWallet.totalBalance();

		ShelleyTransaction transaction = undelegatedShelleyWallet.transfer().to(emptyShelleyWallet.addresses().next(), new BigDecimal(1)).withMetadata(new Object[]{"cardano", 1}).authorize(PASSWORD);
		System.out.println(transaction);

//		ByronTransaction byronTransaction = byronWallet.transfer().to(shelleyWallet, new BigInteger(1000000)).authorize(PASSWORD);
	}


//	public static void main(String... args) {
//		try {
////
////		wallet.stakePool().quit();
////		wallet.stakePool().join(stakePool);
////
////		Transaction transaction = wallet.transfer().to(address, 50000L).andTo(address 2, 25000L).withMetadata(cardano, 1, object[]).authorize(password);
////
////		wallet.transactions().from(date).to(date).ascending().list();
////		wallet.transactions().from(date).to(date).descending().minWithdrawal(100L).list();
////
////		Transaction transaction = wallet.transactions().get(id hex);
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
