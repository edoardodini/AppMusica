package com.mycompany.app.app.chords;

import java.util.List;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;

public interface AbstractAddableChord extends AbstractChord{

	@Override
	public List<Interval> getIntervals();
	
	@Override
	public List<Note> getNotes(Note rootNote);
	
}
