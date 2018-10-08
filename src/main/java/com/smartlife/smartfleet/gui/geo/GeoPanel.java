/**
 * ENMA Ltd. Proprietary License
 */
package com.smartlife.smartfleet.gui.geo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Marius Iulian Grigoras
 *
 * @version 1.0.0 @year 2018
 */
public class GeoPanel extends JPanel {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -567972230749349629L;
	private final int PENCIL_TOOL = 0;
	private final int LINE_TOOL = 1;
	private final int RECTANGLE_TOOL = 2;
	private final int CIRCLE_TOOL = 3;
	private final int SELECT_TOOL = 4;
	private final int TEXT_TOOL = 5;
	private final int ERASER_TOOL = 6;
	private final int FILL_TOOL = 7;

	private final int LINE = 1;
	private final int RECTANGLE = 2;
	private final int CIRCLE = 3;
	private final int TRIANGLE = 4;
	private final int TEXT = 5;

	BufferedImage canvas;
	Graphics2D graphics2D;
	private int activeTool = 0;

	private Stack<Shape> shapes;
	private Stack<Shape> removed;
	private Stack<Shape> preview;

	private int grouped;

	int x1, y1, x2, y2;
	private boolean dragged = false;
	private Color fillColor;
	private boolean transparent;
	private BasicStroke stroke = new BasicStroke((float) 2);
	
	private final List<GeoSegment> segments = new ArrayList<GeoSegment>();
	private final List<LabeledPoint> pois = new ArrayList<LabeledPoint>();
	private final List<ColoredPoint> points = new ArrayList<>();
	private double minEasting, maxEasting, minNorthing, maxNorthing;
	private double oEasting, oNorthing; // coordinates of the origin
	private double scale = -1;
	Insets ins;
	Color elementColor = Color.WHITE;

	@SuppressWarnings("unused")
	private Color currentColor = Color.WHITE;
	@SuppressWarnings("unused")
	private String currentShape = "POINT";

	ShowGpsFrame parent;
	private int inkPanelWidth;
	private int inkPanelHeight;

	public GeoPanel(ShowGpsFrame frame) {
		this.parent = frame;
		this.shapes = new Stack<Shape>();
		this.removed = new Stack<Shape>();
		this.grouped = 1;
		this.preview = new Stack<Shape>();
		this.transparent = true;
		// setMinimumSize(new Dimension(400, 300));
		// setPreferredSize(new Dimension(1600, 800));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		inkPanelWidth = dim.width - 150;
		inkPanelHeight = dim.height - 160;
		this.setSize(inkPanelWidth - 3, inkPanelHeight - 3);
		this.setPreferredSize(new Dimension(inkPanelWidth - 3, inkPanelHeight - 3));
		this.setLayout(null);
		this.printPaintPanelSize(inkPanelWidth, inkPanelHeight);
		setDoubleBuffered(true);
		setBackground(Color.BLACK);
		setFocusable(Boolean.TRUE);
		requestFocus();
		resetMinMaxEastingNorthing();
		addMouseWheelListener(new MouseWheelZoomer());
		MousePanner mousePanner = new MousePanner();
		addMouseListener(mousePanner);
		addMouseMotionListener(mousePanner);

	}

	public void setValues(String currentShape, Color currentColor) {

		this.currentShape = currentShape;
		this.currentColor = currentColor;
	}

	public synchronized void addPoint(ColoredPoint p) {
		this.points.add(p);
	}

	@Override
	protected synchronized void paintComponent(Graphics g_) {
		super.paintComponent(g_);

		Graphics2D g = (Graphics2D) g_.create();

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		System.out.println("Shapes no: "+shapes.size());
//		for (Shape s : shapes) {
//
//			g.setColor(s.getColor());
//			g.setStroke(s.getStroke());
//
//			if (s.getShape() == LINE) {
//				g.drawLine(s.getx1(), s.gety1(), s.getx2(), s.gety2());
//			} else if (s.getShape() == RECTANGLE) {
//
//				g.drawRect(s.getx1(), s.gety1(), s.getx2(), s.gety2());
//				if (s.transparent == false) {
//					g.setColor(s.getfillColor());
//					g.fillRect(s.getx1(), s.gety1(), s.getx2(), s.gety2());
//				}
//			} else if (s.getShape() == CIRCLE) {
//				g.drawOval(s.getx1(), s.gety1(), s.getx2(), s.gety2());
//				if (s.transparent == false) {
//					g.setColor(s.getfillColor());
//					g.fillOval(s.getx1(), s.gety1(), s.getx2(), s.gety2());
//				}
//			} else if (s.getShape() == TEXT) {
//				g.setFont(s.getFont());
//				g.drawString(s.getMessage(), s.getx1(), s.gety1());
//			}
//		}
//		
//		if (preview.size() > 0) {
//			System.out.println("Preview size: "+preview.size());
//			Shape s = preview.pop();
//			g.setColor(s.getColor());
//			g.setStroke(s.getStroke());
//			if (s.getShape() == LINE) {
//				g.drawLine(s.getx1(), s.gety1(), s.getx2(), s.gety2());
//
//			} else if (s.getShape() == RECTANGLE) {
//
//				g.drawRect(s.getx1(), s.gety1(), s.getx2(), s.gety2());
//				if (s.transparent == false) {
//					g.setColor(s.getfillColor());
//					g.fillRect(s.getx1(), s.gety1(), s.getx2(), s.gety2());
//				}
//			} else if (s.getShape() == CIRCLE) {
//				g.drawOval(s.getx1(), s.gety1(), s.getx2(), s.gety2());
//				if (s.transparent == false) {
//					g.setColor(s.getfillColor());
//					g.fillOval(s.getx1(), s.gety1(), s.getx2(), s.gety2());
//				}
//			}
//
//		}
//		
		
		int w = getWidth();
		int h = getHeight();
		ins = getInsets();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);

