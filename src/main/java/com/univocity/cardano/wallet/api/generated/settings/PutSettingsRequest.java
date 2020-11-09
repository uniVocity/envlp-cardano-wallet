package com.univocity.cardano.wallet.api.generated.settings;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;

import com.univocity.cardano.wallet.api.generated.*;

/**
 * 
 * Request body definition for 
 * {@link InternalWalletApiService#putSettings(okhttp3.RequestBody)}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PutSettingsRequest {


	@JsonProperty("settings")
	private Settings settings;

	/**
	 * Returns the settings (optional).
	 * 
	 * @return the settings
	 */
	public Settings getSettings(){
		return settings;
	}

	/**
	 * Defines the settings (optional).
	 * 
	 * @param settings the settings
	 */
	public void setSettings(Settings settings){
		if (settings == null) {
			this.settings = settings;
			return;
		}

		this.settings = settings;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
