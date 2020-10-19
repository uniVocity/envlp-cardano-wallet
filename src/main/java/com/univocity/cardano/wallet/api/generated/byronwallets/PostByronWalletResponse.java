package com.univocity.cardano.wallet.api.generated.byronwallets;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.regex.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#postByronWallet(okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostByronWalletResponse {


	@JsonProperty("id")
	private String id;

	@JsonProperty("balance")
	private Balance balance;

	@JsonProperty("discovery")
	private String discovery;

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

		if (id.length() < 40) {
			throw new IllegalArgumentException("Length of id must have at least 40 characters");
		}

		if (id.length() > 40) {
			throw new IllegalArgumentException("Length of id cannot exceed 40 characters");
		}

	    if(!Pattern.compile("\\p{XDigit}+").matcher(id).matches()){
    		throw new IllegalArgumentException("Value provided for id does not represent a hexadecimal");
    	}

		this.id = id;
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
	 * Returns the mechanism used for discovering addresses.
	 * - Accepted values: {@code [random, sequential]}.
	 * 
	 * - Example: 
	 *   <pre>{@code sequential}</pre>
	 * 
	 * @return the mechanism used for discovering addresses.
	 */
	public String getDiscovery(){
		return discovery;
	}

	/**
	 * Defines the mechanism used for discovering addresses.
	 * - Accepted values: {@code [random, sequential]}.
	 * 
	 * - Example: 
	 *   <pre>{@code sequential}</pre>
	 * 
	 * @param discovery the mechanism used for discovering addresses.
	 */
	public void setDiscovery(String discovery){
		if (discovery == null) {
			throw new IllegalArgumentException("Value of discovery cannot be null");
		}

		this.discovery = discovery;
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

		if (name.length() < 1) {
			throw new IllegalArgumentException("Length of name must have at least 1 characters");
		}

		if (name.length() > 255) {
			throw new IllegalArgumentException("Length of name cannot exceed 255 characters");
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
