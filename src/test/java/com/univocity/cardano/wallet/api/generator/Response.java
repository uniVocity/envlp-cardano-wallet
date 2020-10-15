package com.univocity.cardano.wallet.api.generator;

import java.io.*;
import java.util.*;

public class Response {

	private String description;
	private String contentType;
	private List<JsonSchema> jsonSchemas = new ArrayList<>();
	private boolean isArray;

	private Map<String, String> headers = new LinkedHashMap<>();

	public Response(Map properties, Stack<Object> path) {
		description = (String) properties.remove("description");
		path.push(description == null ? "response" : description);

		Map content = (Map) properties.remove("content");
		if (content != null) {
			Map schema = (Map) content.remove("application/json");
			if (schema != null) {
				contentType = "application/json";

				schema = (Map) schema.remove("schema");
				List oneOf = (List) schema.get("oneOf");
				if (oneOf == null) {
					oneOf = Collections.singletonList(schema);

				}

				for (Object o : oneOf) {
					Map m = (Map) o;
					String type = (String) m.get("type");
					if ("array".equals(type)) {
						isArray = true;
						m.remove(type);
						m = (Map) m.remove("items");
					}

					this.jsonSchemas.add(new JsonSchema(m, path));
				}

			}
		} else {
			contentType = "N/A";
		}

		Map headers = (Map) properties.remove("headers");
		if (headers != null) {
			headers.forEach((k, v) -> {
				Map schema = (Map) ((Map) v).remove("schema");
				String format = (String) schema.remove("format");
				this.headers.put(String.valueOf(k), format);
			});
		}

		if (!properties.isEmpty()) {
			throw new IllegalStateException("Properties not fully processed: " + properties.keySet());
		}
		path.pop();
	}

	public void createClass(String methodSignature, File packageDir, String packageName, String className, Map<String, ClassRef> classes) {
		for(JsonSchema schema : jsonSchemas){
			schema.createClass(methodSignature, packageDir, packageName, className, classes, null);
		}
	}

	public boolean isEmpty(){
		return "N/A".equals(contentType);
	}
}
