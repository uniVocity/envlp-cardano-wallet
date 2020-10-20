package com.univocity.cardano.wallet.common;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.io.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.util.function.*;

public class Utils {

	private static final ObjectWriter OBJECT_PRINTER = new ObjectMapper().writerWithDefaultPrettyPrinter();

	public static String readTextFromResource(String resourcePath, Charset encoding) {
		return readTextFromInput(getInput(resourcePath), encoding);
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

	public static <O extends Wrapper<I>, I> List<O> convertList(List<I> in, Function<I, O> converter){
		List<O> out = new ArrayList<>(in.size());
		for(I original : in){
			out.add(converter.apply(original));
		}
		return out;
	}
}
