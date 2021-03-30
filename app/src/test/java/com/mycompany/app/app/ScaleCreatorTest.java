package com.mycompany.app.app;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ScaleCreatorTest {

	@Test
	public void testScaleCreation() {
		ScaleCreator scaleCreator = new ScaleCreator();
		Note rootNote = new Note("DO");
		Interval tone = new Interval(1, 0);
		Interval half = new Interval(0, 1);
		Interval[] majorScaleInterval = { tone, tone, half, tone, tone, tone, half };
		Scale majorScale = new Scale(Arrays.asList(majorScaleInterval));
		Note[] DoMajorScale = { new Note("DO"), new Note("RE"), new Note("MI"), new Note("FA"), new Note("SOL"),
				new Note("LA"), new Note("SI"), new Note("DO") };
		assertEquals("The scale should be composed by the same elements", Arrays.asList(DoMajorScale), scaleCreator.createScale(rootNote, majorScale));
		Interval[] minorScaleInterval = { tone, half, tone, tone, half, tone, tone };
		Scale minorScale = new Scale(Arrays.asList(minorScaleInterval));
		Note[] DoMinorScale = { new Note("DO"), new Note("RE"), new Note("RE#"), new Note("FA"), new Note("SOL"),
				new Note("SOL#"), new Note("LA#"), new Note("DO") };
		assertEquals("The scale should be composed by the same elements", Arrays.asList(DoMinorScale), scaleCreator.createScale(rootNote, minorScale));
	}

}
