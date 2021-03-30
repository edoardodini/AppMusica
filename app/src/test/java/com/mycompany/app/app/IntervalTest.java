package com.mycompany.app.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntervalTest {

	@Test
	public void testIntervalConstructor() {
		int halfTone = 1;
		int zeroHalfTones = 0;
		int oneTone = 1;
		int zeroTones = 0;
		Interval trialIntervalHalfTone = new Interval(zeroTones, halfTone);
		assertEquals("The number of half tones should be equal", halfTone, trialIntervalHalfTone.getIntervalHalfTones());
		assertEquals("The number of tones should be equal", zeroTones, trialIntervalHalfTone.getIntervalTones());
		Interval trialIntervalOneTone = new Interval(oneTone, zeroHalfTones);
		assertEquals("The number of half tones should be equal", zeroHalfTones, trialIntervalOneTone.getIntervalHalfTones());
		assertEquals("The number of tones should be equal", oneTone, trialIntervalOneTone.getIntervalTones());
	}

	@Test
	public void testNoteConstructorInvalidArgument() {
		int wrongTones = -1;
		int wrongHalfTones = -3;
		int correctTones = 0;
		int correctHalfTones = 1;
		IllegalArgumentException exceptionOne = assertThrows(IllegalArgumentException.class,
				() -> new Interval(wrongTones, correctHalfTones));
		assertEquals("The argument is not an interval", exceptionOne.getMessage());
		IllegalArgumentException exceptionTwo = assertThrows(IllegalArgumentException.class,
				() -> new Interval(correctTones, wrongHalfTones));
		assertEquals("The argument is not an interval", exceptionTwo.getMessage());
		IllegalArgumentException exceptionThree = assertThrows(IllegalArgumentException.class,
				() -> new Interval(wrongTones, wrongHalfTones));
		assertEquals("The argument is not an interval", exceptionThree.getMessage());
	}

}
