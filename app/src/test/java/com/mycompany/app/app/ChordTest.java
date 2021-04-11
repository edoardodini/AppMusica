package com.mycompany.app.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ChordTest {

	@Test
	public void testBasicChord() {
		Interval majorThird = new Interval(2,0);
		Interval minorThird = new Interval(1,1);
		List<Interval> majorChordIntervals = new ArrayList<Interval>(Arrays.asList(majorThird,minorThird));
		Chord majorChord = new Chord(majorChordIntervals);
		assertEquals("The chords should be the same", majorChord.getIntervals(), majorChordIntervals);
	}
	@Test
	public void testChordWrongShape() {
		Interval oneTone = new Interval(1,0);
		Interval majorThird = new Interval(2,0);
		List<Interval> wrongChordIntervals = new ArrayList<Interval>(Arrays.asList(oneTone,majorThird));
		IllegalArgumentException exceptionOne = assertThrows(IllegalArgumentException.class,
				() -> new Chord(wrongChordIntervals));
		assertEquals("The argument is not a valid chord, wrong intervals", exceptionOne.getMessage());
	}
	@Test
	public void testChordWrongSize() {
		Interval majorThird = new Interval(2,0);
		List<Interval> wrongChordIntervals = new ArrayList<Interval>(Arrays.asList(majorThird));
		IllegalArgumentException exceptionOne = assertThrows(IllegalArgumentException.class,
				() -> new Chord(wrongChordIntervals));
		assertEquals("The argument is not a valid chord, not enough intervals", exceptionOne.getMessage());
	}
	@Test
	public void testChordWrongIntervalNotFirstPosition() {
		Interval oneTone = new Interval(1,0);
		Interval majorThird = new Interval(2,0);
		List<Interval> wrongChordIntervals = new ArrayList<Interval>(Arrays.asList(majorThird,oneTone));
		IllegalArgumentException exceptionOne = assertThrows(IllegalArgumentException.class,
				() -> new Chord(wrongChordIntervals));
		assertEquals("The argument is not a valid chord, wrong intervals", exceptionOne.getMessage());
	}
}
