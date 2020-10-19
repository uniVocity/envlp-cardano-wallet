package com.univocity.cardano.wallet.api.generator;

import com.univocity.cardano.wallet.common.*;
import org.apache.commons.lang3.*;

import java.io.*;
import java.math.*;
import java.nio.charset.*;
import java.util.*;

public class Attribute {

	private final String name;
	private String type;
	private final String format;
	private String example;
	private final Number maxLength;
	private final Number minLength;
	private final Number maxItems;
	private final Number minItems;
	private final Number minimum;
	private final Number maximum;
	private final Object defaultValue;
	private final Attribute itemType;
	private final Boolean required;
	private final String description;
	private final JsonSchema nested;
	private final List<String> acceptedValues = new ArrayList<>();
	private final String arrayElementType;
	private Stack<Object> stack;
	private boolean isJavaObject;

	public Attribute(String name, Map properties, Boolean required, Stack<Object> path) {
		this.name = name;
		path.push(name);
		this.stack = (Stack) path.clone();

		this.required = required;
		this.example = ApiGenerator.serializeMapToJson(properties, "example");
		this.type = (String) properties.get("type");

		if ("object".equals(type)) {
			nested = new JsonSchema(properties, path);
		} else {
			nested = null;
		}
		properties.remove("type");
		this.description = (String) properties.remove("description");
		String format = (String) properties.remove("format");
		this.maxLength = (Number) properties.remove("maxLength");
		this.minLength = (Number) properties.remove("minLength");
		this.maxItems = (Number) properties.remove("maxItems");
		this.minItems = (Number) properties.remove("minItems");
		this.minimum = (Number) properties.remove("minimum");
		this.maximum = (Number) properties.remove("maximum");
		this.defaultValue = properties.remove("default");

		if ("number".equalsIgnoreCase(type) && maximum != null && !maximum.equals(100)) {
			if (!String.valueOf(maximum).contains(".") && (example == null || !example.contains("."))) {
				type = "integer";
			}
		}

		Map items = (Map) properties.remove("items");
		if (items == null) {
			itemType = null;
			arrayElementType = null;
		} else {
			this.example = ApiGenerator.serializeMapToJson(items, "example");
			if ("array".equals(type)) {
				arrayElementType = (String) items.get("type");
			} else {
				arrayElementType = null;
			}
			if (arrayElementType != null && !"object".equals(arrayElementType)) {
				itemType = null;
				format = (String) items.remove("format");
			} else {
				itemType = new Attribute((String) items.get("type"), items, Boolean.TRUE, path);
			}
			items.remove("type");
			if (!items.isEmpty()) {
				throw new IllegalStateException("Properties not fully processed: " + items.keySet());
			}
		}

		this.format = format;

		List enumItems = (List) properties.remove("enum");
		if (enumItems != null) {
			enumItems.forEach(e -> acceptedValues.add(String.valueOf(e)));
		}
		if (!properties.isEmpty()) {
			throw new IllegalStateException("Properties not fully processed: " + properties.keySet());
		}
		path.pop();
	}

	String getType() {
		return getType(type, maximum);
	}

	static String getType(String type, Number maximum) {
		if ("string".equalsIgnoreCase(type)) {
			return "String";
		}
		if ("integer".equalsIgnoreCase(type)) {
			if (maximum == null) {
				return "BigInteger";
			}
			BigInteger max = new BigInteger(maximum.toString());

			if (new BigInteger(String.valueOf(Integer.MAX_VALUE)).compareTo(max) > 0) {
				return "Integer";
			}
			if (new BigInteger(String.valueOf(Long.MAX_VALUE)).compareTo(max) > 0) {
				return "Long";
			}
			return "BigInteger";
		}
		if ("boolean".equalsIgnoreCase(type)) {
			return "Boolean";
		}
		if ("number".equalsIgnoreCase(type)) {
			return "Double";
		}
		if (type.contains("/schemas/")) {
			return "LinkedHashMap<Object, Object>";
		}
		if (type.startsWith("LinkedHashMap")) {
			return type;
		}
		throw new IllegalStateException("Unknown attribute type " + type);
	}

	String getTypeOrClassName(boolean wrapInCollectionIfNeeded) {
		if (isJavaObject) {
			return "Object";
		}
		if ("object".equalsIgnoreCase(type)) {
			return StringUtils.capitalize(getJavaName());
		}
		if ("array".equalsIgnoreCase(type)) {
			if (wrapInCollectionIfNeeded) {
				String elementType;
				if (itemType == null) {
					elementType = getType(arrayElementType, maximum);
				} else {
					elementType = itemType.getTypeOrClassName(false);
				}
				return "ArrayList<" + elementType + ">";
			}
			return itemType.getTypeOrClassName(false);
		}
		return getType();
	}

	private String javaName;

