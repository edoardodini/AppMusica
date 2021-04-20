package com.mycompany.app.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Scale implements Sequence {
	private static final Logger LOGGER = LogManager.getLogger(Scale.class);
	private List<Interval> scaleIntervals;
	private IntervalCreator intCreat = new IntervalCreator();

	public Scale(List<Interval> scaleIntervals) {
		this.scaleIntervals = scaleIntervals;
		LOGGER.info("created a scale by setting some intervals and a root note");
	}

	@Override
	public List<Interval> getIntervals() {
		LOGGER.debug("requested the intervals of the scale");
		return scaleIntervals;
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		List<Note> sequence = new ArrayList<>();
		sequence.add(rootNote);
		for (int i = 1; i < scaleIntervals.size() + 1; i++) {
			sequence.add(intCreat.getIntervalUp(sequence.get(i - 1), scaleIntervals.get(i - 1)));
		}
		LOGGER.debug("created a scale of notes starting from a scale and a root note");
		return sequence;
	}
}