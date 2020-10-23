package com.univocity.cardano.wallet.api.generator;

import com.fasterxml.jackson.databind.*;
import com.univocity.cardano.wallet.common.*;
import org.apache.commons.io.*;
import org.apache.commons.lang3.*;
import org.testng.annotations.*;
import org.yaml.snakeyaml.*;
import org.yaml.snakeyaml.constructor.*;
import org.yaml.snakeyaml.representer.*;
import org.yaml.snakeyaml.resolver.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.util.function.*;

@Ignore
public class ApiGenerator {
	static final String home = System.getProperty("user.home");
	static final String yaml = home + "/dev/repository/iog-cardano-wallet/specifications/api/swagger.yaml";
	static final String generated = home + "/dev/repository/envlp/cardano-wallet/src/main/java/com/univocity/cardano/wallet/api/generated";


	private static Reader openYamlFile() throws Exception {
		return new FileReader(new File(yaml));
	}

	public static void main(String... args) throws Exception {

		LoaderOptions loaderOptions = new LoaderOptions();
		loaderOptions.setMaxAliasesForCollections(5000);
		Yaml yaml = new Yaml(new Constructor(), new Representer(), new DumperOptions(), loaderOptions, new Resolver());

		Map globals = yaml.load(openYamlFile());

		globals = (Map) deepCopy(globals);
		Map paths = (Map) globals.get("paths");

		List<Endpoint> endpoints = new ArrayList<>();

		paths.forEach((k, v) -> processEndpoint(k, (Map) v, endpoints));

		System.out.println("-------------------[ Generating class structure ]-------------------");
		File baseDir = new File(generated);


		Set<String> packagesToImport = new TreeSet<>();
		packagesToImport.add("java.util");

		Map<String, ClassRef> classes = new TreeMap<>();
		endpoints.forEach(e -> e.generateClasses(baseDir, packagesToImport, classes));
		Collection<ClassRef> classesToGenerate = mergeClasses(classes.values());

		System.out.println("Generating " + classesToGenerate.size() + ":");
		classesToGenerate.forEach(ClassRef::generate);

		generateApi(baseDir, packagesToImport, endpoints, "InternalWalletApiService", Endpoint::generateInternalApi);
		generateApi(baseDir, packagesToImport, endpoints, "AsynchronousWalletApi", Endpoint::generateAsynchronousApi);
		generateApi(baseDir, packagesToImport, endpoints, "SynchronousWalletApi", Endpoint::generateSynchronousApi);
	}

	private static void generateApi(File baseDir, Set<String> packagesToImport, List<Endpoint> endpoints, String targetFile, BiConsumer<Endpoint, StringBuilder> apiGenerationCall) throws Exception {
		StringBuilder out = new StringBuilder();

		endpoints.forEach(e -> apiGenerationCall.accept(e, out));

		ParameterizedString template = new ParameterizedString(Utils.readTextFromResource("templates/" + targetFile + ".txt", StandardCharsets.UTF_8));
		template.set("API_METHODS", out.toString());

		out.setLength(0);
		packagesToImport.forEach(p -> out.append("import ").append(p).append(".*;\n"));
		template.set("IMPORTS", out.toString());

		System.out.println("--- GENERATED API CLASS ---");
		String code = template.applyParameterValues();
		System.out.println(code);

		IOUtils.write(code, new FileOutputStream(baseDir.toPath().resolve(targetFile + ".java").toFile()), StandardCharsets.UTF_8);
	}

	private static Collection<ClassRef> mergeClasses(Collection<ClassRef> classes) {
		for (ClassRef ref1 : classes) {
			for (ClassRef ref2 : classes) {
				if (ref1 != ref2) {
					if (ref1.isSameCode(ref2)) {
						ref1.common = true;
						ref2.common = true;
					}
				}
			}
		}
		for (ClassRef ref : classes) {
			ref.importCommonPackage();
		}

		Map<String, ClassRef> out = new HashMap<>();
		for (ClassRef ref : classes) {
			if (ref.common) {
				if (out.containsKey(ref.name)) {
					continue;
				}
				ref.moveToCommonPackage();
				out.put(ref.name, ref);
			} else {
				out.put(ref.classFile.getAbsolutePath(), ref);

			}
		}

		return CommonParent.refactorToCommonParent(out.values());
	}

	private static String identValue(Object v, Object k, String identation) {
		String text = String.valueOf(v).trim();
		if (text.contains("\n")) {
			text = StringUtils.replace(text, "\r\n", "\n");
			text = StringUtils.replace(text, "\n\n", "\n");
			String id = StringUtils.repeat(' ', String.valueOf(k).length()) + identation;

			text = "\n" + id + StringUtils.replace(text, "\n", "\n" + id);
		}
		return text;
	}

	private static void expandAll(Map m, int depth) {
		String identation = StringUtils.repeat(' ', depth * 4);
		m.forEach((k, v) -> {
			System.out.print(identation + k);

			Map map = null;
			Collection collection = null;
			Object value = null;

			if (v instanceof Map) {
				map = (Map) v;
			} else if (v instanceof Collection) {
				collection = (Collection) v;
			} else {
				value = v;
			}


			if (value != null) {
				System.out.println(": " + identValue(v, k, identation));
			}

			if (collection != null) {
				int mapCount = 0;
				for (Object o : collection) {
					if (o instanceof Map) {
						mapCount++;
						System.out.println();
						expandAll((Map) o, depth + 1);
					}
				}
				if (mapCount == 0) {
					System.out.println(": " + identValue(v, k, identation));
				}
			}
			if (map != null) {
				System.out.println();
				expandAll(map, depth + 1);
			}

		});
	}

	static Object deepCopy(Object o) {
		Object out;

		try {
			if (o instanceof Collection) {
				out = o.getClass().getConstructor().newInstance();
				((Collection) o).forEach(e -> ((Collection) out).add(deepCopy(e)));
			} else if (o instanceof Map) {
				out = o.getClass().getConstructor().newInstance();
				((Map) o).forEach((k, v) -> ((Map) out).put(k, deepCopy(v)));
			} else {
				String str = String.valueOf(o);
				if (str.contains("style=\"") || str.contains("align=\"")) {
					o = o.toString().replaceAll("<[^>]*>", "");
				}
				out = o;
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}

		return out;
	}

	static String serializeMapToJson(Map map, String key) {
		Object example = map.remove(key);
		if (example != null) {
			if (example instanceof Map) {
				ObjectMapper mapper = new ObjectMapper();
				StringWriter w = new StringWriter();
				try {
					mapper.writerWithDefaultPrettyPrinter().writeValue(w, example);
					example = w.toString();
				} catch (Exception e) {
					example = example.toString();
				}
			}
			return example.toString();
		}
		return null;
	}

	private static void processEndpoint(Object endpoint, Map details, List<Endpoint> endpoints) {
		System.out.println(endpoint);
		expandAll(details, 1);

		System.out.println("-------------------[ Processing endpoint structure ]-------------------");
		Stack<Object> path = new Stack<Object>() {
			@Override
			public Object push(Object item) {
				Object out = super.push(item);
				System.out.println(this.toString());
				return out;
			}
		};
		try {
			endpoints.add(new Endpoint(String.valueOf(endpoint), details, path));
		} catch (Exception e) {
			throw new IllegalStateException("Error processing path " + path, e);
		}
	}
}
