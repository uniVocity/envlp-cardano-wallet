package com.univocity.cardano.wallet.embedded;

import org.slf4j.*;

import java.io.*;
import java.util.function.*;

public class InputStreamer extends Thread {
	private static final Logger log = LoggerFactory.getLogger(InputStreamer.class);

	private final String description;
	private final InputStream inputStream;
	private final Consumer<String> lineConsumer;
	private boolean running;

	public InputStreamer(String description, InputStream inputStream, Consumer<String> lineConsumer) {
		super(description);
		this.description = description;
		this.setDaemon(true);
		this.inputStream = inputStream;
		this.lineConsumer = lineConsumer;
	}

	public void run() {
		running = true;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			while (running) {
				String line;
				while ((line = br.readLine()) != null) {
					lineConsumer.accept(line);
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				if (!running) {
					br.close();
				}
			}
		} catch (IOException e) {
			if (!e.getMessage().contains("Stream closed")) {
				log.warn("Error processing input from " + description, e);
			}
		} finally {
			log.info("Stopped processing input from " + description);
			running = false;
		}
	}

	public void close() {
		log.info("Manually closing input processing from " + description);
		running = false;
	}
}