package com.univocity.cardano.wallet.api.generated.common;


import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

public abstract class AbstractCoinSelectionResponse {


	@JsonProperty("inputs")
	private ArrayList<Input> inputs;

	@JsonProperty("outputs")
	private ArrayList<Output> outputs;

	@JsonProperty("change")
	private ArrayList<Change> change;

	@JsonProperty("certificates")
	private ArrayList<Certificate> certificates;

	@JsonProperty("deposits")
	private ArrayList<Deposit> deposits;

	/**
	 * Returns the list of transaction inputs.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * @return the list of transaction inputs
	 */
	public ArrayList<Input> getInputs(){
		return inputs;
	}

	/**
	 * Defines a list of transaction inputs.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * @param inputs a list of transaction inputs
	 */
	public void setInputs(ArrayList<Input> inputs){
		if (inputs == null) {
			throw new IllegalArgumentException("Value of inputs cannot be null");
		}

		this.inputs = inputs;
	}

	/**
	 * Returns the list of target outputs.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the list of target outputs
	 */
	public ArrayList<Output> getOutputs(){
		return outputs;
	}

	/**
	 * Defines a list of target outputs.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param outputs a list of target outputs
	 */
	public void setOutputs(ArrayList<Output> outputs){
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
	public ArrayList<Change> getChange(){
		return change;
	}

	/**
	 * Defines a list of transaction change outputs.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param change a list of transaction change outputs.
	 */
	public void setChange(ArrayList<Change> change){
		if (change == null) {
			throw new IllegalArgumentException("Value of change cannot be null");
		}

		this.change = change;
	}

	/**
	 * Returns the certificates (optional).
	 * 
	 * @return the certificates
	 */
	public ArrayList<Certificate> getCertificates(){
		return certificates;
	}

	/**
	 * Defines the certificates (optional).
	 * 
	 * @param certificates the certificates
	 */
	public void setCertificates(ArrayList<Certificate> certificates){
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
	public ArrayList<Deposit> getDeposits(){
		return deposits;
	}

	/**
	 * Defines a list of deposits associated with a transaction. (optional).
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @param deposits a list of deposits associated with a transaction.
	 */
	public void setDeposits(ArrayList<Deposit> deposits){
		if (deposits == null) {
			this.deposits = deposits;
			return;
		}

		this.deposits = deposits;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
