/**
 * 
 */
package com.smartlife.smartfleet.geo;

import java.awt.Color;

import com.smartlife.smartfleet.gui.geo.GeoFrame;
import com.smartlife.smartfleet.gui.geo.GeoPoint;
import com.smartlife.smartfleet.gui.geo.GeoSegment;
import com.smartlife.smartfleet.gui.geo.LabeledPoint;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class GeoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GeoFrame frame = new GeoFrame();
		frame.setSize(700, 700);
		frame.setLocationByPlatform(Boolean.TRUE);
		frame.setLocationRelativeTo(null);
		
		frame.addSegment( new GeoSegment(
		        new GeoPoint(0, 0),
		        new GeoPoint(0, 0),
		        Color.BLACK));
		frame.addLabeledPoint(new LabeledPoint(56, 3.1, "HOME"));
		frame.addLabeledPoint(new LabeledPoint(48.8567, 2.3508, "Paris"));
		
		frame.setVisible(Boolean.TRUE);
	}

}
