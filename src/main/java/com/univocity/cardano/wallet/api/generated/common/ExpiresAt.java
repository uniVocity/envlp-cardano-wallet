package com.univocity.cardano.wallet.api.generated.common;


import com.univocity.cardano.wallet.api.generated.common.*;
import java.math.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Absolute time and slot at which the pending transaction TTL (time to live) will lapse.
 * {@code if: status == pending OR status == expired}
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ExpiresAt extends AbstractTimeDetails {
}
