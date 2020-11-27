package com.univocity.cardano.wallet.api.generated.addresses;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#postAnyAddress(okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostAnyAddressRequest {


	@JsonProperty("payment")
	private String payment;

	@JsonProperty("stake")
	private String stake;

	/**
	 * Returns the payment (optional).
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "any" : [ "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt36ms", {
	 *         "all" : [ "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt36ms", "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt37ms" ]
	 *       }, {
	 *         "any" : [ "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt36ms", {
	 *           "all" : [ "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt36ms", "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt37ms" ]
	 *         } ]
	 *       }, {
	 *         "some" : {
	 *           "at_least" : 2,
	 *           "from" : [ "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt37ms", "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt38ms" ]
	 *         }
	 *       } ]
	 *     }
	 *   }</pre>
	 * 
	 * @return the payment
	 */
	public String getPayment(){
		return payment;
	}

	/**
	 * Defines the payment (optional).
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "any" : [ "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt36ms", {
	 *         "all" : [ "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt36ms", "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt37ms" ]
	 *       }, {
	 *         "any" : [ "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt36ms", {
	 *           "all" : [ "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt36ms", "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt37ms" ]
	 *         } ]
	 *       }, {
	 *         "some" : {
	 *           "at_least" : 2,
	 *           "from" : [ "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt37ms", "script_vkh18srsxr3khll7vl3w9mqfu55n6wzxxlxj7qzr2mhnyreluzt38ms" ]
	 *         }
	 *       } ]
	 *     }
	 *   }</pre>
	 * 
	 * @param payment the payment
	 */
	public void setPayment(String payment){
		if (payment == null) {
			this.payment = payment;
			return;
		}

		this.payment = payment;
	}

	/**
	 * Returns the stake (optional).
	 * 
	 * - Example: 
	 *   <pre>{@code stake_vk16apaenn9ut6s40lcw3l8v68xawlrlq20z2966uzcx8jmv2q9uy7qau558d}</pre>
	 * 
	 * @return the stake
	 */
	public String getStake(){
		return stake;
	}

	/**
	 * Defines the stake (optional).
	 * 
	 * - Example: 
	 *   <pre>{@code stake_vk16apaenn9ut6s40lcw3l8v68xawlrlq20z2966uzcx8jmv2q9uy7qau558d}</pre>
	 * 
	 * @param stake the stake
	 */
	public void setStake(String stake){
		if (stake == null) {
			this.stake = stake;
			return;
		}

		this.stake = stake;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
