package com.univocity.cardano.wallet.api.generator;

import org.apache.commons.lang3.*;

import java.io.*;
import java.util.*;

public class EndpointMethod {

	private final String method;
	private final String description;
	private final String operationId;
	private final String summary;
	private final List<String> tags = new ArrayList<>();
	private RequestBody requestBody;
	private Response response;
	private final List<Parameter> parameters = new ArrayList<>();
	private String requestBodyClass;

	public EndpointMethod(String method, Map properties, Stack<Object> path) {
		path.push(method);
		this.method = method;

		this.description = (String) properties.remove("description");
		this.operationId = (String) properties.remove("operationId");
		this.summary = (String) properties.remove("summary");

		List tags = (List) properties.remove("tags");
		if (tags != null) {
			tags.forEach(t -> this.tags.add(String.valueOf(t)));
		}

		Map requestBody = (Map) properties.remove("requestBody");
		if (requestBody != null) {
			this.requestBody = new RequestBody(requestBody, path);
		}


		Map responses = (Map) properties.remove("responses");
		responses.forEach((k, v) -> {
			if (String.valueOf(k).startsWith("2")) {
				this.response = new Response((Map) v, path);
			}
		});

		List parameters = (List) properties.remove("parameters");
		if (parameters != null) {
			parameters.forEach(p -> this.parameters.add(new Parameter((Map) p, path)));
		}

		if (!properties.isEmpty()) {
			throw new IllegalStateException("Properties not fully processed: " + properties.keySet());
		}
		path.pop();
	}

	String contentType;
	String returnType;
	String methodName;
	boolean allowsBody = true;
	File packageDir;
	String packageName;
	String methodSignature;
	String methodInvocation;

	public String generateClasses(File baseDir, Map<String, ClassRef> classes) {
		packageName = tags.get(0);
		packageName = StringUtils.deleteWhitespace(packageName).toLowerCase();
		packageDir = baseDir.toPath().resolve(packageName).toFile();

		String path = packageDir.getAbsolutePath();
		new File(path).mkdirs();
		packageName = StringUtils.replaceChars(path, "/\\", ".");
		packageName = "com." + StringUtils.substringAfterLast(packageName, ".com.");

		if (requestBody != null && requestBody.contentType != null) {
			contentType = requestBody.contentType;
		}

		methodName = operationId;
		requestBodyClass = StringUtils.capitalize(methodName) + "Request";
		if (contentType != null && contentType.equals("application/octet-stream")) {
			requestBodyClass = "byte[]";
		}
		returnType = response == null || response.isEmpty() ? "Void" : StringUtils.capitalize(methodName) + (response.isArray ? "ResponseItem" : "Response");
		allowsBody = !"delete".equalsIgnoreCase(method);

		generateMethodSignatureAndInvocation();
		response.createClass(methodSignature, packageDir, packageName, returnType, classes);
		if (requestBody != null) {
			requestBody.createClass(methodSignature, packageDir, packageName, requestBodyClass, classes);
		}

		parameters.forEach(p -> p.createClass(packageDir, packageName, classes));

		return packageName;
	}

	private void generateMethodSignatureAndInvocation() {
		StringBuilder methodSignature = new StringBuilder();
		StringBuilder methodInvocation = new StringBuilder();

		methodSignature.append(methodName).append('(');
		methodInvocation.append(methodName).append('(');

		int c = 0;
		for (Parameter parameter : parameters) {
			if (c != 0) {
				methodSignature.append(", ");
				methodInvocation.append(", ");
			}
			c++;
			String type = parameter.attribute.getType();
			methodSignature.append(type);
			methodInvocation.append(parameter.name);
		}
		if (allowsBody && requestBody != null) {
			if (methodSignature.charAt(methodSignature.length() - 1) != '(') {
				methodSignature.append(", ");
				methodInvocation.append(", ");
			}
			methodSignature.append("okhttp3.RequestBody");
			methodInvocation.append("Utils.createRequestBody(requestBody)");
		}

		methodSignature.append(')');
		methodInvocation.append(')');
		this.methodSignature = methodSignature.toString();
		this.methodInvocation = methodInvocation.toString();

	}

	public void generateInternalInterface(StringBuilder out, String endpoint) {
		out.append("\n\t/**");
		if (this.description != null) {
			Attribute.appendMultiLine(out, description, false, 1);
		}
		appendParameterDocs(out);
		appendReturnDocs(out, returnType);
		out.append("\n\t */\n");

		if (contentType != null) {
			out.append("\t@Headers(\"Content-Type: ").append(contentType).append("\")\n");
		}

		out.append('\t');
		if ("get".equalsIgnoreCase(method)) {
			out.append("@GET(\"");
		} else if ("post".equalsIgnoreCase(method)) {
			out.append("@POST(\"");
		} else if ("put".equalsIgnoreCase(method)) {
			out.append("@PUT(\"");
		} else if ("delete".equalsIgnoreCase(method)) {
			out.append("@DELETE(\"");
		} else {
			throw new IllegalStateException("Unsupported HTTP method: " + method);
		}

		out.append("/v2" + endpoint).append("\")\n");
		out.append('\t');

		out.append("Call<");
		appendReturnType(out);
		out.append("> ").append(methodName).append('(');

		appendParameters(out, true);
		appendBody(out, true);
		out.append(");\n\n");
	}

