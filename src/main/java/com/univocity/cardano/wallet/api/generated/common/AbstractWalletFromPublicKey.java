package com.univocity.cardano.wallet.api.generated.common;


import java.util.regex.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

public abstract class AbstractWalletFromPublicKey {


	@JsonProperty("name")
	private String name;

	@JsonProperty("account_public_key")
	private String accountPublicKey;

	@JsonProperty("address_pool_gap")
	private Integer addressPoolGap;

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
	 * Returns the address pool gap (optional).
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
	 * Defines the address pool gap (optional).
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
			this.addressPoolGap = addressPoolGap;
			return;
		}

		if (addressPoolGap < 10) {
			throw new IllegalArgumentException("Value of addressPoolGap cannot be less than 10, got '" + addressPoolGap + "'");
		}

		if (addressPoolGap > 100000L) {
			throw new IllegalArgumentException("Value of addressPoolGap cannot be greater than 100000, got '" + addressPoolGap + "'");
		}

		this.addressPoolGap = addressPoolGap;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
