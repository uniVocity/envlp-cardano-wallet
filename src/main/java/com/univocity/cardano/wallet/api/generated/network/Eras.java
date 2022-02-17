package com.univocity.cardano.wallet.api.generated.network;

import com.univocity.cardano.wallet.api.generated.common.*;
import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


/**
 * 
 * If and when each era started or will start.
 * The object is keyed by era names. The values either describe the epoch boundary
 * when the era starts (can be in the future or in the past), or are null if not yet
 * confirmed on-chain.
 * If you need to know the current era, see the `node_era` field of
 * `GET /network/information`.
 * > Due to complications with our current tooling, we cannot mark the era names
 * > as required, but the keys are in fact always present.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Eras {


	@JsonProperty("byron")
	private Byron byron;

	@JsonProperty("shelley")
	private Shelley shelley;

	@JsonProperty("allegra")
	private Allegra allegra;

	@JsonProperty("mary")
	private Mary mary;

	@JsonProperty("alonzo")
	private Alonzo alonzo;

	/**
	 * Returns the byron (optional).
	 * 
	 * @return the byron
	 */
	public Byron getByron(){
		return byron;
	}

	/**
	 * Defines the byron (optional).
	 * 
	 * @param byron the byron
	 */
	public void setByron(Byron byron){
		if (byron == null) {
			this.byron = byron;
			return;
		}

		this.byron = byron;
	}

	/**
	 * Returns the shelley (optional).
	 * 
	 * @return the shelley
	 */
	public Shelley getShelley(){
		return shelley;
	}

	/**
	 * Defines the shelley (optional).
	 * 
	 * @param shelley the shelley
	 */
	public void setShelley(Shelley shelley){
		if (shelley == null) {
			this.shelley = shelley;
			return;
		}

		this.shelley = shelley;
	}

	/**
	 * Returns the allegra (optional).
	 * 
	 * @return the allegra
	 */
	public Allegra getAllegra(){
		return allegra;
	}

	/**
	 * Defines the allegra (optional).
	 * 
	 * @param allegra the allegra
	 */
	public void setAllegra(Allegra allegra){
		if (allegra == null) {
			this.allegra = allegra;
			return;
		}

		this.allegra = allegra;
	}

	/**
	 * Returns the mary (optional).
	 * 
	 * @return the mary
	 */
	public Mary getMary(){
		return mary;
	}

	/**
	 * Defines the mary (optional).
	 * 
	 * @param mary the mary
	 */
	public void setMary(Mary mary){
		if (mary == null) {
			this.mary = mary;
			return;
		}

		this.mary = mary;
	}

	/**
	 * Returns the alonzo (optional).
	 * 
	 * @return the alonzo
	 */
	public Alonzo getAlonzo(){
		return alonzo;
	}

	/**
	 * Defines the alonzo (optional).
	 * 
	 * @param alonzo the alonzo
	 */
	public void setAlonzo(Alonzo alonzo){
		if (alonzo == null) {
			this.alonzo = alonzo;
			return;
		}

		this.alonzo = alonzo;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
