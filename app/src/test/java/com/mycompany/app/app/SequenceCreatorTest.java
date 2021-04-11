package com.mycompany.app.app;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SequenceCreatorTest {
	SequenceCreator sequenceCreator = new SequenceCreator();
	Interval tone = new Interval(1, 0);
	Interval half = new Interval(0, 1);
	
	@Test
	public void testScaleCreation() {
		Note rootNote = new Note("DO");
		Interval[] majorScaleInterval = { tone, tone, half, tone, tone, tone, half };
		Scale majorScale = new Scale(Arrays.asList(majorScaleInterval));
		Note[] DoMajorScale = { new Note("DO"), new Note("RE"), new Note("MI"), new Note("FA"), new Note("SOL"),
				new Note("LA"), new Note("SI"), new Note("DO") };
		assertEquals("The scale should be composed by the same elements", Arrays.asList(DoMajorScale), sequenceCreator.createScale(rootNote, majorScale));
		Interval[] minorScaleInterval = { tone, half, tone, tone, half, tone, tone };
		Scale minorScale = new Scale(Arrays.asList(minorScaleInterval));
		Note[] DoMinorScale = { new Note("DO"), new Note("RE"), new Note("RE#"), new Note("FA"), new Note("SOL"),
				new Note("SOL#"), new Note("LA#"), new Note("DO") };
		assertEquals("The scale should be composed by the same elements", Arrays.asList(DoMinorScale), sequenceCreator.createScale(rootNote, minorScale));
	}
	
	@Test
	public void testScaleEightCreationEasy() {
		Note rootNoteDO = new Note("DO");
		Interval[] majorScaleInterval = { tone, tone, half, tone, tone, tone, half };
		ScaleEight majorScale = new ScaleEight(Arrays.asList(majorScaleInterval));
		Note[] DoMajorScale = { new Note("DO"), new Note("RE"), new Note("MI"), new Note("FA"), new Note("SOL"),
				new Note("LA"), new Note("SI"), new Note("DO") };
		List<Note> majorScaleOfDO = sequenceCreator.createScale(rootNoteDO, majorScale);
		assertEquals("The scale should be composed by the same elements", Arrays.asList(DoMajorScale), majorScaleOfDO);
		for (int i=0;i<majorScaleOfDO.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(DoMajorScale).get(i).getNote(), majorScaleOfDO.get(i).getNote());
		}
	}
	
	@Test
	public void testScaleEightCreationLessTrivial() {
		Note rootNoteDO = new Note("DO");
		Note rootNoteFaDiesis = new Note("FA#");
		Interval[] majorScaleInterval = { tone, tone, half, tone, tone, tone, half };
		Interval[] minorScaleInterval = { tone, half, tone, tone, half, tone, tone };
		ScaleEight majorScale = new ScaleEight(Arrays.asList(majorScaleInterval));
		ScaleEight minorScale = new ScaleEight(Arrays.asList(minorScaleInterval));
		Note[] DoMinorScale = { new Note("DO"), new Note("RE"), new Note("MIb"), new Note("FA"), new Note("SOL"),
				new Note("LAb"), new Note("SIb"), new Note("DO") };
		List<Note> minorScaleOfDO = sequenceCreator.createScale(rootNoteDO,minorScale);
		assertEquals("The scale should be composed by the same elements", Arrays.asList(DoMinorScale), minorScaleOfDO);
		for (int i=0;i<minorScaleOfDO.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(DoMinorScale).get(i).getNote(), minorScaleOfDO.get(i).getNote());
		}
		
		Note[] FaDiesisMajorScale = { new Note("FA#"), new Note("SOL#"), new Note("LA#"), new Note("SI"), new Note("DO#"), 
				new Note("RE#"), new Note("MI#"), new Note("FA#") };
		List<Note> majorScaleOfFaDiesis = sequenceCreator.createScale(rootNoteFaDiesis,majorScale);
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
		ScaleEight majorScale = new ScaleEight(Arrays.asList(majorScaleInterval));
		ScaleEight minorScale = new ScaleEight(Arrays.asList(minorScaleInterval));
		Note[] LAbMajorScale = { new Note("LAb"), new Note("SIb"), new Note("DO"), 
				new Note("REb"), new Note("MIb"), new Note("FA"), new Note("SOL"), new Note("LAb")};
		List<Note> majorScaleOfLAb = sequenceCreator.createScale(rootNoteLAb,majorScale);
		assertEquals("The scale should be composed by the same elements", Arrays.asList(LAbMajorScale), majorScaleOfLAb);
		for (int i=0;i<majorScaleOfLAb.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(LAbMajorScale).get(i).getNote(), majorScaleOfLAb.get(i).getNote());
		}
		
		Note[] LAbMinorScale = { new Note("LAb"), new Note("SIb"), new Note("DOb"), 
				new Note("REb"), new Note("MIb"), new Note("FAb"), new Note("SOLb"), new Note("LAb")};
		List<Note> minorScaleOfLAb = sequenceCreator.createScale(rootNoteLAb,minorScale);
		assertEquals("The scale should be composed by the same elements", Arrays.asList(LAbMinorScale), minorScaleOfLAb);
		for (int i=0;i<minorScaleOfLAb.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(LAbMinorScale).get(i).getNote(), minorScaleOfLAb.get(i).getNote());
		}
	}
	
	@Test
	public void testScaleEightCreationWithRootNoteChange() {
		Note rootNoteFAb = new Note("FAb");
		Interval[] locrianScaleInterval = {half, tone, tone, half, tone, tone, tone};
		ScaleEight locrianScale = new ScaleEight(Arrays.asList(locrianScaleInterval));
		Note[] FAbLocrianScale = {new Note("MI"), new Note("FA"), new Note("SOL"),
				new Note("LA"), new Note("SIb"), new Note("DO"), new Note("RE"), new Note("MI")};
		List<Note> locrianScaleOfFAb = sequenceCreator.createScale(rootNoteFAb,locrianScale);
		assertEquals("The scale should be composed by the same elements", Arrays.asList(FAbLocrianScale), locrianScaleOfFAb);
		for (int i=0;i<locrianScaleOfFAb.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(FAbLocrianScale).get(i).getNote(), locrianScaleOfFAb.get(i).getNote());
		}
	}
	
	@Test
	public void testScaleEightCreationWithRootNoteChangeInTheMiddle() {
		Note rootNoteSOLdiesis = new Note("SOL#");
		Interval[] majorScaleInterval = {tone, tone, half, tone, tone, tone, half};
		ScaleEight majorScale = new ScaleEight(Arrays.asList(majorScaleInterval));
		Note[] SOLdiesisMajorScale = {new Note("LAb"), new Note("SIb"), new Note("DO"), 
				new Note("REb"), new Note("MIb"), new Note("FA"), new Note("SOL"), new Note("LAb")};
		List<Note> majorScaleOfSOLdiesis = sequenceCreator.createScale(rootNoteSOLdiesis,majorScale);
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
		ScaleEight dorianScale = new ScaleEight(Arrays.asList(dorianScaleInterval));
		Interval[] locrianScaleInterval = {half, tone, tone, half, tone, tone, tone};
		ScaleEight locrianScale = new ScaleEight(Arrays.asList(locrianScaleInterval));
		
		Note[] SOLbLocrianScale = {new Note("FA#"), new Note("SOL"), new Note("LA"), 
				new Note("SI"), new Note("DO"), new Note("RE"), new Note("MI"), new Note("FA#")};
		List<Note> locrianScaleOfSOLb = sequenceCreator.createScale(rootNoteSOLb,locrianScale);
		assertEquals("The scale should be composed by the same elements", Arrays.asList(SOLbLocrianScale), locrianScaleOfSOLb);
		for (int i=0;i<locrianScaleOfSOLb.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(SOLbLocrianScale).get(i).getNote(), locrianScaleOfSOLb.get(i).getNote());
		}
		
		Note[] SIbLocrianScale = {new Note("SIb"), new Note("DOb"), new Note("REb"), new Note("MIb"), new Note("FAb"), 
				new Note("SOLb"), new Note("LAb"), new Note("SIb")};
		List<Note> locrianScaleOfSIb = sequenceCreator.createScale(rootNoteSIb,locrianScale);
		assertEquals("The scale should be composed by the same elements", Arrays.asList(SIbLocrianScale), locrianScaleOfSIb);
		for (int i=0;i<locrianScaleOfSIb.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(SIbLocrianScale).get(i).getNote(), locrianScaleOfSIb.get(i).getNote());
		}
		
		Note[] DObLocrianScale = {new Note("SI"), new Note("DO"), new Note("RE"), new Note("MI"), new Note("FA"), 
				new Note("SOL"), new Note("LA"), new Note("SI")};
		List<Note> locrianScaleOfDOb = sequenceCreator.createScale(rootNoteDOb,locrianScale);
		assertEquals("The scale should be composed by the same elements", Arrays.asList(DObLocrianScale), locrianScaleOfDOb);
		for (int i=0;i<locrianScaleOfDOb.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(DObLocrianScale).get(i).getNote(), locrianScaleOfDOb.get(i).getNote());
		}
		
		Note[] REbDorianScale = {new Note("REb"), new Note("MIb"), new Note("FAb"), 
				new Note("SOLb"), new Note("LAb"), new Note("SIb"), new Note("DOb"), new Note("REb")};
		List<Note> dorianScaleOfREb = sequenceCreator.createScale(rootNoteREb,dorianScale);
		assertEquals("The scale should be composed by the same elements", Arrays.asList(REbDorianScale), dorianScaleOfREb);
		for (int i=0;i<dorianScaleOfREb.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(REbDorianScale).get(i).getNote(), dorianScaleOfREb.get(i).getNote());
		}
		
		Note[] MIbDorianScale = {new Note("MIb"), new Note("FA"), new Note("SOLb"), new Note("LAb"), 
				new Note("SIb"), new Note("DO"), new Note("REb"), new Note("MIb")};
		List<Note> dorianScaleOfMib = sequenceCreator.createScale(rootNoteMIb,dorianScale);
		assertEquals("The scale should be composed by the same elements", Arrays.asList(MIbDorianScale), dorianScaleOfMib);
		for (int i=0;i<dorianScaleOfMib.size();i++) {
			assertEquals("The notes should be the same", Arrays.asList(MIbDorianScale).get(i).getNote(), dorianScaleOfMib.get(i).getNote());
		}		
	}
	
	@Test
	public void testBasicChordCreation() {
		Note rootNoteDO = new Note("DO");
		Note rootNoteSOL = new Note("SOL");
		Interval majorThird = new Interval(2,0);
		Interval minorThird = new Interval(1,1);
		Interval[] majorChordIntervals = {majorThird, minorThird};
		Chord majorChord = new Chord(Arrays.asList(majorChordIntervals));
		
		Note[] DOMajorChordNotes = {new Note("DO"), new Note("MI"), new Note("SOL")};
		List<Note> majorChordOfDO = sequenceCreator.createChord(rootNoteDO,majorChord);
		assertEquals("The chord should be composed by the same elements", Arrays.asList(DOMajorChordNotes), majorChordOfDO);
		
		Note[] SOLMajorChordNotes = {new Note("SOL"), new Note("SI"), new Note("RE")};
		List<Note> majorChordOfSOL = sequenceCreator.createChord(rootNoteSOL,majorChord);
		assertEquals("The chord should be composed by the same elements", Arrays.asList(SOLMajorChordNotes), majorChordOfSOL);
	}

}