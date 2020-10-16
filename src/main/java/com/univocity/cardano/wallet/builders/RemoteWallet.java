package com.univocity.cardano.wallet.builders;

import com.univocity.cardano.wallet.*;

public interface RemoteWallet {
	RemoteWalletServer connectToPort(int port);
}
