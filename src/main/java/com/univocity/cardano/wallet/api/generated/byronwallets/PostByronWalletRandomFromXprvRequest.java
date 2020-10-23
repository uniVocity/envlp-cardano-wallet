package com.univocity.cardano.wallet.api.generated.byronwallets;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.regex.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#postByronWallet(okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostByronWalletRandomFromXprvRequest {


	@JsonProperty("style")
	private String style;

	@JsonProperty("name")
	private String name;

	@JsonProperty("encrypted_root_private_key")
	private String encryptedRootPrivateKey;

	@JsonProperty("passphrase_hash")
	private String passphraseHash;

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

		if (name.codePointCount(0, name.length()) < 1) {
			throw new IllegalArgumentException("Length of name must have at least 1 characters, got '" + name.codePointCount(0, name.length()) + "'");
		}

		if (name.codePointCount(0, name.length()) > 255) {
			throw new IllegalArgumentException("Length of name cannot exceed 255 characters, got '" + name.codePointCount(0, name.length()) + "'");
		}

		this.name = name;
	}

	/**
	 * Returns the encrypted root private key.
	 * 
	 * A root private key, encrypted using a given passphrase. The underlying key should contain:
	 * - A private key
	 * - A chain code
	 * - A public key
	 * 
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 256}.
	 * 
	 * @return the encrypted root private key
	 */
	public String getEncryptedRootPrivateKey(){
		return encryptedRootPrivateKey;
	}

	/**
	 * Defines the encrypted root private key.
	 * 
	 * A root private key, encrypted using a given passphrase. The underlying key should contain:
	 * - A private key
	 * - A chain code
	 * - A public key
	 * 
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 256}.
	 * 
	 * @param encryptedRootPrivateKey the encrypted root private key
	 */
	public void setEncryptedRootPrivateKey(String encryptedRootPrivateKey){
		if (encryptedRootPrivateKey == null) {
			throw new IllegalArgumentException("Value of encryptedRootPrivateKey cannot be null");
		}

		if (encryptedRootPrivateKey.codePointCount(0, encryptedRootPrivateKey.length()) < 256) {
			throw new IllegalArgumentException("Length of encryptedRootPrivateKey must have at least 256 characters, got '" + encryptedRootPrivateKey.codePointCount(0, encryptedRootPrivateKey.length()) + "'");
		}

		if (encryptedRootPrivateKey.codePointCount(0, encryptedRootPrivateKey.length()) > 256) {
			throw new IllegalArgumentException("Length of encryptedRootPrivateKey cannot exceed 256 characters, got '" + encryptedRootPrivateKey.codePointCount(0, encryptedRootPrivateKey.length()) + "'");
		}

	    if(!Pattern.compile("\\p{XDigit}+").matcher(encryptedRootPrivateKey).matches()){
    		throw new IllegalArgumentException("Value provided for encryptedRootPrivateKey does not represent a hexadecimal.");
    	}

		this.encryptedRootPrivateKey = encryptedRootPrivateKey;
	}

	/**
	 * Returns the passphrase hash.
	 * 
	 * A hash of master passphrase. The hash should be an output of a Scrypt function with the following parameters:
	 * - logN = 14
	 * - r = 8
	 * - p = 1
	 * 
	 * - Format: {@code hex}.
	 * 
	 * - Example: 
	 *   <pre>{@code 31347c387c317c574342652b796362417576356c2b4258676a344a314c6343675375414c2f5653393661364e576a2b7550766655513d3d7c2f376738486c59723174734e394f6e4e753253302b6a65515a6b5437316b45414941366a515867386539493d}</pre>
	 * 
	 * @return the passphrase hash
	 */
	public String getPassphraseHash(){
		return passphraseHash;
	}

	/**
	 * Defines the passphrase hash.
	 * 
	 * A hash of master passphrase. The hash should be an output of a Scrypt function with the following parameters:
	 * - logN = 14
	 * - r = 8
	 * - p = 1
	 * 
	 * - Format: {@code hex}.
	 * 
	 * - Example: 
	 *   <pre>{@code 31347c387c317c574342652b796362417576356c2b4258676a344a314c6343675375414c2f5653393661364e576a2b7550766655513d3d7c2f376738486c59723174734e394f6e4e753253302b6a65515a6b5437316b45414941366a515867386539493d}</pre>
	 * 
	 * @param passphraseHash the passphrase hash
	 */
	public void setPassphraseHash(String passphraseHash){
		if (passphraseHash == null) {
			throw new IllegalArgumentException("Value of passphraseHash cannot be null");
		}

	    if(!Pattern.compile("\\p{XDigit}+").matcher(passphraseHash).matches()){
    		throw new IllegalArgumentException("Value provided for passphraseHash does not represent a hexadecimal.");
    	}

		this.passphraseHash = passphraseHash;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
