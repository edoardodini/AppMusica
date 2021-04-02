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
				this.scaleIntervals = scaleIntervals;
				LOGGER.debug("created a scale by setting some intervals");
				LOGGER.info(() -> String.format("created a eight scale by setting some intervals"));
			} else {
				LOGGER.debug(() -> String.format("New eight scale not created: invalid arguments"));
				throw new IllegalArgumentException("The argument is not an eight scale");
			}
		} else {
			LOGGER.debug(() -> String.format("New eight scale not created: invalid arguments"));
			throw new IllegalArgumentException("The argument should contain 7 intervals");
		}
	}
	
	private boolean checkIntervals(List<Interval> intList) {
		int tones = 0;
		int halfs = 0;
		int firstHalf = 0;
		int secondHalf = 0;
		for (int i=0;i<intList.size();i++) {
			if (intList.get(i).getIntervalTones()==1 && intList.get(i).getIntervalHalfTones()==0) {
				tones=tones+1;
			}
			if (intList.get(i).getIntervalTones()==0 && intList.get(i).getIntervalHalfTones()==1) {
				if(halfs==0) {
					firstHalf = i;
				}else {
					secondHalf = i;
				}
				halfs=halfs+1;
			}
		}
		if (tones==5 && halfs ==2 && (secondHalf-firstHalf==3||secondHalf-firstHalf==4)) {
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
