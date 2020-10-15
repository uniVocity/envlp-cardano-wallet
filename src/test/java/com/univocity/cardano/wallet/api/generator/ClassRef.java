package com.univocity.cardano.wallet.api.generator;

import org.apache.commons.io.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

class ClassRef {

	public boolean common = false;
	public final String name;
	public final StringBuilder code = new StringBuilder();
	public File classFile;

	public Set<ClassRef> children = new HashSet<>();

	public ClassRef(String name, File classFile) {
		this.name = name;
		this.classFile = classFile;
	}

	void generate() {
		System.out.println("Generating class " + name + " on " + classFile);
		try {
			if (!classFile.getParentFile().exists()) {
				classFile.getParentFile().mkdirs();
			}
			IOUtils.write(code.toString(), new FileOutputStream(classFile), StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new IllegalStateException("Error generating class " + classFile.getAbsolutePath(), e);
		}
	}

	static void insertImport(StringBuilder out, String toImport) {
		if (out.indexOf("import " + toImport) == -1) {
			out.insert(out.indexOf("import"), "import " + toImport + ";\n");
		}
	}

	boolean isSameCode(ClassRef ref) {
		if (!this.name.equals(ref.name)) {
			return false;
		}
		int mine = code.indexOf("public final class ");
		int theirs = ref.code.indexOf("public final class ");
		return this.code.substring(mine).equals(ref.code.substring(theirs));
	}

	public void moveToCommonPackage() {
		String oldPackageName = classFile.getParentFile().getName();
		classFile = classFile.getParentFile().getParentFile().toPath().resolve("common").resolve(classFile.getName()).toFile();
		int start = code.indexOf(oldPackageName + ";");
		int end = code.indexOf(";", start);
		code.replace(start, end, "common");

		start = code.indexOf("import com.univocity.cardano.wallet.api.generated.common.*;");
		if(start >= 0) {
			end = start + "import com.univocity.cardano.wallet.api.generated.common.*;".length();
			code.delete(start, end);
		}

	}

	public void importCommonPackage() {
		insertImport(code, "com.univocity.cardano.wallet.api.generated.common.*");
		for (ClassRef child : children) {
			if (child.common) {
				child.importCommonPackage();
				break;
			}
		}
	}
}
