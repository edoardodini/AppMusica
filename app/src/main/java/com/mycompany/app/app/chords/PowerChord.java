package com.mycompany.app.app.chords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;
import com.mycompany.app.app.Scale;

public class PowerChord implements AbstractChord, AbstractInvertableChord, AbstractAddableChord {
	private Scale temporaryScale;
	
	public PowerChord() {
		temporaryScale = new Scale(Arrays.asList(new Interval(3,1)));
	}

	@Override
	public List<Interval> getIntervals() {
		return new ArrayList<Interval>(Arrays.asList(new Interval(3,1)));	
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		return temporaryScale.getNotes(rootNote);
	}

}
