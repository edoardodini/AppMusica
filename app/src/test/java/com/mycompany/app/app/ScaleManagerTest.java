package com.mycompany.app.app;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ScaleManagerTest {

	private ScaleManager scaleManager;
	private ScaleDatabaseManager db;
	private ArrayList<Scale> list;
	private Scale majorScale;
	private Scale minorScale;

	@Before
	public void setup() {
		db = mock(ScaleDatabaseManager.class);
		list = new ArrayList<Scale>();
		scaleManager = new ScaleManager(db, list);
		Interval half = new Interval(0, 1);
		Interval tone = new Interval(1, 0);
		majorScale = new Scale(new ArrayList<Interval>(Arrays.asList(tone, tone, half, tone, tone, tone, half)));
		minorScale = new Scale(new ArrayList<Interval>(Arrays.asList(tone, half, tone, tone, half, tone, tone)));
	}

	@Test
	public void testEmptyDatabase() {
		scaleManager.updateScale();
		assertThat(scaleManager.getScale()).isEmpty();
	}

	@Test
	public void testAddOneElementToEmptyDatabase() {
		scaleManager.updateScale();
		assertThat(scaleManager.getScale()).isEmpty();

		db.addScale(majorScale);
		List<Scale> scale = new ArrayList<Scale>();
		scale.add(majorScale);

		assertThat(scaleManager.getScale()).isEmpty();
		when(db.getScale()).thenReturn(scale);
		scaleManager.updateScale();

		assertThat(scaleManager.getScale().size()).isEqualTo(1);
		assertThat(scaleManager.getScale().get(0)).isEqualTo(majorScale);
	}

	@Test
	public void testUpdateCallOneGetScale() {
		scaleManager.updateScale();
		verify(db, times(1)).getScale();
	}
	
	@Test
	public void testAddTwoScale() {
		scaleManager.updateScale();
		scaleManager.addScales(new ArrayList<Scale>(Arrays.asList(majorScale,minorScale)));
		verify(db, times(2)).addScale(any(Scale.class));
	}

}
