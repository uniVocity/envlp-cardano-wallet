package com.univocity.cardano.wallet.api.generated.common;


import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * In the Mary era of Cardano, UTxO may contain native assets. These
 * assets are represented on-chain by opaque identifiers which are
 * meaningless to end-users. Therefore, user-facing metadata
 * regarding each token must be stored off-chain, in a metadata
 * registry.
 * Token creators may publish metadata into the registry and client
 * applications can consume these metadata for display to end
 * users. This will work in a similar way to how it is done for stake
 * pool metadata.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Metadata {


	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("ticker")
	private String ticker;

	@JsonProperty("decimals")
	private Integer decimals;

	@JsonProperty("url")
	private String url;

	@JsonProperty("logo")
	private String logo;

	/**
	 * Returns the name.
	 * 
	 * A human-readable name for the asset, intended for display in user
	 * interfaces.
	 * 
	 * - Length range from {@code 1} to {@code 50}.
	 * 
	 * - Example: 
	 *   <pre>{@code SwaggerCoin}</pre>
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Defines the name.
	 * 
	 * A human-readable name for the asset, intended for display in user
	 * interfaces.
	 * 
	 * - Length range from {@code 1} to {@code 50}.
	 * 
	 * - Example: 
	 *   <pre>{@code SwaggerCoin}</pre>
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

		if (name.codePointCount(0, name.length()) > 50) {
			throw new IllegalArgumentException("Length of name cannot exceed 50 characters, got '" + name.codePointCount(0, name.length()) + "'");
		}

		this.name = name;
	}

	/**
	 * Returns the description.
	 * 
	 * A human-readable description for the asset. Good for display in
	 * user interfaces.
	 * 
	 * - Maximum length: {@code 500}.
	 * 
	 * @return the description
	 */
	public String getDescription(){
		return description;
	}

	/**
	 * Defines the description.
	 * 
	 * A human-readable description for the asset. Good for display in
	 * user interfaces.
	 * 
	 * - Maximum length: {@code 500}.
	 * 
	 * @param description the description
	 */
	public void setDescription(String description){
		if (description == null) {
			throw new IllegalArgumentException("Value of description cannot be null");
		}

		if (description.codePointCount(0, description.length()) > 500) {
			throw new IllegalArgumentException("Length of description cannot exceed 500 characters, got '" + description.codePointCount(0, description.length()) + "'");
		}

		this.description = description;
	}

	/**
	 * Returns the ticker (optional).
	 * 
	 * An optional human-readable very short name or acronym for the
	 * asset, intended for display in user interfaces. If `ticker` is not
	 * present, then `name` will be used, but it might be truncated to
	 * fit within the available space.
	 * 
	 * - Length range from {@code 2} to {@code 5}.
	 * 
	 * - Example: 
	 *   <pre>{@code SWAG}</pre>
	 * 
	 * @return the ticker
	 */
	public String getTicker(){
		return ticker;
	}

	/**
	 * Defines the ticker (optional).
	 * 
	 * An optional human-readable very short name or acronym for the
	 * asset, intended for display in user interfaces. If `ticker` is not
	 * present, then `name` will be used, but it might be truncated to
	 * fit within the available space.
	 * 
	 * - Length range from {@code 2} to {@code 5}.
	 * 
	 * - Example: 
	 *   <pre>{@code SWAG}</pre>
	 * 
	 * @param ticker the ticker
	 */
	public void setTicker(String ticker){
		if (ticker == null) {
			this.ticker = ticker;
			return;
		}

		if (ticker.codePointCount(0, ticker.length()) < 2) {
			throw new IllegalArgumentException("Length of ticker must have at least 2 characters, got '" + ticker.codePointCount(0, ticker.length()) + "'");
		}

		if (ticker.codePointCount(0, ticker.length()) > 5) {
			throw new IllegalArgumentException("Length of ticker cannot exceed 5 characters, got '" + ticker.codePointCount(0, ticker.length()) + "'");
		}

		this.ticker = ticker;
	}

	/**
	 * Returns the decimals (optional).
	 * 
	 * Defines a scaling factor for the asset of 10<sup>-n</sup>. The
	 * decimals value _n_ is therefore the number of digits after the
	 * decimal point for quantities of this token.
	 * It is up to API clients to use this metadata field to decimalize
	 * asset quantities before displaying to users. The wallet backend
	 * will always return unscaled token quantities as whole numbers.
	 * 
	 * - Value range from {@code 0} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code 2}</pre>
	 * 
	 * @return the decimals
	 */
	public Integer getDecimals(){
		return decimals;
	}

	/**
	 * Defines the decimals (optional).
	 * 
	 * Defines a scaling factor for the asset of 10<sup>-n</sup>. The
	 * decimals value _n_ is therefore the number of digits after the
	 * decimal point for quantities of this token.
	 * It is up to API clients to use this metadata field to decimalize
	 * asset quantities before displaying to users. The wallet backend
	 * will always return unscaled token quantities as whole numbers.
	 * 
	 * - Value range from {@code 0} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code 2}</pre>
	 * 
	 * @param decimals the decimals
	 */
	public void setDecimals(Integer decimals){
		if (decimals == null) {
			this.decimals = decimals;
			return;
		}

		if (decimals < 0) {
			throw new IllegalArgumentException("Value of decimals cannot be less than 0, got '" + decimals + "'");
		}

		if (decimals > 255L) {
			throw new IllegalArgumentException("Value of decimals cannot be greater than 255, got '" + decimals + "'");
		}

		this.decimals = decimals;
	}

	/**
	 * Returns the URL to the policy's owner(s) or the entity website in charge of the asset. (optional).
	 * - Format: {@code uri}.
	 * - Pattern: {@code ^https://.+}.
	 * - Maximum length: {@code 250}.
	 * 
	 * @return the URL to the policy's owner(s) or the entity website in charge of the asset.
	 */
	public String getUrl(){
		return url;
	}

	/**
	 * Defines a URL to the policy's owner(s) or the entity website in charge of the asset. (optional).
	 * - Format: {@code uri}.
	 * - Pattern: {@code ^https://.+}.
	 * - Maximum length: {@code 250}.
	 * 
	 * @param url a URL to the policy's owner(s) or the entity website in charge of the asset.
	 */
	public void setUrl(String url){
		if (url == null) {
			this.url = url;
			return;
		}

		if (url.codePointCount(0, url.length()) > 250) {
			throw new IllegalArgumentException("Length of url cannot exceed 250 characters, got '" + url.codePointCount(0, url.length()) + "'");
		}

		this.url = url;
	}

	/**
	 * Returns the logo (optional).
	 * 
	 * A base64-encoded `image/png` for displaying the asset. The end image can be expected
	 * to be smaller than 64KB.
	 * 
	 * - Format: {@code base64}.
	 * - Maximum length: {@code 87400}.
	 * 
	 * @return the logo
	 */
	public String getLogo(){
		return logo;
	}

	/**
	 * Defines the logo (optional).
	 * 
	 * A base64-encoded `image/png` for displaying the asset. The end image can be expected
	 * to be smaller than 64KB.
	 * 
	 * - Format: {@code base64}.
	 * - Maximum length: {@code 87400}.
	 * 
	 * @param logo the logo
	 */
	public void setLogo(String logo){
		if (logo == null) {
			this.logo = logo;
			return;
		}

		if (logo.codePointCount(0, logo.length()) > 87400) {
			throw new IllegalArgumentException("Length of logo cannot exceed 87400 characters, got '" + logo.codePointCount(0, logo.length()) + "'");
		}

		this.logo = logo;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
