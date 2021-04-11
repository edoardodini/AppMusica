package com.mycompany.app.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ChordTest {
	Note rootNoteDO = new Note("DO");
	
	@Test
	public void testBasicChordGet() {
		Interval majorThird = new Interval(2,0);
		Interval minorThird = new Interval(1,1);
		List<Interval> majorChordIntervals = new ArrayList<Interval>(Arrays.asList(majorThird,minorThird));
		Chord majorChord = new Chord(rootNoteDO, majorChordIntervals);
		assertEquals("The chords should be the same", majorChord.getIntervals(), majorChordIntervals);
	}
	@Test
	public void testChordWrongShape() {
		Interval oneTone = new Interval(1,0);
		Interval majorThird = new Interval(2,0);
		List<Interval> wrongChordIntervals = new ArrayList<Interval>(Arrays.asList(oneTone,majorThird));
		IllegalArgumentException exceptionOne = assertThrows(IllegalArgumentException.class,
				() -> new Chord(rootNoteDO, wrongChordIntervals));
		assertEquals("The argument is not a valid chord, wrong intervals", exceptionOne.getMessage());
	}
	@Test
	public void testChordWrongSize() {
		Interval majorThird = new Interval(2,0);
		List<Interval> wrongChordIntervals = new ArrayList<Interval>(Arrays.asList(majorThird));
		IllegalArgumentException exceptionOne = assertThrows(IllegalArgumentException.class,
				() -> new Chord(rootNoteDO, wrongChordIntervals));
		assertEquals("The argument is not a valid chord, not enough intervals", exceptionOne.getMessage());
	}
	@Test
	public void testChordWrongIntervalNotFirstPosition() {
		Interval oneTone = new Interval(1,0);
		Interval majorThird = new Interval(2,0);
		List<Interval> wrongChordIntervals = new ArrayList<Interval>(Arrays.asList(majorThird,oneTone));
		IllegalArgumentException exceptionOne = assertThrows(IllegalArgumentException.class,
				() -> new Chord(rootNoteDO, wrongChordIntervals));
		assertEquals("The argument is not a valid chord, wrong intervals", exceptionOne.getMessage());
	}
	@Test
	public void testStrangeButCorrectChord() {
		Interval majorThird = new Interval(2,0);
		Interval minorThird = new Interval(1,1);
		Interval alsoMajorThird = new Interval(0,4);
		Interval alsoMinorThird = new Interval(0,3);
		Interval anotherMajorThird = new Interval(1,2);
		List<Interval> strangeChordIntervals = new ArrayList<Interval>(Arrays.asList(majorThird,minorThird,alsoMajorThird,alsoMinorThird,anotherMajorThird));
		Chord strangeChord = new Chord(rootNoteDO, strangeChordIntervals);
		assertEquals("The chords should be the same", strangeChord.getIntervals(), strangeChordIntervals);
	}
	
	@Test
	public void testBasicChordCreation() {
		Note rootNoteDO = new Note("DO");
		Note rootNoteSOL = new Note("SOL");
		Interval majorThird = new Interval(2,0);
		Interval minorThird = new Interval(1,1);
		Interval[] majorChordIntervals = {majorThird, minorThird};
		Chord majorChordDO = new Chord(rootNoteDO, Arrays.asList(majorChordIntervals));
		Chord majorChordSOL = new Chord(rootNoteSOL, Arrays.asList(majorChordIntervals));
		
		Note[] DOMajorChordNotes = {new Note("DO"), new Note("MI"), new Note("SOL")};
		List<Note> majorChordOfDO = majorChordDO.getNotes();
		assertEquals("The chord should be composed by the same elements", Arrays.asList(DOMajorChordNotes), majorChordOfDO);
		
		Note[] SOLMajorChordNotes = {new Note("SOL"), new Note("SI"), new Note("RE")};
		List<Note> majorChordOfSOL = majorChordSOL.getNotes();
		assertEquals("The chord should be composed by the same elements", Arrays.asList(SOLMajorChordNotes), majorChordOfSOL);
	}
}
