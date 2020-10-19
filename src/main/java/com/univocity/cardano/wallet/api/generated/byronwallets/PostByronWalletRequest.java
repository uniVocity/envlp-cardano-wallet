package com.univocity.cardano.wallet.api.generated.byronwallets;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#postByronWallet(okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostByronWalletRequest {


	@JsonProperty("style")
	private String style;

	@JsonProperty("name")
	private String name;

	@JsonProperty("passphrase")
	private String passphrase;

	@JsonProperty("mnemonic_sentence")
	private ArrayList<String> mnemonicSentence;

	/**
	 * Returns the style (optional).
	 * - Accepted values: {@code [random]}.
	 * 
	 * @return the style
	 */
	public String getStyle(){
		return style;
	}

	/**
	 * Defines the style (optional).
	 * - Accepted values: {@code [random]}.
	 * 
	 * @param style the style
	 */
	public void setStyle(String style){
		if (style == null) {
			this.style = style;
			return;
		}

		this.style = style;
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

		if (passphrase.length() < 10) {
			throw new IllegalArgumentException("Length of passphrase must have at least 10 characters");
		}

		if (passphrase.length() > 255) {
			throw new IllegalArgumentException("Length of passphrase cannot exceed 255 characters");
		}

		this.passphrase = passphrase;
	}

	/**
	 * Returns the list of mnemonic words.
	 * - Format: {@code bip-0039-mnemonic-word{english}}.
	 * - Number of elements can range from {@code 12} to {@code 24}.
	 * 
	 * @return the list of mnemonic words
	 */
	public ArrayList<String> getMnemonicSentence(){
		return mnemonicSentence;
	}

	/**
	 * Defines a list of mnemonic words.
	 * - Format: {@code bip-0039-mnemonic-word{english}}.
	 * - Number of elements can range from {@code 12} to {@code 24}.
	 * 
	 * @param mnemonicSentence a list of mnemonic words
	 */
	public void setMnemonicSentence(ArrayList<String> mnemonicSentence){
		if (mnemonicSentence == null) {
			throw new IllegalArgumentException("Value of mnemonicSentence cannot be null");
		}

		this.mnemonicSentence = mnemonicSentence;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
