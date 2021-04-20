package com.mycompany.app.app.chords;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;

public class InvertedChord extends AbstractChordModifier implements AbstractChord {
	private static final Logger LOGGER = LogManager.getLogger(InvertedChord.class);
	private int inversion;

	public InvertedChord(int inversionKind, AbstractInvertableChord abstractChord) {
		if (inversionKind > 0) {
			if (inversionKind <= abstractChord.getIntervals().size()) {
				inversion = inversionKind;
				chordToDecorate = abstractChord;
				LOGGER.info("created an inverted chord with an inversion of %d", inversion);
			} else {
				LOGGER.debug("the inverted chord cannot be created because of a too big inversion kind");
				throw new IllegalArgumentException("the inversion kind should be minor than the lenght of the chord");
			}
		} else {
			LOGGER.debug("the inverted chord cannot be created because of a negative or zero inversion kind");
			throw new IllegalArgumentException("the inversion kind should be major than zero");
		}
	}

	@Override
	public List<Interval> getIntervals() {
		LOGGER.debug("the inverted chord intervals have been returned");
		return chordToDecorate.getIntervals();
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		List<Note> chord = new ArrayList<>();
		List<Note> notesToDecorate = chordToDecorate.getNotes(rootNote);
		for (int i = 0; i < notesToDecorate.size(); i++) {
			chord.add(notesToDecorate.get((i + inversion) % notesToDecorate.size()));
		}
		LOGGER.debug("the inverted chord notes have been returned");
		return chord;
	}

	public int getInversion() {
		LOGGER.debug("the inverted chord inversion kind has been returned");
		return inversion;
	}

}
