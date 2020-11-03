package com.univocity.cardano.wallet.builders.wallets.transactions;

public interface ShelleyAuthorization extends Authorization<ShelleyTransaction> {

	Authorization<ShelleyTransaction> withMetadata(Object... metadata);
}
