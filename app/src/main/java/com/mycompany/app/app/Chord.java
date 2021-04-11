package com.mycompany.app.app;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Chord implements Sequence{
	private Logger LOGGER = LogManager.getLogger(IntervalCreator.class);
	private List<Interval> chordIntervals;
	private Scale temporaryScale;
	private Note rootNote;
	
	public Chord(Note rootNote, List<Interval> chordIntervals) {
		if(chordIntervals.size()>=2) {
				if(!intervalsAreGood(chordIntervals)) {
					temporaryScale = new Scale(rootNote, chordIntervals);
					this.rootNote = rootNote;
					LOGGER.debug(() -> String.format("The chord cannot be created because of invalid arguments"));
					throw new IllegalArgumentException("The argument is not a valid chord, wrong intervals");
				}else {
					LOGGER.info(() -> String.format("New chord created"));
					this.chordIntervals = chordIntervals;
				}
		}else {
			LOGGER.debug(() -> String.format("The chord cannot be created because of invalid arguments, not enough intervals"));
			throw new IllegalArgumentException("The argument is not a valid chord, not enough intervals");
		}
	}

	@Override
	public List<Interval> getIntervals() {
		LOGGER.debug(() -> String.format("The chord has been requested and returned"));
		return chordIntervals;
	}
	
	private boolean intervalsAreGood(List<Interval> chordIntervals) {
		boolean isChordGood = true;
		for(int i=0;i<chordIntervals.size();i++) {
			if(chordIntervals.get(i).getIntervalTones()*2+chordIntervals.get(i).getIntervalHalfTones()==3||
					chordIntervals.get(i).getIntervalTones()*2+chordIntervals.get(i).getIntervalHalfTones()==4) {
				//do nothing
			}else {
				isChordGood= false;
			}
		}
		return isChordGood;
	}

	@Override
	public List<Note> getNotes() {
		return temporaryScale.getNotes();
	}

	@Override
	public Note getRootNote() {
		return rootNote;
	}
}
