package com.mycompany.app.app.chords;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;

public class SeventhChordTest {

	@Test
	public void testConstructorAndGetters() {
		SeventhChord baseSeventhChord = new SeventhChord(true,false,true);
		ArrayList<Interval> seventhMajorInterval = new ArrayList<Interval>(Arrays.asList(new Interval(2,0),new Interval(1,1),new Interval(2,0)));
		assertEquals("the created triade is major, its intervals should be the ones of a major triade", seventhMajorInterval, baseSeventhChord.getIntervals());
	}
	
	@Test
	public void testGetNotes() {
		SeventhChord diminishedSeventhDO = new SeventhChord(false,false,false);
		SeventhChord halfDiminishedSeventhDO = new SeventhChord(false,false,true);
		SeventhChord minorSeventhDO = new SeventhChord(false,true,false);
		SeventhChord minorMajorSeventhDO = new SeventhChord(false,true,true);
		SeventhChord dominantSeventhDO = new SeventhChord(true,false,false);
		SeventhChord majorSeventhDO = new SeventhChord(true,false,true);
		SeventhChord augmentedSeventhDO = new SeventhChord(true,true,false);
		SeventhChord augmentedAugmentedSeventhDO = new SeventhChord(true,true,true);
		
		ArrayList<Note> diminishedSeventhDONotes = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MIb"),new Note("SOLb"),new Note("LA")));
		ArrayList<Note> halfDiminishedSeventhDONotes = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MIb"),new Note("SOLb"),new Note("SIb")));
		ArrayList<Note> minorSeventhDONotes = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MIb"),new Note("SOL"),new Note("SIb")));
		ArrayList<Note> minorMajorSeventhDONotes = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MIb"),new Note("SOL"),new Note("SI")));
		ArrayList<Note> dominantSeventhDONotes = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MI"),new Note("SOL"),new Note("SIb")));
		ArrayList<Note> majorSeventhDONotes = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MI"),new Note("SOL"),new Note("SI")));
		ArrayList<Note> augmentedSeventhDONotes = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MI"),new Note("SOL#"),new Note("SI")));
		ArrayList<Note> augmentedAugmentedSeventhDONotes = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MI"),new Note("SOL#"),new Note("DO")));
		
		assertEquals("the notes of the generated chord should be the same", diminishedSeventhDONotes, diminishedSeventhDO.getNotes(new Note("DO")));
		assertEquals("the notes of the generated chord should be the same", halfDiminishedSeventhDONotes, halfDiminishedSeventhDO.getNotes(new Note("DO")));
		assertEquals("the notes of the generated chord should be the same", minorSeventhDONotes, minorSeventhDO.getNotes(new Note("DO")));
		assertEquals("the notes of the generated chord should be the same", minorMajorSeventhDONotes, minorMajorSeventhDO.getNotes(new Note("DO")));
		assertEquals("the notes of the generated chord should be the same", dominantSeventhDONotes, dominantSeventhDO.getNotes(new Note("DO")));
		assertEquals("the notes of the generated chord should be the same", majorSeventhDONotes, majorSeventhDO.getNotes(new Note("DO")));
		assertEquals("the notes of the generated chord should be the same", augmentedSeventhDONotes, augmentedSeventhDO.getNotes(new Note("DO")));
		assertEquals("the notes of the generated chord should be the same", augmentedAugmentedSeventhDONotes, augmentedAugmentedSeventhDO.getNotes(new Note("DO")));
	}
	

}
