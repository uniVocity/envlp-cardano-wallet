package com.univocity.cardano.wallet.api.generated.transactions;

import com.univocity.cardano.wallet.api.generated.common.*;
import java.util.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#postTransaction(String, okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PostTransactionRedemptionRequest {


	@JsonProperty("passphrase")
	private String passphrase;

	@JsonProperty("payments")
	private ArrayList<PaymentsRedemption> payments;

	@JsonProperty("withdrawal")
	private ArrayList<String> withdrawal;

	/**
	 * Returns the wallet's master passphrase.
	 * - Length range from {@code 0} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @return the wallet's master passphrase.
	 */
	public String getPassphrase(){
		return passphrase;
	}

	/**
	 * Defines the wallet's master passphrase.
	 * - Length range from {@code 0} to {@code 255}.
	 * 
	 * - Example: 
	 *   <pre>{@code Secure Passphrase}</pre>
	 * 
	 * @param passphrase the wallet's master passphrase.
	 */
	public void setPassphrase(String passphrase){
		if (passphrase == null) {
			throw new IllegalArgumentException("Value of passphrase cannot be null");
		}

		if (passphrase.codePointCount(0, passphrase.length()) < 0) {
			throw new IllegalArgumentException("Length of passphrase must have at least 0 characters, got '" + passphrase.codePointCount(0, passphrase.length()) + "'");
		}

		if (passphrase.codePointCount(0, passphrase.length()) > 255) {
			throw new IllegalArgumentException("Length of passphrase cannot exceed 255 characters, got '" + passphrase.codePointCount(0, passphrase.length()) + "'");
		}

		this.passphrase = passphrase;
	}

	/**
	 * Returns the list of target outputs.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * @return the list of target outputs
	 */
	public ArrayList<PaymentsRedemption> getPayments(){
		return payments;
	}

	/**
	 * Defines a list of target outputs.
	 * - Minimum number of elements: {@code 1}.
	 * 
	 * @param payments a list of target outputs
	 */
	public void setPayments(ArrayList<PaymentsRedemption> payments){
		if (payments == null) {
			throw new IllegalArgumentException("Value of payments cannot be null");
		}

		this.payments = payments;
	}

	/**
	 * Returns the withdrawal.
	 * 
	 * When provided, attempts to withdraw rewards from the default stake address corresponding to the given mnemonic.
	 * Should the rewards be null or too small to be worth withdrawing (i.e. the cost of adding them into the transaction
	 * is more than their own intrinsic value), the server will reject the request with a `withdrawal_not_worth` error.
	 * withdrawal field    | reward balance | result
	 * ---                 | ---            | ---
	 * any recovery phrase | too small      | x Error 403 `withdrawal_not_worth`
	 * any recovery phrase | big enough     | ✓ withdrawal generated
	 * 
	 * - Format: {@code bip-0039-mnemonic-word{english}}.
	 * - Number of elements can range from {@code 15} to {@code 24}.
	 * 
	 * @return the withdrawal
	 */
	public ArrayList<String> getWithdrawal(){
		return withdrawal;
	}

	/**
	 * Defines the withdrawal.
	 * 
	 * When provided, attempts to withdraw rewards from the default stake address corresponding to the given mnemonic.
	 * Should the rewards be null or too small to be worth withdrawing (i.e. the cost of adding them into the transaction
	 * is more than their own intrinsic value), the server will reject the request with a `withdrawal_not_worth` error.
	 * withdrawal field    | reward balance | result
	 * ---                 | ---            | ---
	 * any recovery phrase | too small      | x Error 403 `withdrawal_not_worth`
	 * any recovery phrase | big enough     | ✓ withdrawal generated
	 * 
	 * - Format: {@code bip-0039-mnemonic-word{english}}.
	 * - Number of elements can range from {@code 15} to {@code 24}.
	 * 
	 * @param withdrawal the withdrawal
	 */
	public void setWithdrawal(ArrayList<String> withdrawal){
		if (withdrawal == null) {
			throw new IllegalArgumentException("Value of withdrawal cannot be null");
		}

		this.withdrawal = withdrawal;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
