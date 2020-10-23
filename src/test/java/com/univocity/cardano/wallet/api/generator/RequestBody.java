package com.univocity.cardano.wallet.api.generator;

import java.io.*;
import java.util.*;

public class RequestBody {
	private final Boolean required;
	String contentType;
	String nonJsonDescription;
	JsonSchema jsonSchema;
	String title;

	public RequestBody(Map properties, Stack<Object> path) {
		title = (String)properties.remove("title");
		required = (Boolean) properties.remove("required");

		Map content = (Map) properties.remove("content");
		Map schema = (Map) content.remove("application/json");
		if (schema != null) {
			contentType = "application/json";
			schema = (Map) schema.remove("schema");

			List oneOf = (List) schema.remove("oneOf");
			if (oneOf != null) {
				throw new IllegalStateException("Expecting one json schema only");
			} else {
				jsonSchema = new JsonSchema(schema, path);
			}
		}
		schema = (Map) content.remove("application/octet-stream");
		if(schema != null){
			contentType = "application/octet-stream";
			schema = (Map)schema.remove("schema");
			nonJsonDescription = (String)schema.get("description");
		}

		if (!content.isEmpty()) {
			throw new IllegalStateException("Content properties not fully processed: " + content.keySet());
		}
		if (!properties.isEmpty()) {
			throw new IllegalStateException("Properties not fully processed: " + properties.keySet());
		}
	}

	public void createClass(String methodSignature, File packageDir, String packageName, String className, Map<String, ClassRef> classes) {
		if(jsonSchema != null) {
			jsonSchema.createClass(methodSignature, packageDir, packageName, className, classes, null);
		}
	}
}
