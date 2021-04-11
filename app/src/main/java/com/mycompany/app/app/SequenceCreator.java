package com.mycompany.app.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SequenceCreator {
	private Logger LOGGER = LogManager.getLogger(SequenceCreator.class);
	private IntervalCreator intCreat = new IntervalCreator();

	public List<Note> createScale(Note rootNote, Scale referenceScale) {
		List<Note> scala = new ArrayList<Note>();
		scala = createSequence(rootNote, referenceScale);
		LOGGER.info("created a scale of notes starting from a scale and a root note");
		return scala;
	}
	
	public List<Note> createScale(Note rootNote, ScaleEight referenceScale) {
		List<Note> scala = new ArrayList<Note>();
		scala = createSequence(rootNote, referenceScale);
		normalizeEightScale(scala);
		LOGGER.info("created an eight scale of notes starting from an eight scale and a root note");
		return scala;
	}
	
	private List<Note> createSequence(Note rootNote, Sequence referenceSequence){
		List<Note> sequence = new ArrayList<Note>();
		sequence.add(new Note(rootNote.getNote()));
		for (int i = 1; i < referenceSequence.getIntervals().size() + 1; i++) {
			sequence.add(intCreat.getIntervalUp(sequence.get(i - 1), referenceSequence.getIntervals().get(i - 1)));
		}
		LOGGER.debug("created a sequence of notes starting from a sequence and a root note");
		return sequence;
	}
	
	private void normalizeEightScale(List<Note> scale) {
		String[] changedNotes = new String[7];
		String[] naturalNotes = new String[7];
		String[] alternativeNotes = new String[7];
		for (int i=0;i<7;i++) {
			switch(scale.get(i).getNote()) {
				default:
					//do nothing;
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
		for (int i=0;i<7;i++) {
			if (!(rootNotes[i].equals(naturalNotes[i])||rootNotes[i].equals(alternativeNotes[i]))) {
				changeRootNecessary=true;
			}
		}
		if(changeRootNecessary) {
			rootNotes = rootSequenceFromNaturalNote(alternativeNotes[0]);
		}
		for (int scaleN=0;scaleN<7;scaleN++) {
			if(naturalNotes[scaleN].equals(rootNotes[scaleN])) {
				//do nothing
			}else {
				scale.set(scaleN,new Note(changedNotes[scaleN]));
			}
		}
		scale.set(7,scale.get(0));
	}
	
	private String[] rootSequenceFromNaturalNote(String naturalRootNote) {
		String[] naturalNotes = {"DO","RE","MI","FA","SOL","LA","SI"};
		String[] rootSequence = new String[7];
		int rootIndex = -1;
		for (int i=0;i<7;i++) {
			if (naturalRootNote.equals(naturalNotes[i])) {
				rootIndex = i;
			}
		}
		for (int i=0;i<7;i++) {
			rootSequence[i]=naturalNotes[(i+rootIndex)%7];
		}
		return rootSequence;
	}
}