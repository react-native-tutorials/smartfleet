/**
 * 
 */
package com.smartlife.smartfleet.gui.geo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smartlife.smartfleet.dao.DispositivoDAO;
import com.smartlife.smartfleet.domain.GpsDispositivo;
import com.smartlife.smartfleet.dto.Coordinates;
import com.smartlife.smartfleet.gui.InitSession;
import com.smartlife.smartfleet.gui.SmartMainFrame;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class ShowGpsFrameDriver {
	static final Log logger = LogFactory.getLog(ShowGpsFrame.class);
	final int maxLatitudeInPixels = 1200;
	final int maxLongitudeInPixels = 700;

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		ShowGpsFrameDriver driver = new ShowGpsFrameDriver();
		List<Coordinates> scaledCoords = driver.getScaledCoordinates();
		List<GpsDispositivo> allGps = driver.allGps();
		SwingUtilities.invokeLater(() -> {
			SmartMainFrame mainFrame = new SmartMainFrame(new InitSession());
			ShowGpsFrame frame = new ShowGpsFrame(mainFrame);
			frame.setVisible(Boolean.TRUE);
			frame.addSegment(new GeoSegment(new GeoPoint(0, 0), new GeoPoint(0, 0), Color.BLACK));
			for (GpsDispositivo gps : allGps) {

				double ltd = Double.parseDouble(gps.getLatitude());
				double lng = Double.parseDouble(gps.getLongitud());
				LabeledPoint labeledGps = new LabeledPoint(ltd, lng, "", Color.WHITE);
				frame.addLabeledPoint(labeledGps);
			}
			
			
			// ColoredPoint point = new ColoredPoint(Color.WHITE);
			// Dimension dimension = frame.getDrawPane().getPreferredSize();
			// logger.info(dimension.getHeight()+" x "+dimension.getWidth());
			// int x = 400;
			// int y = 300;
			// point.setLocation(x, y);
			// frame.getDrawPane().addPoint(point);
//			frame.addScaledCoords(scaledCoords);
			});
	}
	
	@SuppressWarnings("resource")
	public List<GpsDispositivo> allGps(){
		AbstractRefreshableApplicationContext context = new ClassPathXmlApplicationContext(
				"com/smartlife/smartfleet/config/appcontext.xml");
		DispositivoDAO dispDAO = (DispositivoDAO) context.getBean("dispositivoDAO");

		List<GpsDispositivo> allGps = dispDAO.findAllGps();
		return allGps;
	}

	@SuppressWarnings("resource")
	public List<Coordinates> getScaledCoordinates() {
		AbstractRefreshableApplicationContext context = new ClassPathXmlApplicationContext(
				"com/smartlife/smartfleet/config/appcontext.xml");
		DispositivoDAO dispDAO = (DispositivoDAO) context.getBean("dispositivoDAO");

		List<GpsDispositivo> allGps = dispDAO.findAllGps();
		double maxDistance = Double.MIN_VALUE;
		int indexOfLargestDistance = 0;
		int index = 0;
		for (GpsDispositivo item : allGps) {
			double latitud = Math.abs(Double.parseDouble(item.getLatitude()));
			double longitud = Math.abs(Double.parseDouble(item.getLongitud()));
			double distanceSquared = longitud * longitud + latitud * latitud;

			if (distanceSquared > maxDistance) {
				maxDistance = distanceSquared;
				indexOfLargestDistance = index;
			}
			index++;
		}

		double displaceLatitude = -Double.parseDouble(allGps.get(indexOfLargestDistance).getLatitude());
		double displaceLongitud = -Double.parseDouble(allGps.get(indexOfLargestDistance).getLongitud());

		double maxLatitude = Double.MIN_VALUE, maxLongitude = Double.MIN_VALUE;

		List<Coordinates> coords = new ArrayList<>();
		for (GpsDispositivo item : allGps) {
			double latitud = Double.parseDouble(item.getLatitude()) + displaceLatitude;
			double longitud = Double.parseDouble(item.getLongitud()) + displaceLongitud;

			Coordinates coord = new Coordinates(latitud, longitud);
			coords.add(coord);

			if (latitud > maxLatitude) {
				maxLatitude = latitud;
			}
			if (longitud > maxLongitude) {
				maxLongitude = longitud;
			}
		}

		double latitudeScale = maxLatitudeInPixels / maxLatitude;
		double longitudeScale = maxLongitudeInPixels / maxLongitude;
		double scale = Math.min(latitudeScale, longitudeScale);
		logger.info("Scale "+scale);
		List<Coordinates> scaledCoords = new ArrayList<>();
		for (Coordinates ite : coords) {
			double scaleLat = ite.getLatitud() * latitudeScale;
			double scaleLong = ite.getLongitud() * longitudeScale;
			Coordinates scaledCoord = new Coordinates(scaleLat, scaleLong);
			scaledCoords.add(scaledCoord);
		}
		return scaledCoords;
	}
}
