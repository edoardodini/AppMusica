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
		assertEquals("The argument is not a note", e.getMessage());
	}

	@Test
	public void testNoteCompareEqualsBeingSame() {
		String noteDo = "DO";
		Note doNote = new Note(noteDo);
		assertEquals("The notes are equals", doNote, doNote);
	}

	@Test
	public void testNoteCompareEqualsBeingEquals() {
		String noteDo = "DO";
		String alsoNoteDo = "DO";
		String noteDoDiesis = "DO#";
		String noteReb = "REb";
		Note firstNote = new Note(noteDo);
		Note secondNote = new Note(alsoNoteDo);
		assertEquals("The notes are equals", firstNote, secondNote);
		firstNote = new Note(noteDoDiesis);
		secondNote = new Note(noteReb);
		assertEquals("The notes are equals", firstNote, secondNote);
	}

	@Test
	public void testNoteCompareEqualsBeingDifferent() {
		String noteDo = "DO";
		String noteRe = "RE";
		Note firstNote = new Note(noteDo);
		Note secondNote = new Note(noteRe);
		assertNotEquals("The notes are different", firstNote, secondNote);
	}

	@Test
	public void testNoteCompareNotNote() {
		String noteDo = "DO";
		Note firstNote = new Note(noteDo);
		assertNotEquals("The notes are different (comparing a note with something else)", firstNote, noteDo);
	}

	@Test
	public void testNoteHasCode() {
		String noteDo = "DO";
		String noteRe = "RE";
		Note firstNote = new Note(noteDo);
		Note secondNote = new Note(noteDo);
		Note thirdNote = new Note(noteRe);
		assertEquals("The note hash code should be equal to the implementation", new Note(noteDo).hashCode(),
				31 * 1 + noteDo.hashCode());
		assertEquals("The notes are the same object son hash code should be equal", firstNote.hashCode(),
				firstNote.hashCode());
		assertEquals("The notes are equals, but different objects, the hash codes should be equal",
				firstNote.hashCode(), secondNote.hashCode());
		assertNotEquals("The notes are different, hash codes should be different", firstNote.hashCode(),
				thirdNote.hashCode());
	}
	
}