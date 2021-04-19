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
		assertEquals("The number of half tones should be equal", halfTone,
				trialIntervalHalfTone.getIntervalHalfTones());
		assertEquals("The number of tones should be equal", zeroTones, trialIntervalHalfTone.getIntervalTones());
		Interval trialIntervalOneTone = new Interval(oneTone, zeroHalfTones);
		assertEquals("The number of half tones should be equal", zeroHalfTones,
				trialIntervalOneTone.getIntervalHalfTones());
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

	@Test
	public void testEqualsSameObject() {
		Interval x = new Interval(1, 1);
		assertEquals("the object are, and should be, equals", x, x);
	}

	@Test
	public void testEqualsNotIntervalObject() {
		Interval x = new Interval(1, 1);
		String notInterval = "not an interval";
		assertNotEquals("the object are not of the same kind, so they should be different", x, notInterval);
	}
	
	@Test
	public void testNotEqualsIntervals() {
		Interval twoTone = new Interval(2, 0);
		Interval oneToneOneHalf = new Interval(1, 1);
		assertNotEquals("the intervals are different", twoTone, oneToneOneHalf);
	}

	@Test
	public void testEqualsIntervals() {
		Interval twoTone = new Interval(2, 0);
		Interval anotherTwoTone = new Interval(2, 0);
		Interval oneToneTwoHalf = new Interval(1, 2);
		Interval fourHalf = new Interval(0, 4);
		assertEquals("the object are composed of the same intervals and so equals", twoTone, anotherTwoTone);
		assertEquals("the object are composed by different parts but the intervals are equal", twoTone, oneToneTwoHalf);
		assertEquals("the object are composed by different parts but the intervals are equal", fourHalf,
				oneToneTwoHalf);
	}

	@Test
	public void testHashCode() {
		int twoTones = 2;
		int zeroHalfs = 0;
		Interval twoTone = new Interval(2, 0);
		Interval anotherTwoTone = new Interval(2, 0);
		Interval oneToneTwoHalf = new Interval(1, 2);
		Interval fiveHalf = new Interval(5, 5);
		assertEquals("the result of hashCode should be as the procedure states",
				new Interval(twoTones, zeroHalfs).hashCode(), 31 * 1 + zeroHalfs + 2 * twoTones);
		assertEquals("the object are the same so hash code should be equals", twoTone.hashCode(), twoTone.hashCode());
		assertEquals("the object are the same so hash code should be equals", twoTone.hashCode(), twoTone.hashCode());
		assertEquals("the object are composed of the same intervals and so has code should be equals",
				twoTone.hashCode(), anotherTwoTone.hashCode());
		assertEquals(
				"the object are composed by different parts but the intervals are equal so has code should be equals",
				twoTone.hashCode(), oneToneTwoHalf.hashCode());
		assertNotEquals(
				"the object are composed by different parts and the intervals are not equal so has code should not be equals",
				fiveHalf.hashCode(), oneToneTwoHalf.hashCode());
	}

}
