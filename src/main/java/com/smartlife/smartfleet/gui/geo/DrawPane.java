package com.smartlife.smartfleet.gui.geo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.smartlife.smartfleet.dto.Coordinates;

public class DrawPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -534246387131587588L;
	protected final Log logger = LogFactory.getLog(getClass());
	@SuppressWarnings("unused")
	private Color currentColor=Color.WHITE;
	private String currentShape="POINT";
	private List<ColoredPoint> points = new ArrayList<>();
	private List<Coordinates> scaledPoints =new ArrayList<>();
	/**
	 * Create the panel.
	 */
	public DrawPane() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(800, 600));
	}

	public void setValues(String currentShape, Color currentColor) {
		
		this.currentShape = currentShape;
		this.currentColor = currentColor;
	}
	
	@Override
	protected synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g.create();

		switch (currentShape) {
		case "POINT":
			for(ColoredPoint point : points) {
				Color pointColor = point.getPointColor();
				g2d.setColor(pointColor);
				int x = point.x;
				int y = point.y;
				g2d.fillOval(x - 1, y - 1, 5, 5);
			}
			
			break;

		default:
			break;
		}
		
		g2d.setColor(Color.WHITE);
		for(Coordinates scc : scaledPoints) {
			double newLat = scc.getLatitud();
			double newLong = scc.getLongitud();
			
			Ellipse2D.Double point = new Ellipse2D.Double(newLat,
					newLong, 5, 5);
			g2d.setColor(Color.white);
			g2d.fill(point);
//			logger.info("Point "+ newLat+" x "+newLong+" added.");
		}
		
	}
	
	public synchronized void addPoint(ColoredPoint p) {
		this.points.add(p);
	}
	
	public synchronized void addScaledPoints(List<Coordinates> coords) {
		this.scaledPoints.addAll(coords);
	}

	public List<Coordinates> getScaledPoints() {
		return scaledPoints;
	}
	
	
}
