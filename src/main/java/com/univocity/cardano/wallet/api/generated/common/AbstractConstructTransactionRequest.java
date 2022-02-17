package com.univocity.cardano.wallet.api.generated.common;


import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

public abstract class AbstractConstructTransactionRequest {


	@JsonProperty("payments")
	private String payments;

	@JsonProperty("withdrawal")
	private String withdrawal;

	@JsonProperty("metadata")
	private Object metadata;

	@JsonProperty("mint")
	private ArrayList<Mint> mint;

	@JsonProperty("delegations")
	private ArrayList<String> delegations;

	@JsonProperty("validity_interval")
	private ValidityInterval validityInterval;

	/**
	 * Returns the payments (optional).
	 * 
	 * @return the payments
	 */
	public String getPayments(){
		return payments;
	}

	/**
	 * Defines the payments (optional).
	 * 
	 * @param payments the payments
	 */
	public void setPayments(String payments){
		if (payments == null) {
			this.payments = payments;
			return;
		}

		this.payments = payments;
	}

	/**
	 * Returns the withdrawal (optional).
	 * 
	 * When provided, instruments the server to automatically withdraw rewards from the source wallet when they are deemed
	 * sufficient (i.e. they contribute to the balance for at least as much as they cost).
	 * As a consequence, the resulting transaction may or may not have a withdrawal object. Summarizing:
	 * withdrawal field | reward balance | result
	 * ---              | ---            | ---
	 * `null`           | too small      | ✓ no withdrawals generated
	 * `null`           | big enough     | ✓ no withdrawals generated
	 * `"self"`         | too small      | ✓ no withdrawals generated
	 * `"self"`         | big enough     | ✓ withdrawal generated
	 * 
	 * - Accepted values: {@code [self]}.
	 * 
	 * @return the withdrawal
	 */
	public String getWithdrawal(){
		return withdrawal;
	}

	/**
	 * Defines the withdrawal (optional).
	 * 
	 * When provided, instruments the server to automatically withdraw rewards from the source wallet when they are deemed
	 * sufficient (i.e. they contribute to the balance for at least as much as they cost).
	 * As a consequence, the resulting transaction may or may not have a withdrawal object. Summarizing:
	 * withdrawal field | reward balance | result
	 * ---              | ---            | ---
	 * `null`           | too small      | ✓ no withdrawals generated
	 * `null`           | big enough     | ✓ no withdrawals generated
	 * `"self"`         | too small      | ✓ no withdrawals generated
	 * `"self"`         | big enough     | ✓ withdrawal generated
	 * 
	 * - Accepted values: {@code [self]}.
	 * 
	 * @param withdrawal the withdrawal
	 */
	public void setWithdrawal(String withdrawal){
		if (withdrawal == null) {
			this.withdrawal = withdrawal;
			return;
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

	/**
	 * Returns the mint (optional).
	 * 
	 * <p>status: <strong>⚠ under development</strong></p>
	 * _This field is not implemented yet, and will always be empty._
	 * Assets minted (created) or unminted (destroyed)
	 * This amount contributes to the total transaction value.
	 * Positive values denote creation of assets and negative values
	 * denote the reverse.
	 * 
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the mint
	 */
	public ArrayList<Mint> getMint(){
		return mint;
	}

	/**
	 * Defines the mint (optional).
	 * 
	 * <p>status: <strong>⚠ under development</strong></p>
	 * _This field is not implemented yet, and will always be empty._
	 * Assets minted (created) or unminted (destroyed)
	 * This amount contributes to the total transaction value.
	 * Positive values denote creation of assets and negative values
	 * denote the reverse.
	 * 
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param mint the mint
	 */
	public void setMint(ArrayList<Mint> mint){
		if (mint == null) {
			this.mint = mint;
			return;
		}

		this.mint = mint;
	}

	/**
	 * Returns the delegations (optional).
	 * 
	 * <p>status: <strong>stable</strong></p>
	 * A list of staking actions (joining, rejoining or leaving from stake pools).
	 * Using '0H' stake key index is supported at this moment. This will change with
	 * multi-account support.
	 * Only one delegation action can be used.
	 * 
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the delegations
	 */
	public ArrayList<String> getDelegations(){
		return delegations;
	}

	/**
	 * Defines the delegations (optional).
	 * 
	 * <p>status: <strong>stable</strong></p>
	 * A list of staking actions (joining, rejoining or leaving from stake pools).
	 * Using '0H' stake key index is supported at this moment. This will change with
	 * multi-account support.
	 * Only one delegation action can be used.
	 * 
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param delegations the delegations
	 */
	public void setDelegations(ArrayList<String> delegations){
		if (delegations == null) {
			this.delegations = delegations;
			return;
		}

		this.delegations = delegations;
	}

	/**
	 * Returns the validity interval (optional).
	 * 
	 * @return the validity interval
	 */
	public ValidityInterval getValidityInterval(){
		return validityInterval;
	}

	/**
	 * Defines the validity interval (optional).
	 * 
	 * @param validityInterval the validity interval
	 */
	public void setValidityInterval(ValidityInterval validityInterval){
		if (validityInterval == null) {
			this.validityInterval = validityInterval;
			return;
		}

		this.validityInterval = validityInterval;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
