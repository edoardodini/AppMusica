package com.mycompany.app.app;

import java.util.List;

public interface ScaleDatabaseManager {
	List<Scale> getScale();
	public void addScale(Scale scaleTOAdd);
}
