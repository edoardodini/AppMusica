package com.mycompany.app.app.chords;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.IntervalCreator;
import com.mycompany.app.app.Note;

public class InvertedChord extends AbstractChordModifier implements AbstractChord  {
	private Logger LOGGER = LogManager.getLogger(IntervalCreator.class);
	private int inversion;
	
	public InvertedChord(int inversionKind, AbstractInvertableChord abstractChord) {
		if(inversionKind>0) {
			if(inversionKind<=abstractChord.getIntervals().size()) {
				inversion = inversionKind; 
				chordToDecorate = abstractChord;
				LOGGER.info("created an inverted chord with an inversion of %d", inversion);
			}else {
				throw new IllegalArgumentException("the inversion kind should be minor than the lenght of the chord");
			}
		}else {
			throw new IllegalArgumentException("the inversion kind should be major than zero");
		}
	}
	
	@Override
	public List<Interval> getIntervals() {
		return chordToDecorate.getIntervals();
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		List<Note> chord = new ArrayList<Note>();
		List<Note> notesToDecorate = chordToDecorate.getNotes(rootNote);
		for (int i = 0; i<notesToDecorate.size();i++) {
			chord.add(notesToDecorate.get((i+inversion)%notesToDecorate.size()));
		}
		return chord;
	}

	public int getInversion() {
		return inversion;
	}

}
