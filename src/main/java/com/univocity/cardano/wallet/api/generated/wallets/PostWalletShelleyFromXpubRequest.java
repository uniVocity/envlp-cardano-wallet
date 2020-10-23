package com.univocity.cardano.wallet.api.generated.wallets;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.regex.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#postWallet(okhttp3.RequestBody)}.Restore from account public key
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostWalletShelleyFromXpubRequest extends AbstractWalletFromPublicKey {
}
