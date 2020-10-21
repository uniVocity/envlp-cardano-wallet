package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.math.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * The epoch in which a stake pool retires.
 * May be omitted if the wallet hasn't yet found a retirement certificate
 * for this stake pool.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Retirement extends AbstractSchedule {
}
