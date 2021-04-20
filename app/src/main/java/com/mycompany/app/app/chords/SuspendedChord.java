package com.mycompany.app.app.chords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;
import com.mycompany.app.app.Scale;

public class SuspendedChord extends AbstractChordModifier
		implements AbstractChord, AbstractAddableChord, AbstractInvertableChord {
	private static final Logger LOGGER = LogManager.getLogger(SuspendedChord.class);
	private int suspensionKind;

	public SuspendedChord(int suspensionKind, AbstractSuspendableChord suspendableChord) {
		if (suspensionKind == 2 || suspensionKind == 4) {
			LOGGER.info("created a new suspended chord");
			this.suspensionKind = suspensionKind;
			chordToDecorate = suspendableChord;
		} else {
			LOGGER.debug("the suspended chord cannot be created because of wrong suspension kind");
			throw new IllegalArgumentException("the suspension kind should be 2 or 4");
		}
	}

	public Object getSuspensionKind() {
		LOGGER.debug("the suspension kind of the suspended chord has been returned");
		return suspensionKind;
	}

	@Override
	public List<Interval> getIntervals() {
		List<Interval> intervalsBase = chordToDecorate.getIntervals();
		List<Interval> intervalsToReturn = new ArrayList<>();
		LOGGER.debug("the intervals of the suspended chord have been returned");
		for (int i = 0; i < intervalsBase.size(); i++) {
			intervalsToReturn.add(intervalsBase.get(i));
		}
		if (suspensionKind == 2) {
			intervalsToReturn.set(0, new Interval(1, 0));
			intervalsToReturn.set(1, new Interval(2, 1));
		} else {
			intervalsToReturn.set(0, new Interval(2, 1));
			intervalsToReturn.set(1, new Interval(1, 0));
		}
		return intervalsToReturn;
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		List<Note> chordBase = chordToDecorate.getNotes(rootNote);
		List<Note> chordToReturn = new ArrayList<>();
		for (int i = 0; i < chordBase.size(); i++) {
			chordToReturn.add(chordBase.get(i));
		}
		Scale biTone;
		if (suspensionKind == 2) {
			biTone = new Scale(Arrays.asList(new Interval(1, 0)));
		} else {
			biTone = new Scale(Arrays.asList(new Interval(2, 1)));
		}
		chordToReturn.set(1, biTone.getNotes(rootNote).get(1));
		LOGGER.debug("the notes of the suspended chord have been returned");
		return chordToReturn;
	}

}
