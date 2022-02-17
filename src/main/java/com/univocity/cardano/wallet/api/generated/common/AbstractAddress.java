package com.univocity.cardano.wallet.api.generated.common;


import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

public abstract class AbstractAddress {


	@JsonProperty("id")
	private String id;

	@JsonProperty("state")
	private String state;

	@JsonProperty("derivation_path")
	private ArrayList<String> derivationPath;

	/**
	 * Returns the id.
	 * 
	 * A sequence of characters that encodes (in Base58 or Bech32) a sequence of bytes
	 * which represents an address on the Cardano blockchain.
	 * Sequences in Base58 encoding are expected to be legacy Byron addresses,
	 * whereas sequences in Bech32 encoding correspond to current Shelley addresses.
	 * For more details, see
	 * [CIP-0019 — Cardano addresses](https://github.com/cardano-foundation/CIPs/tree/master/CIP-0019)
	 * .
	 * 
	 * - Format: {@code base58|bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code addr1sjck9mdmfyhzvjhydcjllgj9vjvl522w0573ncustrrr2rg7h9azg4cyqd36yyd48t5ut72hgld0fg2xfvz82xgwh7wal6g2xt8n996s3xvu5g}</pre>
	 * 
	 * @return the id
	 */
	public String getId(){
		return id;
	}

	/**
	 * Defines the id.
	 * 
	 * A sequence of characters that encodes (in Base58 or Bech32) a sequence of bytes
	 * which represents an address on the Cardano blockchain.
	 * Sequences in Base58 encoding are expected to be legacy Byron addresses,
	 * whereas sequences in Bech32 encoding correspond to current Shelley addresses.
	 * For more details, see
	 * [CIP-0019 — Cardano addresses](https://github.com/cardano-foundation/CIPs/tree/master/CIP-0019)
	 * .
	 * 
	 * - Format: {@code base58|bech32}.
	 * 
	 * - Example: 
	 *   <pre>{@code addr1sjck9mdmfyhzvjhydcjllgj9vjvl522w0573ncustrrr2rg7h9azg4cyqd36yyd48t5ut72hgld0fg2xfvz82xgwh7wal6g2xt8n996s3xvu5g}</pre>
	 * 
	 * @param id the id
	 */
	public void setId(String id){
		if (id == null) {
			throw new IllegalArgumentException("Value of id cannot be null");
		}

		this.id = id;
	}

	/**
	 * Returns the state.
	 * - Accepted values: {@code [used, unused]}.
	 * 
	 * @return the state
	 */
	public String getState(){
		return state;
	}

	/**
	 * Defines the state.
	 * - Accepted values: {@code [used, unused]}.
	 * 
	 * @param state the state
	 */
	public void setState(String state){
		if (state == null) {
			throw new IllegalArgumentException("Value of state cannot be null");
		}

		this.state = state;
	}

	/**
	 * Returns the derivation path.
	 * 
	 * An individual segment within a derivation path.
	 * The `H` suffix indicates a _Hardened_ child private key, which
	 * means that children of this key cannot be derived from the public
	 * key. Indices without a `H` suffix are called _Soft_.
	 * 
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1852H}</pre>
	 * 
	 * @return the derivation path
	 */
	public ArrayList<String> getDerivationPath(){
		return derivationPath;
	}

	/**
	 * Defines the derivation path.
	 * 
	 * An individual segment within a derivation path.
	 * The `H` suffix indicates a _Hardened_ child private key, which
	 * means that children of this key cannot be derived from the public
	 * key. Indices without a `H` suffix are called _Soft_.
	 * 
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * - Example: 
	 *   <pre>{@code 1852H}</pre>
	 * 
	 * @param derivationPath the derivation path
	 */
	public void setDerivationPath(ArrayList<String> derivationPath){
		if (derivationPath == null) {
			throw new IllegalArgumentException("Value of derivationPath cannot be null");
		}

		this.derivationPath = derivationPath;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
