package com.mycompany.app.app.chords;

import java.util.List;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;
import com.mycompany.app.app.Sequence;

public interface AbstractChord extends Sequence {

	@Override
	public List<Interval> getIntervals();
	
	@Override
	public List<Note> getNotes(Note rootNote);

}
