package com.univocity.cardano.wallet.api;

import com.univocity.cardano.wallet.builders.server.*;
import com.univocity.cardano.wallet.builders.stakepools.*;
import com.univocity.cardano.wallet.builders.wallets.*;
import com.univocity.cardano.wallet.builders.wallets.transactions.*;
import org.testng.annotations.*;

import java.math.*;
import java.util.*;

import static org.testng.Assert.*;

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
	public void testStakePoolJoining() throws Exception {
		List<StakePool> pools = server.stakePools().listAsync().get();

		String id = undelegatedShelleyWallet.currentStakePoolId();
		if(pools.get(0).id().equals(id) || (id != null && id.equals(undelegatedShelleyWallet.nextStakePoolId()))){
			undelegatedShelleyWallet.delegate(pools.get(1), PASSWORD);
		} else {
			undelegatedShelleyWallet.delegate(pools.get(0), PASSWORD);
		}

		while(true){
			undelegatedShelleyWallet = undelegatedShelleyWallet.update();
			System.out.println("next pool id " + undelegatedShelleyWallet.nextStakePoolId());
			System.out.println("delegated to " + undelegatedShelleyWallet.currentStakePoolId());

			if(!id.equals(undelegatedShelleyWallet.currentStakePoolId())){
				System.out.println("Stake pool switched");
				break;
			}

			Thread.sleep(5_000);
		}
	}

	@Test(dependsOnMethods = "testStakePoolJoining")
	public void testUndelegateFromStakePool() throws Exception {
		System.out.println("Undelegating");

		undelegatedShelleyWallet.undelegate(PASSWORD);
		String id = undelegatedShelleyWallet.currentStakePoolId();
		if(id == null){
			testStakePoolJoining();
			id = undelegatedShelleyWallet.currentStakePoolId();
		}

		while(id != null){
			undelegatedShelleyWallet = undelegatedShelleyWallet.update();
			id = undelegatedShelleyWallet.currentStakePoolId();
			Thread.sleep(5_000);
		}
		System.out.println("Undelegated");
	}

	@Test
	public void transferTestShelleyToShelley() {
		BigDecimal payerBalance = undelegatedShelleyWallet.totalBalance();
		BigDecimal amountToTransfer = payerBalance.multiply(new BigDecimal("0.01"));
		BigDecimal payeeBalance = emptyShelleyWallet.totalBalance();

		ShelleyTransaction transaction = undelegatedShelleyWallet.transfer().to(emptyShelleyWallet.addresses().next(), new BigDecimal(1)).authorize(PASSWORD);
		assertEquals(transaction.status(), Transaction.Status.PENDING);
		System.out.println(transaction);

		String transactionId = "2b1633ba62ee3bfc553f69579ae308614ed5c6a425844a6dbf4ba0629ab82ecf";
		transaction = undelegatedShelleyWallet.transactions().get(transactionId);

//		System.out.println(transaction);
//		List<ShelleyTransaction> transactions = undelegatedShelleyWallet.transactions().list();
//		assertTrue(transactions.contains(transaction));
//		System.out.println(transactions);

		//transaction.update();

//		{
//			"id" : "2b1633ba62ee3bfc553f69579ae308614ed5c6a425844a6dbf4ba0629ab82ecf",
//				"amount" : {
//			"quantity" : 1129800,
//					"unit" : "lovelace"
//		},
//			"inserted_at" : null,
//				"pending_since" : {
//			"time" : "2020-11-06T15:32:53.6Z",
//					"block" : {
//				"slot_number" : 88,
//						"epoch_number" : 876,
//						"height" : {
//					"quantity" : 87290,
//							"unit" : "block"
//				},
//				"absolute_slot_number" : 175168
//			}
//		},
//			"depth" : null,
//				"direction" : "outgoing",
//				"inputs" : [ {
//			"address" : "addr1vyuxc75xmzzy7sy955pyz4tqg0ycgttjcv2u39ay929q2yq8h7umy",
//					"amount" : {
//				"quantity" : 100000000000,
//						"unit" : "lovelace"
//			},
//			"id" : "8284950bf30f2b94f1f9f85b9b0a81f5ab6b9862679c9aa2bd21675fcd156a45",
//					"index" : 51
//		} ],
//			"outputs" : [ {
//			"address" : "addr1q9p3npvyc00t9elvzyh5mqacle625hxt3x8ym9xds25h8wdwzdq65lps4pmtz6e5gwqsurydjrkc04vn82uwgsnpd0nsyk9frg",
//					"amount" : {
//				"quantity" : 1000000,
//						"unit" : "lovelace"
//			}
//		}, {
//			"address" : "addr1q95t40ycd5gzu9fqf5xvy7k3keld8afzsujy5fhelcmla7e59kkfl90wf7f9vlm99fek6e9l5zh65td8jhw63hn9skqqndny3f",
//					"amount" : {
//				"quantity" : 99998870200,
//						"unit" : "lovelace"
//			}
//		} ],
//			"withdrawals" : [ ],
//			"status" : "pending",
//				"metadata" : null
//		}


//		undelegatedShelleyWallet.transfer().

//		ByronTransaction byronTransaction = byronWallet.transfer().to(shelleyWallet, new BigInteger(1000000)).authorize(PASSWORD);
	}

	@Test
	public void estimateFeeShelleyToShelleyWithMetadata() {
//		99997.735900

		BigDecimal payerBalance = undelegatedShelleyWallet.totalBalance();
		BigDecimal amountToTransfer = payerBalance.multiply(new BigDecimal("0.01"));
		BigDecimal payeeBalance = emptyShelleyWallet.totalBalance();


		Fees<ShelleyTransaction> fees = undelegatedShelleyWallet.transfer().to(emptyShelleyWallet.addresses().next(), new BigDecimal(1)).estimateFees();
		System.out.println(fees.average());

		Transaction transaction = fees.authorize(PASSWORD);
		System.out.println(transaction);
	}

	@Test
	public void testTransferFromShelleyToByron() {
//		99997.735900

		BigDecimal payerBalance = emptyShelleyWallet.totalBalance();
		BigDecimal amountToTransfer = payerBalance.multiply(new BigDecimal("0.01"));
		BigDecimal payeeBalance = byronWallet.totalBalance();


		Fees<ShelleyTransaction> fees = emptyShelleyWallet.transfer().to(byronWallet.addresses().next(), new BigDecimal(1)).withMetadata(new Object[]{"testing"}).estimateFees();
		System.out.println(fees.average());

		Transaction transaction = fees.authorize(PASSWORD);
		System.out.println(transaction);
	}

	@Test
	public void testTransferFromByronToByron() {
//		99997.735900

		BigDecimal payerBalance = icarusWallet.totalBalance();
		BigDecimal amountToTransfer = payerBalance.multiply(new BigDecimal("0.01"));
		BigDecimal payeeBalance = byronWallet.totalBalance();


		Fees<ByronTransaction> fees = icarusWallet.transfer().to(byronWallet.addresses().next(), new BigDecimal(1)).withMetadata(new Object[]{"testing"}).estimateFees();
		System.out.println(fees.average());

		Transaction transaction = fees.authorize(PASSWORD);
		System.out.println(transaction);
	}


	@Test
	public void transferTestShelleyToShelleyWithMetadata() {
		BigDecimal payerBalance = undelegatedShelleyWallet.totalBalance();
		System.out.println(payerBalance);
		BigDecimal amountToTransfer = payerBalance.multiply(new BigDecimal("0.01"));
		BigDecimal payeeBalance = emptyShelleyWallet.totalBalance();

//		ShelleyTransaction transaction = undelegatedShelleyWallet.transfer().to(emptyShelleyWallet.addresses().next(), new BigDecimal(1)).withMetadata(new Object[]{"cardano", 1}).authorize(PASSWORD);
//		System.out.println(transaction);

		String transactionId = "fcaf4d9ec5f272b6fdf7fae1107e4d9536922ca0a9bc77387e0a445cf839d07e";
		ShelleyTransaction transaction = undelegatedShelleyWallet.transactions().get(transactionId);

		transaction = transaction.update();

		System.out.println(transaction);

		List<ShelleyTransaction> transactions = emptyShelleyWallet.transactions().list();
		for (ShelleyTransaction t : transactions) {
			System.out.println("=====================================");
			System.out.println(t);
		}

		System.out.println(emptyShelleyWallet.totalBalance());


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
