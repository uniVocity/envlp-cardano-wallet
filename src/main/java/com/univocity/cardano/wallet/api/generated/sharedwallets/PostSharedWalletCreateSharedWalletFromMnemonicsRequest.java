package com.univocity.cardano.wallet.api.generated.sharedwallets;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#postSharedWallet(okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostSharedWalletCreateSharedWalletFromMnemonicsRequest {


	@JsonProperty("name")
	private String name;

	@JsonProperty("mnemonic_sentence")
	private ArrayList<String> mnemonicSentence;

	@JsonProperty("mnemonic_second_factor")
	private ArrayList<String> mnemonicSecondFactor;

	@JsonProperty("passphrase")
	private String passphrase;

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
	 * Returns the list of mnemonic words.
	 * - Format: {@code bip-0039-mnemonic-word{english}}.
	 * - Number of elements can range from {@code 15} to {@code 24}.
	 * 
	 * @return the list of mnemonic words
	 */
	public ArrayList<String> getMnemonicSentence(){
		return mnemonicSentence;
	}

	/**
	 * Defines a list of mnemonic words.
	 * - Format: {@code bip-0039-mnemonic-word{english}}.
	 * - Number of elements can range from {@code 15} to {@code 24}.
	 * 
	 * @param mnemonicSentence a list of mnemonic words
	 */
	public void setMnemonicSentence(ArrayList<String> mnemonicSentence){
		if (mnemonicSentence == null) {
			throw new IllegalArgumentException("Value of mnemonicSentence cannot be null");
		}

		this.mnemonicSentence = mnemonicSentence;
	}

	/**
	 * Returns the optional passphrase used to encrypt the mnemonic sentence. (optional).
	 * - Format: {@code bip-0039-mnemonic-word{english}}.
	 * - Number of elements can range from {@code 9} to {@code 12}.
	 * 
	 * @return the optional passphrase used to encrypt the mnemonic sentence.
	 */
	public ArrayList<String> getMnemonicSecondFactor(){
		return mnemonicSecondFactor;
	}

	/**
	 * Defines an optional passphrase used to encrypt the mnemonic sentence. (optional).
	 * - Format: {@code bip-0039-mnemonic-word{english}}.
	 * - Number of elements can range from {@code 9} to {@code 12}.
	 * 
	 * @param mnemonicSecondFactor an optional passphrase used to encrypt the mnemonic sentence.
	 */
	public void setMnemonicSecondFactor(ArrayList<String> mnemonicSecondFactor){
		if (mnemonicSecondFactor == null) {
			this.mnemonicSecondFactor = mnemonicSecondFactor;
			return;
		}

		this.mnemonicSecondFactor = mnemonicSecondFactor;
	}

	/**
	 * Returns the master passphrase to lock and protect the wallet for sensitive operation (e.g. sending funds).
	 * - Length range from {@code 10} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @return the master passphrase to lock and protect the wallet for sensitive operation (e.g. sending funds)
	 */
	public String getPassphrase(){
		return passphrase;
	}

	/**
	 * Defines a master passphrase to lock and protect the wallet for sensitive operation (e.g. sending funds).
	 * - Length range from {@code 10} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @param passphrase a master passphrase to lock and protect the wallet for sensitive operation (e.g. sending funds)
	 */
	public void setPassphrase(String passphrase){
		if (passphrase == null) {
			throw new IllegalArgumentException("Value of passphrase cannot be null");
		}

		if (passphrase.codePointCount(0, passphrase.length()) < 10) {
			throw new IllegalArgumentException("Length of passphrase must have at least 10 characters, got '" + passphrase.codePointCount(0, passphrase.length()) + "'");
		}

		if (passphrase.codePointCount(0, passphrase.length()) > 255) {
			throw new IllegalArgumentException("Length of passphrase cannot exceed 255 characters, got '" + passphrase.codePointCount(0, passphrase.length()) + "'");
		}

		this.passphrase = passphrase;
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
