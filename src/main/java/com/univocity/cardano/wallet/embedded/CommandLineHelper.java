package com.univocity.cardano.wallet.embedded;


import com.univocity.cardano.wallet.common.*;
import com.univocity.parsers.csv.*;
import org.apache.commons.io.*;
import org.apache.commons.lang3.*;
import org.slf4j.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class CommandLineHelper {

	private static final Logger log = LoggerFactory.getLogger(CommandLineHelper.class);
	private Map<String, String> environmentVariables = new LinkedHashMap<>();

	public Process startProcess(String command) throws IOException {
		return startProcessSafely(command, 0, TimeUnit.MILLISECONDS).process;
	}

	public ProcessHolder startProcessSafely(String command, long timeToLive, TimeUnit timeUnit) throws IOException {
		return startProcessSafely(createProcessBuilder(command), timeToLive, timeUnit);
	}

	public ProcessHolder startProcessSafely(ProcessBuilder builder, long timeToLive, TimeUnit timeUnit) throws IOException {
		final ProcessHolder out = new ProcessHolder(builder.start());

		if (timeToLive != 0) {
			final long timeout = timeUnit.toMillis(timeToLive);
			final long startTime = System.currentTimeMillis();

			new Thread(() -> {
				try {
					while (out.isRunning() && System.currentTimeMillis() - startTime < timeout) {
						Thread.sleep(50);
					}
					if (out.isRunning()) {
						out.close();
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				} catch (Exception ex) {
					// ignore
				}
			}).start();
		}

		return out;
	}

	public List<String> executeProcessSafely(ProcessBuilder builder, long timeToLive, TimeUnit timeUnit) throws IOException {
		InputStreamReader in = null;
		ProcessHolder holder = null;
		try {
			holder = startProcessSafely(builder, timeToLive, timeUnit);
			Process pid = holder.process;
			in = new InputStreamReader(pid.getInputStream());
			return IOUtils.readLines(in);
		} catch (Exception ex) {
			log.error("Error executing process", ex);
			return Collections.emptyList();
		} finally {
			closeIgnoringErrors(in);
			closeIgnoringErrors(holder);
		}
	}

	public List<String> executeProcessSafely(String command, long timeToLive, TimeUnit timeUnit) throws IOException {
		return executeProcessSafely(createProcessBuilder(command), timeToLive, timeUnit);
	}

	public List<String> executeProcess(String command) throws IOException {
		return executeProcessSafely(command, 0, TimeUnit.MILLISECONDS);
	}

	public void setEnvironmentVariable(String variable, String value) {
		Utils.notBlank(variable, "Environment variable name");
		Utils.notNull(value, "Value of environment variable " + variable);
		environmentVariables.put(variable, value);
	}

	private ProcessBuilder createProcessBuilder(String command) {
		ProcessBuilder builder = new ProcessBuilder(extractCommandWithArguments(command));
		builder.redirectErrorStream(true);

		environmentVariables.forEach((k, v) -> builder.environment().put(k, v));

		return builder;
	}

	private static final CsvParserSettings commandLineSettings;

	static {

		commandLineSettings = new CsvParserSettings();
		commandLineSettings.getFormat().setDelimiter(' ');
		commandLineSettings.getFormat().setQuoteEscape('\\');
		commandLineSettings.getFormat().setQuote('\'');
	}

	private static String[] extractCommandWithArguments(String command) {
		if (!SystemUtils.IS_OS_WINDOWS) {
			if (command.indexOf('|') > 0) { //invokes a shell to handle piped commands.
				return new String[]{"/bin/sh", "-c", command};
			}
		}

		CsvParserSettings settings = commandLineSettings.clone();
		int singleQuote = command.indexOf('\'');
		int doubleQuote = command.indexOf('"');

		if (singleQuote != -1) {
			if (doubleQuote != -1) {
				settings.getFormat().setQuote(singleQuote < doubleQuote ? '\'' : '"');
			} else {
				settings.getFormat().setQuote('\'');
			}
		} else if (doubleQuote != -1) {
			settings.getFormat().setQuote('"');
		}

		String[] args = new CsvParser(settings).parseLine(command);
		args = ArrayUtils.removeAllOccurences(args, null);
		return args;
	}

	public String executeAndReturnOutput(File directory, String command) {
		List<String> results;
		try {
			ProcessBuilder builder = createProcessBuilder(command);
			builder.directory(directory);
			results = executeProcessSafely(builder, 10, TimeUnit.SECONDS);
		} catch (IOException e) {
			throw new IllegalStateException("Error executing '" + command + "'", e);
		}

		StringBuilder out = new StringBuilder();
		for (String result : results) {
			out.append(result);
			out.append('\n');
		}
		return out.toString().trim();
	}

	public final Process startProcess(File directory, String command) {
		ProcessBuilder builder = createProcessBuilder(command);
		builder.directory(directory);

		try {
			Process pid = builder.start();
			return pid;
		} catch (Exception e) {
			throw new IllegalStateException("Error executing '" + command + "' in " + directory, e);
		}
	}

	public final void execute(File directory, String command, PrintStream output) {
		InputStreamReader in = null;
		try {
			Process pid = startProcess(directory, command);
			in = new InputStreamReader(pid.getInputStream());

			if (output != null) {
				BufferedReader reader = new BufferedReader(in);
				String line;
				while ((line = reader.readLine()) != null) {
					output.println(line);
				}
			}

			int status = 0;
			if ((status = pid.waitFor()) != 0) {
				throw new IllegalStateException("Error executing '" + command + "' in " + directory + ". Error code: " + status);
			}

			in.close();
		} catch (Exception e) {
			throw new IllegalStateException("Error executing '" + command + "' in " + directory, e);
		} finally {
			closeIgnoringErrors(in);
		}
	}

	private static void closeIgnoringErrors(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (Throwable e) {
				//ignore
			}
		}
	}
}
