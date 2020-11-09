package com.univocity.cardano.wallet.api.generator;

import java.io.*;
import java.util.*;

import static com.univocity.cardano.wallet.api.generator.ApiGenerator.*;

public class Endpoint {

	private final String endpoint;

	private List<EndpointMethod> methods = new ArrayList<>();

	public Endpoint(String endpoint, Map properties, Stack<Object> path) {
		path.push(endpoint);
		this.endpoint = endpoint;
		properties.forEach((k, v) -> populateEndpointMethods(String.valueOf(k), (Map) v, path));
		path.pop();
	}

	private void populateEndpointMethods(String method, Map properties, Stack<Object> path) {

		Map requestBody = (Map) properties.get("requestBody");
		if (requestBody != null) {
			Map content = (Map) requestBody.get("content");
			if (content != null) {
				Map schema = (Map) content.get("application/json");
				if (schema != null) {
					schema = (Map) schema.get("schema");
					List oneOf = (List) schema.get("oneOf");
					if (oneOf != null) {
						oneOf.forEach(o -> inlineOneOf(method, properties, o, path));
						return;
					}
				}
			}
		}

		methods.add(new EndpointMethod(method, properties, path));
	}

	private void inlineOneOf(String method, Map properties, Object oneOfElement, Stack<Object> path) {
		properties = (Map) deepCopy(properties);
		Map requestBody = (Map) properties.get("requestBody");
		Map content = (Map) requestBody.get("content");
		Map schema = (Map) content.get("application/json");
		schema.put("schema", oneOfElement);

		requestBody.put("title", ((Map)oneOfElement).get("title"));
		methods.add(new EndpointMethod(method, properties, path));
	}


	public void generateClasses(File baseDir, Set<String> packageNames, Map<String, ClassRef> classes) {
		System.out.println("Generating classes for " + endpoint);
		methods.forEach(m -> packageNames.add(m.generateClasses(baseDir, classes)));
	}

	public void generateInternalApi(StringBuilder out) {
		methods.forEach(m -> m.generateInternalInterface(out, endpoint));
	}

	public void generateAsynchronousApi(StringBuilder out) {
		methods.forEach(m -> {
			StringBuilder tmp = new StringBuilder();
			m.generateAsynchronousApi(tmp, endpoint);
			if(out.indexOf(tmp.toString()) < 0){
				out.append(tmp);
			}
		});
	}

	public void generateSynchronousApi(StringBuilder out) {
		methods.forEach(m -> {
			StringBuilder tmp = new StringBuilder();
			m.generateSynchronousApi(tmp, endpoint);
			if(out.indexOf(tmp.toString()) < 0){
				out.append(tmp);
			}
		});
	}
}
