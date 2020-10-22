package com.univocity.cardano.wallet.api;

import com.univocity.cardano.wallet.builders.server.*;
import com.univocity.cardano.wallet.builders.wallets.*;

public class TestRemoteServer {

	public static void main(String... args) {
		RemoteWalletServer server = WalletServer.remote("http://localhost").connectToPort(4444);

		System.out.println(server.wallets().list());

		Wallet wallet;
		wallet = server.wallets().create("wallet name").addressPoolGap(20).shelley().fromSeed("seed abc a").password("querty");
		wallet = server.wallets().create("wallet name").addressPoolGap(20).shelley().fromSeed("seed abc a").secondFactor("a v c").password("querty");
		wallet = server.wallets().create("wallet name").shelley().fromSeed("seed abc a").secondFactor("a v c").password("querty");
		wallet = server.wallets().create("wallet name").shelley().fromPublicKey("hex xpub");
		wallet = server.wallets().create("wallet name").byron().fromSeed("seed abc a").password("qwerty");
		wallet = server.wallets().create("wallet name").byron().fromPrivateKey("rootprivatekey HEX").password("qwerty");
		wallet = server.wallets().create("wallet name").icarus().fromSeed("seed abc a").password("qwerty");
		wallet = server.wallets().create("wallet name").ledger().fromSeed("seed abc a").password("qwerty");
		wallet = server.wallets().create("wallet name").trezor().fromSeed("seed abc a").password("qwerty");
		wallet = server.wallets().create("wallet name").addressPoolGap(10).icarus().fromPublicKey("hex xpub");
		wallet = server.wallets().create("wallet name").addressPoolGap(10).ledger().fromPublicKey("hex xpub");
		wallet = server.wallets().create("wallet name").addressPoolGap(10).trezor().fromPublicKey("hex xpub");
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

		System.exit(0);
	}
}
