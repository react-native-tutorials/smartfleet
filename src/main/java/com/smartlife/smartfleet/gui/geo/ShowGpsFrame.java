package com.smartlife.smartfleet.gui.geo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.smartlife.smartfleet.domain.ApplicationParameter;
import com.smartlife.smartfleet.domain.Dispositivo;
import com.smartlife.smartfleet.domain.Equipo;
import com.smartlife.smartfleet.domain.Estado;
import com.smartlife.smartfleet.domain.EstadoEquipo;
import com.smartlife.smartfleet.domain.GpsDispositivo;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.gui.CoordinateBar;
import com.smartlife.smartfleet.gui.SmartMainFrame;

public class ShowGpsFrame extends JFrame {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 8542370274071387544L;
	protected final Log logger = LogFactory.getLog(getClass());
	static final String FRAME_TITLE = "SmartFleet ~ Rastreo GPS";
	static final String CHOOSE_COLOR = "CHOOSE_COLOR";
	static final String DRAW_LOCATION = "DRAW_LOCATION";
	static final String DRAW_LINE = "DRAW_LINE";
	static final String DRAW_CIRCLE = "DRAW_CIRCLE";

	private CoordinateBar coordinateBar;
	private JPanel mainPane;
	private JButton btnChooseColor;
	private JToolBar toolBar;
	private JButton btnDrawLocation;
	private JButton btnDrawLine;
	private JButton btnDrawCircle;
	// private ShowGpsPanel gpsPanel;
	// private DrawPane drawPane;
	private GeoPanel map;
	ShowGpsFrameActionListener listener;
	SmartFacade smartFacade;
	Color chosenColor = Color.BLACK;
	String currentShape = "";
	int oX;
	int oY;
	private Long lastId;
	SmartMainFrame parent;
	Timer timer = new Timer();
	/**
	 * Create the frame.
	 */
	public ShowGpsFrame(SmartMainFrame mainFrame) {
		this.parent = mainFrame;
		this.smartFacade = (SmartFacade)mainFrame.getContext().getBean("smartFacade");
		this.listener = new ShowGpsFrameActionListener(this);
		setTitle(FRAME_TITLE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1600, 800);
		setContentPane(getMainPane());
		setLocationRelativeTo(null);
		
		displayInitPoints();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parent.setVisible(Boolean.TRUE);
			}
		});
		
		ApplicationParameter appParam = smartFacade.findParameter("GPS_TIMER");

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				lastId = getLastId();
				List<GpsDispositivo> newOnes = smartFacade.findAllGpsParam(lastId);
				if (!newOnes.isEmpty()) {
					
					addLabelsToWindow(newOnes, null, Color.GREEN, false);
				}
			}
		};
		timer.schedule(task, 0, appParam.getIntValue());
	}

	private void displayInitPoints() {
		List<GpsDispositivo> allGps = smartFacade.findAllGps();
		logger.info("All GPS points "+allGps.size());
		boolean isInit = false;
		if (!allGps.isEmpty()) {
			isInit = true;
			addSegment(new GeoSegment(new GeoPoint(0, 0), new GeoPoint(0, 0), Color.BLACK));
			// smWin.addLabeledPoint(new LabeledPoint( -12.09634, -77.06790, "HOME",
			// Color.WHITE));
			addLabelsToWindow(allGps, this, Color.WHITE, isInit);
		}
	}
	
	public void addLabelsToWindow(List<GpsDispositivo> allGps, ShowGpsFrame smWin, Color color, boolean isInit) {
		final GpsDispositivo lastIdDisp = smartFacade.findPreviousGpsLocation(lastId);

		for (GpsDispositivo gps : allGps) {
			lastId = gps.getId();
			
//			DISPOSITIVO DISP = GPS.GETDISPOSITIVO();
//			EQUIPO EQUIPO = SMARTFACADE.FINDEQUIPOBYDISPASSIGNED(DISP);
//			EstadoEquipo equiState = smartFacade.findLastEstEquiByEquipo(equipo.getId());
//			if(equiState!=null) {
//				logger.info("ID equiState: "+equiState.getId());
//				Estado estado = smartFacade.findEstadoByEquiState(equiState.getId());
//				final int colorInt = estado.getColor();
//				logger.info(colorInt);
//				color = new Color(colorInt);
//			}
			
			
			double ltd = Double.parseDouble(gps.getLatitude());
			double lng = Double.parseDouble(gps.getLongitud());
			LabeledPoint labeledGps = new LabeledPoint(ltd, lng, "", color);
			addLabeledPoint(labeledGps);

			if (!isInit) {
				final double lastIdltd = Double.parseDouble(lastIdDisp.getLatitude());
				final double lastIdlng = Double.parseDouble(lastIdDisp.getLongitud());
				LabeledPoint lastIdLabel = new LabeledPoint(lastIdltd, lastIdlng, "", color);

				addLabeledPoint(lastIdLabel);
				// GeoSegment segment = new GeoSegment(lastIdLabel, labeledGps, Color.ORANGE);
				// smWin.addSegment(segment);
			}
			getMap().repaint();
		}
		setLastId(lastId);
	}

	public JPanel getMainPane() {
		if (mainPane == null) {
			mainPane = new JPanel();
			mainPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			mainPane.setLayout(new BorderLayout(0, 0));
			// mainPane.add(getToolBar(), BorderLayout.NORTH);
			// mainPane.add(getDrawPane(), BorderLayout.CENTER);
			// create coordinate bar at the bottom
			coordinateBar = new CoordinateBar();
			toolBar = (new GeoToolbar(this)).getToolBar();
			mainPane.add(toolBar, BorderLayout.PAGE_START);
			mainPane.add(coordinateBar, BorderLayout.PAGE_END);
			mainPane.add(getMap(), BorderLayout.CENTER);
		}
		return mainPane;
	}

	public CoordinateBar getCoordinateBar() {
		return this.coordinateBar;
	}

	public JToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new JToolBar();
			toolBar.setBackground(Color.BLACK);
			toolBar.setFloatable(false);
			toolBar.add(getBtnChooseColor());
			toolBar.add(getBtnDrawLocation());
			// toolBar.add(getBtnDrawLine());
			// toolBar.add(getBtnDrawCircle());
		}
		return toolBar;
	}

	public JButton getBtnChooseColor() {
		if (btnChooseColor == null) {
			btnChooseColor = new JButton("Choose Color");
			btnChooseColor.setForeground(Color.WHITE);
			btnChooseColor.setBackground(Color.BLACK);
			btnChooseColor.setActionCommand(CHOOSE_COLOR);
			btnChooseColor.addActionListener(listener);
		}
		return btnChooseColor;
	}

	public JButton getBtnDrawLocation() {
		if (btnDrawLocation == null) {
			btnDrawLocation = new JButton("Draw Location");
			btnDrawLocation.setBackground(Color.BLACK);
			btnDrawLocation.setForeground(Color.WHITE);
			btnDrawLocation.setActionCommand(DRAW_LOCATION);
			btnDrawLocation.addActionListener(listener);
		}
		return btnDrawLocation;
	}

	public JButton getBtnDrawLine() {
		if (btnDrawLine == null) {
			btnDrawLine = new JButton("Draw Line");
			btnDrawLine.setBackground(Color.BLACK);
			btnDrawLine.setForeground(Color.WHITE);
			btnDrawLine.setActionCommand(DRAW_LINE);
			btnDrawLine.addActionListener(listener);
		}
		return btnDrawLine;
	}

	public JButton getBtnDrawCircle() {
		if (btnDrawCircle == null) {
			btnDrawCircle = new JButton("Draw Circle");
			btnDrawCircle.setForeground(Color.WHITE);
			btnDrawCircle.setBackground(Color.BLACK);
			btnDrawCircle.setActionCommand(DRAW_CIRCLE);
			btnDrawCircle.addActionListener(listener);
		}
		return btnDrawCircle;
	}

	/**
	 * 
	 */
	public void chooseColor() {
		chosenColor = JColorChooser.showDialog(this, "Choose Color", Color.YELLOW);
		btnChooseColor.setForeground(chosenColor);
	}

	// public DrawPane getDrawPane() {
	// if(drawPane == null) {
	// drawPane = new DrawPane();
	// drawPane.addMouseListener(new MouseAdapter() {
	// @Override
	// public void mouseClicked(MouseEvent e) {
	// super.mouseClicked(e);
	// oX = e.getX();
	// oY = e.getY();
	// drawPane.setValues(currentShape, chosenColor);
	// ColoredPoint coloredPoint = new ColoredPoint(chosenColor);
	// coloredPoint.setLocation(oX, oY);
	// drawPane.addPoint(coloredPoint);
	// drawPane.repaint();
	// }
	// });
	// }
	// return drawPane;
	// }

	public void drawPoint() {
		currentShape = "POINT";
	}

	public void drawLine() {
		currentShape = "LINE";
	}

	public GeoPanel getMap() {
		if (map == null) {
			map = new GeoPanel(this);
//			map.addMouseListener(new MouseAdapter() {
//				@Override
//				public void mouseClicked(MouseEvent e) {
//					super.mouseClicked(e);
//					oX = e.getX();
//					oY = e.getY();
//					map.setValues(currentShape, chosenColor);
//					ColoredPoint coloredPoint = new ColoredPoint(chosenColor);
//			coloredPoint.setLocation(oX, oY);
//					if (currentShape == "POINT") {
//						map.addPoint(coloredPoint);
//					}
//					if (currentShape == "LINE") {
//
//					}
//					map.repaint();
//				}
//			});

		}
		return map;
	}

	protected void printCoords(MouseEvent e) {
		String posX = String.valueOf((int) e.getPoint().getX());
    	String posY = String.valueOf((int) e.getPoint().getY());
    	getCoordinateBar().getCoordinates().setText(posX + ",  " + posY + " px");
	}

	// public void addColoredPoint(ColoredPoint point) {
	// drawPane.addPoint(point);
	// }
	//
	// public void addScaledCoords(List<Coordinates> allScaled) {
	// drawPane.addScaledPoints(allScaled);
	// }
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

	public Color getChosenColor() {
		return chosenColor;
	}

}
