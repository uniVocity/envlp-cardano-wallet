package com.univocity.cardano.wallet.embedded.services;

import com.univocity.cardano.wallet.common.*;
import com.univocity.cardano.wallet.embedded.*;
import org.slf4j.*;

import java.util.*;
import java.util.function.*;

public abstract class ProcessManager extends CardanoToolWrapper {

	private static final Logger log = LoggerFactory.getLogger(ProcessManager.class);

	private Process process;
	private boolean stopped;
	private Consumer<String> outputConsumer;
	private InputStreamer inputStreamer;
	private final Object lock = new Object();
	private long startTime;
	private ParameterizedString command;

	public ProcessManager(String toolDirPath, String toolName) {
		this(toolDirPath, toolName, null);
	}

	public ProcessManager(String toolDirPath, String toolName, Consumer<String> outputConsumer) {
		super(toolDirPath, toolName);
		this.outputConsumer = outputConsumer;
		Runtime.getRuntime().addShutdownHook(new Thread(this::stopProcess));
	}

	public ProcessManager setStartupCommand(String command) {
		this.command = new ParameterizedString(command);
		return this;
	}

	public void stopProcess() {
		stopped = true;
		if (process != null) {
			process.destroy();
			process = null;
		}
		synchronized (lock) {
			lock.notify();
		}
	}

	public void setOutputConsumer(Consumer<String> outputConsumer) {
		this.outputConsumer = outputConsumer;
		startReadingProcessOutput();
	}

	private void startReadingProcessOutput() {
		if (inputStreamer != null) {
			inputStreamer.close();
		}
		if (process != null) {
			startTime = System.currentTimeMillis();
			if (outputConsumer != null) {
				inputStreamer = new InputStreamer(toolName + " output", process.getInputStream(), outputConsumer);
				inputStreamer.start();
			} else {
				log.warn("No consumer defined for output of {}. Logs will not be available.", toolName);
			}
		}
	}

	public final String getStartupCommand() {
		if (this.command == null) {
			throw new IllegalStateException("No startup command provided for " + toolName);
		}
		return command.toString();
	}

	public final void restartProcess() {
		stopProcess();
		stopped = false;
		startProcess();
	}

	public final void startProcess() {
		if (process == null && !stopped) {
			log.info("Starting {} process", toolName);

			if (command == null) {
				throw new IllegalStateException("No startup command defined for " + toolName);
			}
			this.command.getParameterValues().forEach((k, v) -> Utils.notNull(v, k));
			String command = this.command.applyParameterValues();

			process = startProcess("new process", command);

			if (process == null || !process.isAlive()) {
				log.warn("Unable to start " + toolName);
			} else {
				startReadingProcessOutput();
				log.info("Starting {} monitor thread", toolName);
				Thread monitor = new Thread(ProcessManager.this::startProcessMonitor, toolName + " monitor");
				monitor.setDaemon(true);
				monitor.start();
			}
		}
	}

	private void startProcessMonitor() {
		if (process != null) {
			while (process != null && process.isAlive()) {
				try {
					synchronized (lock) {
						lock.wait(10_000);
					}
				} catch (InterruptedException e) {
					log.warn(toolName + " monitoring thread interrupted", e);
					Thread.currentThread().interrupt();
				}
			}
		}
		if (!stopped && process != null && !process.isAlive()) {
			process = null;
		}
	}

	public final void waitForProcess() throws InterruptedException {
		process.waitFor();
	}

	public final boolean isProcessRunning() {
		return process != null && process.isAlive();
	}

	public long getUptime() {
		return System.currentTimeMillis() - startTime;
	}

	public String getToolName() {
		return toolName;
	}

	public void setCommandParameterValidationEnabled(boolean commandParameterValidationEnabled) {
		command.setParameterValidationEnabled(commandParameterValidationEnabled);
	}

	public boolean isCommandParameterValidationEnabled() {
		return command.isParameterValidationEnabled();
	}

	public Set<String> getCommandParameters() {
		return command.getParameters();
	}

	public String getStartupCommandParameter(String parameterName) {
		Object out = command.get(parameterName);
		if (out == null) {
			return null;
		}
		return out.toString();
	}

	public void setCommandParameter(String parameter, Object value) {
		command.set(parameter, value);
	}
}
