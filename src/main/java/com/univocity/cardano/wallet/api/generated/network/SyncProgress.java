package com.univocity.cardano.wallet.api.generated.network;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Estimated synchronization progress of the node with the underlying network. Note that this may
 * change quite arbitrarily as the node may switch to shorter or longer chain forks.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SyncProgress extends AbstractProgress {
}