		if (this.scale == -1)
			scale();

		g.setColor(Color.YELLOW);

		for (GeoSegment seg : segments) {
			GeoPoint pA = seg.getPointA();
			GeoPoint pB = seg.getPointB();

			g.setColor(seg.getColor());
			g.setStroke(seg.getStroke());

			g.drawLine(convertX(pA.getEasting()), convertY(pA.getNorthing(), h), convertX(pB.getEasting()),
					convertY(pB.getNorthing(), h));
		}

		for (LabeledPoint poi : pois) {
			int x = convertX(poi.getEasting());
			int y = convertY(poi.getNorthing(), h);
			Color color = poi.getPointColor();
			Color strColor = poi.getStrColor();
			g.setColor(color);
			g.fillOval(x - 1, y - 1, 7, 7);
			g.setColor(strColor);
			g.drawString(poi.getLabel(), x, y);
		}

		for (ColoredPoint pt : points) {
			Color pointColor = pt.getPointColor();
			g.setColor(pointColor);
			int x = pt.x;
			int y = pt.y;
			Ellipse2D.Double pte = new Ellipse2D.Double(x, y, 5, 5);
			g.fill(pte);
		}

		g.setColor(Color.YELLOW);
		// unit is the unit of the scale. It must be a power of ten, such that unit *
		// scale in [25, 250]
		// scale = 5451.008257653895;
		double unit = Math.pow(10, Math.ceil(Math.log10(25 / scale)));
		System.out.println("Scale: " + scale + " Unit: " + unit);
		String strUnit;
		if (unit >= 1) {
			strUnit = ((int) unit) + " km";
		} else {
			strUnit = ((int) (1000 * unit)) + " m";
		}
		g.drawString(strUnit + " \u2194 " + ((int) (unit * scale)) + " px", 20, 20 + g.getFontMetrics().getHeight());
		// draw a 1-kilometer segment
		for (int i = 6; i <= 9; i++) {
			g.drawLine(20, i, 20 + (int) (unit * scale * (i < 8 ? 1 : .5)), i);
		}
	}

	public synchronized void clear() {
		this.segments.clear();
		this.pois.clear();

		resetMinMaxEastingNorthing();
	}

	public synchronized void addSegment(GeoSegment segment) {
		this.segments.add(segment);

		updateMinMaxEastingNorthing(segment.getPointA());
		updateMinMaxEastingNorthing(segment.getPointB());
	}

	public void addSegments(Collection<GeoSegment> segments) {
		for (GeoSegment seg : segments)
			addSegment(seg);
	}

	public synchronized void addLabeledPoint(LabeledPoint poi) {
		this.pois.add(poi);

		updateMinMaxEastingNorthing(poi);
	}

	private synchronized void updateMinMaxEastingNorthing(GeoPoint point) {
		double easting = point.getEasting();
		// System.out.println("EASTING "+easting);
		if (easting > maxEasting)
			maxEasting = easting;
		if (easting < minEasting)
			minEasting = easting;

		double northing = point.getNorthing();

		if (northing > maxNorthing)
			maxNorthing = northing;
		if (northing < minNorthing)
			minNorthing = northing;
	}

	private synchronized void resetMinMaxEastingNorthing() {
		minEasting = Double.MAX_VALUE;
		maxEasting = Double.MIN_VALUE;
		minNorthing = Double.MAX_VALUE;
		maxNorthing = Double.MIN_VALUE;

		this.scale = -1;
	}

	private synchronized void scale() {
		int w = getWidth();
		int h = getHeight();
		System.out.println("W: " + w + " h: " + h);
		System.out.println("maxEasting: " + maxEasting + " minEasting: " + minEasting);
		System.out.println("maxNorthing: " + maxNorthing + " minNorthing: " + minNorthing);
		// this.scale = Math.min(w / (maxEasting - minEasting), h / (maxNorthing -
		// minNorthing));
		// this.scale = Math.min(w / (maxEasting), h / (maxNorthing));
		this.scale = 3024.7405629830646;
		if (!pois.isEmpty()) {
			LabeledPoint lastAdded = pois.get(pois.size() - 1);
			oNorthing = lastAdded.getNorthing() - 0.200;
			oEasting = lastAdded.getEasting() - 0.100;
			// System.out.println("LAST::"+oEasting+" °°° "+oNorthing);
		} else {
			oEasting = 0;
			oNorthing = 0;
		}

	}

	private int applyScale(double km) {
		return (int) (km * scale);
	}

	private int convertX(double easting) {
		return applyScale(easting - oEasting);
	}

	private int convertY(double northing, int height) {
		return height - applyScale(northing - oNorthing);
	}

	class MouseWheelZoomer implements MouseWheelListener {
		private static final double zoomFactor = .05;

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			double oldScale = scale;

			int rotation = e.getWheelRotation();
			if (rotation > 0) {
				scale /= (1 + rotation * zoomFactor);
			} else {
				scale *= (1 - rotation * zoomFactor);
			}

			// When zooming, the easting/northing at the cursor position must
			// remain the same, so we have to pan in addition to changing the
			// scale. The maths for easting (same goes for northing):
			//
			// before: x = (easting - oEasting) * oldScale
			// after: x = (easting - newOEasting) * scale
			//
			// (x remains the same, easting remains the same)
			//
			// hence: newOEasting = easting - (easting - oEasting) * oldScale / scale
			// with: easting = x/scale + oEasting
			// hence finally: newOEasting = oEasting + x * (1/oldScale - 1/scale)
			int x = e.getX();
			int y = e.getY();
			int h = getHeight();

			oEasting = oEasting + x * (1 / oldScale - 1 / scale);
			oNorthing = oNorthing + (h - y) * (1 / oldScale - 1 / scale);

			System.out.println(rotation + " => " + scale);
			repaint();
		}
	}

	private class MousePanner implements MouseListener, MouseMotionListener {
		private int dragOriginX, dragOriginY;
		private double dragOriginOEasting, dragOriginONorthing;

		@Override
		public void mousePressed(MouseEvent e) {
			dragOriginX = e.getX();
			dragOriginY = e.getY();
			dragOriginOEasting = oEasting;
			dragOriginONorthing = oNorthing;
			
			printCoords(e);
			x1 = e.getX();
			y1 = e.getY();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int deltaX = e.getX() - dragOriginX;
			int deltaY = e.getY() - dragOriginY;

			oEasting = dragOriginOEasting - deltaX / scale;
			oNorthing = dragOriginONorthing + deltaY / scale;
			System.out.println("ActiveTool: "+activeTool);
			
			
			Color primary = currentColor;
			Color secondary = fillColor;
			if (SwingUtilities.isRightMouseButton(e)) {
				primary = secondary;
				secondary = currentColor;
			}
			printCoords(e);
			x2 = e.getX();
			y2 = e.getY();
			dragged = true;
			if(activeTool == LINE_TOOL) {
				preview.push(new Shape(x1, y1, x2, y2,Color.WHITE,stroke,1,Color.WHITE,transparent));
				repaint();
			}
			
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			printCoords(e);
		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			grouped++;
			// TODO Auto-generated method stub
			Color primary = currentColor;
			Color secondary = fillColor;
			if (SwingUtilities.isRightMouseButton(e)) {
				primary = secondary;
				secondary = currentColor;
			}

			if (activeTool == LINE_TOOL && dragged) {
				shapes.push(new Shape(x1, y1, x2, y2, primary, stroke, 1, fillColor, transparent));
				repaint();
				// graphics2D.drawLine(x1, y1, x2, y2);
			}
			
			dragged = false;
			removed.removeAllElements();
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

	public Color getElementColor() {
		return elementColor;
	}

	public void setElementColor(Color elementColor) {
		this.elementColor = elementColor;
	}

	public void printCoords(MouseEvent e) {
		String posX = String.valueOf((int) e.getPoint().getX());
		String posY = String.valueOf((int) e.getPoint().getY());
		parent.getCoordinateBar().getCoordinates().setText(posX + ",  " + posY + " px");
	}

	public void printPaintPanelSize(int width, int height) {
		parent.getCoordinateBar().getFrameSize().setText(width + ",  " + height + " px");
	}
	
	public void setTool(int tool) {
		this.activeTool = tool;
	}
	
	public void setTransparency(Boolean b) {
		this.transparent = b;
	}
	
	public void setThickness(float f) {
		stroke = new BasicStroke(f);
		graphics2D.setStroke(stroke);
	}
	
	public void setFillColor(Color c) {
		this.fillColor = c;
	}
	
	public void setColor(Color c) {
		currentColor = c;
		graphics2D.setColor(c);
	}
}
