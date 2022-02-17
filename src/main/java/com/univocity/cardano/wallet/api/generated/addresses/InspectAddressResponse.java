package com.univocity.cardano.wallet.api.generated.addresses;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.math.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#inspectAddress(String)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class InspectAddressResponse {


	@JsonProperty("address_style")
	private String addressStyle;

	@JsonProperty("stake_reference")
	private String stakeReference;

	@JsonProperty("network_tag")
	private BigInteger networkTag;

	@JsonProperty("spending_key_hash")
	private String spendingKeyHash;

	@JsonProperty("spending_key_bech32")
	private String spendingKeyBech32;

	@JsonProperty("stake_key_hash")
	private String stakeKeyHash;

	@JsonProperty("stake_key_bech32")
	private String stakeKeyBech32;

	@JsonProperty("script_hash")
	private String scriptHash;

	@JsonProperty("script_hash_bech32")
	private String scriptHashBech32;

	@JsonProperty("pointer")
	private Pointer pointer;

	@JsonProperty("address_root")
	private String addressRoot;

	@JsonProperty("derivation_path")
	private String derivationPath;

	@JsonProperty("address_type")
	private Integer addressType;

	/**
	 * Returns the address style.
	 * - Accepted values: {@code [Shelley, Icarus, Byron]}.
	 * 
	 * @return the address style
	 */
	public String getAddressStyle(){
		return addressStyle;
	}

	/**
	 * Defines the address style.
	 * - Accepted values: {@code [Shelley, Icarus, Byron]}.
	 * 
	 * @param addressStyle the address style
	 */
	public void setAddressStyle(String addressStyle){
		if (addressStyle == null) {
			throw new IllegalArgumentException("Value of addressStyle cannot be null");
		}

		this.addressStyle = addressStyle;
	}

	/**
	 * Returns the stake reference.
	 * - Accepted values: {@code [none, by value, by pointer]}.
	 * 
	 * @return the stake reference
	 */
	public String getStakeReference(){
		return stakeReference;
	}

	/**
	 * Defines the stake reference.
	 * - Accepted values: {@code [none, by value, by pointer]}.
	 * 
	 * @param stakeReference the stake reference
	 */
	public void setStakeReference(String stakeReference){
		if (stakeReference == null) {
			throw new IllegalArgumentException("Value of stakeReference cannot be null");
		}

		this.stakeReference = stakeReference;
	}

	/**
	 * Returns the can be null for 'Icarus' and 'Byron' styles. (optional).
	 * - Minimum value: {@code 0}.
	 * 
	 * @return the can be null for 'Icarus' and 'Byron' styles.
	 */
	public BigInteger getNetworkTag(){
		return networkTag;
	}

	/**
	 * Defines the can be null for 'Icarus' and 'Byron' styles. (optional).
	 * - Minimum value: {@code 0}.
	 * 
	 * @param networkTag the can be null for 'Icarus' and 'Byron' styles.
	 */
	public void setNetworkTag(BigInteger networkTag){
		if (networkTag == null) {
			this.networkTag = networkTag;
			return;
		}

		if (networkTag.compareTo(new BigInteger("0")) < 0){
			throw new IllegalArgumentException("'" + networkTag + "': value of networkTag cannot be less than 0");
		}

		this.networkTag = networkTag;
	}

	/**
	 * Returns the spending key hash (optional).
	 * - Format: {@code base16}.
	 * - Length must be exactly {@code 56}.
	 * 
	 * @return the spending key hash
	 */
	public String getSpendingKeyHash(){
		return spendingKeyHash;
	}

	/**
	 * Defines the spending key hash (optional).
	 * - Format: {@code base16}.
	 * - Length must be exactly {@code 56}.
	 * 
	 * @param spendingKeyHash the spending key hash
	 */
	public void setSpendingKeyHash(String spendingKeyHash){
		if (spendingKeyHash == null) {
			this.spendingKeyHash = spendingKeyHash;
			return;
		}

		if (spendingKeyHash.codePointCount(0, spendingKeyHash.length()) < 56) {
			throw new IllegalArgumentException("Length of spendingKeyHash must have at least 56 characters, got '" + spendingKeyHash.codePointCount(0, spendingKeyHash.length()) + "'");
		}

		if (spendingKeyHash.codePointCount(0, spendingKeyHash.length()) > 56) {
			throw new IllegalArgumentException("Length of spendingKeyHash cannot exceed 56 characters, got '" + spendingKeyHash.codePointCount(0, spendingKeyHash.length()) + "'");
		}

		this.spendingKeyHash = spendingKeyHash;
	}

	/**
	 * Returns the spending key bech32 (optional).
	 * - Format: {@code bech32}.
	 * 
	 * @return the spending key bech32
	 */
	public String getSpendingKeyBech32(){
		return spendingKeyBech32;
	}

	/**
	 * Defines the spending key bech32 (optional).
	 * - Format: {@code bech32}.
	 * 
	 * @param spendingKeyBech32 the spending key bech32
	 */
	public void setSpendingKeyBech32(String spendingKeyBech32){
		if (spendingKeyBech32 == null) {
			this.spendingKeyBech32 = spendingKeyBech32;
			return;
		}

		this.spendingKeyBech32 = spendingKeyBech32;
	}

	/**
	 * Returns the stake key hash (optional).
	 * - Format: {@code base16}.
	 * - Length must be exactly {@code 56}.
	 * 
	 * @return the stake key hash
	 */
	public String getStakeKeyHash(){
		return stakeKeyHash;
	}

	/**
	 * Defines the stake key hash (optional).
	 * - Format: {@code base16}.
	 * - Length must be exactly {@code 56}.
	 * 
	 * @param stakeKeyHash the stake key hash
	 */
	public void setStakeKeyHash(String stakeKeyHash){
		if (stakeKeyHash == null) {
			this.stakeKeyHash = stakeKeyHash;
			return;
		}

		if (stakeKeyHash.codePointCount(0, stakeKeyHash.length()) < 56) {
			throw new IllegalArgumentException("Length of stakeKeyHash must have at least 56 characters, got '" + stakeKeyHash.codePointCount(0, stakeKeyHash.length()) + "'");
		}

		if (stakeKeyHash.codePointCount(0, stakeKeyHash.length()) > 56) {
			throw new IllegalArgumentException("Length of stakeKeyHash cannot exceed 56 characters, got '" + stakeKeyHash.codePointCount(0, stakeKeyHash.length()) + "'");
		}

		this.stakeKeyHash = stakeKeyHash;
	}

	/**
	 * Returns the stake key bech32 (optional).
	 * - Format: {@code bech32}.
	 * 
	 * @return the stake key bech32
	 */
	public String getStakeKeyBech32(){
		return stakeKeyBech32;
	}

	/**
	 * Defines the stake key bech32 (optional).
	 * - Format: {@code bech32}.
	 * 
	 * @param stakeKeyBech32 the stake key bech32
	 */
	public void setStakeKeyBech32(String stakeKeyBech32){
		if (stakeKeyBech32 == null) {
			this.stakeKeyBech32 = stakeKeyBech32;
			return;
		}

		this.stakeKeyBech32 = stakeKeyBech32;
	}

	/**
	 * Returns the script hash (optional).
	 * - Format: {@code base16}.
	 * - Length must be exactly {@code 64}.
	 * 
	 * @return the script hash
	 */
	public String getScriptHash(){
		return scriptHash;
	}

	/**
	 * Defines the script hash (optional).
	 * - Format: {@code base16}.
	 * - Length must be exactly {@code 64}.
	 * 
	 * @param scriptHash the script hash
	 */
	public void setScriptHash(String scriptHash){
		if (scriptHash == null) {
			this.scriptHash = scriptHash;
			return;
		}

		if (scriptHash.codePointCount(0, scriptHash.length()) < 64) {
			throw new IllegalArgumentException("Length of scriptHash must have at least 64 characters, got '" + scriptHash.codePointCount(0, scriptHash.length()) + "'");
		}

		if (scriptHash.codePointCount(0, scriptHash.length()) > 64) {
			throw new IllegalArgumentException("Length of scriptHash cannot exceed 64 characters, got '" + scriptHash.codePointCount(0, scriptHash.length()) + "'");
		}

		this.scriptHash = scriptHash;
	}

	/**
	 * Returns the script hash bech32 (optional).
	 * - Format: {@code bech32}.
	 * 
	 * @return the script hash bech32
	 */
	public String getScriptHashBech32(){
		return scriptHashBech32;
	}

	/**
	 * Defines the script hash bech32 (optional).
	 * - Format: {@code bech32}.
	 * 
	 * @param scriptHashBech32 the script hash bech32
	 */
	public void setScriptHashBech32(String scriptHashBech32){
		if (scriptHashBech32 == null) {
			this.scriptHashBech32 = scriptHashBech32;
			return;
		}

		this.scriptHashBech32 = scriptHashBech32;
	}

	/**
	 * Returns the pointer (optional).
	 * 
	 * @return the pointer
	 */
	public Pointer getPointer(){
		return pointer;
	}

	/**
	 * Defines the pointer (optional).
	 * 
	 * @param pointer the pointer
	 */
	public void setPointer(Pointer pointer){
		if (pointer == null) {
			this.pointer = pointer;
			return;
		}

		this.pointer = pointer;
	}

	/**
	 * Returns the only for 'Icarus' and 'Byron' styles. (optional).
	 * - Format: {@code base16}.
	 * 
	 * @return the only for 'Icarus' and 'Byron' styles.
	 */
	public String getAddressRoot(){
		return addressRoot;
	}

	/**
	 * Defines the only for 'Icarus' and 'Byron' styles. (optional).
	 * - Format: {@code base16}.
	 * 
	 * @param addressRoot the only for 'Icarus' and 'Byron' styles.
	 */
	public void setAddressRoot(String addressRoot){
		if (addressRoot == null) {
			this.addressRoot = addressRoot;
			return;
		}

		this.addressRoot = addressRoot;
	}

	/**
	 * Returns the only for 'Byron' style. (optional).
	 * - Format: {@code base16}.
	 * 
	 * @return the only for 'Byron' style.
	 */
	public String getDerivationPath(){
		return derivationPath;
	}

	/**
	 * Defines the only for 'Byron' style. (optional).
	 * - Format: {@code base16}.
	 * 
	 * @param derivationPath the only for 'Byron' style.
	 */
	public void setDerivationPath(String derivationPath){
		if (derivationPath == null) {
			this.derivationPath = derivationPath;
			return;
		}

		this.derivationPath = derivationPath;
	}

	/**
	 * Returns the address type (optional).
	 * 
	 * The raw type field of the address.
	 * Details about possible address types are following (refer also to [cddl](https://github.com/input-output-hk/cardano-ledger/blob/master/eras/alonzo/test-suite/cddl-files/alonzo.cddl)).
	 * | address_type | binary prefix  |   Meaning                                                |
	 * | ------------ |:--------------:|:--------------------------------------------------------:|
	 * |      0       |  0000          |   base address: keyhash28,keyhash28                      |
	 * |      1       |  0001          |   base address: scripthash28,keyhash28                   |
	 * |      2       |  0010          |   base address: keyhash28,scripthash28                   |
	 * |      3       |  0011          |   base address: scripthash28,scripthash28                |
	 * |      4       |  0100          |   pointer address: keyhash28, 3 variable length uint     |
	 * |      5       |  0101          |   pointer address: scripthash28, 3 variable length uint  |
	 * |      6       |  0110          |   enterprise address: keyhash28                          |
	 * |      7       |  0111          |   enterprise address: scripthash28                       |
	 * |      8       |  1000          |   byron/icarus                                           |
	 * |      14      |  1110          |   reward account: keyhash28                              |
	 * |      15      |  1111          |   reward account: scripthash28                           |
	 * 
	 * - Value range from {@code 0} to {@code 15}.
	 * 
	 * @return the address type
	 */
	public Integer getAddressType(){
		return addressType;
	}

	/**
	 * Defines the address type (optional).
	 * 
	 * The raw type field of the address.
	 * Details about possible address types are following (refer also to [cddl](https://github.com/input-output-hk/cardano-ledger/blob/master/eras/alonzo/test-suite/cddl-files/alonzo.cddl)).
	 * | address_type | binary prefix  |   Meaning                                                |
	 * | ------------ |:--------------:|:--------------------------------------------------------:|
	 * |      0       |  0000          |   base address: keyhash28,keyhash28                      |
	 * |      1       |  0001          |   base address: scripthash28,keyhash28                   |
	 * |      2       |  0010          |   base address: keyhash28,scripthash28                   |
	 * |      3       |  0011          |   base address: scripthash28,scripthash28                |
	 * |      4       |  0100          |   pointer address: keyhash28, 3 variable length uint     |
	 * |      5       |  0101          |   pointer address: scripthash28, 3 variable length uint  |
	 * |      6       |  0110          |   enterprise address: keyhash28                          |
	 * |      7       |  0111          |   enterprise address: scripthash28                       |
	 * |      8       |  1000          |   byron/icarus                                           |
	 * |      14      |  1110          |   reward account: keyhash28                              |
	 * |      15      |  1111          |   reward account: scripthash28                           |
	 * 
	 * - Value range from {@code 0} to {@code 15}.
	 * 
	 * @param addressType the address type
	 */
	public void setAddressType(Integer addressType){
		if (addressType == null) {
			this.addressType = addressType;
			return;
		}

		if (addressType < 0) {
			throw new IllegalArgumentException("Value of addressType cannot be less than 0, got '" + addressType + "'");
		}

		if (addressType > 15L) {
			throw new IllegalArgumentException("Value of addressType cannot be greater than 15, got '" + addressType + "'");
		}

		this.addressType = addressType;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
