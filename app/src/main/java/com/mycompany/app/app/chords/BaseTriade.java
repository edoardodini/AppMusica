package com.mycompany.app.app.chords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;
import com.mycompany.app.app.Scale;

public class BaseTriade implements AbstractBasicChord,  AbstractAddableChord,  AbstractAumDimChord, 
AbstractSuspendableChord,  AbstractChord,  AbstractInvertableChord{
	protected Scale baseScale;
	public BaseTriade(boolean major) {
		if(major) {
			baseScale = new Scale(new ArrayList<Interval>(Arrays.asList(new Interval(2,0), new Interval(1,1))));
		}else {
			baseScale = new Scale(new ArrayList<Interval>(Arrays.asList(new Interval(1,1), new Interval(2,0))));
		}
	}

	@Override
	public List<Interval> getIntervals() {
		return baseScale.getIntervals();
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		return baseScale.getNotes(rootNote);
	}

}
