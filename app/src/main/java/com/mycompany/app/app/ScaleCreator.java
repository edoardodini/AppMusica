package com.mycompany.app.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScaleCreator {
	private Logger LOGGER = LogManager.getLogger(ScaleCreator.class);
	private IntervalCreator intCreat = new IntervalCreator();

	public List<Note> createScale(Note rootNote, Scale referenceScale) {
		List<Note> scala = new ArrayList<Note>(); //[referenceScale.getIntervals().size() + 1];
		scala.add(new Note(rootNote.getNote()));
		for (int i = 1; i < referenceScale.getIntervals().size() + 1; i++) {
			scala.add(intCreat.getIntervalUp(scala.get(i - 1), referenceScale.getIntervals().get(i - 1)));
		}
		LOGGER.info("created a scale of notes starting from a scale and a root note");
		return scala;
	}

}