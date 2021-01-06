package com.univocity.cardano.wallet.api.generated.wallets;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.regex.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

public abstract class AbstractWalletResponse {


	@JsonProperty("id")
	private String id;

	@JsonProperty("address_pool_gap")
	private Integer addressPoolGap;

	@JsonProperty("balance")
	private Balance balance;

	@JsonProperty("delegation")
	private Delegation delegation;

	@JsonProperty("name")
	private String name;

	@JsonProperty("passphrase")
	private Passphrase passphrase;

	@JsonProperty("state")
	private State state;

	@JsonProperty("tip")
	private Tip tip;

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
	 * Returns the address pool gap.
	 * 
	 * Number of consecutive unused addresses allowed.
	 * **IMPORTANT DISCLAIMER:** Using values other than `20` automatically makes your wallet invalid with regards to BIP-44 address discovery. It means that you **will not** be able to fully restore
	 * your wallet in a different software which is strictly following BIP-44.
	 * Beside, using large gaps is **not recommended** as it may induce important performance degradations. Use at your own risks.
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
	 * Returns the balance.
	 * 
	 * @return the balance
	 */
	public Balance getBalance(){
		return balance;
	}

	/**
	 * Defines the balance.
	 * 
	 * @param balance the balance
	 */
	public void setBalance(Balance balance){
		if (balance == null) {
			throw new IllegalArgumentException("Value of balance cannot be null");
		}

		this.balance = balance;
	}

	/**
	 * Returns the delegation.
	 * 
	 * @return the delegation
	 */
	public Delegation getDelegation(){
		return delegation;
	}

	/**
	 * Defines the delegation.
	 * 
	 * @param delegation the delegation
	 */
	public void setDelegation(Delegation delegation){
		if (delegation == null) {
			throw new IllegalArgumentException("Value of delegation cannot be null");
		}

		this.delegation = delegation;
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
	 * Returns the passphrase (optional).
	 * 
	 * @return the passphrase
	 */
	public Passphrase getPassphrase(){
		return passphrase;
	}

	/**
	 * Defines the passphrase (optional).
	 * 
	 * @param passphrase the passphrase
	 */
	public void setPassphrase(Passphrase passphrase){
		if (passphrase == null) {
			this.passphrase = passphrase;
			return;
		}

		this.passphrase = passphrase;
	}

	/**
	 * Returns the state.
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "status" : "ready"
	 *     }
	 *   }</pre>
	 * 
	 * @return the state
	 */
	public State getState(){
		return state;
	}

	/**
	 * Defines the state.
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "status" : "ready"
	 *     }
	 *   }</pre>
	 * 
	 * @param state the state
	 */
	public void setState(State state){
		if (state == null) {
			throw new IllegalArgumentException("Value of state cannot be null");
		}

		this.state = state;
	}

	/**
	 * Returns the tip.
	 * 
	 * @return the tip
	 */
	public Tip getTip(){
		return tip;
	}

	/**
	 * Defines the tip.
	 * 
	 * @param tip the tip
	 */
	public void setTip(Tip tip){
		if (tip == null) {
			throw new IllegalArgumentException("Value of tip cannot be null");
		}

		this.tip = tip;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
