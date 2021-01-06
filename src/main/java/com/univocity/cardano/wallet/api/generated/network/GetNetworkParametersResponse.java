package com.univocity.cardano.wallet.api.generated.network;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.regex.*;
import java.math.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#getNetworkParameters()}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class GetNetworkParametersResponse {


	@JsonProperty("genesis_block_hash")
	private String genesisBlockHash;

	@JsonProperty("blockchain_start_time")
	private String blockchainStartTime;

	@JsonProperty("slot_length")
	private SlotLength slotLength;

	@JsonProperty("epoch_length")
	private EpochLength epochLength;

	@JsonProperty("security_parameter")
	private SecurityParameter securityParameter;

	@JsonProperty("active_slot_coefficient")
	private ActiveSlotCoefficient activeSlotCoefficient;

	@JsonProperty("decentralization_level")
	private DecentralizationLevel decentralizationLevel;

	@JsonProperty("desired_pool_number")
	private BigInteger desiredPoolNumber;

	@JsonProperty("minimum_utxo_value")
	private MinimumUtxoValue minimumUtxoValue;

	@JsonProperty("hardfork_at")
	private HardforkAt hardforkAt;

	/**
	 * Returns the hash of genesis block.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 64}.
	 * 
	 * - Example: 
	 *   <pre>{@code 3c07030e36bfffe67e2e2ec09e5293d384637cd2f004356ef320f3fe6c52041a}</pre>
	 * 
	 * @return the hash of genesis block
	 */
	public String getGenesisBlockHash(){
		return genesisBlockHash;
	}

	/**
	 * Defines the hash of genesis block.
	 * - Format: {@code hex}.
	 * - Length must be exactly {@code 64}.
	 * 
	 * - Example: 
	 *   <pre>{@code 3c07030e36bfffe67e2e2ec09e5293d384637cd2f004356ef320f3fe6c52041a}</pre>
	 * 
	 * @param genesisBlockHash the hash of genesis block
	 */
	public void setGenesisBlockHash(String genesisBlockHash){
		if (genesisBlockHash == null) {
			throw new IllegalArgumentException("Value of genesisBlockHash cannot be null");
		}

		if (genesisBlockHash.codePointCount(0, genesisBlockHash.length()) < 64) {
			throw new IllegalArgumentException("Length of genesisBlockHash must have at least 64 characters, got '" + genesisBlockHash.codePointCount(0, genesisBlockHash.length()) + "'");
		}

		if (genesisBlockHash.codePointCount(0, genesisBlockHash.length()) > 64) {
			throw new IllegalArgumentException("Length of genesisBlockHash cannot exceed 64 characters, got '" + genesisBlockHash.codePointCount(0, genesisBlockHash.length()) + "'");
		}

	    if(!Pattern.compile("\\p{XDigit}+").matcher(genesisBlockHash).matches()){
    		throw new IllegalArgumentException("Value provided for genesisBlockHash does not represent a hexadecimal.");
    	}

		this.genesisBlockHash = genesisBlockHash;
	}

	/**
	 * Returns the blockchain start time.
	 * - Format: {@code iso-8601-date-and-time}.
	 * 
	 * - Example: 
	 *   <pre>{@code Thu Feb 28 01:16:45 ACDT 2019}</pre>
	 * 
	 * @return the blockchain start time
	 */
	public String getBlockchainStartTime(){
		return blockchainStartTime;
	}

	/**
	 * Defines the blockchain start time.
	 * - Format: {@code iso-8601-date-and-time}.
	 * 
	 * - Example: 
	 *   <pre>{@code Thu Feb 28 01:16:45 ACDT 2019}</pre>
	 * 
	 * @param blockchainStartTime the blockchain start time
	 */
	public void setBlockchainStartTime(String blockchainStartTime){
		if (blockchainStartTime == null) {
			throw new IllegalArgumentException("Value of blockchainStartTime cannot be null");
		}

		this.blockchainStartTime = blockchainStartTime;
	}

	/**
	 * Returns the slot length.
	 * 
	 * @return the slot length
	 */
	public SlotLength getSlotLength(){
		return slotLength;
	}

	/**
	 * Defines the slot length.
	 * 
	 * @param slotLength the slot length
	 */
	public void setSlotLength(SlotLength slotLength){
		if (slotLength == null) {
			throw new IllegalArgumentException("Value of slotLength cannot be null");
		}

		this.slotLength = slotLength;
	}

	/**
	 * Returns the epoch length.
	 * 
	 * @return the epoch length
	 */
	public EpochLength getEpochLength(){
		return epochLength;
	}

	/**
	 * Defines the epoch length.
	 * 
	 * @param epochLength the epoch length
	 */
	public void setEpochLength(EpochLength epochLength){
		if (epochLength == null) {
			throw new IllegalArgumentException("Value of epochLength cannot be null");
		}

		this.epochLength = epochLength;
	}

	/**
	 * Returns the security parameter.
	 * 
	 * @return the security parameter
	 */
	public SecurityParameter getSecurityParameter(){
		return securityParameter;
	}

	/**
	 * Defines the security parameter.
	 * 
	 * @param securityParameter the security parameter
	 */
	public void setSecurityParameter(SecurityParameter securityParameter){
		if (securityParameter == null) {
			throw new IllegalArgumentException("Value of securityParameter cannot be null");
		}

		this.securityParameter = securityParameter;
	}

	/**
	 * Returns the active slot coefficient.
	 * 
	 * @return the active slot coefficient
	 */
	public ActiveSlotCoefficient getActiveSlotCoefficient(){
		return activeSlotCoefficient;
	}

	/**
	 * Defines the active slot coefficient.
	 * 
	 * @param activeSlotCoefficient the active slot coefficient
	 */
	public void setActiveSlotCoefficient(ActiveSlotCoefficient activeSlotCoefficient){
		if (activeSlotCoefficient == null) {
			throw new IllegalArgumentException("Value of activeSlotCoefficient cannot be null");
		}

		this.activeSlotCoefficient = activeSlotCoefficient;
	}

	/**
	 * Returns the decentralization level.
	 * 
	 * @return the decentralization level
	 */
	public DecentralizationLevel getDecentralizationLevel(){
		return decentralizationLevel;
	}

	/**
	 * Defines the decentralization level.
	 * 
	 * @param decentralizationLevel the decentralization level
	 */
	public void setDecentralizationLevel(DecentralizationLevel decentralizationLevel){
		if (decentralizationLevel == null) {
			throw new IllegalArgumentException("Value of decentralizationLevel cannot be null");
		}

		this.decentralizationLevel = decentralizationLevel;
	}

	/**
	 * Returns the desired pool number.
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 100}</pre>
	 * 
	 * @return the desired pool number
	 */
	public BigInteger getDesiredPoolNumber(){
		return desiredPoolNumber;
	}

	/**
	 * Defines the desired pool number.
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 100}</pre>
	 * 
	 * @param desiredPoolNumber the desired pool number
	 */
	public void setDesiredPoolNumber(BigInteger desiredPoolNumber){
		if (desiredPoolNumber == null) {
			throw new IllegalArgumentException("Value of desiredPoolNumber cannot be null");
		}

		if (desiredPoolNumber.compareTo(new BigInteger("0")) < 0){
			throw new IllegalArgumentException("'" + desiredPoolNumber + "': value of desiredPoolNumber cannot be less than 0");
		}

		this.desiredPoolNumber = desiredPoolNumber;
	}

	/**
	 * Returns the minimum utxo value.
	 * 
	 * @return the minimum utxo value
	 */
	public MinimumUtxoValue getMinimumUtxoValue(){
		return minimumUtxoValue;
	}

	/**
	 * Defines the minimum utxo value.
	 * 
	 * @param minimumUtxoValue the minimum utxo value
	 */
	public void setMinimumUtxoValue(MinimumUtxoValue minimumUtxoValue){
		if (minimumUtxoValue == null) {
			throw new IllegalArgumentException("Value of minimumUtxoValue cannot be null");
		}

		this.minimumUtxoValue = minimumUtxoValue;
	}

	/**
	 * Returns the hardfork at (optional).
	 * 
	 * @return the hardfork at
	 */
	public HardforkAt getHardforkAt(){
		return hardforkAt;
	}

	/**
	 * Defines the hardfork at (optional).
	 * 
	 * @param hardforkAt the hardfork at
	 */
	public void setHardforkAt(HardforkAt hardforkAt){
		if (hardforkAt == null) {
			this.hardforkAt = hardforkAt;
			return;
		}

		this.hardforkAt = hardforkAt;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
