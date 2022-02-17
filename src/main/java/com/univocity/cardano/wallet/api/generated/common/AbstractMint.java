package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


public abstract class AbstractMint {


	@JsonProperty("receiving_address")
	private String receivingAddress;

	@JsonProperty("amount")
	private Amount amount;

	/**
	 * Returns the receiving address.
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
	 * @return the receiving address
	 */
	public String getReceivingAddress(){
		return receivingAddress;
	}

	/**
	 * Defines the receiving address.
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
	 * @param receivingAddress the receiving address
	 */
	public void setReceivingAddress(String receivingAddress){
		if (receivingAddress == null) {
			throw new IllegalArgumentException("Value of receivingAddress cannot be null");
		}

		this.receivingAddress = receivingAddress;
	}

	/**
	 * Returns the amount.
	 * 
	 * @return the amount
	 */
	public Amount getAmount(){
		return amount;
	}

	/**
	 * Defines the amount.
	 * 
	 * @param amount the amount
	 */
	public void setAmount(Amount amount){
		if (amount == null) {
			throw new IllegalArgumentException("Value of amount cannot be null");
		}

		this.amount = amount;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
