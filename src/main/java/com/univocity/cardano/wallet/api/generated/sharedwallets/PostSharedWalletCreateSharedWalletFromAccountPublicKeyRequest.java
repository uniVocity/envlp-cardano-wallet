package com.univocity.cardano.wallet.api.generated.sharedwallets;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.regex.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#postSharedWallet(okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostSharedWalletCreateSharedWalletFromAccountPublicKeyRequest {


	@JsonProperty("name")
	private String name;

	@JsonProperty("account_public_key")
	private String accountPublicKey;

	@JsonProperty("account_index")
	private String accountIndex;

	@JsonProperty("payment_script_template")
	private PaymentScriptTemplate paymentScriptTemplate;

	@JsonProperty("delegation_script_template")
	private DelegationScriptTemplate delegationScriptTemplate;

	@JsonProperty("script_validation")
	private String scriptValidation;

	/**
	 * Returns the name.
	 * - Length range from {@code 1} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Alan's Wallet}</pre>
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Defines the name.
	 * - Length range from {@code 1} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Alan's Wallet}</pre>
	 * 
	 * @param name the name
	 */
	public void setName(String name){
		if (name == null) {
			throw new IllegalArgumentException("Value of name cannot be null");
		}

		if (name.codePointCount(0, name.length()) < 1) {
			throw new IllegalArgumentException("Length of name must have at least 1 characters, got '" + name.codePointCount(0, name.length()) + "'");
		}

		if (name.codePointCount(0, name.length()) > 255) {
			throw new IllegalArgumentException("Length of name cannot exceed 255 characters, got '" + name.codePointCount(0, name.length()) + "'");
		}

		this.name = name;
	}

	/**
	 * Returns the extended account public key (public key + chain code).
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 128}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1423856bc91c49e928f6f30f4e8d665d53eb4ab6028bd0ac971809d514c92db11423856bc91c49e928f6f30f4e8d665d53eb4ab6028bd0ac971809d514c92db1}</pre>
	 * 
	 * @return the extended account public key (public key + chain code)
	 */
	public String getAccountPublicKey(){
		return accountPublicKey;
	}

	/**
	 * Defines an extended account public key (public key + chain code).
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 128}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1423856bc91c49e928f6f30f4e8d665d53eb4ab6028bd0ac971809d514c92db11423856bc91c49e928f6f30f4e8d665d53eb4ab6028bd0ac971809d514c92db1}</pre>
	 * 
	 * @param accountPublicKey an extended account public key (public key + chain code)
	 */
	public void setAccountPublicKey(String accountPublicKey){
		if (accountPublicKey == null) {
			throw new IllegalArgumentException("Value of accountPublicKey cannot be null");
		}

		if (accountPublicKey.codePointCount(0, accountPublicKey.length()) < 128) {
			throw new IllegalArgumentException("Length of accountPublicKey must have at least 128 characters, got '" + accountPublicKey.codePointCount(0, accountPublicKey.length()) + "'");
		}

		if (accountPublicKey.codePointCount(0, accountPublicKey.length()) > 128) {
			throw new IllegalArgumentException("Length of accountPublicKey cannot exceed 128 characters, got '" + accountPublicKey.codePointCount(0, accountPublicKey.length()) + "'");
		}

	    if(!Pattern.compile("\\p{XDigit}+").matcher(accountPublicKey).matches()){
    		throw new IllegalArgumentException("Value provided for accountPublicKey does not represent a hexadecimal.");
    	}

		this.accountPublicKey = accountPublicKey;
	}

	/**
	 * Returns the account index.
	 * 
	 * An individual segment within a derivation path.
	 * The `H` suffix indicates a _Hardened_ child private key, which
	 * means that children of this key cannot be derived from the public
	 * key. Indices without a `H` suffix are called _Soft_.
	 * 
	 * 
	 * - Example: 
	 *   <pre>{@code 1852H}</pre>
	 * 
	 * @return the account index
	 */
	public String getAccountIndex(){
		return accountIndex;
	}

	/**
	 * Defines the account index.
	 * 
	 * An individual segment within a derivation path.
	 * The `H` suffix indicates a _Hardened_ child private key, which
	 * means that children of this key cannot be derived from the public
	 * key. Indices without a `H` suffix are called _Soft_.
	 * 
	 * 
	 * - Example: 
	 *   <pre>{@code 1852H}</pre>
	 * 
	 * @param accountIndex the account index
	 */
	public void setAccountIndex(String accountIndex){
		if (accountIndex == null) {
			throw new IllegalArgumentException("Value of accountIndex cannot be null");
		}

		this.accountIndex = accountIndex;
	}

	/**
	 * Returns the payment script template.
	 * 
	 * @return the payment script template
	 */
	public PaymentScriptTemplate getPaymentScriptTemplate(){
		return paymentScriptTemplate;
	}

	/**
	 * Defines the payment script template.
	 * 
	 * @param paymentScriptTemplate the payment script template
	 */
	public void setPaymentScriptTemplate(PaymentScriptTemplate paymentScriptTemplate){
		if (paymentScriptTemplate == null) {
			throw new IllegalArgumentException("Value of paymentScriptTemplate cannot be null");
		}

		this.paymentScriptTemplate = paymentScriptTemplate;
	}

	/**
	 * Returns the delegation script template (optional).
	 * 
	 * @return the delegation script template
	 */
	public DelegationScriptTemplate getDelegationScriptTemplate(){
		return delegationScriptTemplate;
	}

	/**
	 * Defines the delegation script template (optional).
	 * 
	 * @param delegationScriptTemplate the delegation script template
	 */
	public void setDelegationScriptTemplate(DelegationScriptTemplate delegationScriptTemplate){
		if (delegationScriptTemplate == null) {
			this.delegationScriptTemplate = delegationScriptTemplate;
			return;
		}

		this.delegationScriptTemplate = delegationScriptTemplate;
	}

	/**
	 * Returns the script validation (optional).
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
	 * @return the script validation
	 */
	public String getScriptValidation(){
		return scriptValidation;
	}

	/**
	 * Defines the script validation (optional).
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
	 * @param scriptValidation the script validation
	 */
	public void setScriptValidation(String scriptValidation){
		if (scriptValidation == null) {
			this.scriptValidation = scriptValidation;
			return;
		}

		this.scriptValidation = scriptValidation;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
