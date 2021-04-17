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

public class SuspendableChordTest {
	Interval majorThird = new Interval(2,0);
	Interval minorThird = new Interval(1,1);
	List<Interval> mockedChordIntervals = new ArrayList<Interval>(Arrays.asList(majorThird, minorThird));
	Note rootNoteDO = new Note("DO");
	List<Note> mockedChordNotes = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MI"),new Note("SOL")));
	int suspensionKind;
	
	@Test
	public void testBasicGettersAndCreation() {
		suspensionKind = 2;
		AbstractSuspendableChord suspendableChord = mock(AbstractSuspendableChord.class);
		when(suspendableChord.getNotes(rootNoteDO)).thenReturn(mockedChordNotes);
		when(suspendableChord.getIntervals()).thenReturn(mockedChordIntervals);
		SuspendedChord firstSuspendedChord = new SuspendedChord(suspensionKind, suspendableChord);
		assertEquals("the inversion kind should be the same setted",suspensionKind, firstSuspendedChord.getSuspensionKind());
		assertEquals("the chord to decorate should be the same setted",suspendableChord, firstSuspendedChord.getChordToDecorate());
		verify(suspendableChord, times(0)).getIntervals();
		verify(suspendableChord, times(0)).getNotes(any(Note.class));
		suspensionKind = 4;
		SuspendedChord secondInvertedChord = new SuspendedChord(suspensionKind, suspendableChord);
		assertEquals("the inversion kind should be the same setted",suspensionKind, secondInvertedChord.getSuspensionKind());
		assertEquals("the chord to decorate should be the same setted",suspendableChord, secondInvertedChord.getChordToDecorate());
		verify(suspendableChord, times(0)).getIntervals();
		verify(suspendableChord, times(0)).getNotes(any(Note.class));
	}
	
	@Test
	public void testBasicSuspendedChord() {
		suspensionKind=2;
		AbstractSuspendableChord suspendableChord = mock(AbstractSuspendableChord.class);
		when(suspendableChord.getNotes(rootNoteDO)).thenReturn(mockedChordNotes);
		when(suspendableChord.getIntervals()).thenReturn(mockedChordIntervals);
		SuspendedChord firstSuspendableChord = new SuspendedChord(suspensionKind, suspendableChord);
		Note[] sus2NotesMajorChordOfDO = {new Note("DO"),new Note("RE"),new Note("SOL")};
		Interval[] sus2IntervalsMajorChordOfDO = {new Interval(1,0), new Interval(2,1)};
		verify(suspendableChord, times(0)).getNotes(any(Note.class));
		assertEquals("The chords should be the same",Arrays.asList(sus2NotesMajorChordOfDO), firstSuspendableChord.getNotes(rootNoteDO));
		verify(suspendableChord, times(1)).getNotes(rootNoteDO);
		verify(suspendableChord, times(0)).getIntervals();
		assertEquals("The intervals should be the same", Arrays.asList(sus2IntervalsMajorChordOfDO), firstSuspendableChord.getIntervals());
		verify(suspendableChord, times(1)).getNotes(rootNoteDO);
		verify(suspendableChord, times(1)).getIntervals();
	}
	
	@Test
	public void testWrongSusChord() {
		AbstractSuspendableChord suspendableChord = mock(AbstractSuspendableChord.class);
		suspensionKind=3;
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new SuspendedChord(suspensionKind, suspendableChord));
		assertEquals("the suspension kind should be 2 or 4", exception.getMessage());
	}
	
	@Test
	public void testComplexCase() {
		suspensionKind = 2;
		AbstractSuspendableChord suspendableChord = mock(AbstractSuspendableChord.class);
		when(suspendableChord.getNotes(rootNoteDO)).thenReturn(mockedChordNotes);
		when(suspendableChord.getIntervals()).thenReturn(mockedChordIntervals);
		
		SuspendedChord sus2Chord = new SuspendedChord(suspensionKind, suspendableChord);
		Note[] complexSus2ChordOfDO = {new Note("DO"),new Note("RE"),new Note("SOL")};
		Interval[] complexSus2ChordOfDOInterval = {new Interval(1,0),new Interval(2,1)};
		assertEquals("The chords should be the same", Arrays.asList(complexSus2ChordOfDO), sus2Chord.getNotes(rootNoteDO));
		assertEquals("The intervals should be the same",Arrays.asList(complexSus2ChordOfDOInterval), sus2Chord.getIntervals());
		suspensionKind=4;
		SuspendedChord complexSus4Chord = new SuspendedChord(suspensionKind, suspendableChord);
		Note[] complexSus4ChordOfDO = {new Note("DO"),new Note("FA"),new Note("SOL")};
		Interval[] complexSus4ChordOfDOInterval = {new Interval(2,1),new Interval(1,0)};
		assertEquals("The chords should be the same", Arrays.asList(complexSus4ChordOfDO), complexSus4Chord.getNotes(rootNoteDO));
		assertEquals("The intervals should be the same",Arrays.asList(complexSus4ChordOfDOInterval), complexSus4Chord.getIntervals());
	}

}
