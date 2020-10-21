package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.math.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * The rewards the wallet can expect to receive at the end of an epoch, in the long term, if delegating to
 * this pool.
 * For more details, see the
 * [Design Specification for Delegation and Incentives in Cardano](https://hydra.iohk.io/job/Cardano/cardano-ledger-specs/delegationDesignSpec/latest/download-by-type/doc-pdf/delegation_design_spec)
 * document.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class NonMyopicMemberRewards extends AbstractAmount {
}
