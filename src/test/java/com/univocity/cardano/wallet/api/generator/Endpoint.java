package com.univocity.cardano.wallet.api.generator;

import java.io.*;
import java.util.*;

public class Endpoint {

	private final String endpoint;

	private List<EndpointMethod> methods = new ArrayList<>();

	public Endpoint(String endpoint, Map properties, Stack<Object> path) {
		path.push(endpoint);
		this.endpoint = endpoint;
		properties.forEach((k, v) -> methods.add(new EndpointMethod(String.valueOf(k), (Map) v, path)));
		path.pop();
	}

	public void generateClasses(File baseDir, Set<String> packageNames, Map<String, ClassRef> classes) {
		System.out.println("Generating classes for " + endpoint);
		methods.forEach(m -> packageNames.add(m.generateClasses(baseDir, classes)));
	}

	public void generateInternalApi(StringBuilder out) {
		methods.forEach(m -> m.generateInternalInterface(out, endpoint));
	}

	public void generateAsynchronousApi(StringBuilder out) {
		methods.forEach(m -> m.generateAsynchronousApi(out, endpoint));
	}

	public void generateSynchronousApi(StringBuilder out) {
		methods.forEach(m -> m.generateSynchronousApi(out, endpoint));
	}
}
