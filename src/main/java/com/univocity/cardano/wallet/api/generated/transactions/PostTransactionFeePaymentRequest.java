package com.univocity.cardano.wallet.api.generated.transactions;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#postTransactionFee(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostTransactionFeePaymentRequest {


	@JsonProperty("payments")
	private ArrayList<PaymentsPayment> payments;

	@JsonProperty("withdrawal")
	private String withdrawal;

	@JsonProperty("metadata")
	private Object metadata;

	/**
	 * Returns the list of target outputs.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * @return the list of target outputs
	 */
	public ArrayList<PaymentsPayment> getPayments(){
		return payments;
	}

	/**
	 * Defines a list of target outputs.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * @param payments a list of target outputs
	 */
	public void setPayments(ArrayList<PaymentsPayment> payments){
		if (payments == null) {
			throw new IllegalArgumentException("Value of payments cannot be null");
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

	@Override
	public String toString() {
		return printObject(this);
	}

}
