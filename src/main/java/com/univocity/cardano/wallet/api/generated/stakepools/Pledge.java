package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.math.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Minimal stake amount that a stake pool is willing to honor.
 * May be omitted if the wallet hasn't found the pool's registration cerificate yet.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Pledge extends AbstractAmount {
}
