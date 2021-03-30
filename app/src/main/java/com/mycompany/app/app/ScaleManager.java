package com.mycompany.app.app;

import java.util.List;

public class ScaleManager {

	private ScaleDatabaseManager scaleDB;
	private List<Scale> scale;
	
	public ScaleManager(ScaleDatabaseManager database, List<Scale> list) {
		scaleDB = database;
		scale = list;
	}
	
	public List<Scale> getScale() {
		return scale;
	}
	
	public void updateScale() {
		scale = scaleDB.getScale();
	}
	
	public void addScales(List<Scale> scales) {
		for(int i=0;i<scales.size();i++) {
			scaleDB.addScale(scales.get(i));
		}
	}
	
}
