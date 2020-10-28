package com.univocity.cardano.wallet.api;

import com.univocity.cardano.wallet.builders.server.*;
import com.univocity.cardano.wallet.builders.wallets.*;
import com.univocity.cardano.wallet.common.*;

public class TestRemoteServer {

	public static void main(String... args) {
		try {
			RemoteWalletServer server = WalletServer.remote("http://localhost").connectToPort(4444);

			String seed = Seed.generateEnglishSeedPhrase(18);
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

//		wallet = server.wallets().create("byron2").byron().fromSeed("there anxiety vast trim family coast dismiss nut autumn detail record rule").password("qwertyqwerty"); //50e4e9dc55e41469ca465f774dd8902436d9e351

//      TODO: don't know how to generate the correct byron private key
//		wallet = server.wallets().create("byron_prv").byron().fromPrivateKey("f0d955181360f8e4756e09cd142f152e5fbd8059a1fe69fd0192e77669b48740c3eb3a428954f42d541e15d77e6215e291e32d64563fd9992a6c1084271bcfba834313ba333d60a90641c06d713457c958b7b0775649f7d53d4764309a86314b").password("qwertyqwerty");

//		wallet = server.wallets().create("icarus wallet").icarus().fromSeed("mercy inside toilet topic fringe half pistol pioneer bunker sting grocery now").password("qwertyqwerty");
//		wallet = server.wallets().create("ledger wallet").ledger().fromSeed("follow asthma napkin govern shop devote loop remind unlock educate kidney relief").password("qwertyqwerty");
//		wallet = server.wallets().create("trezor wallet").trezor().fromSeed("prefer manual glare fame wing unaware can wheel slender rather arrow always lobster brush remember adapt unlock risk").password("qwertyqwerty");
//		wallet = server.wallets().create("icarus pub2").icarus().fromPublicKey("c4d619afdf3c9b333146552ec2a22895a188f3aca9e8f0ddb7528c1b181994e92a59ffcfd34d64c082e9d779b3beb081c3d57885094ea097e4770975d93a59f1");
//		wallet = server.wallets().create("ledger pub").ledger().fromPublicKey("c4d619afdf3c9b333146552ec2a22895a188f3aca9e8f0ddb7528c1b181994e92a59ffcfd34d64c082e9d779b3beb081c3d57885094ea097e4770975d93a59f1");
//		wallet = server.wallets().create("trezor pub").trezor().fromPublicKey("aab93f99c52f1aaa03e5c0010a78e7908ae85f0b9906b660f4592c80b48fc9d7ac471f97dd08c2f4a0f50d7d34e0a9e0a1024fd859f804410cb8fcbc4c9a3d89");
//
//
//			wallet = server.wallets().getById("870f52b3fb75f17933dfea41bc6b904bb7381851");
//			System.out.println(wallet);
//
//			wallet = server.wallets().getById("50e4e9dc55e41469ca465f774dd8902436d9e351");
//			System.out.println(wallet);

//			wallet = server.wallets().getById("fe1113717c595d6bd415a325445efc83d1d291cf");
//			System.out.println(wallet);
//
//			wallet.delete();

//		wallet.getUTxoStatistics();


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
