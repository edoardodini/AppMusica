package com.mycompany.app.app.chords;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;
import com.mycompany.app.app.Scale;

public class SeventhChord implements AbstractAddableChord,  AbstractSuspendableChord,  
AbstractChord,  AbstractInvertableChord{
	private static final Logger LOGGER = LogManager.getLogger(SeventhChord.class);
	private ArrayList<Interval> thirds = new ArrayList<Interval>();
	private Scale internalScale;

	public SeventhChord(boolean isFirstThirdMajor, boolean isSecondThirdMajor, boolean isThirdThirdMajor) {
		boolean[] thirds= {isFirstThirdMajor,isSecondThirdMajor,isThirdThirdMajor};
		for(int i=0; i<thirds.length;i++)
		if (thirds[i]) {
			this.thirds.add(new Interval(2,0));
		}else {
			this.thirds.add(new Interval(1,1));
		}
		internalScale=new Scale(this.thirds);
		LOGGER.info("created a seventh chord");
	}

	@Override
	public List<Interval> getIntervals() {
		LOGGER.debug("returned the seventh chord intervals");
		return thirds;
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		LOGGER.debug("returned the seventh chord notes");
		return internalScale.getNotes(rootNote);
	}

}