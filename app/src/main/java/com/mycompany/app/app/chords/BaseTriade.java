package com.mycompany.app.app.chords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;
import com.mycompany.app.app.Scale;

public class BaseTriade implements AbstractBasicChord,  AbstractAddableChord,  AbstractAumDimChord, 
AbstractSuspendableChord,  AbstractChord,  AbstractInvertableChord{
	private static final Logger LOGGER = LogManager.getLogger(BaseTriade.class);
	protected Scale baseScale;
	
	public BaseTriade(boolean major) {
		LOGGER.info("a base triade has been created");
		if(major) {
			baseScale = new Scale(new ArrayList<Interval>(Arrays.asList(new Interval(2,0), new Interval(1,1))));
		}else {
			baseScale = new Scale(new ArrayList<Interval>(Arrays.asList(new Interval(1,1), new Interval(2,0))));
		}
	}

	@Override
	public List<Interval> getIntervals() {
		LOGGER.debug("the base triade intervals have been returned");
		return baseScale.getIntervals();
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		LOGGER.debug("the base triade notes have been returned");
		return baseScale.getNotes(rootNote);
	}

}
