package com.univocity.cardano.wallet.builders;

import com.univocity.cardano.wallet.*;

public interface RemoteWallet {
	WalletServer connectToPort(int port);
}
