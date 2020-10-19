package com.univocity.cardano.wallet.api.generated.stakepools;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * Information about the stake pool.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Metadata {


	@JsonProperty("ticker")
	private String ticker;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("homepage")
	private String homepage;

	/**
	 * Returns the ticker.
	 * - Length range from {@code 3} to {@code 5}.
	 * 
	 * - Example: 
	 *   <pre>{@code IOHK}</pre>
	 * 
	 * @return the ticker
	 */
	public String getTicker(){
		return ticker;
	}

	/**
	 * Defines the ticker.
	 * - Length range from {@code 3} to {@code 5}.
	 * 
	 * - Example: 
	 *   <pre>{@code IOHK}</pre>
	 * 
	 * @param ticker the ticker
	 */
	public void setTicker(String ticker){
		if (ticker == null) {
			throw new IllegalArgumentException("Value of ticker cannot be null");
		}

		if (ticker.length() < 3) {
			throw new IllegalArgumentException("Length of ticker must have at least 3 characters");
		}

		if (ticker.length() > 5) {
			throw new IllegalArgumentException("Length of ticker cannot exceed 5 characters");
		}

		this.ticker = ticker;
	}

	/**
	 * Returns the name.
	 * - Length range from {@code 1} to {@code 50}.
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Defines the name.
	 * - Length range from {@code 1} to {@code 50}.
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

		if (name.length() > 50) {
			throw new IllegalArgumentException("Length of name cannot exceed 50 characters");
		}

		this.name = name;
	}

	/**
	 * Returns the description (optional).
	 * - Maximum length: {@code 255}.
	 * 
	 * @return the description
	 */
	public String getDescription(){
		return description;
	}

	/**
	 * Defines the description (optional).
	 * - Maximum length: {@code 255}.
	 * 
	 * @param description the description
	 */
	public void setDescription(String description){
		if (description == null) {
			this.description = description;
			return;
		}

		if (description.length() > 255) {
			throw new IllegalArgumentException("Length of description cannot exceed 255 characters");
		}

		this.description = description;
	}

	/**
	 * Returns the homepage.
	 * - Format: {@code uri}.
	 * 
	 * - Example: 
	 *   <pre>{@code https://iohk.io}</pre>
	 * 
	 * @return the homepage
	 */
	public String getHomepage(){
		return homepage;
	}

	/**
	 * Defines the homepage.
	 * - Format: {@code uri}.
	 * 
	 * - Example: 
	 *   <pre>{@code https://iohk.io}</pre>
	 * 
	 * @param homepage the homepage
	 */
	public void setHomepage(String homepage){
		if (homepage == null) {
			throw new IllegalArgumentException("Value of homepage cannot be null");
		}

		this.homepage = homepage;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