	private void appendParameterDocs(StringBuilder out) {
		for (Parameter parameter : parameters) {
			out.append("\n\t * @param ").append(parameter.name);
			if (parameter.attribute != null) {
				out.append(' ');
				parameter.attribute.appendDocumentation(out, true);
			}
		}

		if (allowsBody && requestBody != null) {
			if ("byte[]".equals(requestBodyClass)) {
				if (requestBody.nonJsonDescription != null) {
					out.append("\n\t * @param requestBody a request body containing a {@code byte[]} loaded from a ").append(StringUtils.uncapitalize(requestBody.nonJsonDescription));
				} else {
					out.append("\n\t * @param requestBody a request body containing serialized information in a byte array");
				}
			} else {
				out.append("\n\t * @param requestBody a request body containing the json representation of {@link ").append(requestBodyClass).append("}");
			}
		}
	}

	private void appendReturnDocs(StringBuilder out, String returnType) {
		if (returnType.equals("Void")) {
			out.append("\n\t * @return ").append("a Retrofit {@link Call} which is used as a handle for this HTTP request. No response body is expected.");
		} else {
			out.append("\n\t * @return ").append("a Retrofit {@link Call} wrapping a successful response body represented by an instance of {@link ").append(returnType).append("}");
		}
	}

	private void appendParameters(StringBuilder out, boolean annotate) {
		int c = 0;
		for (Parameter parameter : parameters) {
			if (c != 0) {
				out.append(", ");
			}
			c++;
			if (annotate) {
				if ("path".equalsIgnoreCase(parameter.location)) {
					out.append("@Path(\"").append(parameter.name).append("\") ");
				} else {
					out.append("@Query(\"").append(parameter.name).append("\") ");
				}
			}
			String type = parameter.attribute.getType();
			out.append(type).append(' ').append(parameter.name);
		}
	}

	private void appendBody(StringBuilder out, boolean annotate) {
		if (allowsBody && requestBody != null) {
			if (out.charAt(out.length() - 1) != '(') {
				out.append(", ");
			}
			if (annotate) {
				out.append("@Body ");
				out.append("RequestBody requestBody");
			} else {
				out.append(requestBodyClass).append(" requestBody");
			}
		}
	}

	public void generateAsynchronousApi(StringBuilder out, String endpoint) {
		out.append("\n\t/**");
		if (this.description != null) {
			Attribute.appendMultiLine(out, description, false, 1);
		}
		//out.append("\n\t * Invokes {@code").append(endpoint).append("}");

		appendParameterDocs(out);

		out.append("\n\t * @param callback code to be executed once a response is available. ");
		if (returnType.equals("Void")) {
			out.append("No response body is expected.");
		} else {
			out.append("The response will be an instance of {@link ").append(returnType).append("}");
		}

		out.append("\n\t */\n");

		out.append('\t');

		out.append("public void ").append(methodName).append('(');

		appendParameters(out, false);
		appendBody(out, false);

		if (out.charAt(out.length() - 1) != '(') {
			out.append(", ");
		}
		out.append("WalletApiCallback<");
		appendReturnType(out);
		out.append("> callback){\n");
		out.append("\t\tapi.").append(methodInvocation).append(".enqueue(new WalletApiCallbackAdapter<>(callback));\n");
		out.append("\t}\n");
	}

	private void appendReturnType(StringBuilder out) {
		if (response.isArray) {
			out.append("List<").append(returnType).append(">");
		} else {
			out.append(returnType);
		}
	}

	public void generateSynchronousApi(StringBuilder out, String endpoint) {
		out.append("\n\t/**");
		if (this.description != null) {
			Attribute.appendMultiLine(out, description, false, 1);
		}
		//out.append("\n\t * Invokes {@code").append(endpoint).append("}");

		appendParameterDocs(out);

		boolean isVoid = returnType.equals("Void");

		if (!isVoid) {
			if (response.isArray) {
				out.append("\n\t * @return the server response as a list of {@link ").append(returnType).append("}");
			} else {
				out.append("\n\t * @return the server response as an instance of {@link ").append(returnType).append("}");
			}
		}
		out.append("\n\t */\n");
		out.append("\tpublic ");
		if (isVoid) {
			out.append("void");
		} else {
			appendReturnType(out);
		}
		out.append(' ').append(methodName).append('(');

		appendParameters(out, false);
		appendBody(out, false);

		out.append("){\n");
		out.append("\t\t");
		if (!isVoid) {
			out.append("return ");
		}
		out.append("executeSync(api.").append(methodInvocation).append(");\n");
		out.append("\t}\n");
	}
}
