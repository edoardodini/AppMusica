package com.mycompany.app.app.chords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;
import com.mycompany.app.app.Scale;

public class DimAumTriade extends AbstractChordModifier
		implements AbstractChord, AbstractAddableChord, AbstractInvertableChord {
	private static final Logger LOGGER = LogManager.getLogger(DimAumTriade.class);
	private boolean isAugmented;

	public DimAumTriade(boolean isAum, AbstractAumDimChord dimAumChord) {
		boolean isMajor = isMajor(dimAumChord);
		if ((isMajor && isAum) || (!isMajor && !isAum)) {
			LOGGER.info("the DimAumTriade has been created");
			isAugmented = isAum;
			chordToDecorate = dimAumChord;
		} else if (isAum) {
			LOGGER.debug("the DimAumTriade has not been created: not possible to augment a minor triade");
			throw new IllegalArgumentException("it is not possible to augment a minor triade");
		} else {
			LOGGER.debug("the DimAumTriade has not been created: not possible to diminish a major triade");
			throw new IllegalArgumentException("it is not possible to diminish a major triade");
		}
	}

	@Override
	public List<Interval> getIntervals() {
		LOGGER.debug("the DimAumTriade intervals have been returned");
		if (isAugmented) {
			return new ArrayList<>(Arrays.asList(new Interval(2, 0), new Interval(2, 0)));
		} else {
			return new ArrayList<>(Arrays.asList(new Interval(1, 1), new Interval(1, 1)));
		}
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		List<Note> baseNotes = chordToDecorate.getNotes(rootNote);
		List<Note> notesToReturn = new ArrayList<>();
		for (int i = 0; i < baseNotes.size(); i++) {
			notesToReturn.add(baseNotes.get(i));
		}
		Scale biTone;
		if (isAugmented) {
			biTone = new Scale(Arrays.asList(new Interval(0, 1)));
			notesToReturn.set(2, biTone.getNotes(notesToReturn.get(2)).get(1));
		} else {
			biTone = new Scale(Arrays.asList(new Interval(1, 1), new Interval(1, 1)));
			notesToReturn.set(1, biTone.getNotes(notesToReturn.get(0)).get(1));
			notesToReturn.set(2, biTone.getNotes(notesToReturn.get(0)).get(2));
		}
		LOGGER.debug("the DimAumTriade notes have been returned");
		return notesToReturn;
	}

	public boolean isAugmented() {
		LOGGER.debug("the DimAumTriade augmented/dimished state has been returned");
		return isAugmented;
	}

	private boolean isMajor(AbstractAumDimChord dimAumChord) {
		List<Interval> intervals = dimAumChord.getIntervals();
		return intervals.get(0).equals(new Interval(2, 0));
	}
}
