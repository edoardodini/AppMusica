package com.mycompany.app.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScaleEight implements Sequence {

	private static final Logger LOGGER = LogManager.getLogger(ScaleEight.class);
	private List<Interval> scaleIntervals;
	private Scale temporaryScale;

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
			switch (scale.get(i).getNote()) {
			default:
				// do nothing
			case "DO":
				changedNotes[i] = "SI#";
				naturalNotes[i] = "DO";
				alternativeNotes[i] = "SI";
				break;
			case "DO#":
				changedNotes[i] = "REb";
				naturalNotes[i] = "DO";
				alternativeNotes[i] = "RE";
				break;
			case "REb":
				changedNotes[i] = "DO#";
				naturalNotes[i] = "RE";
				alternativeNotes[i] = "DO";
				break;
			case "RE":
				changedNotes[i] = "";
				naturalNotes[i] = "RE";
				alternativeNotes[i] = "";
				break;
			case "RE#":
				changedNotes[i] = "MIb";
				naturalNotes[i] = "RE";
				alternativeNotes[i] = "MI";
				break;
			case "MIb":
				changedNotes[i] = "RE#";
				naturalNotes[i] = "MI";
				alternativeNotes[i] = "RE";
				break;
			case "MI":
				changedNotes[i] = "FAb";
				naturalNotes[i] = "MI";
				alternativeNotes[i] = "FA";
				break;
			case "FAb":
				changedNotes[i] = "MI";
				naturalNotes[i] = "FA";
				alternativeNotes[i] = "MI";
				break;
			case "FA":
				changedNotes[i] = "MI#";
				naturalNotes[i] = "FA";
				alternativeNotes[i] = "MI";
				break;
			case "FA#":
				changedNotes[i] = "SOLb";
				naturalNotes[i] = "FA";
				alternativeNotes[i] = "SOL";
				break;
			case "SOLb":
				changedNotes[i] = "FA#";
				naturalNotes[i] = "SOL";
				alternativeNotes[i] = "FA";
				break;
			case "SOL":
				changedNotes[i] = "";
				naturalNotes[i] = "SOL";
				alternativeNotes[i] = "";
				break;
			case "SOL#":
				changedNotes[i] = "LAb";
				naturalNotes[i] = "SOL";
				alternativeNotes[i] = "LA";
				break;
			case "LAb":
				changedNotes[i] = "SOL#";
				naturalNotes[i] = "LA";
				alternativeNotes[i] = "SOL";
				break;
			case "LA":
				changedNotes[i] = "";
				naturalNotes[i] = "LA";
				alternativeNotes[i] = "";
				break;
			case "LA#":
				changedNotes[i] = "SIb";
				naturalNotes[i] = "LA";
				alternativeNotes[i] = "SI";
				break;
			case "SIb":
				changedNotes[i] = "LA#";
				naturalNotes[i] = "SI";
				alternativeNotes[i] = "LA";
				break;
			case "SI":
				changedNotes[i] = "DOb";
				naturalNotes[i] = "SI";
				alternativeNotes[i] = "DO";
				break;
			case "DOb":
				changedNotes[i] = "SI";
				naturalNotes[i] = "DO";
				alternativeNotes[i] = "SI";
				break;
			}
		}
		String[] rootNotes = rootSequenceFromNaturalNote(naturalNotes[0]);
		boolean changeRootNecessary = false;
		for (int i = 0; i < 7; i++) {
			if (!(rootNotes[i].equals(naturalNotes[i]) || rootNotes[i].equals(alternativeNotes[i]))) {
				changeRootNecessary = true;
			}
		}
		if (changeRootNecessary) {
			rootNotes = rootSequenceFromNaturalNote(alternativeNotes[0]);
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