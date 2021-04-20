package com.mycompany.app.app.chords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;
import com.mycompany.app.app.Scale;

public class AddedChord extends AbstractChordModifier implements AbstractChord, AbstractInvertableChord {
	private static final Logger LOGGER = LogManager.getLogger(AddedChord.class);
	private int addKind;

	public AddedChord(int addKind, AbstractAddableChord addableChord) {
		if (addKind == 9 || addKind == 11 || addKind == 13) {
			LOGGER.info("new added chord created");
			chordToDecorate = addableChord;
			this.addKind = addKind;
		} else {
			LOGGER.debug("the added chord has not been created because of a wrong add kind");
			throw new IllegalArgumentException("the add kind should be 9, 11 or 13");
		}
	}

	@Override
	public List<Interval> getIntervals() {
		List<Interval> chordIntervals = new ArrayList<>();
		List<Interval> intervalsToDecorate = chordToDecorate.getIntervals();
		int tones = 0;
		int halfs = 0;
		for (int i = 0; i < intervalsToDecorate.size(); i++) {
			tones = tones + intervalsToDecorate.get(i).getIntervalTones();
			halfs = halfs + intervalsToDecorate.get(i).getIntervalHalfTones();
			chordIntervals.add(intervalsToDecorate.get(i));
		}
		halfs = tones * 2 + halfs;
		int halfsToReturn = 0;
		if (addKind == 9) {
			halfsToReturn = 14 - halfs;
		} else if (addKind == 11) {
			halfsToReturn = 17 - halfs;
		} else {
			halfsToReturn = 21 - halfs;
		}
		int tonesToReturn = halfsToReturn / 2;
		halfsToReturn = halfsToReturn % 2;
		chordIntervals.add(new Interval(tonesToReturn, halfsToReturn));
		LOGGER.debug("the added chord intervals have been returned");
		return chordIntervals;
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		List<Note> chordNotes = new ArrayList<>();
		List<Note> chordToDecorateNotes = chordToDecorate.getNotes(rootNote);
		for (int i = 0; i < chordToDecorateNotes.size(); i++) {
			chordNotes.add(chordToDecorateNotes.get(i));
		}
		int halfsDistance = 0;
		if (addKind == 9) {
			halfsDistance = 14;
		} else if (addKind == 11) {
			halfsDistance = 17;
		} else {
			halfsDistance = 21;
		}
		Scale biTone = new Scale(Arrays.asList(new Interval(0, halfsDistance)));
		chordNotes.add(biTone.getNotes(rootNote).get(1));
		LOGGER.debug("the added chord notes have been returned");
		return chordNotes;
	}

	public Object getAddKind() {
		LOGGER.debug("the added chord notes have been returned");
		return addKind;
	}

}