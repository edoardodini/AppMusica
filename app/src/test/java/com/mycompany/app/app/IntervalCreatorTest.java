package com.mycompany.app.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntervalCreatorTest {

	@Test
	public void testNullInterval() {
		IntervalCreator intervalManager = new IntervalCreator();
		// NoteComparator noteComp = new NoteComparator();
		int zeroTones = 0;
		int zeroHalfTones = 0;
		Interval nullInterval = new Interval(zeroTones, zeroHalfTones);
		Note noteOfTest = new Note("DO");
		assertEquals("The note should be the same", noteOfTest,
				intervalManager.getIntervalUp(noteOfTest, nullInterval));
	}

	@Test
	public void testRealInterval() {
		IntervalCreator intervalManager = new IntervalCreator();
		int zeroHalfTones = 0;
		int oneHalfTone = 1;
		int zeroTones = 0;
		int oneTone = 1;
		Interval oneHalfToneInterval = new Interval(zeroTones, oneHalfTone);
		Interval oneToneInterval = new Interval(oneTone, zeroHalfTones);
		Note noteOfTest = new Note("MI");
		Note noteHalfToneUp = new Note("FA");
		Note noteOneAndHalfToneUp = new Note("SOL");

		noteOfTest = intervalManager.getIntervalUp(noteOfTest, oneHalfToneInterval);
		assertEquals("The note should be the same", noteOfTest, noteHalfToneUp);

		noteOfTest = intervalManager.getIntervalUp(noteOfTest, oneToneInterval);
		assertEquals("The note should be the same", noteOfTest, noteOneAndHalfToneUp);
	}

	@Test
	public void testRoundInterval() {
		IntervalCreator intervalManager = new IntervalCreator();
		int oneHalfTone = 1;
		int zeroTones = 0;
		Interval oneHalfToneInterval = new Interval(zeroTones, oneHalfTone);
		Note noteOfTest = new Note("SI");
		Note noteHalfToneUp = new Note("SI#");
		Note noteHalfToneUpTwo = new Note("DO");
		noteOfTest = intervalManager.getIntervalUp(noteOfTest, oneHalfToneInterval);
		assertEquals("The note should be the same", noteOfTest, noteHalfToneUp);
		assertEquals("The note should be the same", noteOfTest, noteHalfToneUpTwo);
	}

	@Test
	public void testBigInterval() {
		IntervalCreator intervalManager = new IntervalCreator();
		int twelveHalfTones = 12;
		int zeroTones = 0;
		Interval oneOctave = new Interval(zeroTones, twelveHalfTones);
		Note noteOfTest = new Note("RE");
		Note noteOneOctaveUp = new Note("RE");
		noteOfTest = intervalManager.getIntervalUp(noteOfTest, oneOctave);
		assertEquals("The note should be the same", noteOfTest, noteOneOctaveUp);
	}
}