	private String getJavaName() {
		if (javaName == null) {
			String name = this.name;
			if (name.equals("object")) {
				Stack path = (Stack) stack.clone();
				StringBuilder tmp = new StringBuilder();
				while (!path.isEmpty()) {
					String e = path.pop().toString();
					if (!e.contains(" ") && !e.equals("object") && !e.contains("/") && Character.isLowerCase(e.charAt(0)) && !e.equals("post") && !e.equals("get") && !e.equals("put")) {
						if (tmp.length() > 0) {
							tmp.append('_');
						}
						tmp.append(e);
					}
				}
				if (tmp.charAt(tmp.length() - 1) == 's') {
					tmp.deleteCharAt(tmp.length() - 1);
				}
				name = tmp.toString();
			}

			StringBuilder tmp = new StringBuilder();
			String[] parts = name.toLowerCase().split("_");
			tmp.append(parts[0]);
			for (int i = 1; i < parts.length; i++) {
				tmp.append(Character.toUpperCase(parts[i].charAt(0)));
				tmp.append(parts[i].substring(1));
			}
			javaName = tmp.toString();
		}
		return javaName;
	}

	void appendAttributeDeclaration(StringBuilder out) {
		if ("array".equalsIgnoreCase(type)) {
			ClassRef.insertImport(out, "java.util.*");
		}

		out.append("\n\t@JsonProperty(\"").append(name).append("\")\n");
		out.append("\tprivate ").append(getTypeOrClassName(true)).append(' ').append(getJavaName()).append(";\n");
	}

	private boolean appendDescriptionOrName(StringBuilder out, boolean gettter) {
		boolean usingDescription = false;
		if (description != null) {
			String desc = StringUtils.uncapitalize(description.trim());
			if (desc.indexOf('\n') == -1) {
				if (gettter) {
					if (desc.startsWith("an ")) {
						desc = desc.substring(3);
					} else if (desc.startsWith("a ")) {
						desc = desc.substring(2);
					}
				}
				if (!desc.startsWith("an ") && !desc.startsWith("the ") && !desc.startsWith("a ")) {
					out.append("the ");
				}
				out.append(desc);
				usingDescription = true;
			}
		}

		if (!usingDescription) {
			out.append("the ").append(name.replace('_', ' '));
		}
		return usingDescription;
	}

	private static final String OPEN_CODE_BLOCK = "  <pre>{@code ";
	private static final String CLOSE_CODE_BLOCK = "  }</pre>";

	static void appendMultiLine(StringBuilder out, String description, boolean code, int indentationCount) {
		description = description.trim();

		String openCodeBlock = "";
		String closeCodeBlock = "";

		if (code) {
			openCodeBlock = OPEN_CODE_BLOCK;
			closeCodeBlock = CLOSE_CODE_BLOCK;
		}
		String indentation = StringUtils.repeat('\t', indentationCount);
		indentation = "\n" + indentation + " * ";

		out.append(openCodeBlock);
		if (description.indexOf('\n') > 0) {
			String[] lines = StringUtils.split(StringUtils.remove(description, '\r'), '\n');
			out.append(code || indentationCount == 0 ? "" : indentation);
			String condition = null;
			boolean inCode = false;
			for (String line : lines) {
				if (line.startsWith("if: ") || line.startsWith("status: ")) {
					condition = line;
					continue;
				}

				if (line.startsWith("```")) {
					line = !inCode ? OPEN_CODE_BLOCK : CLOSE_CODE_BLOCK;
					inCode = !inCode;
				}

				out.append(indentation).append(code ? "    " : "").append(line);
			}
			if (condition != null) {
				out.append(indentation).append("{@code ").append(code ? "    " : "").append(condition).append("}");
			}
			out.append(indentation);
		} else {
			if (description.startsWith("if: ") || description.startsWith("status: ")) {
				out.append(indentation);
			}

			out.append(description);
			closeCodeBlock = closeCodeBlock.trim();
		}
		out.append(closeCodeBlock);
	}

	void appendDocumentation(StringBuilder out, boolean gettter) {
		boolean usingDescription = appendDescriptionOrName(out, gettter);
		if (required != null && required == Boolean.FALSE) {
			out.append(" (optional)");
		}
		if (out.charAt(out.length() - 1) != '.') {
			out.append('.');
		}
		if (!usingDescription && description != null) {
			appendMultiLine(out, description, false, 1);
		}
		if (!acceptedValues.isEmpty()) {
			out.append("\n\t * - Accepted values: {@code ").append(acceptedValues).append("}.");
		}
		if (defaultValue != null) {
			out.append("\n\t * - Defaults to: {@code ").append(defaultValue).append("}.");
		}
		if (format != null) {
			out.append("\n\t * - Format: {@code ").append(format).append("}.");
		}
		if (minimum != null && maximum != null) {
			out.append("\n\t * - Value range from {@code ").append(minimum).append("}").append(" to {@code ").append(maximum).append("}.");
		} else {
			if (minimum != null) {
				out.append("\n\t * - Minimum value: {@code ").append(minimum).append("}.");
			}
			if (maximum != null) {
				out.append("\n\t * - Maximum value: {@code ").append(maximum).append("}.");
			}
		}
		if (minLength != null && maxLength != null) {
			if (minLength.equals(maxLength)) {
				out.append("\n\t * - Length must be exactly {@code ").append(minLength).append("}.");
			} else {
				out.append("\n\t * - Length range from {@code ").append(minLength).append("}").append(" to {@code ").append(maxLength).append("}.");
			}
		} else {
			if (minLength != null) {
				out.append("\n\t * - Minimum length: {@code ").append(minLength).append("}.");
			}
			if (maxLength != null) {
				out.append("\n\t * - Maximum length: {@code ").append(maxLength).append("}.");
			}
		}
		if (minItems != null && maxItems != null) {
			if (minItems.equals(maxItems)) {
				out.append("\n\t * - Number of elements must be exactly {@code ").append(minItems).append("}.");
			} else {
				out.append("\n\t * - Number of elements can range from {@code ").append(minItems).append("}").append(" to {@code ").append(maxItems).append("}.");
			}
		} else {
			if (minItems != null) {
				out.append("\n\t * - Minimum number of elements: {@code ").append(minItems).append("}.");
			}
			if (maxItems != null) {
				out.append("\n\t * - Maximum number of elements: {@code ").append(maxItems).append("}.");
			}
		}
		if (example != null) {
			out.append("\n\t * \n\t * - Example: \n\t * ");
			appendMultiLine(out, example, true, 1);
		}
	}

