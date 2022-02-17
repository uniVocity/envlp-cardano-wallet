package com.univocity.cardano.wallet.api.generated.transactions;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.regex.*;
import java.math.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * An asset on the Cardano blockchain. An asset is uniquely identified by
 * its `policy_id` and `asset_name` (together, these form the _asset id_).
 * Two assets with the same `asset_name` and `policy_id` are
 * interchangeable. Yet, different assets with a same `policy_id` but
 * different `asset_name` are treated as separate assets, as are two
 * assets with the same `asset_name` but different `policy_id`.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class AssetsPaymentsRedemption extends AbstractAssetBalance {
}
