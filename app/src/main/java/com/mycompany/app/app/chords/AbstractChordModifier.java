package com.mycompany.app.app.chords;

public abstract class AbstractChordModifier implements AbstractChord{
protected AbstractChord chordToDecorate;

public AbstractChord getChordToDecorate() {
	return chordToDecorate;
}

}
