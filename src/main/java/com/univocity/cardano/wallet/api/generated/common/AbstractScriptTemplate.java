package com.univocity.cardano.wallet.api.generated.common;


import static com.univocity.cardano.wallet.common.Utils.*;
import com.fasterxml.jackson.annotation.*;


public abstract class AbstractScriptTemplate {


	@JsonProperty("cosigners")
	private Object cosigners;

	@JsonProperty("template")
	private String template;

	/**
	 * Returns the cosigners.
	 * 
	 * - Example: 
	 *   <pre>{@code [{cosigner#0=1423856bc91c49e928f6f30f4e8d665d53eb4ab6028bd0ac971809d514c92db11423856bc91c49e928f6f30f4e8d665d53eb4ab6028bd0ac971809d514c92db1}]}</pre>
	 * 
	 * @return the cosigners
	 */
	public Object getCosigners(){
		return cosigners;
	}

	/**
	 * Defines the cosigners.
	 * 
	 * - Example: 
	 *   <pre>{@code [{cosigner#0=1423856bc91c49e928f6f30f4e8d665d53eb4ab6028bd0ac971809d514c92db11423856bc91c49e928f6f30f4e8d665d53eb4ab6028bd0ac971809d514c92db1}]}</pre>
	 * 
	 * @param cosigners the cosigners
	 */
	public void setCosigners(Object cosigners){
		if (cosigners == null) {
			throw new IllegalArgumentException("Value of cosigners cannot be null");
		}

		this.cosigners = cosigners;
	}

	/**
	 * Returns the template.
	 * 
	 * @return the template
	 */
	public String getTemplate(){
		return template;
	}

	/**
	 * Defines the template.
	 * 
	 * @param template the template
	 */
	public void setTemplate(String template){
		if (template == null) {
			throw new IllegalArgumentException("Value of template cannot be null");
		}

		this.template = template;
	}

	@Override
	public String toString() {
		return printObject(this);
	}

}
