package com.univocity.cardano.wallet.api.generated.wallets;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#postWallet(okhttp3.RequestBody)}.Restore from root private key
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostWalletRequest {


	@JsonProperty("name")
	private String name;

	@JsonProperty("mnemonic_sentence")
	private ArrayList<String> mnemonicSentence;

	@JsonProperty("mnemonic_second_factor")
	private ArrayList<String> mnemonicSecondFactor;

	@JsonProperty("passphrase")
	private String passphrase;

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

		if (name.length() < 1) {
			throw new IllegalArgumentException("Length of name must have at least 1 characters");
		}

		if (name.length() > 255) {
			throw new IllegalArgumentException("Length of name cannot exceed 255 characters");
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

		if (passphrase.length() < 10) {
			throw new IllegalArgumentException("Length of passphrase must have at least 10 characters");
		}

		if (passphrase.length() > 255) {
			throw new IllegalArgumentException("Length of passphrase cannot exceed 255 characters");
		}

		this.passphrase = passphrase;
	}

	/**
	 * Returns the address pool gap (optional).
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
	 * Defines the address pool gap (optional).
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
			this.addressPoolGap = addressPoolGap;
			return;
		}

		if (addressPoolGap < 10) {
			throw new IllegalArgumentException("Value of addressPoolGap cannot be less than 10");
		}

		if (addressPoolGap > 100000L) {
			throw new IllegalArgumentException("Value of addressPoolGap cannot be greater than 100000");
		}

		this.addressPoolGap = addressPoolGap;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
