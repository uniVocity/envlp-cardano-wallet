package com.univocity.cardano.wallet.api.generated.common;


import java.util.regex.*;
import java.math.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


public abstract class AbstractAssetBalance {


	@JsonProperty("policy_id")
	private String policyId;

	@JsonProperty("asset_name")
	private String assetName;

	@JsonProperty("quantity")
	private BigInteger quantity;

	/**
	 * Returns the policy id.
	 * 
	 * A unique identifier of the asset's monetary policy. The policy
	 * controls how assets of this kind are created and destroyed.
	 * The contents are the blake2b-224 hash of the monetary policy
	 * script, encoded in hexadecimal.
	 * 
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 56}.
	 * 
	 * - Example: 
	 *   <pre>{@code 65ab82542b0ca20391caaf66a4d4d7897d281f9c136cd3513136945b}</pre>
	 * 
	 * @return the policy id
	 */
	public String getPolicyId(){
		return policyId;
	}

	/**
	 * Defines the policy id.
	 * 
	 * A unique identifier of the asset's monetary policy. The policy
	 * controls how assets of this kind are created and destroyed.
	 * The contents are the blake2b-224 hash of the monetary policy
	 * script, encoded in hexadecimal.
	 * 
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 56}.
	 * 
	 * - Example: 
	 *   <pre>{@code 65ab82542b0ca20391caaf66a4d4d7897d281f9c136cd3513136945b}</pre>
	 * 
	 * @param policyId the policy id
	 */
	public void setPolicyId(String policyId){
		if (policyId == null) {
			throw new IllegalArgumentException("Value of policyId cannot be null");
		}

		if (policyId.codePointCount(0, policyId.length()) < 56) {
			throw new IllegalArgumentException("Length of policyId must have at least 56 characters, got '" + policyId.codePointCount(0, policyId.length()) + "'");
		}

		if (policyId.codePointCount(0, policyId.length()) > 56) {
			throw new IllegalArgumentException("Length of policyId cannot exceed 56 characters, got '" + policyId.codePointCount(0, policyId.length()) + "'");
		}

	    if(!Pattern.compile("\\p{XDigit}+").matcher(policyId).matches()){
    		throw new IllegalArgumentException("Value provided for policyId does not represent a hexadecimal.");
    	}

		this.policyId = policyId;
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
	 * Returns the number of assets for the given `policy_id` and `asset_name`.
	 * - Minimum value: {@code 0}.
	 * 
	 * @return the number of assets for the given `policy_id` and `asset_name`.
	 */
	public BigInteger getQuantity(){
		return quantity;
	}

	/**
	 * Defines the number of assets for the given `policy_id` and `asset_name`.
	 * - Minimum value: {@code 0}.
	 * 
	 * @param quantity the number of assets for the given `policy_id` and `asset_name`.
	 */
	public void setQuantity(BigInteger quantity){
		if (quantity == null) {
			throw new IllegalArgumentException("Value of quantity cannot be null");
		}

		if (quantity.compareTo(new BigInteger("0")) < 0){
			throw new IllegalArgumentException("'" + quantity + "': value of quantity cannot be less than 0");
		}

		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
