package com.univocity.cardano.wallet.api.generated.assets;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#mintBurnAssets(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class MintBurnAssetsResponse {


	@JsonProperty("transaction")
	private Transaction transaction;

	@JsonProperty("minted_burned")
	private ArrayList<MintedBurned> mintedBurned;

	/**
	 * Returns the transaction.
	 * 
	 * @return the transaction
	 */
	public Transaction getTransaction(){
		return transaction;
	}

	/**
	 * Defines the transaction.
	 * 
	 * @param transaction the transaction
	 */
	public void setTransaction(Transaction transaction){
		if (transaction == null) {
			throw new IllegalArgumentException("Value of transaction cannot be null");
		}

		this.transaction = transaction;
	}

	/**
	 * Returns the entry for each unique asset minted and/or burned, containing helpful information.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * @return the entry for each unique asset minted and/or burned, containing helpful information.
	 */
	public ArrayList<MintedBurned> getMintedBurned(){
		return mintedBurned;
	}

	/**
	 * Defines an entry for each unique asset minted and/or burned, containing helpful information.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * @param mintedBurned an entry for each unique asset minted and/or burned, containing helpful information.
	 */
	public void setMintedBurned(ArrayList<MintedBurned> mintedBurned){
		if (mintedBurned == null) {
			throw new IllegalArgumentException("Value of mintedBurned cannot be null");
		}

		this.mintedBurned = mintedBurned;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
