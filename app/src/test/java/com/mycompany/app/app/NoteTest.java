package com.mycompany.app.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class NoteTest {

	@Test
	public void testNoteConstructor() {
		String trialNoteString = "DO";
		String trialSecondNoteString = "DO#";
		Note trialNote = new Note(trialNoteString);
		assertEquals("The note is equal to the asigned one", trialNoteString, trialNote.getNote());
		Note trialSecondNote = new Note(trialSecondNoteString);
		assertEquals("The note is equal to the asigned one", trialSecondNoteString, trialSecondNote.getNote());
	}
	
	@Test
	public void testNoteConstructorInvalidArgument() {
		String trialWrongNoteString = "wrongNote";
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Note(trialWrongNoteString));
		assertEquals("The argument is not a note",e.getMessage());
	}
	
	@Test
	public void testNoteCompareEqualsBeingSame() {
		String noteDo = "DO";
		Note doNote = new Note(noteDo);
		assertTrue("The notes are equals", doNote.equals(doNote));
	}
	
	@Test
	public void testNoteCompareEqualsBeingEquals() {
		String noteDo = "DO";
		String alsoNoteDo = "DO";
		String noteDoDiesis = "DO#";
		String noteReb = "REb";
		Note firstNote = new Note(noteDo);
		Note secondNote = new Note(alsoNoteDo);
		assertTrue("The notes are equals", firstNote.equals(secondNote));
		firstNote = new Note(noteDoDiesis);
		secondNote = new Note(noteReb);
		assertTrue("The notes are equals", firstNote.equals(secondNote));
	}
	
	@Test
	public void testNoteCompareEqualsBeingDifferent() {
		String noteDo = "DO";
		String noteRe = "RE";
		Note firstNote = new Note(noteDo);
		Note secondNote = new Note(noteRe);
		assertTrue("The notes are different", !firstNote.equals(secondNote));
	}
	
	@Test
	public void testNoteCompareNotNote() {
		String noteDo = "DO";
		Note firstNote = new Note(noteDo);
		assertTrue("The notes are different", !firstNote.equals(noteDo));
	}
	
}
