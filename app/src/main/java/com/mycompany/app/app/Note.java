package com.mycompany.app.app;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Note {

	private static final Logger LOGGER = LogManager.getLogger(Note.class);

	private String noteField;
	private String[] validNotes = { "DO", "DO#", "DOb", "RE", "RE#", "REb", "MI", "MI#", "MIb", "FA", "FA#", "FAb",
			"SOL", "SOL#", "SOLb", "LA", "LA#", "LAb", "SI", "SI#", "SIb" };
	private HashMap<String, Integer> noteUguali = new HashMap<>();

	private static boolean stringInList(String[] arr, String targetValue) {
		for (String s : arr) {
			if (s.equals(targetValue)) {
				return true;
			}
		}
		return false;
	}

	public Note(String noteString) {
		if (stringInList(validNotes, noteString)) {
			noteField = noteString;
			noteUguali.put("SI#", 1);
			noteUguali.put("DO", 1);
			noteUguali.put("DO#", 2);
			noteUguali.put("REb", 2);
			noteUguali.put("RE", 3);
			noteUguali.put("RE#", 4);
			noteUguali.put("MIb", 4);
			noteUguali.put("MI", 5);
			noteUguali.put("FAb", 5);
			noteUguali.put("MI#", 6);
			noteUguali.put("FA", 6);
			noteUguali.put("FA#", 7);
			noteUguali.put("SOLb", 7);
			noteUguali.put("SOL", 8);
			noteUguali.put("SOL#", 9);
			noteUguali.put("LAb", 9);
			noteUguali.put("LA", 10);
			noteUguali.put("LA#", 11);
			noteUguali.put("SIb", 11);
			noteUguali.put("SI", 12);
			noteUguali.put("DOb", 12);
			LOGGER.info(() -> String.format("New note created: %s", noteField));
		} else {
			LOGGER.debug(() -> String.format("New note not created for invalid argument: %s", noteString));
			throw new IllegalArgumentException("The argument is not a note");
		}
	}

	public String getNote() {
		LOGGER.debug(() -> String.format("Asked the note, it is: %s", noteField));
		return noteField;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = result * prime + noteField.hashCode();
		LOGGER.debug("The note hash code is returned");
		return result;
	}

	@Override
	public boolean equals(Object o) {

		// If the object is compared with itself then return true
		if (o == this) {
			LOGGER.debug("The note is compared to the same object so they are equals");
			return true;
		}

		/*
		 * Check if o is an instance of Complex or not "null instance of [type]" also
		 * returns false
		 */
		if (!(o instanceof Note)) {
			LOGGER.debug(() -> String.format("The note is compared to an object that is not a note, it is a: %s",
					o.getClass()));
			return false;
		}

		// typecast o to Complex so that we can compare data members
		Note c = (Note) o;

		// Compare the data members and return accordingly
		if (noteUguali.get(noteField).equals(noteUguali.get(c.getNote()))) {
			LOGGER.debug("The note is compared to a note and they are equals, both: %s", noteField);
			return true;
		} else {
			LOGGER.debug("The note is compared to a note and they are not equals, one is a %s and the other is a %s",
					noteField, c.getNote());
			return false;
		}
	}
}
