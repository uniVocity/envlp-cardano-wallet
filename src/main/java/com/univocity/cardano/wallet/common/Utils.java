package com.univocity.cardano.wallet.common;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import okhttp3.*;
import org.apache.commons.codec.binary.*;
import org.apache.commons.io.*;
import org.slf4j.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class Utils {

	private static final Logger log = LoggerFactory.getLogger(Utils.class);

	private static final ObjectWriter OBJECT_PRINTER = new ObjectMapper().writerWithDefaultPrettyPrinter();
	private static File tempDir;

	static {
		try {
			File tmp = File.createTempFile("tmp", ".txt");
			tempDir = tmp.getParentFile();
			tmp.delete();
		} catch (Exception e) {
			log.warn("Unable to determine temporary directory", e);
		}
	}

	public static File tempDir(){
		return tempDir;
	}

	public static File createTempFile(String prefix) {
		try {
			File out = File.createTempFile(prefix, ".tmp", tempDir);
			out.deleteOnExit();
			return out;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

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

	public static Map toMetadata(Object o) {
		Map out = new HashMap();
		if (o instanceof String) {
			out.put("string", o);
		} else if (o instanceof Number) {
			out.put("int", o);
		} else if (o instanceof byte[]) {
			out.put("bytes", Hex.encodeHexString((byte[]) o));
		} else if (o instanceof Map) {
			out.put("map", toKeyValueList((Map) o));
		} else if (o instanceof Collection) {
			out.put("list", toValueList((Collection) o));
		}
		return out;
	}

	private static List<Map> toValueList(Collection values) {
		List<Map> out = new ArrayList<>(values.size());

		for (Object v : values) {
			out.add(toMetadata(v));
		}
		return out;
	}

	private static List<Map> toKeyValueList(Map map) {
		List<Map> out = new ArrayList<>(map.size());

		map.forEach((k, v) -> {
			Map entry = new LinkedHashMap();
			entry.put("k", toMetadata(k));
			entry.put("v", toMetadata(v));
			out.add(entry);
		});

		return out;
	}

	public static Map<Long, Object> fromMetadata(Object o) {
		if (o == null) {
			return Collections.emptyMap();
		}
		Map<Long, Object> out = new LinkedHashMap<>();
		Map metadata = (Map) o;

		metadata.forEach((k, v) -> out.put(Long.parseLong(k.toString()), toMetadataValue(v)));

		return out;
	}

	private static Object toMetadataValue(Object o) {
		if (o == null) {
			return null;
		}
		if (o instanceof Map) {
			Set<Map.Entry> entries = ((Map) o).entrySet();
			if (entries.size() > 1) {
				throw new IllegalStateException("Unexpected metadata element with multiple entries: " + entries);
			}
			for (Map.Entry e : entries) {
				String type = e.getKey().toString();
				switch (type) {
					case "string":
					case "int":
						return e.getValue();
					case "bytes":
						try {
							return Hex.decodeHex((String) e.getValue());
						} catch (Exception ex) {
							return e.getValue();
						}
					case "list":
						Collection values = (Collection) e.getValue();
						List out = new ArrayList();
						values.forEach(v -> out.add(toMetadataValue(v)));
						return out;
					case "map":
						Collection keyValuePairs = (Collection) e.getValue();
						Map map = new LinkedHashMap();
						keyValuePairs.forEach(v -> {
							Map item = ((Map) v);
							map.put(toMetadataValue(item.get("k")), toMetadataValue(item.get("v")));
						});
						return map;
					default:
						throw new IllegalStateException("Unable to handle metadata entry: " + e);
				}
			}
		}
		throw new IllegalStateException("Unable to process metadata value: " + o);
	}

	public static DateTimeFormatter iso8601DateFormatter() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ss'Z'");
	}

	public static String toFormattedISO8601Date(LocalDateTime date) {
		if (date == null) {
			return null;
		}
		return iso8601DateFormatter().format(date);
	}

	public static int randomPortNumber() {
		int start = ThreadLocalRandom.current().nextInt(1100, 65535);
		for(int port = start; port < 65535; port++){
			if (isLocalPortFree(port)) {
				return port;
			}
		}
		for(int port = start - 1; port >= 1100; port--){
			if (isLocalPortFree(port)) {
				return port;
			}
		}
		throw new IllegalStateException("No free ports available");
	}

	public static boolean isLocalPortFree(int port) {
		try {
			new ServerSocket(port).close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
