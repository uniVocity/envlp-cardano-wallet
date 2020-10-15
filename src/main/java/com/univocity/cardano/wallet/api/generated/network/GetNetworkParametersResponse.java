package com.univocity.cardano.wallet.api.generated.network;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.regex.*;
import com.univocity.cardano.wallet.api.generated.*;
import com.fasterxml.jackson.annotation.*;


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

	@JsonProperty("epoch_stability")
	private EpochStability epochStability;

	@JsonProperty("active_slot_coefficient")
	private ActiveSlotCoefficient activeSlotCoefficient;

	@JsonProperty("decentralization_level")
	private DecentralizationLevel decentralizationLevel;

	@JsonProperty("desired_pool_number")
	private Integer desiredPoolNumber;

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

		if (genesisBlockHash.length() < 64) {
			throw new IllegalArgumentException("Length of genesisBlockHash must have at least 64 characters");
		}

		if (genesisBlockHash.length() > 64) {
			throw new IllegalArgumentException("Length of genesisBlockHash cannot exceed 64 characters");
		}

	    if(!Pattern.compile("\\p{XDigit}+").matcher(genesisBlockHash).matches()){
    		throw new IllegalArgumentException("Value provided for genesisBlockHash does not represent a hexadecimal");
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
	 * Returns the epoch stability.
	 * 
	 * @return the epoch stability
	 */
	public EpochStability getEpochStability(){
		return epochStability;
	}

	/**
	 * Defines the epoch stability.
	 * 
	 * @param epochStability the epoch stability
	 */
	public void setEpochStability(EpochStability epochStability){
		if (epochStability == null) {
			throw new IllegalArgumentException("Value of epochStability cannot be null");
		}

		this.epochStability = epochStability;
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
	public Integer getDesiredPoolNumber(){
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
	public void setDesiredPoolNumber(Integer desiredPoolNumber){
		if (desiredPoolNumber == null) {
			throw new IllegalArgumentException("Value of desiredPoolNumber cannot be null");
		}

		if (desiredPoolNumber < 0) {
			throw new IllegalArgumentException("Value of desiredPoolNumber cannot be less than 0");
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
}
