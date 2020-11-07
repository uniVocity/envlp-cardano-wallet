package com.univocity.cardano.wallet.common;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import okhttp3.*;
import org.apache.commons.io.*;

import java.io.*;
import java.nio.charset.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.function.*;

public class Utils {

	private static final ObjectWriter OBJECT_PRINTER = new ObjectMapper().writerWithDefaultPrettyPrinter();


	public static String readTextFromResource(String resourcePath, Charset encoding) {
		return readTextFromInput(getInput(resourcePath), encoding);
	}

	public static List<String> readLinesFromResource(String resourcePath, Charset encoding) {
		return readLinesFromInput(getInput(resourcePath), encoding);
	}

	public static List<String> readLinesFromInput(InputStream in, Charset encoding) {
		try {
			return IOUtils.readLines(in, encoding);
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			}
			throw new IllegalArgumentException("Unable to read contents from input", e);
		}
	}

	public static String readTextFromInput(InputStream in, Charset encoding) {
		try {
			StringBuilder out = new StringBuilder();

			for (String line : readLinesFromInput(in, encoding)) {
				out.append(line).append('\n');
			}
			if (out.length() > 0) {
				out.deleteCharAt(out.length() - 1);
			}
			return out.toString();
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			}
			throw new IllegalArgumentException("Unable to read contents from input", e);
		}
	}

	private static InputStream getInput(String path) {
		try {
			if (path != null) {
				path = FilenameUtils.separatorsToUnix(path);
			}

			File file = new File(path);
			if (!file.exists()) {
				InputStream input = Utils.class.getResourceAsStream(path);
				if (input == null) {
					input = Utils.class.getClassLoader().getResourceAsStream(path);
				}
				if (input == null) {
					input = ClassLoader.getSystemResourceAsStream(path);
				}
				if (input == null) {
					throw new IllegalStateException("Unable to determine resource from given path: " + path);
				} else {
					return input;
				}
			} else {
				return new FileInputStream(file);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unable to read resource from given path " + path, e);
		}
	}

	/**
	 * Ensures a given argument is not null.
	 *
	 * @param o         the object to validate
	 * @param fieldName the description of the field
	 */
	public static final void notNull(Object o, String fieldName) {
		if (o == null) {
			throw new IllegalArgumentException(fieldName + " cannot be null");
		}
	}

	/**
	 * Throws an IllegalArgumentException if the given array is null or empty.
	 *
	 * @param argDescription the description of the elements
	 * @param args           the elements to be validated.
	 * @param <T>            Type of arguments to be validated
	 */
	public static <T> void notEmpty(String argDescription, T... args) {
		if (args == null) {
			throw new IllegalArgumentException(argDescription + " must not be null");
		}
		if (args.length == 0) {
			throw new IllegalArgumentException(argDescription + " must not be empty");
		}
	}

	/**
	 * Ensures a given {@link CharSequence} argument is not null/empty/blank
	 *
	 * @param o         a character sequence
	 * @param fieldName the description of the field
	 */
	public static final void notBlank(CharSequence o, String fieldName) {
		notNull(o, fieldName);
		if (o.toString().trim().isEmpty()) {
			throw new IllegalArgumentException(fieldName + " cannot be blank");
		}
	}

	public static String printObject(Object o) {
		if (o == null) {
			return "null";
		}
		try {
			return OBJECT_PRINTER.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			return o.toString();
		}
	}

	public static RequestBody createRequestBody(Object requestBody) {
		return okhttp3.RequestBody.create(requestBody.toString(), MediaType.parse("application/json"));
	}

	public static <O, I> List<O> convertList(List<? extends I> in, Function<? extends I, ? extends O> converter) {
		List<O> out = new ArrayList<>(in.size());
		Function conv = converter;
		for (Object original : in) {
			out.add((O) conv.apply(original));
		}
		return out;
	}

	private static String getShortestString(String... strings) {
		String shortest = "";
		for (String s : strings) {
			if (shortest.length() < s.length()) {
				shortest = s;
			}
		}
		return shortest;
	}

	public static String longestCommonSubstring(String... strings) {
		char[] shortest = getShortestString(strings).toCharArray();

		StringBuilder tmp = new StringBuilder();
		String common = "";
		for (int i = 0; i < shortest.length; i++) {
			char c = shortest[i];
			tmp.append(c);

			for (String s : strings) {
				if (!s.contains(tmp)) {
					tmp.setLength(0);
					tmp.append(c);
					for (String str : strings) {
						if (!str.contains(tmp)) {
							tmp.setLength(0);
							break;
						}
					}
					break;
				}
			}

			if (tmp.length() > 0 && tmp.length() > common.length()) {
				common = tmp.toString();
			}
		}

		return common;
	}

	public static Map<Long, Object> toMetadata(Map in) {
		if (in == null || in.isEmpty()) {
			return Collections.emptyMap();
		}
		Map<Long, Object> out = new TreeMap<>();

		in.forEach((k, v) -> out.put(Long.valueOf(k.toString()), toMetadata(v)));

		return out;
	}

	public static Object toMetadata(Object o) {
		if (o instanceof Map) {
			Map out = new HashMap();
			((Map<?, ?>) o).forEach((k, v) -> {
				out.put(k, toMetadata(v));
			});
			o = out;
		} else if (o instanceof Collection) {
			List out = new ArrayList();
			((List<?>) o).forEach(e -> out.add(toMetadata(e)));
			o = out;
		}
		return o;
	}

	public static DateTimeFormatter iso8601DateFormatter(){
		return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss'Z'");
	}

	public static String toFormattedISO8601Date(LocalDateTime date) {
		if (date == null) {
			return null;
		}
		return iso8601DateFormatter().format(date);
	}
}
