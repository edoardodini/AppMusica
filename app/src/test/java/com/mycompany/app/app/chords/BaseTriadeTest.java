package com.mycompany.app.app.chords;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;

public class BaseTriadeTest {

	@Test
	public void testConstructorAndGetters() {
		BaseTriade baseTriadeMajor = new BaseTriade(true);
		BaseTriade baseTriadeMinor = new BaseTriade(false);
		ArrayList<Interval> majorInterval = new ArrayList<Interval>(Arrays.asList(new Interval(2,0),new Interval(1,1)));
		ArrayList<Interval> minorInterval = new ArrayList<Interval>(Arrays.asList(new Interval(1,1),new Interval(2,0)));
		assertEquals("the created triade is major, its intervals should be the ones of a major triade", majorInterval, baseTriadeMajor.getIntervals());
		assertEquals("the created triade is minor, its intervals should be the ones of a major triade", minorInterval, baseTriadeMinor.getIntervals());
	}
	
	@Test
	public void testGetNotes() {
		BaseTriade baseTriadeMajor = new BaseTriade(true);
		BaseTriade baseTriadeMinor = new BaseTriade(false);
		ArrayList<Note> majorChordDO = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MI"),new Note("SOL")));
		ArrayList<Note> minorChordDO = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MIb"),new Note("SOL")));
		ArrayList<Note> majorChordMIb = new ArrayList<Note>(Arrays.asList(new Note("MIb"),new Note("SOL"),new Note("SIb")));
		ArrayList<Note> minorChordMIb = new ArrayList<Note>(Arrays.asList(new Note("MIb"),new Note("FA#"),new Note("SIb")));
		assertEquals("the notes of the generated chord should be the same", majorChordDO, baseTriadeMajor.getNotes(new Note("DO")));
		assertEquals("the notes of the generated chord should be the same", minorChordDO, baseTriadeMinor.getNotes(new Note("DO")));
		assertEquals("the notes of the generated chord should be the same", majorChordMIb, baseTriadeMajor.getNotes(new Note("MIb")));
		assertEquals("the notes of the generated chord should be the same", minorChordMIb, baseTriadeMinor.getNotes(new Note("MIb")));
	}

}
