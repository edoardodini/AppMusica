package com.mycompany.app.app.chords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;
import com.mycompany.app.app.Scale;

public class DimAumAlteredTriade extends AbstractChordModifier implements AbstractChord, AbstractAddableChord, AbstractInvertableChord{
	private boolean isAugmented;

	public DimAumAlteredTriade(boolean isAum, AbstractAumDimChord dimAumChord) {
		boolean isMajor = isMajor(dimAumChord);
		if((isMajor&&isAum)||(!isMajor&&!isAum)) {
			isAugmented = isAum;
			chordToDecorate = dimAumChord;
		}else if(isAum) {
			throw new IllegalArgumentException("it is not possible to augment a minor triade");
		}else {
			throw new IllegalArgumentException("it is not possible to diminish a major triade");
		}
	}

	@Override
	public List<Interval> getIntervals() {
		if (isAugmented) {
			return new ArrayList<Interval>(Arrays.asList(new Interval(2,0),new Interval(2,0)));
		}else {
			return new ArrayList<Interval>(Arrays.asList(new Interval(1,1),new Interval(1,1)));
		}
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		List<Note> baseNotes = chordToDecorate.getNotes(rootNote);
		List<Note> notesToReturn = new ArrayList<Note>();
		for(int i=0;i<baseNotes.size();i++) {
			notesToReturn.add(baseNotes.get(i));
		}
		Scale biTone;
		if (isAugmented) {
			biTone = new Scale(Arrays.asList(new Interval(0,1)));
			notesToReturn.set(2,biTone.getNotes(notesToReturn.get(2)).get(1));
		}else {
			biTone = new Scale(Arrays.asList(new Interval(1,1),new Interval(1,1)));
			notesToReturn.set(1,biTone.getNotes(notesToReturn.get(0)).get(1));
			notesToReturn.set(2,biTone.getNotes(notesToReturn.get(0)).get(2));
		}
		return notesToReturn;
	}

	public boolean isAugmented() {
		return isAugmented;
	}
	
	private boolean isMajor(AbstractAumDimChord dimAumChord) {
		List<Interval> intervals = dimAumChord.getIntervals();
		if (intervals.get(0).equals(new Interval(2,0))&&intervals.get(1).equals(new Interval(1,1))) {
			return true;
		}else {
			return false;
		}
	}
}
