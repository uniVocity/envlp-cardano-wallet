package com.univocity.cardano.wallet.api.generated.byrontransactionsnew;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#signByronTransaction(String, okhttp3.RequestBody)}.The result of signing a transaction (serialized and encoded).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SignByronTransactionResponse extends AbstractBalanceTransaction {
}
