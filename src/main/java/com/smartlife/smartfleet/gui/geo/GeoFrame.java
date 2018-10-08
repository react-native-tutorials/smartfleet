/**
 * ENMA Ltd. Proprietary License
 */
package com.smartlife.smartfleet.gui.geo;

import java.awt.BorderLayout;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.smartlife.smartfleet.gui.InitSession;
import com.smartlife.smartfleet.gui.SmartMainFrame;

/**
 * 
 * @author Marius Iulian Grigoras
 *
 * @version 1.0.0 @year 2018
 */
public class GeoFrame extends JFrame {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -5338228188907548347L;
	private GeoPanel map;

	private Long lastId;
// TODO remove this
	SmartMainFrame mainFrame;
ShowGpsFrame frame;
	public GeoFrame() {
	
		setLookAndFeel();
		mainFrame = new SmartMainFrame(new InitSession());
		frame =new ShowGpsFrame(mainFrame);
		map = new GeoPanel(frame);
		setLayout(new BorderLayout());
		add(map, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			System.setProperty("os.name", "Windows");
			System.setProperty("os.version", "8.1");
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes all the registered segments and POIs.
	 */
	public void clear() {
		map.clear();
		lastId = 0L;
	}

	/**
	 * Adds a segment to the list of segments to display.
	 * 
	 * @param segment
	 *            the segment to add
	 */
	public void addSegment(GeoSegment segment) {
		map.addSegment(segment);
	}

	/**
	 * Adds a whole collection of segments to the list of segments to display
	 * 
	 * @param segments
	 *            the collection of segments to add
	 */
	public void addSegments(Collection<GeoSegment> segments) {
		map.addSegments(segments);
	}

	/**
	 * Adds a point of interest (POI) to the list of POIs to display.
	 * 
	 * @param poi
	 *            the POI to add
	 */
	public void addLabeledPoint(LabeledPoint poi) {
		map.addLabeledPoint(poi);
	}

	public Long getLastId() {
		return lastId;
	}

	public void setLastId(Long lastId) {
		this.lastId = lastId;
	}

}
