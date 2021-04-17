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

public class AddableChordTest {
	
	Interval majorThird = new Interval(2,0);
	Interval minorThird = new Interval(1,1);
	List<Interval> mockedChordIntervals = new ArrayList<Interval>(Arrays.asList(majorThird, minorThird));
	Note rootNoteDO = new Note("DO");
	List<Note> mockedChordNotes = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MI"),new Note("SOL")));
	int addKind = 9;
	
	@Test
	public void testBasicGettersAndCreation() {
		addKind = 9;
		AbstractAddableChord addableChord = mock(AbstractAddableChord.class);
		when(addableChord.getNotes(rootNoteDO)).thenReturn(mockedChordNotes);
		when(addableChord.getIntervals()).thenReturn(mockedChordIntervals);
		AddedChord firstInvertedChord = new AddedChord(addKind, addableChord);
		assertEquals("the inversion kind should be the same setted",addKind, firstInvertedChord.getAddKind());
		assertEquals("the chord to decorate should be the same setted",addableChord, firstInvertedChord.getChordToDecorate());
		verify(addableChord, times(0)).getIntervals();
		verify(addableChord, times(0)).getNotes(any(Note.class));
		addKind = 11;
		AddedChord secondInvertedChord = new AddedChord(addKind, addableChord);
		assertEquals("the inversion kind should be the same setted",addKind, secondInvertedChord.getAddKind());
		assertEquals("the chord to decorate should be the same setted",addableChord, secondInvertedChord.getChordToDecorate());
		verify(addableChord, times(0)).getIntervals();
		verify(addableChord, times(0)).getNotes(any(Note.class));
	}
	
	@Test
	public void testBasicAddedChord() {
		AbstractAddableChord addableChord = mock(AbstractAddableChord.class);
		when(addableChord.getNotes(rootNoteDO)).thenReturn(mockedChordNotes);
		when(addableChord.getIntervals()).thenReturn(mockedChordIntervals);
		AddedChord firstAddedChord = new AddedChord(addKind, addableChord);
		Note[] nineAddedNotesMajorChordOfDO = {new Note("DO"),new Note("MI"),new Note("SOL"), new Note("RE")};
		Interval[] nineAddedIntervalsMajorChordOfDO = {majorThird, minorThird, new Interval(3,1)};
		verify(addableChord, times(0)).getNotes(any(Note.class));
		assertEquals("The chords should be the same",Arrays.asList(nineAddedNotesMajorChordOfDO), firstAddedChord.getNotes(rootNoteDO));
		verify(addableChord, times(1)).getNotes(rootNoteDO);
		verify(addableChord, times(0)).getIntervals();
		assertEquals("The intervals should be the same", Arrays.asList(nineAddedIntervalsMajorChordOfDO), firstAddedChord.getIntervals());
		verify(addableChord, times(1)).getNotes(rootNoteDO);
		verify(addableChord, times(1)).getIntervals();
	}
	
	@Test
	public void testWrongAddChord() {
		AbstractAddableChord addableChord = mock(AbstractAddableChord.class);
		addKind=8;
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new AddedChord(addKind, addableChord));
		assertEquals("the add kind should be 9, 11 or 13", exception.getMessage());
	}
	
	@Test
	public void testComplexCase() {
		addKind = 9;
		List<Interval> mockedChordOfDOInterval = new ArrayList<Interval>(Arrays.asList(majorThird,minorThird,majorThird));
		List<Note> mockedChordOfDONote = new ArrayList<Note>(Arrays.asList(new Note("DO"),new Note("MI"),new Note("SOL"),new Note("SI")));
		AbstractAddableChord addableChord = mock(AbstractAddableChord.class);
		when(addableChord.getNotes(rootNoteDO)).thenReturn(mockedChordOfDONote);
		when(addableChord.getIntervals()).thenReturn(mockedChordOfDOInterval);
		
		AddedChord complexNineAddedChord = new AddedChord(addKind, addableChord);
		Note[] complexNineAddedChordOfDO = {new Note("DO"),new Note("MI"),new Note("SOL"), new Note("SI"), new Note("RE")};
		Interval[] complexNineAddedChordOfDOInterval = {majorThird, minorThird,majorThird,new Interval(1,1)};
		assertEquals("The chords should be the same", Arrays.asList(complexNineAddedChordOfDO), complexNineAddedChord.getNotes(rootNoteDO));
		assertEquals("The intervals should be the same",Arrays.asList(complexNineAddedChordOfDOInterval), complexNineAddedChord.getIntervals());
		addKind=11;
		AddedChord complexElevenAddedChord = new AddedChord(addKind, addableChord);
		Note[] complexElevenAddedChordOfDO = {new Note("DO"),new Note("MI"),new Note("SOL"), new Note("SI"), new Note("FA")};
		Interval[] complexElevenAddedChordOfDOInterval = {majorThird, minorThird,majorThird,new Interval(3,0)};
		assertEquals("The chords should be the same", Arrays.asList(complexElevenAddedChordOfDO), complexElevenAddedChord.getNotes(rootNoteDO));
		assertEquals("The intervals should be the same",Arrays.asList(complexElevenAddedChordOfDOInterval), complexElevenAddedChord.getIntervals());
		addKind=13;
		AddedChord complex13AddedChord = new AddedChord(addKind, addableChord);
		Note[] complex13AddedChordOfDO = {new Note("DO"),new Note("MI"),new Note("SOL"), new Note("SI"), new Note("LA")};
		Interval[] complex13AddedChordOfDOInterval = {majorThird, minorThird,majorThird,new Interval(5,0)};
		assertEquals("The chords should be the same", Arrays.asList(complex13AddedChordOfDO), complex13AddedChord.getNotes(rootNoteDO));
		assertEquals("The intervals should be the same",Arrays.asList(complex13AddedChordOfDOInterval), complex13AddedChord.getIntervals());
	}
	

}
