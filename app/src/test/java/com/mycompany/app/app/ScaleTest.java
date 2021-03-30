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
		assertEquals("The scale should be the same", majorScale.getIntervals(), majorScaleIntervals);
	}

}
