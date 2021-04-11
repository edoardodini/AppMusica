package com.mycompany.app.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ScaleEightTest {
	Note rootNoteDO = new Note("DO");
	Interval half = new Interval(0,1);
	Interval tone = new Interval(1,0);
	

	@Test
	public void testScaleEightNoIntervals() {
		List<Interval> wrongScaleIntervals = new ArrayList<Interval>(Arrays.asList());
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new ScaleEight(rootNoteDO, wrongScaleIntervals));
		assertEquals("The argument should contain 7 intervals", exception.getMessage());
	}
	
	@Test
	public void testScaleEightNotEnoughIntervals() {
		List<Interval> wrongScaleIntervals2 = new ArrayList<Interval>(Arrays.asList(tone, half));
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new ScaleEight(rootNoteDO, wrongScaleIntervals2));
		assertEquals("The argument should contain 7 intervals", exception.getMessage());
	}
	
	@Test
	public void testScaleEightEnoughIntervalsButWrongKind() {
		List<Interval> wrongScaleIntervals6Tones1Half = new ArrayList<Interval>(Arrays.asList(tone, half, tone, tone, tone, tone, tone));
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new ScaleEight(rootNoteDO, wrongScaleIntervals6Tones1Half));
		assertEquals("The argument is not an eight scale", exception.getMessage());
		
		Interval toneAndHalf = new Interval(1,1);
		List<Interval> wrongScaleIntervalsOneToneAndHalfIntervalPresent = new ArrayList<Interval>(Arrays.asList(tone, half, toneAndHalf, tone, tone, tone, tone));
		exception = assertThrows(IllegalArgumentException.class,
				() -> new ScaleEight(rootNoteDO, wrongScaleIntervalsOneToneAndHalfIntervalPresent));
		assertEquals("The argument is not an eight scale", exception.getMessage());
		
		Interval nullTone = new Interval(0,0);
		List<Interval> wrongScaleIntervalsOneNullInterval = new ArrayList<Interval>(Arrays.asList(tone, half, nullTone, tone, tone, tone, tone));
		exception = assertThrows(IllegalArgumentException.class,
				() -> new ScaleEight(rootNoteDO, wrongScaleIntervalsOneNullInterval));
		assertEquals("The argument is not an eight scale", exception.getMessage());
	}
	
	@Test
	public void testScaleEightEnoughIntervalsButWrongPosition() {
		List<Interval> wrongScaleIntervals7 = new ArrayList<Interval>(Arrays.asList(half, half, tone, tone, tone, tone, tone));
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new ScaleEight(rootNoteDO, wrongScaleIntervals7));
		assertEquals("The argument is not an eight scale", exception.getMessage());
	}
	
	@Test
	public void testScaleEightConstructorAndGet() {
		List<Interval> majorScaleIntervals = new ArrayList<Interval>(Arrays.asList(tone,tone, half, tone, tone, tone, half));
		ScaleEight majorScale = new ScaleEight(rootNoteDO, majorScaleIntervals);
		assertEquals("The scale should be the same", majorScale.getIntervals(), majorScaleIntervals);
		
		List<Interval> minorScaleIntervals = new ArrayList<Interval>(Arrays.asList(tone, half, tone, tone, half, tone, tone));
		ScaleEight minorScale = new ScaleEight(rootNoteDO, minorScaleIntervals);
		assertEquals("The scale should be the same", minorScale.getIntervals(), minorScaleIntervals);
	}
	
	
	
	
	
	@Test
	public void testScaleEightCreationEasy() {
		Interval[] majorScaleInterval = { tone, tone, half, tone, tone, tone, half };
		ScaleEight majorScale = new ScaleEight(rootNoteDO, Arrays.asList(majorScaleInterval));
		Note[] DoMajorScale = { new Note("DO"), new Note("RE"), new Note("MI"), new Note("FA"), new Note("SOL"),
				new Note("LA"), new Note("SI"), new Note("DO") };
		List<Note> majorScaleOfDO = majorScale.getNotes();
		assertEquals("The scale should be composed by the same elements", Arrays.asList(DoMajorScale), majorScaleOfDO);
		for (int i=0;i<majorScaleOfDO.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(DoMajorScale).get(i).getNote(), majorScaleOfDO.get(i).getNote());
		}
	}
	
	@Test
	public void testScaleEightCreationLessTrivial() {
		Note rootNoteFaDiesis = new Note("FA#");
		Interval[] majorScaleInterval = { tone, tone, half, tone, tone, tone, half };
		Interval[] minorScaleInterval = { tone, half, tone, tone, half, tone, tone };
		ScaleEight FADiesisMajorScale = new ScaleEight(rootNoteFaDiesis, Arrays.asList(majorScaleInterval));
		ScaleEight DOminorScale = new ScaleEight(rootNoteDO, Arrays.asList(minorScaleInterval));
		Note[] DoMinorScale = { new Note("DO"), new Note("RE"), new Note("MIb"), new Note("FA"), new Note("SOL"),
				new Note("LAb"), new Note("SIb"), new Note("DO") };
		List<Note> minorScaleOfDO = DOminorScale.getNotes();
		assertEquals("The scale should be composed by the same elements", Arrays.asList(DoMinorScale), minorScaleOfDO);
		for (int i=0;i<minorScaleOfDO.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(DoMinorScale).get(i).getNote(), minorScaleOfDO.get(i).getNote());
		}
		
		Note[] FaDiesisMajorScale = { new Note("FA#"), new Note("SOL#"), new Note("LA#"), new Note("SI"), new Note("DO#"), 
				new Note("RE#"), new Note("MI#"), new Note("FA#") };
		List<Note> majorScaleOfFaDiesis = FADiesisMajorScale.getNotes();
		assertEquals("The scale should be composed by the same elements", Arrays.asList(FaDiesisMajorScale), majorScaleOfFaDiesis);
		for (int i=0;i<majorScaleOfFaDiesis.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(FaDiesisMajorScale).get(i).getNote(), majorScaleOfFaDiesis.get(i).getNote());
		}
	}
		
		
	@Test
	public void testScaleEightCreationStartingWithB() {
		Note rootNoteLAb = new Note("LAb");
		Interval[] majorScaleInterval = { tone, tone, half, tone, tone, tone, half };
		Interval[] minorScaleInterval = { tone, half, tone, tone, half, tone, tone };
		ScaleEight majorScaleLAb = new ScaleEight(rootNoteLAb, Arrays.asList(majorScaleInterval));
		ScaleEight minorScaleLAb = new ScaleEight(rootNoteLAb, Arrays.asList(minorScaleInterval));
		Note[] LAbMajorScale = { new Note("LAb"), new Note("SIb"), new Note("DO"), 
				new Note("REb"), new Note("MIb"), new Note("FA"), new Note("SOL"), new Note("LAb")};
		List<Note> majorScaleOfLAb = majorScaleLAb.getNotes();
		assertEquals("The scale should be composed by the same elements", Arrays.asList(LAbMajorScale), majorScaleOfLAb);
		for (int i=0;i<majorScaleOfLAb.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(LAbMajorScale).get(i).getNote(), majorScaleOfLAb.get(i).getNote());
		}
		
		Note[] LAbMinorScale = { new Note("LAb"), new Note("SIb"), new Note("DOb"), 
				new Note("REb"), new Note("MIb"), new Note("FAb"), new Note("SOLb"), new Note("LAb")};
		List<Note> minorScaleOfLAb = minorScaleLAb.getNotes();
		assertEquals("The scale should be composed by the same elements", Arrays.asList(LAbMinorScale), minorScaleOfLAb);
		for (int i=0;i<minorScaleOfLAb.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(LAbMinorScale).get(i).getNote(), minorScaleOfLAb.get(i).getNote());
		}
	}
	
	@Test
	public void testScaleEightCreationWithRootNoteChange() {
		Note rootNoteFAb = new Note("FAb");
		Interval[] locrianScaleInterval = {half, tone, tone, half, tone, tone, tone};
		ScaleEight locrianScaleFAb = new ScaleEight(rootNoteFAb, Arrays.asList(locrianScaleInterval));
		Note[] FAbLocrianScale = {new Note("MI"), new Note("FA"), new Note("SOL"),
				new Note("LA"), new Note("SIb"), new Note("DO"), new Note("RE"), new Note("MI")};
		List<Note> locrianScaleOfFAb = locrianScaleFAb.getNotes();
		assertEquals("The scale should be composed by the same elements", Arrays.asList(FAbLocrianScale), locrianScaleOfFAb);
		for (int i=0;i<locrianScaleOfFAb.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(FAbLocrianScale).get(i).getNote(), locrianScaleOfFAb.get(i).getNote());
		}
	}
	
	@Test
	public void testScaleEightCreationWithRootNoteChangeInTheMiddle() {
		Note rootNoteSOLdiesis = new Note("SOL#");
		Interval[] majorScaleInterval = {tone, tone, half, tone, tone, tone, half};
		ScaleEight majorScaleSOLdiesis = new ScaleEight(rootNoteSOLdiesis, Arrays.asList(majorScaleInterval));
		Note[] SOLdiesisMajorScale = {new Note("LAb"), new Note("SIb"), new Note("DO"), 
				new Note("REb"), new Note("MIb"), new Note("FA"), new Note("SOL"), new Note("LAb")};
		List<Note> majorScaleOfSOLdiesis = majorScaleSOLdiesis.getNotes();
		assertEquals("The scale should be composed by the same elements", Arrays.asList(SOLdiesisMajorScale), majorScaleOfSOLdiesis);
		for (int i=0;i<majorScaleOfSOLdiesis.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(SOLdiesisMajorScale).get(i).getNote(), majorScaleOfSOLdiesis.get(i).getNote());
		}
	}
	
	@Test
	public void testScaleEightCreationFor100Coverage() {
		Note rootNoteREb = new Note("REb");
		Note rootNoteMIb = new Note("MIb");
		Note rootNoteSOLb = new Note("SOLb");
		Note rootNoteSIb = new Note("SIb");
		Note rootNoteDOb = new Note("DOb");
		Interval[] dorianScaleInterval = {tone, half, tone, tone, tone, half, tone};
		ScaleEight dorianScaleREb = new ScaleEight(rootNoteREb, Arrays.asList(dorianScaleInterval));
		ScaleEight dorianScaleMIb = new ScaleEight(rootNoteMIb, Arrays.asList(dorianScaleInterval));
		
		Interval[] locrianScaleInterval = {half, tone, tone, half, tone, tone, tone};
		ScaleEight locrianScaleSOLb = new ScaleEight(rootNoteSOLb, Arrays.asList(locrianScaleInterval));
		ScaleEight locrianScaleSIb = new ScaleEight(rootNoteSIb, Arrays.asList(locrianScaleInterval));
		ScaleEight locrianScaleDOb = new ScaleEight(rootNoteDOb, Arrays.asList(locrianScaleInterval));
		
		Note[] SOLbLocrianScale = {new Note("FA#"), new Note("SOL"), new Note("LA"), 
				new Note("SI"), new Note("DO"), new Note("RE"), new Note("MI"), new Note("FA#")};
		List<Note> locrianScaleOfSOLb = locrianScaleSOLb.getNotes();
		assertEquals("The scale should be composed by the same elements", Arrays.asList(SOLbLocrianScale), locrianScaleOfSOLb);
		for (int i=0;i<locrianScaleOfSOLb.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(SOLbLocrianScale).get(i).getNote(), locrianScaleOfSOLb.get(i).getNote());
		}
		
		Note[] SIbLocrianScale = {new Note("SIb"), new Note("DOb"), new Note("REb"), new Note("MIb"), new Note("FAb"), 
				new Note("SOLb"), new Note("LAb"), new Note("SIb")};
		List<Note> locrianScaleOfSIb = locrianScaleSIb.getNotes();
		assertEquals("The scale should be composed by the same elements", Arrays.asList(SIbLocrianScale), locrianScaleOfSIb);
		for (int i=0;i<locrianScaleOfSIb.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(SIbLocrianScale).get(i).getNote(), locrianScaleOfSIb.get(i).getNote());
		}
		
		Note[] DObLocrianScale = {new Note("SI"), new Note("DO"), new Note("RE"), new Note("MI"), new Note("FA"), 
				new Note("SOL"), new Note("LA"), new Note("SI")};
		List<Note> locrianScaleOfDOb = locrianScaleDOb.getNotes();
		assertEquals("The scale should be composed by the same elements", Arrays.asList(DObLocrianScale), locrianScaleOfDOb);
		for (int i=0;i<locrianScaleOfDOb.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(DObLocrianScale).get(i).getNote(), locrianScaleOfDOb.get(i).getNote());
		}
		
		Note[] REbDorianScale = {new Note("REb"), new Note("MIb"), new Note("FAb"), 
				new Note("SOLb"), new Note("LAb"), new Note("SIb"), new Note("DOb"), new Note("REb")};
		List<Note> dorianScaleOfREb = dorianScaleREb.getNotes();
		assertEquals("The scale should be composed by the same elements", Arrays.asList(REbDorianScale), dorianScaleOfREb);
		for (int i=0;i<dorianScaleOfREb.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(REbDorianScale).get(i).getNote(), dorianScaleOfREb.get(i).getNote());
		}
		
		Note[] MIbDorianScale = {new Note("MIb"), new Note("FA"), new Note("SOLb"), new Note("LAb"), 
				new Note("SIb"), new Note("DO"), new Note("REb"), new Note("MIb")};
		List<Note> dorianScaleOfMib = dorianScaleMIb.getNotes();
		assertEquals("The scale should be composed by the same elements", Arrays.asList(MIbDorianScale), dorianScaleOfMib);
		for (int i=0;i<dorianScaleOfMib.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(MIbDorianScale).get(i).getNote(), dorianScaleOfMib.get(i).getNote());
		}		
	}
	
	

}
