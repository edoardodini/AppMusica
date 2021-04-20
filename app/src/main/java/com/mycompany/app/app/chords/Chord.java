package com.mycompany.app.app.chords;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;
import com.mycompany.app.app.Scale;

public class Chord implements AbstractSuspendableChord, AbstractInvertableChord, AbstractAddableChord {
	private static final Logger LOGGER = LogManager.getLogger(Chord.class);
	private List<Interval> chordIntervals;
	private Scale temporaryScale;

	public Chord(List<Interval> chordIntervals) {
		if (chordIntervals.size() >= 2) {
			if (!intervalsAreGood(chordIntervals)) {
				LOGGER.debug("The chord cannot be created because of invalid arguments");
				throw new IllegalArgumentException("The argument is not a valid chord, wrong intervals");
			} else {
				LOGGER.info("New chord created");
				this.chordIntervals = chordIntervals;
				temporaryScale = new Scale(chordIntervals);
			}
		} else {
			LOGGER.debug("The chord cannot be created because of invalid arguments, not enough intervals");
			throw new IllegalArgumentException("The argument is not a valid chord, not enough intervals");
		}
	}

	@Override
	public List<Interval> getIntervals() {
		LOGGER.debug("The chord has been requested and returned");
		return chordIntervals;
	}

	private boolean intervalsAreGood(List<Interval> chordIntervals) {
		boolean isChordGood = true;
		for (int i = 0; i < chordIntervals.size(); i++) {
			if (chordIntervals.get(i).getIntervalTones() * 2 + chordIntervals.get(i).getIntervalHalfTones() == 3
					|| chordIntervals.get(i).getIntervalTones() * 2
							+ chordIntervals.get(i).getIntervalHalfTones() == 4) {
				// do nothing
			} else {
				isChordGood = false;
			}
		}
		return isChordGood;
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		LOGGER.debug(
				() -> String.format("The chord creation has been requested with root note: %s", rootNote.getNote()));
		return temporaryScale.getNotes(rootNote);
	}
}
