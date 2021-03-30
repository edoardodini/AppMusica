package com.mycompany.app.app;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Scale implements Sequence {
	private Logger LOGGER = LogManager.getLogger(IntervalCreator.class);
	private List<Interval> scaleIntervals;

	public Scale(List<Interval> scaleIntervals) {
		this.scaleIntervals = scaleIntervals;
		LOGGER.debug("created a scale by setting some intervals");
	}

	public List<Interval> getIntervals() {
		LOGGER.debug("requested the intervals of the scale");
		return scaleIntervals;
	}
}
