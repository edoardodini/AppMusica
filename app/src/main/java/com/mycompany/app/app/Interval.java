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
	
	@Override
	public boolean equals(Object o) {

		// If the object is compared with itself then return true
		if (o == this) {
			LOGGER.debug(() -> String.format("The interval is compared to the same object so they are equals"));
			return true;
		}

		/*
		 * Check if o is an instance of Complex or not "null instance of [type]" also returns false
		 */
		if (!(o instanceof Interval)) {
			LOGGER.debug(() -> String.format("The interval is compared to an object that is not an interval, it is a: " + o.getClass()));
			return false;
		}

		// typecast o to Complex so that we can compare data members
		Interval c = (Interval) o;

		// Compare the data members and return accordingly
		if (halfTones+tones*2 == c.getIntervalHalfTones()+c.getIntervalTones()*2) {
			LOGGER.debug(() -> String.format("The interval is compared to an interval and they are equals"));
			return true;
		} else {
			LOGGER.debug(() -> String.format("The interval is compared to an interval and they are not equals"));
			return false;
		}
	}
	
	
}
