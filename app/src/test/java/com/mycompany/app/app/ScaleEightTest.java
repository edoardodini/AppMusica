package com.mycompany.app.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ScaleEightTest {

	@Test
	public void testScaleEightNoIntervals() {
		List<Interval> wrongScaleIntervals = new ArrayList<Interval>(Arrays.asList());
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new ScaleEight(wrongScaleIntervals));
		assertEquals("The argument should contain 7 intervals", exception.getMessage());
	}
	
	@Test
	public void testScaleEightNotEnoughIntervals() {
		Interval half = new Interval(0,1);
		Interval tone = new Interval(1,0);
		List<Interval> wrongScaleIntervals2 = new ArrayList<Interval>(Arrays.asList(tone, half));
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new ScaleEight(wrongScaleIntervals2));
		assertEquals("The argument should contain 7 intervals", exception.getMessage());
	}
	
	@Test
	public void testScaleEightEnoughIntervalsButWrongKind() {
		Interval half = new Interval(0,1);
		Interval tone = new Interval(1,0);
		List<Interval> wrongScaleIntervals7 = new ArrayList<Interval>(Arrays.asList(tone, half, tone, tone, tone, tone, tone));
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new ScaleEight(wrongScaleIntervals7));
		assertEquals("The argument is not an eight scale", exception.getMessage());
	}
	
	@Test
	public void testScaleEightEnoughIntervalsButWrongPosition() {
		Interval half = new Interval(0,1);
		Interval tone = new Interval(1,0);
		List<Interval> wrongScaleIntervals7 = new ArrayList<Interval>(Arrays.asList(half, half, tone, tone, tone, tone, tone));
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new ScaleEight(wrongScaleIntervals7));
		assertEquals("The argument is not an eight scale", exception.getMessage());
	}
	
	@Test
	public void testScaleEightConstructorAndGet() {
		Interval half = new Interval(0,1);
		Interval tone = new Interval(1,0);
		List<Interval> majorScaleIntervals = new ArrayList<Interval>(Arrays.asList(tone,tone, half, tone, tone, tone, half));
		ScaleEight majorScale = new ScaleEight(majorScaleIntervals);
		assertEquals("The scale should be the same", majorScale.getIntervals(), majorScaleIntervals);
	}

}
