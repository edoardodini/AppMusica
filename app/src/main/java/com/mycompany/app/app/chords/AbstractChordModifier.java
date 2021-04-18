package com.mycompany.app.app.chords;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractChordModifier implements AbstractChord{
	private static final Logger LOGGER = LogManager.getLogger(AbstractChordModifier.class);
	protected AbstractChord chordToDecorate;

public AbstractChord getChordToDecorate() {
	LOGGER.debug("the chord to decorate has been returned");
	return chordToDecorate;
}

}
