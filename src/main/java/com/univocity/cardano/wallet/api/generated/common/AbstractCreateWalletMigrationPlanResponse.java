package com.univocity.cardano.wallet.api.generated.common;


import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

public abstract class AbstractCreateWalletMigrationPlanResponse {


	@JsonProperty("selections")
	private ArrayList<Selection> selections;

	@JsonProperty("total_fee")
	private TotalFee totalFee;

	@JsonProperty("balance_leftover")
	private BalanceLeftover balanceLeftover;

	@JsonProperty("balance_selected")
	private BalanceSelected balanceSelected;

	/**
	 * Returns the selections.
	 * 
	 * The complete set of selections required for a migration.
	 * Each selection is the basis for a single transaction.
	 * The ordering of selections within the list is not significant.
	 * After conversion into transactions, the transactions can be
	 * broadcast to the network in any order to perform the migration.
	 * 
	 * 
	 * @return the selections
	 */
	public ArrayList<Selection> getSelections(){
		return selections;
	}

	/**
	 * Defines the selections.
	 * 
	 * The complete set of selections required for a migration.
	 * Each selection is the basis for a single transaction.
	 * The ordering of selections within the list is not significant.
	 * After conversion into transactions, the transactions can be
	 * broadcast to the network in any order to perform the migration.
	 * 
	 * 
	 * @param selections the selections
	 */
	public void setSelections(ArrayList<Selection> selections){
		if (selections == null) {
			throw new IllegalArgumentException("Value of selections cannot be null");
		}

		this.selections = selections;
	}

	/**
	 * Returns the total fee.
	 * 
	 * @return the total fee
	 */
	public TotalFee getTotalFee(){
		return totalFee;
	}

	/**
	 * Defines the total fee.
	 * 
	 * @param totalFee the total fee
	 */
	public void setTotalFee(TotalFee totalFee){
		if (totalFee == null) {
			throw new IllegalArgumentException("Value of totalFee cannot be null");
		}

		this.totalFee = totalFee;
	}

	/**
	 * Returns the balance leftover.
	 * 
	 * @return the balance leftover
	 */
	public BalanceLeftover getBalanceLeftover(){
		return balanceLeftover;
	}

	/**
	 * Defines the balance leftover.
	 * 
	 * @param balanceLeftover the balance leftover
	 */
	public void setBalanceLeftover(BalanceLeftover balanceLeftover){
		if (balanceLeftover == null) {
			throw new IllegalArgumentException("Value of balanceLeftover cannot be null");
		}

		this.balanceLeftover = balanceLeftover;
	}

	/**
	 * Returns the balance selected.
	 * 
	 * @return the balance selected
	 */
	public BalanceSelected getBalanceSelected(){
		return balanceSelected;
	}

	/**
	 * Defines the balance selected.
	 * 
	 * @param balanceSelected the balance selected
	 */
	public void setBalanceSelected(BalanceSelected balanceSelected){
		if (balanceSelected == null) {
			throw new IllegalArgumentException("Value of balanceSelected cannot be null");
		}

		this.balanceSelected = balanceSelected;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
