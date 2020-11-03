package com.univocity.cardano.wallet.builders.wallets.transactions;

import com.univocity.cardano.wallet.builders.wallets.*;
import com.univocity.cardano.wallet.builders.wallets.addresses.*;

import java.math.*;

import static com.univocity.cardano.wallet.common.Wrapper.*;

public interface Payee<T extends Authorization<?>> {

	default T to(Wallet wallet, BigDecimal amountInAda){
		return to(wallet.addresses().next(), amountInAda);
	}

	default T to(Address address, BigDecimal amountInAda){
		return to(address.id(), amountInAda);
	}

	default T to(String address, BigDecimal amountInAda){
		return to(address, adaToLovelace(amountInAda));
	}

	default T to(Wallet wallet, BigInteger amountInLovelace){
		return to(wallet.addresses().next(), amountInLovelace);
	}

	default T to(Address address, BigInteger amountInLovelace){
		return to(address.id(), amountInLovelace);
	}

	T to(String address, BigInteger amountInLovelace);
}
