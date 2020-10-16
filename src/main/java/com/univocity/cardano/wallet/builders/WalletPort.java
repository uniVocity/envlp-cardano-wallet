package com.univocity.cardano.wallet.builders;

import com.univocity.cardano.wallet.*;

public interface WalletPort {
	EmbeddedWalletServer startAtPort(int walletPort);
}
