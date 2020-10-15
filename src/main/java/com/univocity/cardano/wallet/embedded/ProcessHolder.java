package com.univocity.cardano.wallet.embedded;

import java.io.*;

/**
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public final class ProcessHolder implements Closeable {

	public final Process process;
	private boolean isRunning;

	public ProcessHolder(Process process) {
		this.process = process;
		this.isRunning = true;
	}

	@Override
	public final void close() {
		isRunning = false;
		try {
			process.destroy();
		} catch (Throwable t) {
			//ignore.
		}
	}

	public final boolean isRunning() {
		return isRunning;
	}
}