	void appendAttributeGetter(StringBuilder out) {
		out.append("\n\t/**\n\t * Returns ");
		appendDocumentation(out, true);
		out.append("\n\t * \n\t * ").append("@return ");
		appendDescriptionOrName(out, true);
		out.append("\n\t */");
		out.append("\n\tpublic ").append(getTypeOrClassName(true)).append(" get").append(StringUtils.capitalize(getJavaName())).append("(){\n");
		out.append("\t\treturn ").append(getJavaName()).append(";\n");
		out.append("\t}\n");
	}

	void appendAttributeSetter(StringBuilder out) {
		out.append("\n\t/**\n\t * Defines ");
		appendDocumentation(out, false);
		out.append("\n\t * \n\t * ").append("@param ").append(getJavaName()).append(' ');
		appendDescriptionOrName(out, false);
		out.append("\n\t */");
		out.append("\n\tpublic void ").append("set").append(StringUtils.capitalize(getJavaName())).append("(").append(getTypeOrClassName(true)).append(' ').append(getJavaName()).append("){\n");
		appendVerificationCode(out);
		out.append("\t\tthis.").append(getJavaName()).append(" = ").append(getJavaName()).append(";\n");
		out.append("\t}\n");
	}

	private void appendVerificationCode(StringBuilder out) {
		Boolean required = this.required;
		if (required == null) {
			if (minLength != null || maxLength != null || minimum != null || maximum != null) {
				required = Boolean.TRUE;
			}
		}
		if (required != null) {
			if (required == Boolean.TRUE) {
				appendTemplate(out, "notNullValidation", "NAME", getJavaName());
			} else {
				appendTemplate(out, "allowNull", "NAME", getJavaName());
			}
		}
		appendTemplate(out, "minLengthValidation", "NAME", getJavaName(), "MIN_LENGTH", getString(minLength));
		appendTemplate(out, "maxLengthValidation", "NAME", getJavaName(), "MAX_LENGTH", getString(maxLength));

		if ("BigInteger".equals(getJavaType())) {
			appendTemplate(out, "minBigIntegerValidation", "NAME", getJavaName(), "MIN", getString(minimum));
			appendTemplate(out, "maxBigIntegerValidation", "NAME", getJavaName(), "MAX", getString(maximum));
		} else {
			appendTemplate(out, "minValueValidation", "NAME", getJavaName(), "MIN", getString(minimum));
			appendTemplate(out, "maxValueValidation", "NAME", getJavaName(), "MAX", getString(maximum));
		}
		if ("hex".equalsIgnoreCase(format)) {
			ClassRef.insertImport(out, "java.util.regex.*");
			appendTemplate(out, "hexValidation", "NAME", getJavaName());
		}
	}

	String getJavaType(){
		try {
			return getType(type, maximum);
		} catch (Exception e) {
			return null;
		}
	}

	private String getString(Object value) {
		if (value == null) {
			return null;
		}
		return value.toString();
	}

	private static void appendTemplate(StringBuilder out, String name, String... keyPairs) {
		for (int i = 0; i < keyPairs.length; i += 2) {
			if (keyPairs[i + 1] == null) {
				return;
			}
		}
		ParameterizedString template = new ParameterizedString(Utils.readTextFromResource("templates/" + name + ".txt", StandardCharsets.UTF_8));
		for (int i = 0; i < keyPairs.length; i += 2) {
			template.set(keyPairs[i], keyPairs[i + 1]);
		}
		out.append(template.applyParameterValues());
		while (out.charAt(out.length() - 1) == '\n') {
			out.deleteCharAt(out.length() - 1);
		}
		out.append("\n\n");
	}

	public void createClass(File packageDir, String packageName, Map<String, ClassRef> classes, ClassRef parent) {
		if (nested != null) {
			if (!nested.createClass(null, packageDir, packageName, getTypeOrClassName(false), classes, parent)) {
				isJavaObject = true;
			}
		} else if ("object".equals(arrayElementType) && itemType != null) {
			itemType.createClass(packageDir, packageName, classes, parent);
		}
	}
}
