package com.univocity.cardano.wallet.api.generated.assets;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.regex.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class MintBurn {


	@JsonProperty("monetary_policy_index")
	private String monetaryPolicyIndex;

	@JsonProperty("asset_name")
	private String assetName;

	@JsonProperty("operation")
	private Operation operation;

	/**
	 * Returns the monetary policy index (optional).
	 * 
	 * An individual segment within a derivation path.
	 * The `H` suffix indicates a _Hardened_ child private key, which
	 * means that children of this key cannot be derived from the public
	 * key. Indices without a `H` suffix are called _Soft_.
	 * 
	 * 
	 * @return the monetary policy index
	 */
	public String getMonetaryPolicyIndex(){
		return monetaryPolicyIndex;
	}

	/**
	 * Defines the monetary policy index (optional).
	 * 
	 * An individual segment within a derivation path.
	 * The `H` suffix indicates a _Hardened_ child private key, which
	 * means that children of this key cannot be derived from the public
	 * key. Indices without a `H` suffix are called _Soft_.
	 * 
	 * 
	 * @param monetaryPolicyIndex the monetary policy index
	 */
	public void setMonetaryPolicyIndex(String monetaryPolicyIndex){
		if (monetaryPolicyIndex == null) {
			this.monetaryPolicyIndex = monetaryPolicyIndex;
			return;
		}

		this.monetaryPolicyIndex = monetaryPolicyIndex;
	}

	/**
	 * Returns the asset name.
	 * 
	 * The asset on-chain type which acts as a sub-identifier within a
	 * policy. Although we call it "asset name", the value needn't be
	 * text, and it could even be empty.
	 * For policies with a single fungible asset item, asset name is
	 * typically an empty string.
	 * This value can be up to 32 bytes of arbitrary data (which is 64
	 * hexadecimal digits).
	 * 
	 * - Format: {@code hex}.
	 * - Maximum length: {@code 64}.
	 * 
	 * - Example: 
	 *   <pre>{@code }</pre>
	 * 
	 * @return the asset name
	 */
	public String getAssetName(){
		return assetName;
	}

	/**
	 * Defines the asset name.
	 * 
	 * The asset on-chain type which acts as a sub-identifier within a
	 * policy. Although we call it "asset name", the value needn't be
	 * text, and it could even be empty.
	 * For policies with a single fungible asset item, asset name is
	 * typically an empty string.
	 * This value can be up to 32 bytes of arbitrary data (which is 64
	 * hexadecimal digits).
	 * 
	 * - Format: {@code hex}.
	 * - Maximum length: {@code 64}.
	 * 
	 * - Example: 
	 *   <pre>{@code }</pre>
	 * 
	 * @param assetName the asset name
	 */
	public void setAssetName(String assetName){
		if (assetName == null) {
			throw new IllegalArgumentException("Value of assetName cannot be null");
		}

		if (assetName.codePointCount(0, assetName.length()) > 64) {
			throw new IllegalArgumentException("Length of assetName cannot exceed 64 characters, got '" + assetName.codePointCount(0, assetName.length()) + "'");
		}

	    if(!Pattern.compile("\\p{XDigit}+").matcher(assetName).matches()){
    		throw new IllegalArgumentException("Value provided for assetName does not represent a hexadecimal.");
    	}

		this.assetName = assetName;
	}

	/**
	 * Returns the operation.
	 * 
	 * @return the operation
	 */
	public Operation getOperation(){
		return operation;
	}

	/**
	 * Defines the operation.
	 * 
	 * @param operation the operation
	 */
	public void setOperation(Operation operation){
		if (operation == null) {
			throw new IllegalArgumentException("Value of operation cannot be null");
		}

		this.operation = operation;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
