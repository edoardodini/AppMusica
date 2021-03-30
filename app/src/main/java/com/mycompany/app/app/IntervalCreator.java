package com.mycompany.app.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntervalCreator {
	private Logger LOGGER = LogManager.getLogger(IntervalCreator.class);
	private Note[] allNotes = { new Note("DO"), new Note("DO#"), new Note("RE"), new Note("RE#"), new Note("MI"),new Note("FA"), new Note("FA#"), new Note("SOL"), new Note("SOL#"), new Note("LA"), new Note("LA#"),new Note("SI") };

	// private NoteComparator noteComp = new NoteComparator();

	public Note getIntervalUp(Note note, Interval interval) {
		int i = 0;
		Note noteToBeReturned;
		while (!note.equals(allNotes[i])) {
			i++;
		}
		noteToBeReturned = allNotes[(i + (interval.getIntervalTones() * 2) + interval.getIntervalHalfTones()) % 12];
		LOGGER.info(() -> String.format("Asked the note far from %s %d tones and %d half tones: %s", note.getNote(),interval.getIntervalTones(), interval.getIntervalHalfTones(), noteToBeReturned.getNote()));
		return noteToBeReturned;
	}

}
