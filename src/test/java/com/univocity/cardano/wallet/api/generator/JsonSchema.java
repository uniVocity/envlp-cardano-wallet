package com.univocity.cardano.wallet.api.generator;


import java.io.*;
import java.util.*;

import static com.univocity.cardano.wallet.api.generator.Attribute.*;
import static com.univocity.cardano.wallet.api.generator.ClassRef.*;

public class JsonSchema {

	private final String type;
	private final String description;
	private final String title;
	private final List<Attribute> attributes = new ArrayList<>();
	private String additionalPropertiesType;
	private final String example;
	private final Boolean nullable;
	private boolean buildConstructor;

	public JsonSchema(Map properties, Stack<Object> path) {
		type = (String) properties.remove("type");

		description = (String) properties.remove("description");
		List required = (List) properties.remove("required");
		title = (String) properties.remove("title");

		path.push(title == null ? description == null ? type : description : title);

		Map attributes = (Map) properties.remove("properties");
		if (attributes != null) {
			attributes.forEach((k, v) -> this.attributes.add(new Attribute((String) k, (Map) v, required != null && required.contains(k), path)));
		} else if (!"object".equalsIgnoreCase(type)) {
			properties.put("type", type);
			this.attributes.add(new Attribute("result", properties, true, path));
			buildConstructor = true;
		}

		this.additionalPropertiesType = extractAdditionalPropertiesType(properties);

		this.example = ApiGenerator.serializeMapToJson(properties, "example");
		this.nullable = (Boolean) properties.remove("nullable");

		if (!properties.isEmpty()) {
			throw new IllegalStateException("Properties not fully processed: " + properties.keySet());
		}
		path.pop();
	}

	static String extractAdditionalPropertiesType(Map properties) {
		Object additionalProperties = properties.remove("additionalProperties");
		String out = null;
		if (additionalProperties != null) {
			if (additionalProperties instanceof Map) {
				Map additionalPropertiesMap = (Map) additionalProperties;
				out = (String) additionalPropertiesMap.remove("type");
				if (out == null) {
					out = (String) additionalPropertiesMap.remove("$ref");
				}
				if (!additionalPropertiesMap.isEmpty()) {
					throw new IllegalStateException("Properties not fully processed: " + additionalPropertiesMap.keySet());
				}
			} else {
				if (additionalProperties instanceof Boolean) {
					if ((Boolean) additionalProperties) {
						throw new IllegalStateException("Don't know what to do with 'TRUE' additional properties");
					}
				}
			}
		} else {
			out = null;
		}
		return out;
	}

	public boolean createClass(String methodSignature, File packageDir, String packageName, String className, Map<String, ClassRef> classes, ClassRef parent) {
		if (attributes.isEmpty()) {
			return false;
		}
		File classFile = packageDir.toPath().resolve(className + ".java").toFile();
		ClassRef classRef = new ClassRef(className, classFile);
		if (!classes.containsKey(classFile.getAbsolutePath())) {
			classes.put(classFile.getAbsolutePath(), classRef);
		}

		if (parent != null) {
			parent.children.add(classRef);
		}

		for (Attribute attribute : attributes) {
			attribute.createClass(packageDir, packageName, classes, classRef);
		}

		StringBuilder out = classRef.code;
		out.append("package ").append(packageName).append(";\n\n");

		out.append("import static com.univocity.cardano.wallet.common.Utils.*;\n");
		out.append("import com.fasterxml.jackson.annotation.*;\n\n");
		if (parent == null) {
			out.append("import com.univocity.cardano.wallet.api.generated.*;\n");
		}
//		boolean clearAttributes = false;
//		if (attributes.isEmpty()) {
//			if (additionalPropertiesType != null) {
//				clearAttributes = true;
//				String type = Attribute.getType(additionalPropertiesType);
//				Map props = new HashMap();
//				if(type.startsWith("LinkedHashMap")){
//					props.put("type", type);
//				} else {
//					props.put("type", "LinkedHashMap<" + type + ", " + type + ">");
//				}
//				attributes.add(new Attribute("value", props, true, new Stack<>()));
//
//				out.append("import java.util.*;\n\n");
//			}
//		}

		out.append("\n/**\n * ");
		if (methodSignature != null && parent == null) {
			String apiPath = "InternalWalletApiService";
			if (className.endsWith("Response")) {
				out.append("\n * Response body produced by \n * {@link ").append(apiPath).append('#').append(methodSignature).append("}.");
			} else if (className.endsWith("Request")) {
				out.append("\n * Request body definition for \n * {@link ").append(apiPath).append('#').append(methodSignature).append("}.");
			}
		}

		if (this.description != null) {
			Attribute.appendMultiLine(out, description, false, 0);
		}
		out.append("\n */\n");

		out.append("@JsonIgnoreProperties(ignoreUnknown = true)\n");
		out.append("public final class ").append(className).append(" {\n\n");

		for (Attribute attribute : attributes) {
			attribute.appendAttributeDeclaration(out);

			if ("BigInteger".equals(attribute.getJavaType())) {
				insertImport(out, "java.math.*");
			}
		}

		if (buildConstructor) {
			out.append("\n\tpublic ").append(className).append("(){\n");
			out.append("\t\tthis(null);\n");
			out.append("\t}\n");

			out.append("\n\tpublic ").append(className).append('(').append(getType(type, null)).append(' ').append("result").append("){\n");
			out.append("\t\tthis.result = result;\n");
			out.append("\t}\n");
		}

		for (Attribute attribute : attributes) {
			attribute.appendAttributeGetter(out);
			attribute.appendAttributeSetter(out);
		}

		appendToString(out);

		out.append("}\n");
//
//		if(clearAttributes){
//			attributes.clear();
//		}
		return true;
	}


	void appendToString(StringBuilder out) {
		out.append("\n\t@Override");
		out.append("\n\tpublic String toString() {");
		out.append("\n\t\treturn printObject(this);");
		out.append("\n\t}\n\n");
	}
}
