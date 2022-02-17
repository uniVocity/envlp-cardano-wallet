package com.univocity.cardano.wallet.api.generated.network;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.math.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * The minimum ada / Lovelace quantity required for new transaction outputs.
 * It is only applicable for pure-ada outputs. If outputs contain other assets
 * or a datum hash, the true minimum will be higher than this value.
 * With Alonzo, `minimum_utxo_value` is not a real protocol parameter, but rather
 * derived from from the Alonzo genesis `adaPerUTxOWord`.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class MinimumUtxoValue extends AbstractAmount {
}
