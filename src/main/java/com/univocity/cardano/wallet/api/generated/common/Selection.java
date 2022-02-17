package com.univocity.cardano.wallet.api.generated.common;


import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Selection {


	@JsonProperty("inputs")
	private ArrayList<InputsSelection> inputs;

	@JsonProperty("outputs")
	private ArrayList<OutputsSelection> outputs;

	@JsonProperty("change")
	private ArrayList<ChangeSelection> change;

	@JsonProperty("collateral")
	private ArrayList<CollateralSelection> collateral;

	@JsonProperty("withdrawals")
	private ArrayList<WithdrawalsSelection> withdrawals;

	@JsonProperty("certificates")
	private ArrayList<CertificatesSelection> certificates;

	@JsonProperty("deposits_taken")
	private ArrayList<DepositsTakenSelection> depositsTaken;

	@JsonProperty("deposits_returned")
	private ArrayList<DepositsReturnedSelection> depositsReturned;

	@JsonProperty("metadata")
	private String metadata;

	/**
	 * Returns the list of transaction inputs.
	 * 
	 * @return the list of transaction inputs
	 */
	public ArrayList<InputsSelection> getInputs(){
		return inputs;
	}

	/**
	 * Defines a list of transaction inputs.
	 * 
	 * @param inputs a list of transaction inputs
	 */
	public void setInputs(ArrayList<InputsSelection> inputs){
		if (inputs == null) {
			throw new IllegalArgumentException("Value of inputs cannot be null");
		}

		this.inputs = inputs;
	}

	/**
	 * Returns the list of target outputs with amount specified.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the list of target outputs with amount specified
	 */
	public ArrayList<OutputsSelection> getOutputs(){
		return outputs;
	}

	/**
	 * Defines a list of target outputs with amount specified.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param outputs a list of target outputs with amount specified
	 */
	public void setOutputs(ArrayList<OutputsSelection> outputs){
		if (outputs == null) {
			throw new IllegalArgumentException("Value of outputs cannot be null");
		}

		this.outputs = outputs;
	}

	/**
	 * Returns the list of transaction change outputs.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the list of transaction change outputs.
	 */
	public ArrayList<ChangeSelection> getChange(){
		return change;
	}

	/**
	 * Defines a list of transaction change outputs.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param change a list of transaction change outputs.
	 */
	public void setChange(ArrayList<ChangeSelection> change){
		if (change == null) {
			throw new IllegalArgumentException("Value of change cannot be null");
		}

		this.change = change;
	}

	/**
	 * Returns the list of transaction inputs used for collateral (optional).
	 * 
	 * @return the list of transaction inputs used for collateral
	 */
	public ArrayList<CollateralSelection> getCollateral(){
		return collateral;
	}

	/**
	 * Defines a list of transaction inputs used for collateral (optional).
	 * 
	 * @param collateral a list of transaction inputs used for collateral
	 */
	public void setCollateral(ArrayList<CollateralSelection> collateral){
		if (collateral == null) {
			this.collateral = collateral;
			return;
		}

		this.collateral = collateral;
	}

	/**
	 * Returns the list of withdrawals from stake addresses. (optional).
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the list of withdrawals from stake addresses.
	 */
	public ArrayList<WithdrawalsSelection> getWithdrawals(){
		return withdrawals;
	}

	/**
	 * Defines a list of withdrawals from stake addresses. (optional).
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param withdrawals a list of withdrawals from stake addresses.
	 */
	public void setWithdrawals(ArrayList<WithdrawalsSelection> withdrawals){
		if (withdrawals == null) {
			this.withdrawals = withdrawals;
			return;
		}

		this.withdrawals = withdrawals;
	}

	/**
	 * Returns the certificates (optional).
	 * 
	 * @return the certificates
	 */
	public ArrayList<CertificatesSelection> getCertificates(){
		return certificates;
	}

	/**
	 * Defines the certificates (optional).
	 * 
	 * @param certificates the certificates
	 */
	public void setCertificates(ArrayList<CertificatesSelection> certificates){
		if (certificates == null) {
			this.certificates = certificates;
			return;
		}

		this.certificates = certificates;
	}

	/**
	 * Returns the list of deposits associated with a transaction. (optional).
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the list of deposits associated with a transaction.
	 */
	public ArrayList<DepositsTakenSelection> getDepositsTaken(){
		return depositsTaken;
	}

	/**
	 * Defines a list of deposits associated with a transaction. (optional).
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param depositsTaken a list of deposits associated with a transaction.
	 */
	public void setDepositsTaken(ArrayList<DepositsTakenSelection> depositsTaken){
		if (depositsTaken == null) {
			this.depositsTaken = depositsTaken;
			return;
		}

		this.depositsTaken = depositsTaken;
	}

	/**
	 * Returns the list of deposits associated with a transaction. (optional).
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the list of deposits associated with a transaction.
	 */
	public ArrayList<DepositsReturnedSelection> getDepositsReturned(){
		return depositsReturned;
	}

	/**
	 * Defines a list of deposits associated with a transaction. (optional).
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param depositsReturned a list of deposits associated with a transaction.
	 */
	public void setDepositsReturned(ArrayList<DepositsReturnedSelection> depositsReturned){
		if (depositsReturned == null) {
			this.depositsReturned = depositsReturned;
			return;
		}

		this.depositsReturned = depositsReturned;
	}

	/**
	 * Returns the transaction metadata, serialized according to the expected on-chain binary format, base64-encoded. (optional).
	 * - Format: {@code base64}.
	 * 
	 * @return the transaction metadata, serialized according to the expected on-chain binary format, base64-encoded.
	 */
	public String getMetadata(){
		return metadata;
	}

	/**
	 * Defines the transaction metadata, serialized according to the expected on-chain binary format, base64-encoded. (optional).
	 * - Format: {@code base64}.
	 * 
	 * @param metadata the transaction metadata, serialized according to the expected on-chain binary format, base64-encoded.
	 */
	public void setMetadata(String metadata){
		if (metadata == null) {
			this.metadata = metadata;
			return;
		}

		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
