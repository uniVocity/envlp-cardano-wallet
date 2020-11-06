package com.univocity.cardano.wallet.builders.wallets.transactions;

import java.util.*;

public interface ShelleyAuthorization extends Authorization<ShelleyTransaction> {

	Authorization<ShelleyTransaction> withMetadata(Object[] metadata);

	Authorization<ShelleyTransaction> withMetadata(Map<Long, Object> metadata);
}
