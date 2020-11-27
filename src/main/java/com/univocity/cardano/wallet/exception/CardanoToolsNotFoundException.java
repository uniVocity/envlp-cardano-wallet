package com.univocity.cardano.wallet.exception;

import java.io.*;

public class CardanoToolsNotFoundException extends IllegalStateException{

	public CardanoToolsNotFoundException(String pathToCardanoToolsDir){
		super("Cardano tools directory not found: " + pathToCardanoToolsDir);
	}

	public CardanoToolsNotFoundException(File cardanoToolsDir){
		this(cardanoToolsDir.getAbsolutePath());
	}
}
