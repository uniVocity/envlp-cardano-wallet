package com.univocity.cardano.wallet.api.generated.transactionsnew;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#submitTransaction(String, okhttp3.RequestBody)}.The result of signing a transaction (serialized and encoded).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SubmitTransactionRequest extends AbstractTransactionRequest {
}
