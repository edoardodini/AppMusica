package com.mycompany.app.app.chords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;
import com.mycompany.app.app.Scale;

public class AddedChord extends AbstractChordModifier implements AbstractChord, AbstractInvertableChord {
	private int addKind;

	public AddedChord(int addKind, AbstractAddableChord addableChord) {
		if(addKind==9||addKind==11||addKind==13) {
			chordToDecorate = addableChord;
			this.addKind = addKind;
		}
		else {
			throw new IllegalArgumentException("the add kind should be 9, 11 or 13");
		}
	}

	@Override
	public List<Interval> getIntervals() {
		List<Interval> chordIntervals = new ArrayList<Interval>();
		List<Interval> intervalsToDecorate = chordToDecorate.getIntervals();
		int tones=0;
		int halfs=0;
		for(int i=0; i<intervalsToDecorate.size();i++) {
			tones = tones + intervalsToDecorate.get(i).getIntervalTones();
			halfs = halfs + intervalsToDecorate.get(i).getIntervalHalfTones();
			chordIntervals.add(intervalsToDecorate.get(i));
		}
		halfs = tones*2+halfs;
		int halfsToReturn = 0;
		if (addKind==9) {
			halfsToReturn = 14-halfs;
		}else if (addKind==11) {
			halfsToReturn = 17-halfs;
		}else {
			halfsToReturn = 21-halfs;
		}
		int tonesToReturn = halfsToReturn/2;
		halfsToReturn = halfsToReturn%2;
		chordIntervals.add(new Interval(tonesToReturn,halfsToReturn));
		return chordIntervals;
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		List<Note> chordNotes = new ArrayList<Note>();
		List<Note> chordToDecorateNotes = chordToDecorate.getNotes(rootNote);
		for(int i=0; i<chordToDecorateNotes.size();i++) {
			chordNotes.add(chordToDecorateNotes.get(i));
		}
		int halfsDistance = 0;
		if (addKind==9) {
			halfsDistance = 14;
		}else if (addKind==11) {
			halfsDistance = 17;
		}else {
			halfsDistance = 21;
		}
		Scale biTone = new Scale(Arrays.asList(new Interval(0,halfsDistance)));
		chordNotes.add(biTone.getNotes(rootNote).get(1));
		return chordNotes;
	}

	public Object getAddKind() {
		return addKind;
	}

}
