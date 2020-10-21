package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Variable margin on the total reward given to an operator before splitting rewards between stakeholders.
 * May be omitted if the wallet hasn't found the pool's registration cerificate yet.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Margin extends AbstractPercentage {
}
