package com.mycompany.app.app;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScaleManager {
	private static final Logger LOGGER = LogManager.getLogger(ScaleManager.class);
	private ScaleDatabaseManager scaleDB;
	private List<Scale> scale;
	
	public ScaleManager(ScaleDatabaseManager database, List<Scale> list) {
		LOGGER.debug("new scale manager created");
		scaleDB = database;
		scale = list;
	}
	
	public List<Scale> getScale() {
		LOGGER.debug("the scale manager returned the scale list");
		return scale;
	}
	
	public void updateScale() {
		LOGGER.debug("the scale manager updated the scale list");
		scale = scaleDB.getScale();
	}
	
	public void addScales(List<Scale> scales) {
		for(int i=0;i<scales.size();i++) {
			LOGGER.debug("the scale manager added a scale to the db");
			scaleDB.addScale(scales.get(i));
		}
	}
	
}
