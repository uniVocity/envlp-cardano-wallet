package com.univocity.cardano.wallet.api.generated.coinselections;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import com.univocity.cardano.wallet.api.generated.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * Response body produced by 
 * {@link InternalWalletApiService#selectCoins(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SelectCoinsResponse {


	@JsonProperty("inputs")
	private ArrayList<Input> inputs;

	@JsonProperty("outputs")
	private ArrayList<Output> outputs;

	/**
	 * Returns the list of transaction inputs.
	 * - Minimum number of elements: {@code 0}.
	 * 
	 * @return the list of transaction inputs
	 */
	public ArrayList<Input> getInputs(){
		return inputs;
	}

	/**
	 * Defines a list of transaction inputs.
	 * - Minimum number of elements: {@code 0}.
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
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * @return the list of target outputs
	 */
	public ArrayList<Output> getOutputs(){
		return outputs;
	}

	/**
	 * Defines a list of target outputs.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * @param outputs a list of target outputs
	 */
	public void setOutputs(ArrayList<Output> outputs){
		if (outputs == null) {
			throw new IllegalArgumentException("Value of outputs cannot be null");
		}

		this.outputs = outputs;
	}
}
