package com.univocity.cardano.wallet.embedded;

import com.univocity.cardano.wallet.exception.*;
import org.apache.commons.lang3.*;
import org.slf4j.*;

import java.io.*;
import java.nio.file.*;
import java.util.function.*;

public class CardanoToolWrapper {

	private static final Logger log = LoggerFactory.getLogger(CardanoToolWrapper.class);

	private final CommandLineHelper commandLineHelper;

	protected final String toolName;
	private final String executableName;
	protected final File toolDir;

	public CardanoToolWrapper(String toolDirectoryPath, String toolName) {
		this.commandLineHelper = new CommandLineHelper();
		this.toolName = toolName;
		this.toolDir = new File(toolDirectoryPath);

		if(!toolDir.exists()){
			throw new CardanoToolsNotFoundException("Cardano tools directory does not exist: " + toolDirectoryPath);
		}

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

	public void setEnvironmentVariable(String variable, String value) {
		commandLineHelper.setEnvironmentVariable(variable, value);
	}

	protected String executeNoInput(String action, String command, boolean printOutput) {
		return execute(action, null, command, printOutput);
	}

	protected Process startProcess(String action, String command) {
		return execute(action, null, command, (fullCommand) -> commandLineHelper.startProcess(toolDir, fullCommand));
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

	protected boolean executeExpectingNoOutput(String action, String input, String command) {
		return StringUtils.isBlank(execute(action, input, command, true, false));
	}

	protected String execute(String action, String input, String command, boolean printOutput) {
		return execute(action, input, command, printOutput, true);
	}

	private String execute(String action, String input, String command, boolean printOutput, boolean outputExpected) {
		String output = execute(action, input, command, (fullCommand) -> commandLineHelper.executeAndReturnOutput(toolDir, fullCommand));
		if (StringUtils.isBlank(output)) {
			if (outputExpected) {
				log.warn("Could not {}.", action);
			}
			return null;
		}

		output = output.trim();
		if (!outputExpected) {
			log.warn("Could not {}:\n{}", action, output);
			return null;
		}
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
