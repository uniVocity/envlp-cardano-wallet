package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

public abstract class AbstractWalletMigrationResponse {


	@JsonProperty("migration_cost")
	private MigrationCost migrationCost;

	@JsonProperty("leftovers")
	private Leftovers leftovers;

	/**
	 * Returns the migration cost.
	 * 
	 * @return the migration cost
	 */
	public MigrationCost getMigrationCost(){
		return migrationCost;
	}

	/**
	 * Defines the migration cost.
	 * 
	 * @param migrationCost the migration cost
	 */
	public void setMigrationCost(MigrationCost migrationCost){
		if (migrationCost == null) {
			throw new IllegalArgumentException("Value of migrationCost cannot be null");
		}

		this.migrationCost = migrationCost;
	}

	/**
	 * Returns the leftovers.
	 * 
	 * @return the leftovers
	 */
	public Leftovers getLeftovers(){
		return leftovers;
	}

	/**
	 * Defines the leftovers.
	 * 
	 * @param leftovers the leftovers
	 */
	public void setLeftovers(Leftovers leftovers){
		if (leftovers == null) {
			throw new IllegalArgumentException("Value of leftovers cannot be null");
		}

		this.leftovers = leftovers;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
