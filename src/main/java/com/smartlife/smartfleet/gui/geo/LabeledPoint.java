/*
 * ENMA Ltd. Proprietary License
 */
package com.smartlife.smartfleet.gui.geo;

import java.awt.Color;

/**
 * Geographic point, defined by its coordinates and by a label.
 *
 * @author Marius Iulian Grigoras
 *
 * @version 1.0.0 @year 2018
 */
public class LabeledPoint extends GeoPoint {

	private final String label;
	private Color pointColor = Color.YELLOW;
	private Color strColor = Color.YELLOW;
	
	public LabeledPoint(double ltd, double lng, String lbl) {
		super(ltd, lng);
		this.label = lbl;
	}
	
	public LabeledPoint(GeoPoint geoPoint, String lbl) {
		this(geoPoint.getLatitude(), geoPoint.getLongitude(), lbl);
	}
	
	public LabeledPoint(double ltd, double lng, String lbl, Color color) {
		super(ltd, lng);
		this.label = lbl;
		this.pointColor = color;
	}
	
	public LabeledPoint(double ltd, double lng, String lbl, Color color, Color strColor) {
		super(ltd, lng);
		this.label = lbl;
		this.pointColor = color;
		this.strColor = strColor;
	}

	public String getLabel() {
		return label;
	}

	public Color getPointColor() {
		return pointColor;
	}

	public Color getStrColor() {
		return strColor;
	}
	
}
