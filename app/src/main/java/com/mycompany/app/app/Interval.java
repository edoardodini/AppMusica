package com.mycompany.app.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Interval {
	private int tones;
	private int halfTones;
	private Logger LOGGER = LogManager.getLogger(Interval.class);

	public Interval(int tones, int halfTones) {
		if ((tones >= 0) && (halfTones >= 0)) {
			this.tones = tones;
			this.halfTones = halfTones;
			LOGGER.info(() -> String.format("New interval created: %d tones and %d half tones", tones, halfTones));
		} else {
			LOGGER.debug(() -> String.format("New interval not created: invalid arguments"));
			throw new IllegalArgumentException("The argument is not an interval");
		}
	}

	public int getIntervalTones() {
		LOGGER.debug(() -> String.format("The tones of the interval are asked and returned: %d tones", tones));
		return tones;
	}

	public int getIntervalHalfTones() {
		LOGGER.debug(() -> String.format("The half tones of the interval are asked and returned: %d half tones", halfTones));
		return halfTones;
	}
}
