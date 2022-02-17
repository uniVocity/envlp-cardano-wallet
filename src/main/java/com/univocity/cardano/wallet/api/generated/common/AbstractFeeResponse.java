package com.univocity.cardano.wallet.api.generated.common;


import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

public abstract class AbstractFeeResponse {


	@JsonProperty("estimated_min")
	private EstimatedMin estimatedMin;

	@JsonProperty("estimated_max")
	private EstimatedMax estimatedMax;

	@JsonProperty("minimum_coins")
	private ArrayList<MinimumCoin> minimumCoins;

	@JsonProperty("deposit")
	private Deposit deposit;

	/**
	 * Returns the estimated min.
	 * 
	 * @return the estimated min
	 */
	public EstimatedMin getEstimatedMin(){
		return estimatedMin;
	}

	/**
	 * Defines the estimated min.
	 * 
	 * @param estimatedMin the estimated min
	 */
	public void setEstimatedMin(EstimatedMin estimatedMin){
		if (estimatedMin == null) {
			throw new IllegalArgumentException("Value of estimatedMin cannot be null");
		}

		this.estimatedMin = estimatedMin;
	}

	/**
	 * Returns the estimated max.
	 * 
	 * @return the estimated max
	 */
	public EstimatedMax getEstimatedMax(){
		return estimatedMax;
	}

	/**
	 * Defines the estimated max.
	 * 
	 * @param estimatedMax the estimated max
	 */
	public void setEstimatedMax(EstimatedMax estimatedMax){
		if (estimatedMax == null) {
			throw new IllegalArgumentException("Value of estimatedMax cannot be null");
		}

		this.estimatedMax = estimatedMax;
	}

	/**
	 * Returns the minimum coins.
	 * 
	 * A list of minimum coin values that each output in a payment must satisfy. The values themselves depends on two things:
	 *   - (a) Some updatable protocol parameters fixed by the network.
	 *   - (b) The nature of the outputs (i.e. the kind of assets it includes).
	 * The list is a direct 1:1 mapping of the requested outputs. Said differently, it has the **same number of items** and **items
	 * are ordered in the same way** as **requested outputs** are ordered. In the case where there's no explicitly requested outputs (e.g.
	 * when calculating fee for delegation), this list is empty.
	 * For example, an output containing only `Ada` may require to be of at least `1 Ada`. An output containing only an hypothetical `AppleCoin`
	 * may require to also carry a minimum of `1.2 Ada`. Note that no matter what, a minimum coin value is always given in Lovelace / Ada.
	 * > ℹ️ This mechanism is used by the protocol to protect against flooding of the network with worthless assets. By requiring a minimum coin value to every
	 * UTxO, they are given an intrinsic value indexed itself on the value of Ada.
	 * 
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the minimum coins
	 */
	public ArrayList<MinimumCoin> getMinimumCoins(){
		return minimumCoins;
	}

	/**
	 * Defines the minimum coins.
	 * 
	 * A list of minimum coin values that each output in a payment must satisfy. The values themselves depends on two things:
	 *   - (a) Some updatable protocol parameters fixed by the network.
	 *   - (b) The nature of the outputs (i.e. the kind of assets it includes).
	 * The list is a direct 1:1 mapping of the requested outputs. Said differently, it has the **same number of items** and **items
	 * are ordered in the same way** as **requested outputs** are ordered. In the case where there's no explicitly requested outputs (e.g.
	 * when calculating fee for delegation), this list is empty.
	 * For example, an output containing only `Ada` may require to be of at least `1 Ada`. An output containing only an hypothetical `AppleCoin`
	 * may require to also carry a minimum of `1.2 Ada`. Note that no matter what, a minimum coin value is always given in Lovelace / Ada.
	 * > ℹ️ This mechanism is used by the protocol to protect against flooding of the network with worthless assets. By requiring a minimum coin value to every
	 * UTxO, they are given an intrinsic value indexed itself on the value of Ada.
	 * 
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param minimumCoins the minimum coins
	 */
	public void setMinimumCoins(ArrayList<MinimumCoin> minimumCoins){
		if (minimumCoins == null) {
			throw new IllegalArgumentException("Value of minimumCoins cannot be null");
		}

		this.minimumCoins = minimumCoins;
	}

	/**
	 * Returns the deposit.
	 * 
	 * @return the deposit
	 */
	public Deposit getDeposit(){
		return deposit;
	}

	/**
	 * Defines the deposit.
	 * 
	 * @param deposit the deposit
	 */
	public void setDeposit(Deposit deposit){
		if (deposit == null) {
			throw new IllegalArgumentException("Value of deposit cannot be null");
		}

		this.deposit = deposit;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
