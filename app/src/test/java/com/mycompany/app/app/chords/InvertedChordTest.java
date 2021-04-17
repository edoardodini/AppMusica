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

public class InvertedChordTest {
	Interval majorThird = new Interval(2,0);
	Interval minorThird = new Interval(1,1);
	List<Interval> majorChordIntervals = new ArrayList<Interval>(Arrays.asList(majorThird, minorThird));
	Note rootNoteDO = new Note("DO");
	List<Note> mockedChordNotes = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MI"),new Note("SOL")));
	
	
	@Test
	public void testBasicGettersAndCreation() {
		int inversionKind = 1;
		AbstractInvertableChord addableChord = mock(AbstractInvertableChord.class);
		when(addableChord.getNotes(rootNoteDO)).thenReturn(mockedChordNotes);
		when(addableChord.getIntervals()).thenReturn(majorChordIntervals);
		verify(addableChord, times(0)).getIntervals();
		InvertedChord firstInvertedChord = new InvertedChord(inversionKind, addableChord);
		verify(addableChord, times(1)).getIntervals();
		assertEquals("the inversion kind should be the same setted",firstInvertedChord.getInversion(),inversionKind);
		assertEquals("the chord to decorate should be the same setted",firstInvertedChord.getChordToDecorate(),addableChord);
		verify(addableChord, times(0)).getNotes(any(Note.class));
		assertEquals("The intervals should be the same", firstInvertedChord.getIntervals(), majorChordIntervals);
		verify(addableChord, times(2)).getIntervals();
	}
	
	@Test
	public void testBasicInvertedChord() {
		int inversionKind = 1;
		AbstractInvertableChord invertableChord = mock(AbstractInvertableChord.class);
		when(invertableChord.getNotes(rootNoteDO)).thenReturn(mockedChordNotes);
		when(invertableChord.getIntervals()).thenReturn(majorChordIntervals);
		
		InvertedChord firstInvertedChord = new InvertedChord(inversionKind, invertableChord);
		Note[] firstInvertedMajorChordOfDO = {new Note("MI"),new Note("SOL"),new Note("DO")};
		verify(invertableChord, times(0)).getNotes(any(Note.class));
		assertEquals("The chords should be the same",Arrays.asList(firstInvertedMajorChordOfDO), firstInvertedChord.getNotes(rootNoteDO));
		verify(invertableChord, times(1)).getNotes(rootNoteDO);
		assertEquals("The intervals should be the same",majorChordIntervals, firstInvertedChord.getIntervals());
		verify(invertableChord, times(1)).getNotes(rootNoteDO);
		verify(invertableChord, times(2)).getIntervals();
	}
	
	@Test
	public void testWrongInvertedChord() {
		int inversionKind = 3;
		AbstractInvertableChord invertableChord = mock(AbstractInvertableChord.class);
		when(invertableChord.getIntervals()).thenReturn(majorChordIntervals);
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new InvertedChord(inversionKind, invertableChord));
		assertEquals("the inversion kind should be minor than the lenght of the chord", exception.getMessage());
	}
	
	@Test
	public void testWrongInvertedChordNegativeOrZeroInversion() {
		int inversionKindZero = 0;
		int inversionKindMinusOne = -1;
		AbstractInvertableChord invertableChord = mock(AbstractInvertableChord.class);
		when(invertableChord.getIntervals()).thenReturn(majorChordIntervals);
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new InvertedChord(inversionKindZero, invertableChord));
		assertEquals("the inversion kind should be major than zero", exception.getMessage());
		
		IllegalArgumentException exceptionTwo = assertThrows(IllegalArgumentException.class,
				() -> new InvertedChord(inversionKindMinusOne, invertableChord));
		assertEquals("the inversion kind should be major than zero", exceptionTwo.getMessage());
	}
	
	@Test
	public void testComplexCase() {
		int inversionKind = 3;
		List<Interval> mockedChordOfDOInterval = new ArrayList<Interval>(Arrays.asList(majorThird,minorThird,majorThird));
		List<Note> mockedChordOfDONote = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MI"),new Note("SOL"),new Note("SI")));
		AbstractInvertableChord invertableChord = mock(AbstractInvertableChord.class);
		when(invertableChord.getNotes(rootNoteDO)).thenReturn(mockedChordOfDONote);
		when(invertableChord.getIntervals()).thenReturn(mockedChordOfDOInterval);
		
		InvertedChord complexThirdInvertedChord = new InvertedChord(inversionKind, invertableChord);
		Note[] complexThirdInvertedChordOfDO = {new Note("SI"),new Note("DO"),new Note("MI"),new Note("SOL")};
		assertEquals("The chords should be the same", Arrays.asList(complexThirdInvertedChordOfDO), complexThirdInvertedChord.getNotes(rootNoteDO));
		inversionKind=1;
		InvertedChord complexFirstInvertedChord = new InvertedChord(inversionKind, invertableChord);
		Note[] complexFirstInvertedChordOfDO = {new Note("MI"),new Note("SOL"),new Note("SI"),new Note("DO")};
		assertEquals("The chords should be the same", Arrays.asList(complexFirstInvertedChordOfDO), complexFirstInvertedChord.getNotes(rootNoteDO));
	}

}
