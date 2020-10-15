package com.univocity.cardano.wallet.embedded;

import org.apache.commons.lang3.*;
import org.slf4j.*;

import java.io.*;
import java.nio.file.*;
import java.util.function.*;

public class CardanoToolWrapper {

	private static final Logger log = LoggerFactory.getLogger(CardanoToolWrapper.class);

	protected final String toolName;
	private final String executableName;
	private final File toolDir;

	public CardanoToolWrapper(String toolDirectoryPath, String toolName) {
		this.toolName = toolName;
		this.toolDir = new File(toolDirectoryPath);
		String executableName = toolName;
		if (SystemUtils.IS_OS_WINDOWS) {
			executableName += ".exe";
		} else {
			executableName = "./" + executableName;
		}

		File executable = Paths.get(toolDirectoryPath, executableName).toFile();
		if (executable.exists() && !executable.canExecute()) {
			if (!executable.setExecutable(true)) {
				log.warn("Could not make file '{}' executable", executable.getAbsolutePath());
			}
		}

		this.executableName = executableName;
	}

	protected String executeNoInput(String action, String command, boolean printOutput) {
		return execute(action, null, command, printOutput);
	}

	protected Process startProcess(String action, String command) {
		return execute(action, null, command, (fullCommand) -> CommandLineHelper.startProcess(toolDir, fullCommand));
	}

	protected <V> V execute(String action, String input, String command, Function<String, V> commandExecution) {
		log.info("Executing '{}': {} {}...", action, executableName, command);

		StringBuilder fullCommand = new StringBuilder();
		if (input != null) {
			input = input.trim();
			if (SystemUtils.IS_OS_WINDOWS) {
				//	for /F "tokens=*" %i in ('seed') do cardano-address.exe key from-recovery-phrase Shelley %i //TODO: untested
				fullCommand.append("for /F \"tokens=*\" %i in ('").append(input).append("') do ");
			} else {
				fullCommand.append("echo '").append(input).append("' | ");
			}
		}

		fullCommand.append(executableName);
		fullCommand.append(' ');
		fullCommand.append(command);
		if (input != null && SystemUtils.IS_OS_WINDOWS) {
			fullCommand.append(" %i");
		}
		return commandExecution.apply(fullCommand.toString());
	}

	protected String execute(String action, String input, String command, boolean printOutput) {
		String output = execute(action, input, command, (fullCommand) -> CommandLineHelper.executeAndReturnOutput(toolDir, fullCommand));
		if (StringUtils.isBlank(output)) {
			log.warn("Could not {}.", action);
			return null;
		} else {
			output = output.trim();
			if (printOutput) {
				if (output.indexOf('\n') > 0) {
					log.info("Result: \n{}", output);
				} else {
					log.info("Result: '{}'", output);
				}
			}
			return output;
		}
	}
}
