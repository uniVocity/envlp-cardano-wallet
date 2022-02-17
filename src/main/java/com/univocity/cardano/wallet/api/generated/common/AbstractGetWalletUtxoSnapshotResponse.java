package com.univocity.cardano.wallet.api.generated.common;


import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

public abstract class AbstractGetWalletUtxoSnapshotResponse {


	@JsonProperty("entries")
	private ArrayList<Entrie> entries;

	/**
	 * Returns the complete set of UTxO entries associated with a wallet.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the complete set of UTxO entries associated with a wallet.
	 */
	public ArrayList<Entrie> getEntries(){
		return entries;
	}

	/**
	 * Defines the complete set of UTxO entries associated with a wallet.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param entries the complete set of UTxO entries associated with a wallet.
	 */
	public void setEntries(ArrayList<Entrie> entries){
		if (entries == null) {
			throw new IllegalArgumentException("Value of entries cannot be null");
		}

		this.entries = entries;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
