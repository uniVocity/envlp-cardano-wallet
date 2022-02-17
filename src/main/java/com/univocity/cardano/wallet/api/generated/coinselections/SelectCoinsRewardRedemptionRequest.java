package com.univocity.cardano.wallet.api.generated.coinselections;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#selectCoins(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SelectCoinsRewardRedemptionRequest {


	@JsonProperty("payments")
	private ArrayList<Payment> payments;

	@JsonProperty("withdrawal")
	private ArrayList<String> withdrawal;

	@JsonProperty("metadata")
	private Object metadata;

	/**
	 * Returns the list of target outputs with amount specified.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the list of target outputs with amount specified
	 */
	public ArrayList<Payment> getPayments(){
		return payments;
	}

	/**
	 * Defines a list of target outputs with amount specified.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param payments a list of target outputs with amount specified
	 */
	public void setPayments(ArrayList<Payment> payments){
		if (payments == null) {
			throw new IllegalArgumentException("Value of payments cannot be null");
		}

		this.payments = payments;
	}

	/**
	 * Returns the withdrawal.
	 * 
	 * When provided, attempts to withdraw rewards from the default stake address corresponding to the given mnemonic.
	 * Should the rewards be null or too small to be worth withdrawing (i.e. the cost of adding them into the transaction
	 * is more than their own intrinsic value), the server will reject the request with a `withdrawal_not_worth` error.
	 * withdrawal field    | reward balance | result
	 * ---                 | ---            | ---
	 * any recovery phrase | too small      | x Error 403 `withdrawal_not_worth`
	 * any recovery phrase | big enough     | ✓ withdrawal generated
	 * 
	 * - Format: {@code bip-0039-mnemonic-word{english}}.
	 * - Number of elements can range from {@code 15} to {@code 24}.
	 * 
	 * @return the withdrawal
	 */
	public ArrayList<String> getWithdrawal(){
		return withdrawal;
	}

	/**
	 * Defines the withdrawal.
	 * 
	 * When provided, attempts to withdraw rewards from the default stake address corresponding to the given mnemonic.
	 * Should the rewards be null or too small to be worth withdrawing (i.e. the cost of adding them into the transaction
	 * is more than their own intrinsic value), the server will reject the request with a `withdrawal_not_worth` error.
	 * withdrawal field    | reward balance | result
	 * ---                 | ---            | ---
	 * any recovery phrase | too small      | x Error 403 `withdrawal_not_worth`
	 * any recovery phrase | big enough     | ✓ withdrawal generated
	 * 
	 * - Format: {@code bip-0039-mnemonic-word{english}}.
	 * - Number of elements can range from {@code 15} to {@code 24}.
	 * 
	 * @param withdrawal the withdrawal
	 */
	public void setWithdrawal(ArrayList<String> withdrawal){
		if (withdrawal == null) {
			throw new IllegalArgumentException("Value of withdrawal cannot be null");
		}

		this.withdrawal = withdrawal;
	}

	/**
	 * Returns the metadata (optional).
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "0" : {
	 *         "string" : "cardano"
	 *       },
	 *       "1" : {
	 *         "int" : 14
	 *       },
	 *       "2" : {
	 *         "bytes" : "2512a00e9653fe49a44a5886202e24d77eeb998f"
	 *       },
	 *       "3" : {
	 *         "list" : [ {
	 *           "int" : 14
	 *         }, {
	 *           "int" : 42
	 *         }, {
	 *           "string" : "1337"
	 *         } ]
	 *       },
	 *       "4" : {
	 *         "map" : [ {
	 *           "k" : {
	 *             "string" : "key"
	 *           },
	 *           "v" : {
	 *             "string" : "value"
	 *           }
	 *         }, {
	 *           "k" : {
	 *             "int" : 14
	 *           },
	 *           "v" : {
	 *             "int" : 42
	 *           }
	 *         } ]
	 *       }
	 *     }
	 *   }</pre>
	 * 
	 * @return the metadata
	 */
	public Object getMetadata(){
		return metadata;
	}

	/**
	 * Defines the metadata (optional).
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "0" : {
	 *         "string" : "cardano"
	 *       },
	 *       "1" : {
	 *         "int" : 14
	 *       },
	 *       "2" : {
	 *         "bytes" : "2512a00e9653fe49a44a5886202e24d77eeb998f"
	 *       },
	 *       "3" : {
	 *         "list" : [ {
	 *           "int" : 14
	 *         }, {
	 *           "int" : 42
	 *         }, {
	 *           "string" : "1337"
	 *         } ]
	 *       },
	 *       "4" : {
	 *         "map" : [ {
	 *           "k" : {
	 *             "string" : "key"
	 *           },
	 *           "v" : {
	 *             "string" : "value"
	 *           }
	 *         }, {
	 *           "k" : {
	 *             "int" : 14
	 *           },
	 *           "v" : {
	 *             "int" : 42
	 *           }
	 *         } ]
	 *       }
	 *     }
	 *   }</pre>
	 * 
	 * @param metadata the metadata
	 */
	public void setMetadata(Object metadata){
		if (metadata == null) {
			this.metadata = metadata;
			return;
		}

		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
