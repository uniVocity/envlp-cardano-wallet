package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * The live pool stake relative to the *total* stake.
 * For more details, see the section "Relative Stake: Active vs Total" in
 * [Design Specification for Delegation and Incentives in Cardano](https://hydra.iohk.io/job/Cardano/cardano-ledger-specs/delegationDesignSpec/latest/download-by-type/doc-pdf/delegation_design_spec).
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class RelativeStake extends AbstractPercentage {
}
