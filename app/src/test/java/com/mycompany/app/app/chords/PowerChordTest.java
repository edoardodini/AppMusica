package com.mycompany.app.app.chords;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;

public class PowerChordTest {
	Note rootNoteDO = new Note("DO");
	Interval correctFifth = new Interval(3,1);
	List<Interval> powerChordIntervals = new ArrayList<Interval>(Arrays.asList(correctFifth));
	List<Note> powerChordNoteDO = new ArrayList<Note>(Arrays.asList(new Note("DO"), new Note("SOL")));
	
	Note rootNoteSOL = new Note("SOL");
	Note rootNoteSIb = new Note("SIb");
	List<Note> powerChordNoteSOL = new ArrayList<Note>(Arrays.asList(new Note("SOL"), new Note("RE")));
	List<Note> powerChordNoteSIb = new ArrayList<Note>(Arrays.asList(new Note("SIb"), new Note("MI#")));
	
	@Test
	public void testGetIntervals() {
		PowerChord powerChord = new PowerChord();
		assertEquals("the intervals should be correct", powerChordIntervals, powerChord.getIntervals());
	}
	
	@Test
	public void testPowerChordBase() {
		PowerChord powerChord = new PowerChord();
		assertEquals("the intervals should be correct", powerChordNoteDO, powerChord.getNotes(rootNoteDO));
	}
	
	@Test
	public void testPowerChordFull() {
		PowerChord powerChord = new PowerChord();
		assertEquals("the intervals should be correct", powerChordNoteSOL, powerChord.getNotes(rootNoteSOL));
		assertEquals("the intervals should be correct", powerChordNoteSIb, powerChord.getNotes(rootNoteSIb));
	}

}
