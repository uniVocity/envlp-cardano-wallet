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

	@JsonProperty("validation")
	private String validation;

	/**
	 * Returns the payment (optional).
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "any" : [ "addr_shared_vkh1zxt0uvrza94h3hv4jpv0ttddgnwkvdgeyq8jf9w30mcs6y8w3nq", "stake_shared_vkh1nqc00hvlc6cq0sfhretk0rmzw8dywmusp8retuqnnxzajtzhjg5", {
	 *         "all" : [ "addr_shared_vkh1zxt0uvrza94h3hv4jpv0ttddgnwkvdgeyq8jf9w30mcs6y8w3nq", "addr_shared_vkh1y3zl4nqgm96ankt96dsdhc86vd5geny0wr7hu8cpzdfcqskq2cp" ]
	 *       }, {
	 *         "any" : [ "addr_shared_vkh1zxt0uvrza94h3hv4jpv0ttddgnwkvdgeyq8jf9w30mcs6y8w3nq", {
	 *           "all" : [ "addr_shared_vkh1zxt0uvrza94h3hv4jpv0ttddgnwkvdgeyq8jf9w30mcs6y8w3nq", "addr_shared_vkh1y3zl4nqgm96ankt96dsdhc86vd5geny0wr7hu8cpzdfcqskq2cp" ]
	 *         } ]
	 *       }, {
	 *         "some" : {
	 *           "at_least" : 2,
	 *           "from" : [ "addr_shared_vkh1zxt0uvrza94h3hv4jpv0ttddgnwkvdgeyq8jf9w30mcs6y8w3nq", "addr_shared_vkh1y3zl4nqgm96ankt96dsdhc86vd5geny0wr7hu8cpzdfcqskq2cp" ]
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
	 *       "any" : [ "addr_shared_vkh1zxt0uvrza94h3hv4jpv0ttddgnwkvdgeyq8jf9w30mcs6y8w3nq", "stake_shared_vkh1nqc00hvlc6cq0sfhretk0rmzw8dywmusp8retuqnnxzajtzhjg5", {
	 *         "all" : [ "addr_shared_vkh1zxt0uvrza94h3hv4jpv0ttddgnwkvdgeyq8jf9w30mcs6y8w3nq", "addr_shared_vkh1y3zl4nqgm96ankt96dsdhc86vd5geny0wr7hu8cpzdfcqskq2cp" ]
	 *       }, {
	 *         "any" : [ "addr_shared_vkh1zxt0uvrza94h3hv4jpv0ttddgnwkvdgeyq8jf9w30mcs6y8w3nq", {
	 *           "all" : [ "addr_shared_vkh1zxt0uvrza94h3hv4jpv0ttddgnwkvdgeyq8jf9w30mcs6y8w3nq", "addr_shared_vkh1y3zl4nqgm96ankt96dsdhc86vd5geny0wr7hu8cpzdfcqskq2cp" ]
	 *         } ]
	 *       }, {
	 *         "some" : {
	 *           "at_least" : 2,
	 *           "from" : [ "addr_shared_vkh1zxt0uvrza94h3hv4jpv0ttddgnwkvdgeyq8jf9w30mcs6y8w3nq", "addr_shared_vkh1y3zl4nqgm96ankt96dsdhc86vd5geny0wr7hu8cpzdfcqskq2cp" ]
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

	/**
	 * Returns the validation (optional).
	 * 
	 * Script validation level. Required validation sifts off scripts that would not
	 * be accepted by the ledger. Recommended level filters out scripts that do not pass
	 * required validation and additionally when:
	 *   * 'all' is non-empty
	 *   * there are redundant timelocks in a given level
	 *   * there are no duplicated verification keys in a given level
	 *   * 'at_least' coeffcient is positive
	 *   * 'all', 'any' are non-empty and `'at_least' has no less elements in the list
	 *      than the coeffcient after timelocks are filtered out.
	 * 
	 * - Accepted values: {@code [required, recommended]}.
	 * 
	 * @return the validation
	 */
	public String getValidation(){
		return validation;
	}

	/**
	 * Defines the validation (optional).
	 * 
	 * Script validation level. Required validation sifts off scripts that would not
	 * be accepted by the ledger. Recommended level filters out scripts that do not pass
	 * required validation and additionally when:
	 *   * 'all' is non-empty
	 *   * there are redundant timelocks in a given level
	 *   * there are no duplicated verification keys in a given level
	 *   * 'at_least' coeffcient is positive
	 *   * 'all', 'any' are non-empty and `'at_least' has no less elements in the list
	 *      than the coeffcient after timelocks are filtered out.
	 * 
	 * - Accepted values: {@code [required, recommended]}.
	 * 
	 * @param validation the validation
	 */
	public void setValidation(String validation){
		if (validation == null) {
			this.validation = validation;
			return;
		}

		this.validation = validation;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
