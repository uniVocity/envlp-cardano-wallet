package com.univocity.cardano.wallet.api.generated.common;


import com.univocity.cardano.wallet.api.generated.common.*;
import java.math.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * An amount of Ada spent or received, from the perspective of the wallet.
 * That is, for outgoing transaction, it represents the amount of Ada consumed
 * as inputs, minus the amount of Ada spent as fees, as deposits or to addresses
 * which do not belong to the wallet.
 * For incoming transaction, it represents the total amount of Ada received to
 * addresses that belong to the wallet.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Amount extends AbstractAmount {
}
