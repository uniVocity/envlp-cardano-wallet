package com.univocity.cardano.wallet.api.generated.assets;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.regex.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class MintedBurned {


	@JsonProperty("monetary_policy_index")
	private String monetaryPolicyIndex;

	@JsonProperty("policy_id")
	private String policyId;

	@JsonProperty("asset_name")
	private String assetName;

	@JsonProperty("subject")
	private String subject;

	@JsonProperty("script")
	private String script;

	/**
	 * Returns the monetary policy index.
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
	 * @return the monetary policy index
	 */
	public String getMonetaryPolicyIndex(){
		return monetaryPolicyIndex;
	}

	/**
	 * Defines the monetary policy index.
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
	 * @param monetaryPolicyIndex the monetary policy index
	 */
	public void setMonetaryPolicyIndex(String monetaryPolicyIndex){
		if (monetaryPolicyIndex == null) {
			throw new IllegalArgumentException("Value of monetaryPolicyIndex cannot be null");
		}

		this.monetaryPolicyIndex = monetaryPolicyIndex;
	}

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
	 * Returns the subject.
	 * 
	 * A user-facing short fingerprint which combines the `policy_id` and `asset_name`
	 * to allow for an easier human comparison of assets. Note that it is generally
	 * **not okay** to use this fingerprint as a unique identifier for it is not collision
	 * resistant. Yet within the context of a single wallet, it makes for a (rather)
	 * short user-facing comparison mean.
	 * 
	 * - Pattern: {@code ^(asset)1[0-9a-z]*$}.
	 * - Length must be exactly {@code 44}.
	 * 
	 * - Example: 
	 *   <pre>{@code asset1rjklcrnsdzqp65wjgrg55sy9723kw09mlgvlc3}</pre>
	 * 
	 * @return the subject
	 */
	public String getSubject(){
		return subject;
	}

	/**
	 * Defines the subject.
	 * 
	 * A user-facing short fingerprint which combines the `policy_id` and `asset_name`
	 * to allow for an easier human comparison of assets. Note that it is generally
	 * **not okay** to use this fingerprint as a unique identifier for it is not collision
	 * resistant. Yet within the context of a single wallet, it makes for a (rather)
	 * short user-facing comparison mean.
	 * 
	 * - Pattern: {@code ^(asset)1[0-9a-z]*$}.
	 * - Length must be exactly {@code 44}.
	 * 
	 * - Example: 
	 *   <pre>{@code asset1rjklcrnsdzqp65wjgrg55sy9723kw09mlgvlc3}</pre>
	 * 
	 * @param subject the subject
	 */
	public void setSubject(String subject){
		if (subject == null) {
			throw new IllegalArgumentException("Value of subject cannot be null");
		}

		if (subject.codePointCount(0, subject.length()) < 44) {
			throw new IllegalArgumentException("Length of subject must have at least 44 characters, got '" + subject.codePointCount(0, subject.length()) + "'");
		}

		if (subject.codePointCount(0, subject.length()) > 44) {
			throw new IllegalArgumentException("Length of subject cannot exceed 44 characters, got '" + subject.codePointCount(0, subject.length()) + "'");
		}

		this.subject = subject;
	}

	/**
	 * Returns the script under which this asset was minted or burned.
	 * 
	 * @return the script under which this asset was minted or burned.
	 */
	public String getScript(){
		return script;
	}

	/**
	 * Defines the script under which this asset was minted or burned.
	 * 
	 * @param script the script under which this asset was minted or burned.
	 */
	public void setScript(String script){
		if (script == null) {
			throw new IllegalArgumentException("Value of script cannot be null");
		}

		this.script = script;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
