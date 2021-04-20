package com.mycompany.app.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScaleEight implements Sequence {

	private static final Logger LOGGER = LogManager.getLogger(ScaleEight.class);
	private List<Interval> scaleIntervals;
	private Scale temporaryScale;
	private String[] allPossibleNotes=     {"DOb","DO", "DO#","REb","RE","RE#","MIb","MI" ,"MI#","FAb","FA" ,"FA#" ,"SOLb","SOL","SOL#","LAb" ,"LA","LA#","SIb","SI" ,"SI#"};
	private String[] allChangedNotes =     {"SI" ,"SI#","REb","DO#","",  "MIb","RE#","FAb","FA" ,"MI" ,"MI#","SOLb","FA#" ,""   ,"LAb" ,"SOL#",""  ,"SIb","LA#","DOb","DO" };
	private String[] allNaturalNotes =     {"DO" ,"DO", "DO", "RE", "RE","RE" ,"MI" ,"MI" ,"MI" ,"FA" ,"FA" ,"FA"  ,"SOL" ,"SOL","SOL" ,"LA"  ,"LA","LA" ,"SI" ,"SI" ,"SI" };
	private String[] allAlternativeNotes = {"SI" ,"SI", "RE", "DO", "",  "MI" ,"RE" ,"FA" ,"FA" ,"MI" ,"MI" ,"SOL" ,"FA"  ,""   ,"LA"  ,"SOL" ,""  ,"SI" ,"LA" ,"DO" ,"DO" };

	public ScaleEight(List<Interval> scaleIntervals) {
		if (scaleIntervals.size() == 7) {
			if (checkIntervals(scaleIntervals)) {
				this.scaleIntervals = scaleIntervals;
				temporaryScale = new Scale(scaleIntervals);
				LOGGER.debug("created a scale by setting some intervals");
				LOGGER.info("created a eight scale by setting some intervals");
			} else {
				LOGGER.debug("New eight scale not created: invalid arguments");
				throw new IllegalArgumentException("The argument is not an eight scale");
			}
		} else {
			LOGGER.debug("New eight scale not created: invalid arguments");
			throw new IllegalArgumentException("The argument should contain 7 intervals");
		}
	}

	private boolean checkIntervals(List<Interval> intList) {
		int tones = 0;
		int halfs = 0;
		int firstHalf = 0;
		int secondHalf = 0;
		for (int i = 0; i < intList.size(); i++) {
			if (intList.get(i).getIntervalTones() == 1 && intList.get(i).getIntervalHalfTones() == 0) {
				tones = tones + 1;
			}
			if (intList.get(i).getIntervalTones() == 0 && intList.get(i).getIntervalHalfTones() == 1) {
				if (halfs == 0) {
					firstHalf = i;
				} else {
					secondHalf = i;
				}
				halfs = halfs + 1;
			}
		}
		if (tones == 5 && halfs == 2) {
			return (secondHalf - firstHalf == 3 || secondHalf - firstHalf == 4);
		} else {
			return false;
		}
	}

	@Override
	public List<Interval> getIntervals() {
		LOGGER.debug("requested the intervals of the scale");
		return scaleIntervals;
	}

	@Override
	public List<Note> getNotes(Note rootNote) {
		LOGGER.debug("requested the notes of the scale");
		return normalizeEightScale(temporaryScale.getNotes(rootNote));
	}

	private List<Note> normalizeEightScale(List<Note> scale) {
		List<Note> normalizedScale = new ArrayList<>();
		for (int i = 0; i < scale.size(); i++) {
			normalizedScale.add(scale.get(i));
		}
		String[] changedNotes = new String[7];
		String[] naturalNotes = new String[7];
		String[] alternativeNotes = new String[7];
		for (int i = 0; i < 7; i++) {
			for (int pointer=0; pointer<allPossibleNotes.length; pointer++) {
				if (scale.get(i).getNote().equals(allPossibleNotes[pointer])){
					changedNotes[i] = allChangedNotes[pointer];
					naturalNotes[i] = allNaturalNotes[pointer];
					alternativeNotes[i] = allAlternativeNotes[pointer];
				}
			}
		}
		String[] rootNotes = rootSequenceFromNaturalNote(naturalNotes[0]);
		for (int i = 0; i < 7; i++) {
			if (!(rootNotes[i].equals(naturalNotes[i]) || rootNotes[i].equals(alternativeNotes[i]))) {
				rootNotes = rootSequenceFromNaturalNote(alternativeNotes[0]);
			}
		}
		for (int scaleN = 0; scaleN < 7; scaleN++) {
			if (naturalNotes[scaleN].equals(rootNotes[scaleN])) {
				// do nothing
			} else {
				normalizedScale.set(scaleN, new Note(changedNotes[scaleN]));
			}
		}
		normalizedScale.set(7, normalizedScale.get(0));
		return normalizedScale;
	}

	private String[] rootSequenceFromNaturalNote(String naturalRootNote) {
		String[] naturalNotes = { "DO", "RE", "MI", "FA", "SOL", "LA", "SI" };
		String[] rootSequence = new String[7];
		int rootIndex = -1;
		for (int i = 0; i < 7; i++) {
			if (naturalRootNote.equals(naturalNotes[i])) {
				rootIndex = i;
			}
		}
		for (int i = 0; i < 7; i++) {
			rootSequence[i] = naturalNotes[(i + rootIndex) % 7];
		}
		return rootSequence;
	}

}