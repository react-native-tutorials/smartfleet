/**
 * ENMA Ltd. Proprietary License
 */
package com.smartlife.smartfleet.gui.geo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;

/**
 * Segment of a linear shape, such as a road.
 * 
 * @author Marius Iulian Grigoras
 *
 * @version 1.0.0 @year 2018
 */
public class GeoSegment {

	private static final Stroke BASIC_STROKE = new BasicStroke();
	
	private final GeoPoint pointA, pointB;
	private final Color color;
	private final Stroke stroke;
	
	public GeoSegment(GeoPoint pointA, GeoPoint pointB, Color color) {
		this(pointA, pointB, color, BASIC_STROKE);
	}
	
	public GeoSegment(GeoPoint pointA, GeoPoint pointB, Color color, Stroke stroke) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.color = color;
		this.stroke = stroke;
	}
	
	
	
	public GeoPoint getPointA() {
		return pointA;
	}
	
	public GeoPoint getPointB() {
		return pointB;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Stroke getStroke() {
		return stroke;
	}
}
