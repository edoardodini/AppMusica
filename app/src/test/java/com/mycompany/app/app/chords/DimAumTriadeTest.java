package com.mycompany.app.app.chords;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.mycompany.app.app.Interval;
import com.mycompany.app.app.Note;

public class DimAumTriadeTest {
	Interval majorThird = new Interval(2,0);
	Interval minorThird = new Interval(1,1);
	List<Interval> mockedMajorChordIntervals = new ArrayList<Interval>(Arrays.asList(majorThird, minorThird));
	List<Interval> mockedMinorChordIntervals = new ArrayList<Interval>(Arrays.asList(minorThird, minorThird));
	Note rootNoteDO = new Note("DO");
	List<Note> mockedMajorChordNotes = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MI"),new Note("SOL")));
	List<Note> mockedMinorChordNotes = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MI"),new Note("SOL")));
	boolean isAugmented;
	
	
	@Test
	public void testBasicGettersAndCreation() {
		isAugmented = true;
		AbstractAumDimChord dimAumChord = mock(AbstractAumDimChord.class);
		when(dimAumChord.getNotes(rootNoteDO)).thenReturn(mockedMajorChordNotes);
		when(dimAumChord.getIntervals()).thenReturn(mockedMajorChordIntervals);
		DimAumTriade dimAumTriade = new DimAumTriade(isAugmented, dimAumChord);
		verify(dimAumChord, times(1)).getIntervals();
		assertEquals("the inversion kind should be the same setted",isAugmented, dimAumTriade.isAugmented());
		assertEquals("the chord to decorate should be the same setted",dimAumChord, dimAumTriade.getChordToDecorate());
		verify(dimAumChord, times(1)).getIntervals();
		verify(dimAumChord, times(0)).getNotes(any(Note.class));
		isAugmented = false;
		when(dimAumChord.getNotes(rootNoteDO)).thenReturn(mockedMinorChordNotes);
		when(dimAumChord.getIntervals()).thenReturn(mockedMinorChordIntervals);
		DimAumTriade secondDimAumTriade = new DimAumTriade(isAugmented, dimAumChord);
		assertEquals("the inversion kind should be the same setted",isAugmented, secondDimAumTriade.isAugmented());
		assertEquals("the chord to decorate should be the same setted",dimAumChord, secondDimAumTriade.getChordToDecorate());
		verify(dimAumChord, times(2)).getIntervals();
		verify(dimAumChord, times(0)).getNotes(any(Note.class));
	}
	
	@Test
	public void testBasicAugmentedChord() {
		isAugmented = true;
		AbstractAumDimChord AumDimChord = mock(AbstractAumDimChord.class);
		when(AumDimChord.getNotes(rootNoteDO)).thenReturn(mockedMajorChordNotes);
		when(AumDimChord.getIntervals()).thenReturn(mockedMajorChordIntervals);
		DimAumTriade firstAugmentedChord = new DimAumTriade(isAugmented, AumDimChord);
		verify(AumDimChord, times(1)).getIntervals();
		Note[] notesMajorChordOfDOAum = {new Note("DO"),new Note("MI"),new Note("SOL#")};
		Interval[] intervalsMajorChordOfDOAum = {majorThird,majorThird};
		verify(AumDimChord, times(0)).getNotes(any(Note.class));
		assertEquals("The chords should be the same",Arrays.asList(notesMajorChordOfDOAum), firstAugmentedChord.getNotes(rootNoteDO));
		verify(AumDimChord, times(1)).getNotes(rootNoteDO);
		verify(AumDimChord, times(1)).getIntervals();
		assertEquals("The intervals should be the same", Arrays.asList(intervalsMajorChordOfDOAum), firstAugmentedChord.getIntervals());
		verify(AumDimChord, times(1)).getNotes(rootNoteDO);
		verify(AumDimChord, times(1)).getIntervals();
	}
	
	@Test
	public void testBasicDiminishedChord() {
		isAugmented = false;
		AbstractAumDimChord AumDimChord = mock(AbstractAumDimChord.class);
		when(AumDimChord.getNotes(rootNoteDO)).thenReturn(mockedMinorChordNotes);
		when(AumDimChord.getIntervals()).thenReturn(mockedMinorChordIntervals);
		DimAumTriade firstDiminishedChord = new DimAumTriade(isAugmented, AumDimChord);
		verify(AumDimChord, times(1)).getIntervals();
		Note[] notesMajorChordOfDODim = {new Note("DO"),new Note("MIb"),new Note("SOLb")};
		Interval[] intervalsMajorChordOfDODim = {minorThird,minorThird};
		verify(AumDimChord, times(0)).getNotes(any(Note.class));
		assertEquals("The chords should be the same",Arrays.asList(notesMajorChordOfDODim), firstDiminishedChord.getNotes(rootNoteDO));
		verify(AumDimChord, times(1)).getNotes(rootNoteDO);
		verify(AumDimChord, times(1)).getIntervals();
		assertEquals("The intervals should be the same", Arrays.asList(intervalsMajorChordOfDODim), firstDiminishedChord.getIntervals());
		verify(AumDimChord, times(1)).getNotes(rootNoteDO);
		verify(AumDimChord, times(1)).getIntervals();
	}
	
	@Test
	public void testWrongAugmentedChord() {
		AbstractAumDimChord aumDimChord = mock(AbstractAumDimChord.class);
		when(aumDimChord.getIntervals()).thenReturn(mockedMinorChordIntervals);
		isAugmented=true;
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new DimAumTriade(isAugmented, aumDimChord));
		assertEquals("it is not possible to augment a minor triade", exception.getMessage());
	}
	
	@Test
	public void testWrongDiminishedChord() {
		AbstractAumDimChord aumDimChord = mock(AbstractAumDimChord.class);
		when(aumDimChord.getIntervals()).thenReturn(mockedMajorChordIntervals);
		isAugmented=false;
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new DimAumTriade(isAugmented, aumDimChord));
		assertEquals("it is not possible to diminish a major triade", exception.getMessage());
	}

}
