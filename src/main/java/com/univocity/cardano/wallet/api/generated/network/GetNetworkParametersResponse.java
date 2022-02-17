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

	@JsonProperty("eras")
	private Eras eras;

	@JsonProperty("maximum_collateral_input_count")
	private BigInteger maximumCollateralInputCount;

	@JsonProperty("minimum_collateral_percentage")
	private BigInteger minimumCollateralPercentage;

	@JsonProperty("maximum_token_bundle_size")
	private MaximumTokenBundleSize maximumTokenBundleSize;

	@JsonProperty("execution_unit_prices")
	private ExecutionUnitPrices executionUnitPrices;

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
	 *   <pre>{@code Wed Feb 27 11:46:45 BRT 2019}</pre>
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
	 *   <pre>{@code Wed Feb 27 11:46:45 BRT 2019}</pre>
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
	 * Returns the eras.
	 * 
	 * @return the eras
	 */
	public Eras getEras(){
		return eras;
	}

	/**
	 * Defines the eras.
	 * 
	 * @param eras the eras
	 */
	public void setEras(Eras eras){
		if (eras == null) {
			throw new IllegalArgumentException("Value of eras cannot be null");
		}

		this.eras = eras;
	}

	/**
	 * Returns the maximum collateral input count.
	 * 
	 * The maximum number of collateral inputs that can be used in a single
	 * transaction.
	 * 
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 3}</pre>
	 * 
	 * @return the maximum collateral input count
	 */
	public BigInteger getMaximumCollateralInputCount(){
		return maximumCollateralInputCount;
	}

	/**
	 * Defines the maximum collateral input count.
	 * 
	 * The maximum number of collateral inputs that can be used in a single
	 * transaction.
	 * 
	 * - Minimum value: {@code 0}.
	 * 
	 * - Example: 
	 *   <pre>{@code 3}</pre>
	 * 
	 * @param maximumCollateralInputCount the maximum collateral input count
	 */
	public void setMaximumCollateralInputCount(BigInteger maximumCollateralInputCount){
		if (maximumCollateralInputCount == null) {
			throw new IllegalArgumentException("Value of maximumCollateralInputCount cannot be null");
		}

		if (maximumCollateralInputCount.compareTo(new BigInteger("0")) < 0){
			throw new IllegalArgumentException("'" + maximumCollateralInputCount + "': value of maximumCollateralInputCount cannot be less than 0");
		}

		this.maximumCollateralInputCount = maximumCollateralInputCount;
	}

	/**
	 * Returns the minimum collateral percentage (optional).
	 * 
	 * The minimum required amount of collateral as a percentage of the
	 * total transaction fee.
	 * 
	 * - Minimum value: {@code 0}.
	 * 
	 * @return the minimum collateral percentage
	 */
	public BigInteger getMinimumCollateralPercentage(){
		return minimumCollateralPercentage;
	}

	/**
	 * Defines the minimum collateral percentage (optional).
	 * 
	 * The minimum required amount of collateral as a percentage of the
	 * total transaction fee.
	 * 
	 * - Minimum value: {@code 0}.
	 * 
	 * @param minimumCollateralPercentage the minimum collateral percentage
	 */
	public void setMinimumCollateralPercentage(BigInteger minimumCollateralPercentage){
		if (minimumCollateralPercentage == null) {
			this.minimumCollateralPercentage = minimumCollateralPercentage;
			return;
		}

		if (minimumCollateralPercentage.compareTo(new BigInteger("0")) < 0){
			throw new IllegalArgumentException("'" + minimumCollateralPercentage + "': value of minimumCollateralPercentage cannot be less than 0");
		}

		this.minimumCollateralPercentage = minimumCollateralPercentage;
	}

	/**
	 * Returns the maximum token bundle size.
	 * 
	 * @return the maximum token bundle size
	 */
	public MaximumTokenBundleSize getMaximumTokenBundleSize(){
		return maximumTokenBundleSize;
	}

	/**
	 * Defines the maximum token bundle size.
	 * 
	 * @param maximumTokenBundleSize the maximum token bundle size
	 */
	public void setMaximumTokenBundleSize(MaximumTokenBundleSize maximumTokenBundleSize){
		if (maximumTokenBundleSize == null) {
			throw new IllegalArgumentException("Value of maximumTokenBundleSize cannot be null");
		}

		this.maximumTokenBundleSize = maximumTokenBundleSize;
	}

	/**
	 * Returns the execution unit prices (optional).
	 * 
	 * @return the execution unit prices
	 */
	public ExecutionUnitPrices getExecutionUnitPrices(){
		return executionUnitPrices;
	}

	/**
	 * Defines the execution unit prices (optional).
	 * 
	 * @param executionUnitPrices the execution unit prices
	 */
	public void setExecutionUnitPrices(ExecutionUnitPrices executionUnitPrices){
		if (executionUnitPrices == null) {
			this.executionUnitPrices = executionUnitPrices;
			return;
		}

		this.executionUnitPrices = executionUnitPrices;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
