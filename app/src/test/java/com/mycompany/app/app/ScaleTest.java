package com.mycompany.app.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ScaleTest {

	@Test
	public void testScaleConstructorAndGet() {
		Interval half = new Interval(0,1);
		Interval tone = new Interval(1,0);
		List<Interval> majorScaleIntervals = new ArrayList<Interval>(Arrays.asList(tone,tone, half, tone, tone, tone, half));		
		Scale majorScale = new Scale(majorScaleIntervals);
		assertEquals("The intervals should be the same", majorScale.getIntervals(), majorScaleIntervals);
	}
	
	Interval tone = new Interval(1, 0);
	Interval half = new Interval(0, 1);
	
	@Test
	public void testScaleCreation() {
		Note rootNote = new Note("DO");
		Interval[] majorScaleInterval = { tone, tone, half, tone, tone, tone, half };
		Scale majorScale = new Scale(Arrays.asList(majorScaleInterval));
		Note[] DoMajorScale = { new Note("DO"), new Note("RE"), new Note("MI"), new Note("FA"), new Note("SOL"),
				new Note("LA"), new Note("SI"), new Note("DO") };
		assertEquals("The scale should be composed by the same elements", Arrays.asList(DoMajorScale), majorScale.getNotes(rootNote));
		
		Interval[] minorScaleInterval = { tone, half, tone, tone, half, tone, tone };
		Scale minorScale = new Scale(Arrays.asList(minorScaleInterval));
		Note[] DoMinorScale = { new Note("DO"), new Note("RE"), new Note("RE#"), new Note("FA"), new Note("SOL"),
				new Note("SOL#"), new Note("LA#"), new Note("DO") };
		assertEquals("The scale should be composed by the same elements", Arrays.asList(DoMinorScale), minorScale.getNotes(rootNote));
	}

}
