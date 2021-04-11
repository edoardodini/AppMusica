package com.mycompany.app.app;

import java.util.List;

public interface Sequence {
	
	public List<Interval> getIntervals();
	
	public List<Note> getNotes();
	
	public Note getRootNote();
}
