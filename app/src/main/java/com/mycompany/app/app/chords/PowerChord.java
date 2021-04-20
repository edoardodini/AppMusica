package com.mycompany.app.app.chords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;
import com.mycompany.app.app.Scale;

public class PowerChord implements AbstractChord, AbstractInvertableChord, AbstractAddableChord {
	private static final Logger LOGGER = LogManager.getLogger(PowerChord.class);
	private Scale temporaryScale;

	public PowerChord() {
		LOGGER.info("new power chord created");
		temporaryScale = new Scale(Arrays.asList(new Interval(3, 1)));
	}

	@Override
	public List<Interval> getIntervals() {
		LOGGER.debug("the power chord intervals have been returned");
		return new ArrayList<>(Arrays.asList(new Interval(3, 1)));
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		LOGGER.debug("the power chord notes have been returned");
		return temporaryScale.getNotes(rootNote);
	}

}
