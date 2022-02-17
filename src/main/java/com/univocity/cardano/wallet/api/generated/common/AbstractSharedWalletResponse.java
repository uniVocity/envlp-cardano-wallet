package com.univocity.cardano.wallet.api.generated.common;


import java.util.regex.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;
import com.univocity.cardano.wallet.api.generated.sharedwallets.*;

public abstract class AbstractSharedWalletResponse {


	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("account_index")
	private String accountIndex;

	@JsonProperty("address_pool_gap")
	private Integer addressPoolGap;

	@JsonProperty("payment_script_template")
	private PaymentScriptTemplate paymentScriptTemplate;

	@JsonProperty("delegation_script_template")
	private DelegationScriptTemplate delegationScriptTemplate;

	@JsonProperty("state")
	private State state;

	/**
	 * Returns the unique identifier for the wallet.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * 
	 * - Example: 
	 *   <pre>{@code 2512a00e9653fe49a44a5886202e24d77eeb998f}</pre>
	 * 
	 * @return the unique identifier for the wallet
	 */
	public String getId(){
		return id;
	}

	/**
	 * Defines a unique identifier for the wallet.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 40}.
	 * 
	 * - Example: 
	 *   <pre>{@code 2512a00e9653fe49a44a5886202e24d77eeb998f}</pre>
	 * 
	 * @param id a unique identifier for the wallet
	 */
	public void setId(String id){
		if (id == null) {
			throw new IllegalArgumentException("Value of id cannot be null");
		}

		if (id.codePointCount(0, id.length()) < 40) {
			throw new IllegalArgumentException("Length of id must have at least 40 characters, got '" + id.codePointCount(0, id.length()) + "'");
		}

		if (id.codePointCount(0, id.length()) > 40) {
			throw new IllegalArgumentException("Length of id cannot exceed 40 characters, got '" + id.codePointCount(0, id.length()) + "'");
		}

	    if(!Pattern.compile("\\p{XDigit}+").matcher(id).matches()){
    		throw new IllegalArgumentException("Value provided for id does not represent a hexadecimal.");
    	}

		this.id = id;
	}

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
	 * Returns the address pool gap.
	 * 
	 * Number of consecutive unused addresses allowed.
	 * **IMPORTANT DISCLAIMER:** Using values other than `20` automatically makes your wallet invalid with regards to BIP-44 address discovery. It means that you **will not** be able to fully restore
	 * your wallet in a different software which is strictly following BIP-44.
	 * Beside, using large gaps is **not recommended** as it may induce important performance degradations. Use at your own risks.
	 * **IMPORTANT DISCLAIMER 2:** There is no way to `import` addresses generated outside (e.g. using cardano-addresses) into the wallet.
	 * Wallet only discovers transactions on its used and unused addresses that are within its currently seen `address_pool_gap`.
	 * Transactions on addresses that "belong" to the wallet but happen to be beyond its `address_pool_gap` will not be visible to the wallet.
	 * This is a technical limitation of the industry standard [BIP-44](https://github.com/bitcoin/bips/blob/master/bip-0044.mediawiki). See also [CIP-1852](https://github.com/cardano-foundation/CIPs/blob/master/CIP-1852/CIP-1852.md).
	 * 
	 * - Defaults to: {@code 20}.
	 * - Value range from {@code 10} to {@code 100000}.
	 * 
	 * - Example: 
	 *   <pre>{@code 20}</pre>
	 * 
	 * @return the address pool gap
	 */
	public Integer getAddressPoolGap(){
		return addressPoolGap;
	}

	/**
	 * Defines the address pool gap.
	 * 
	 * Number of consecutive unused addresses allowed.
	 * **IMPORTANT DISCLAIMER:** Using values other than `20` automatically makes your wallet invalid with regards to BIP-44 address discovery. It means that you **will not** be able to fully restore
	 * your wallet in a different software which is strictly following BIP-44.
	 * Beside, using large gaps is **not recommended** as it may induce important performance degradations. Use at your own risks.
	 * **IMPORTANT DISCLAIMER 2:** There is no way to `import` addresses generated outside (e.g. using cardano-addresses) into the wallet.
	 * Wallet only discovers transactions on its used and unused addresses that are within its currently seen `address_pool_gap`.
	 * Transactions on addresses that "belong" to the wallet but happen to be beyond its `address_pool_gap` will not be visible to the wallet.
	 * This is a technical limitation of the industry standard [BIP-44](https://github.com/bitcoin/bips/blob/master/bip-0044.mediawiki). See also [CIP-1852](https://github.com/cardano-foundation/CIPs/blob/master/CIP-1852/CIP-1852.md).
	 * 
	 * - Defaults to: {@code 20}.
	 * - Value range from {@code 10} to {@code 100000}.
	 * 
	 * - Example: 
	 *   <pre>{@code 20}</pre>
	 * 
	 * @param addressPoolGap the address pool gap
	 */
	public void setAddressPoolGap(Integer addressPoolGap){
		if (addressPoolGap == null) {
			throw new IllegalArgumentException("Value of addressPoolGap cannot be null");
		}

		if (addressPoolGap < 10) {
			throw new IllegalArgumentException("Value of addressPoolGap cannot be less than 10, got '" + addressPoolGap + "'");
		}

		if (addressPoolGap > 100000L) {
			throw new IllegalArgumentException("Value of addressPoolGap cannot be greater than 100000, got '" + addressPoolGap + "'");
		}

		this.addressPoolGap = addressPoolGap;
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
	 * Returns the state.
	 * 
	 * @return the state
	 */
	public State getState(){
		return state;
	}

	/**
	 * Defines the state.
	 * 
	 * @param state the state
	 */
	public void setState(State state){
		if (state == null) {
			throw new IllegalArgumentException("Value of state cannot be null");
		}

		this.state = state;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
