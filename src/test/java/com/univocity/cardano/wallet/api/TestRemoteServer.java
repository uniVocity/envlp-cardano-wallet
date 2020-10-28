package com.univocity.cardano.wallet.api;

import com.univocity.cardano.wallet.builders.server.*;
import com.univocity.cardano.wallet.builders.wallets.*;
import com.univocity.cardano.wallet.common.*;
import com.univocity.cardano.wallet.embedded.services.*;

public class TestRemoteServer {

	public static void main(String... args) {
		try {
			RemoteWalletServer server = WalletServer.remote("http://localhost").connectToPort(4444);

			String seed = Seed.generateEnglishSeedPhrase(12);
			String factor = Seed.generateEnglishSeedPhrase(9);
			System.out.println(seed);
			System.out.println(factor);

			Wallet wallet;
//
//			wallet = server.wallets().create("wallet name 2").shelley()
//					.fromSeed("inside file hole illegal join recipe ski option stable treat toss pyramid throw utility veteran ethics auction abuse curious essence congress kingdom surround task")
//					.secondFactor("enjoy region endless vivid hawk flock poet green broom")
//					.password("qwertyqwerty"); // 870f52b3fb75f17933dfea41bc6b904bb7381851
																			  //f35dbf54027d2b149f23de8a9ef6d44154f076a47e07fc02823c9f4cb2cd466cb3eafae68b2ac39ff1ccbd222304060b8fe75767d6d09b47c1c03f565be6c02f
			                                                                  //xpub1de5qy33g35w3dfglcfu449g8nhyrmrfd27dfw756ukz5j069erzngafm4p2l9razuga3smtdmq3l9wjxj7u5tdhxvk429j9d0v5490c8qs5ck
//			wallet = server.wallets().create("public").shelley().fromPublicKey("f35dbf54027d2b149f23de8a9ef6d44154f076a47e07fc02823c9f4cb2cd466cb3eafae68b2ac39ff1ccbd222304060b8fe75767d6d09b47c1c03f565be6c02f");
//

//		wallet = server.wallets().create("byron2").byron().fromSeed("there anxiety vast trim family coast dismiss nut autumn detail record rule").password("qwertyqwerty"); //50e4e9dc55e41469ca465f774dd8902436d9e351

//      TODO: don't know how to generate the correct byron private key
//		wallet = server.wallets().create("byron_prv").byron().fromPrivateKey("f0d955181360f8e4756e09cd142f152e5fbd8059a1fe69fd0192e77669b48740c3eb3a428954f42d541e15d77e6215e291e32d64563fd9992a6c1084271bcfba834313ba333d60a90641c06d713457c958b7b0775649f7d53d4764309a86314b").password("qwertyqwerty");

//		wallet = server.wallets().create("wallet name").icarus().fromSeed("seed abc a").password("qwertyqwerty");
//		wallet = server.wallets().create("wallet name").ledger().fromSeed("seed abc a").password("qwertyqwerty");
//		wallet = server.wallets().create("wallet name").trezor().fromSeed("seed abc a").password("qwertyqwerty");
//		wallet = server.wallets().create("wallet name").icarus().fromPublicKey("hex xpub");
//		wallet = server.wallets().create("wallet name").ledger().fromPublicKey("hex xpub");
//		wallet = server.wallets().create("wallet name").trezor().fromPublicKey("hex xpub");
//
//
//		Wallet wallet = server.wallets().getById("hex xpub");
//		wallet.getUTxoStatistics();
//		wallet.delete();
//		wallet.rename("new wallet name");
//		wallet.updatePassword("old", "new");
//
//		wallet.addresses().unused().list();
//		wallet.addresses().used().list();
//		wallet.addresses().all().list();
//
//		wallet.stakePool().quit();
//		wallet.stakePool().join(stakePool);
//
//		Transaction transaction = wallet.transfer().to("address", 50000L).andTo("address 2", 25000L).withMetadata("cardano", 1, "object[]").authorize("password");
//
//		wallet.transactions().from("date").to("date").ascending().list();
//		wallet.transactions().from("date").to("date").descending().minWithdrawal(100L).list();
//
//		Transaction transaction = wallet.transactions().get("id hex");
//		transaction.forget();

			System.out.println(server.wallets().list());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
}
