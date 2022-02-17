package com.univocity.cardano.wallet.api.generated.assets;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#mintBurnAssets(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class MintBurnAssetsRequest {


	@JsonProperty("mint_burn")
	private ArrayList<MintBurn> mintBurn;

	@JsonProperty("passphrase")
	private String passphrase;

	@JsonProperty("metadata")
	private Object metadata;

	@JsonProperty("time_to_live")
	private TimeToLive timeToLive;

	/**
	 * Returns the mint burn.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * @return the mint burn
	 */
	public ArrayList<MintBurn> getMintBurn(){
		return mintBurn;
	}

	/**
	 * Defines the mint burn.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * @param mintBurn the mint burn
	 */
	public void setMintBurn(ArrayList<MintBurn> mintBurn){
		if (mintBurn == null) {
			throw new IllegalArgumentException("Value of mintBurn cannot be null");
		}

		this.mintBurn = mintBurn;
	}

	/**
	 * Returns the wallet's master passphrase.
	 * - Length range from {@code 0} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @return the wallet's master passphrase.
	 */
	public String getPassphrase(){
		return passphrase;
	}

	/**
	 * Defines the wallet's master passphrase.
	 * - Length range from {@code 0} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @param passphrase the wallet's master passphrase.
	 */
	public void setPassphrase(String passphrase){
		if (passphrase == null) {
			throw new IllegalArgumentException("Value of passphrase cannot be null");
		}

		if (passphrase.codePointCount(0, passphrase.length()) < 0) {
			throw new IllegalArgumentException("Length of passphrase must have at least 0 characters, got '" + passphrase.codePointCount(0, passphrase.length()) + "'");
		}

		if (passphrase.codePointCount(0, passphrase.length()) > 255) {
			throw new IllegalArgumentException("Length of passphrase cannot exceed 255 characters, got '" + passphrase.codePointCount(0, passphrase.length()) + "'");
		}

		this.passphrase = passphrase;
	}

	/**
	 * Returns the metadata (optional).
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "0" : {
	 *         "string" : "cardano"
	 *       },
	 *       "1" : {
	 *         "int" : 14
	 *       },
	 *       "2" : {
	 *         "bytes" : "2512a00e9653fe49a44a5886202e24d77eeb998f"
	 *       },
	 *       "3" : {
	 *         "list" : [ {
	 *           "int" : 14
	 *         }, {
	 *           "int" : 42
	 *         }, {
	 *           "string" : "1337"
	 *         } ]
	 *       },
	 *       "4" : {
	 *         "map" : [ {
	 *           "k" : {
	 *             "string" : "key"
	 *           },
	 *           "v" : {
	 *             "string" : "value"
	 *           }
	 *         }, {
	 *           "k" : {
	 *             "int" : 14
	 *           },
	 *           "v" : {
	 *             "int" : 42
	 *           }
	 *         } ]
	 *       }
	 *     }
	 *   }</pre>
	 * 
	 * @return the metadata
	 */
	public Object getMetadata(){
		return metadata;
	}

	/**
	 * Defines the metadata (optional).
	 * 
	 * - Example: 
	 *   <pre>{@code 
	 *     {
	 *       "0" : {
	 *         "string" : "cardano"
	 *       },
	 *       "1" : {
	 *         "int" : 14
	 *       },
	 *       "2" : {
	 *         "bytes" : "2512a00e9653fe49a44a5886202e24d77eeb998f"
	 *       },
	 *       "3" : {
	 *         "list" : [ {
	 *           "int" : 14
	 *         }, {
	 *           "int" : 42
	 *         }, {
	 *           "string" : "1337"
	 *         } ]
	 *       },
	 *       "4" : {
	 *         "map" : [ {
	 *           "k" : {
	 *             "string" : "key"
	 *           },
	 *           "v" : {
	 *             "string" : "value"
	 *           }
	 *         }, {
	 *           "k" : {
	 *             "int" : 14
	 *           },
	 *           "v" : {
	 *             "int" : 42
	 *           }
	 *         } ]
	 *       }
	 *     }
	 *   }</pre>
	 * 
	 * @param metadata the metadata
	 */
	public void setMetadata(Object metadata){
		if (metadata == null) {
			this.metadata = metadata;
			return;
		}

		this.metadata = metadata;
	}

	/**
	 * Returns the time to live (optional).
	 * 
	 * @return the time to live
	 */
	public TimeToLive getTimeToLive(){
		return timeToLive;
	}

	/**
	 * Defines the time to live (optional).
	 * 
	 * @param timeToLive the time to live
	 */
	public void setTimeToLive(TimeToLive timeToLive){
		if (timeToLive == null) {
			this.timeToLive = timeToLive;
			return;
		}

		this.timeToLive = timeToLive;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
