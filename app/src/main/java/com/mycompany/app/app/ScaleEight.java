package com.mycompany.app.app;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScaleEight implements Sequence {

	private Logger LOGGER = LogManager.getLogger(IntervalCreator.class);
	private List<Interval> scaleIntervals;

	public ScaleEight(List<Interval> scaleIntervals) {
		if (scaleIntervals.size()==7) {
			if (checkIntervals(scaleIntervals)) {
				LOGGER.info(() -> String.format("created a eight scale by setting some intervals"));
			} else {
				LOGGER.debug(() -> String.format("New eight scale not created: invalid arguments"));
				throw new IllegalArgumentException("The argument is not an eight scale");
			}
		} else {
			LOGGER.debug(() -> String.format("New eight scale not created: invalid arguments"));
			throw new IllegalArgumentException("The argument should contain 7 intervals");
		}
		
		this.scaleIntervals = scaleIntervals;
		LOGGER.debug("created a scale by setting some intervals");
	}
	
	private boolean checkIntervals(List<Interval> intList) {
		int tones = 0;
		int halfs = 0;
		for (int i=0;i<intList.size();i++) {
			if (intList.get(i).getIntervalTones()==1 && intList.get(i).getIntervalHalfTones()==0) {
				tones=tones+1;
			}
			if (intList.get(i).getIntervalTones()==0 && intList.get(i).getIntervalHalfTones()==1) {
				halfs=halfs+1;
			}
		}
		if (tones==5 && halfs ==2) {
			return true;
		}else {
			return false;
		}
	}

	public List<Interval> getIntervals() {
		LOGGER.debug("requested the intervals of the scale");
		return scaleIntervals;
	}

}
