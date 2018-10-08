/**
 * 
 */
package com.smartlife.smartfleet.gui.geo;

import java.awt.Color;
import java.awt.Point;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class ColoredPoint extends Point {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 2004263754396905965L;

	Color pointColor;
	
	public ColoredPoint(Color currentColor) {
		super();
		this.pointColor = currentColor;
	}

	public Color getPointColor() {
		return pointColor;
	}
	
}
