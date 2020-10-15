package com.univocity.cardano.wallet.api.generator;

import java.io.*;
import java.util.*;

public class Parameter {

	final String location;
	private final String description;
	final String name;
	private final Boolean required;
	private final Boolean allowEmptyValue;
	final Attribute attribute;

	public Parameter(Map properties, Stack<Object> path){
		location = (String)properties.remove("in");
		name = (String)properties.remove("name");
		path.push(name);
		required = (Boolean)properties.remove("required");
		allowEmptyValue = (Boolean)properties.remove("allowEmptyValue");
		attribute = new Attribute(name, (Map)properties.remove("schema"), required, path);
		description = (String)properties.remove("description");

		if (!properties.isEmpty()) {
			throw new IllegalStateException("Properties not fully processed: " + properties.keySet());
		}
		path.pop();
	}

	public void createClass(File packageDir, String packageName, Map<String, ClassRef> classes) {
		attribute.createClass(packageDir, packageName, classes, null);
	}
}
