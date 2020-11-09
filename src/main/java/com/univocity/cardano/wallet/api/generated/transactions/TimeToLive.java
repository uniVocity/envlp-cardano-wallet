package com.univocity.cardano.wallet.api.generated.transactions;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * The TTL (time to live) is the time period in which the transaction
 * will be accepted into node mempools.
 * After the TTL has lapsed, the transaction is considered
 * expired. At this point, nodes will give up on broadcasting the
 * transaction, and the wallet will release the funds allocated to
 * the transaction so they can be used for other payments.
 * The TTL should be long enough that the transaction has time to be
 * propagated through the network and confirmed, but short enough so
 * that - in the event of failures - UTxO are returned to the wallet
 * in a timely manner.
 * The TTL value is given in seconds. It will be converted to a slot
 * number internally.
 * If the TTL is not provided for a payment, a reasonable default
 * value will be used.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class TimeToLive extends AbstractLengthDetails {
}
