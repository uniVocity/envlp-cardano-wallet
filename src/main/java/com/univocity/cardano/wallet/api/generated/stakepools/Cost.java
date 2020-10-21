package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.math.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Estimated cost set by the pool operator when registering his pool.
 * This fixed cost is taken from each reward earned by the pool before splitting rewards between stakeholders.
 * May be omitted if the wallet hasn't found the pool's registration cerificate yet.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Cost extends AbstractAmount {
}
