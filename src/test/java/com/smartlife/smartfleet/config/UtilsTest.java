/**
 * 
 */
package com.smartlife.smartfleet.config;

import java.io.File;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smartlife.smartfleet.dao.DispositivoDAO;
import com.smartlife.smartfleet.dao.EquipoDAO;
import com.smartlife.smartfleet.dao.EstadoDAO;
import com.smartlife.smartfleet.dao.TipoDAO;
import com.smartlife.smartfleet.dao.UserDAO;
import com.smartlife.smartfleet.domain.Dispositivo;
import com.smartlife.smartfleet.domain.Estado;
import com.smartlife.smartfleet.domain.GpsDispositivo;
import com.smartlife.smartfleet.domain.Razon;
import com.smartlife.smartfleet.domain.common.Tipo;
import com.smartlife.smartfleet.domain.security.Rol;
import com.smartlife.smartfleet.domain.security.UserRol;
import com.smartlife.smartfleet.domain.security.Usuario;
import com.smartlife.smartfleet.dto.Coordinates;
import com.smartlife.smartfleet.dto.EquipmentDetail;
import com.smartlife.smartfleet.dto.StateDetail;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class UtilsTest {
	protected final Log logger = LogFactory.getLog(getClass());
	
	AbstractRefreshableApplicationContext context;
	
	@Before
	public void setUp() {
		context = new ClassPathXmlApplicationContext(
				"com/smartlife/smartfleet/config/appcontext.xml");
	}
	
	@Test
	public void testClassPath() {
		URL fileUrl = getClass().getResource("/hibernate-configuration-3.0.dtd");
		File file = new File(fileUrl.getFile());
		boolean itExists = file.exists();
		Assert.assertTrue(itExists);
		Assert.assertTrue(Boolean.TRUE);
	}

	@Test
	public void initContext() {
		Assert.assertTrue(Boolean.TRUE);
	}	
	
	@Test
	public void testUserDAO() {
		UserDAO userDAO = (UserDAO)context.getBean("userDAO");
		Usuario user = userDAO.findUserByName("marius.grigoras");
		List<UserRol> userRoles = user.getUserRoles();
		logger.info("ACTIVE "+userRoles.size());
		for(UserRol userrol : userRoles) {
			Rol rol = userrol.getRol();
			logger.info("ROL "+rol.getRolNom());
		}
		Assert.assertTrue(Boolean.TRUE);
	}
	
	@Test
	public void loadSpringContext() {
		
//		OperadorDAO opeDAO = (OperadorDAO)context.getBean("operadorDAO");
//		List<Operador> all = opeDAO.findAllOperadores();
//		Assert.assertTrue(all.size()==2);
//		
//		Operador opeA = opeDAO.findOperadorByCdg("OPE-02");
//		logger.info(opeA.toString());
//		Assert.assertNotNull(opeA);
//		
//		opeDAO.deleteOperador(opeA);
//		logger.info("delete operador complete");
		
//		Operador ope = new Operador("1234", "Frank", "Delone");
//		opeDAO.saveOperador(ope);
//		List<Operador> opeAll = opeDAO.findAllOperadores();
//		Assert.assertTrue(opeAll.size()==2);
		
//		TurnoDAO turnoDAO = (TurnoDAO)contxt.getBean("turnoDAO");
//		
//		Turno turno = new Turno();
//		turno.setNomTurno("TURNO MA�ANA");
//		turno.setHoras(8);
//		turno.setHoraIni(fromStringToTime("06:00"));
//		turno.setHoraFin(fromStringToTime("14:00"));
//		turnoDAO.saveTurno(turno);
//		
//		Turno turnoMa�ana = turnoDAO.findByName("TURNO MA�ANA");
//		logger.info(turnoMa�ana.toString());
//		
//		turnoDAO.deleteTurno(turnoMa�ana);
//		logger.info("delete turno complete");
//	
//
//		Turno tM= turnoDAO.findByName("TURNO MAN");
//		tM.setNomTurno("TURNO MA�ANA");
//		tM.setHoraIni(fromStringToTime("06:00"));
//		turnoDAO.updateTurno(tM);
//		
//		List<Turno> allTurno = turnoDAO.findAll();
//		int allSize = allTurno.size();
//		Assert.assertEquals(3, allSize);
//		
//		TipoDAO tipoDAO = (TipoDAO)contxt.getBean("tipoDAO");
//		List<Tipo> allTipos = tipoDAO.findAll();
//		int allTiposSize = allTipos.size();
//		Assert.assertEquals(6, allTiposSize);
//		
		Assert.assertTrue(Boolean.TRUE);
	}
	
	@Test
	public void estadoTest() {
		
		EstadoDAO estadoDAO = (EstadoDAO) context.getBean("estadoDAO");
		TipoDAO tipoDAO = (TipoDAO) context.getBean("tipoDAO");
		Estado estado = new Estado();
		Tipo tipo = tipoDAO.findTipoById("TE_EQUI");
		estado.setCategoria(tipo);
		estado.setEstado("NUEVO ESTADOS");
		List<Razon> razones = new ArrayList<Razon>();
		Razon razon = new Razon();
		razon.setEstado(estado);
		razon.setRazon("NUEVO RAZON");
		razones.add(razon);
		estado.setRazones(razones);
		estadoDAO.saveEstado(estado);
		
		Assert.assertTrue(Boolean.TRUE);
	}
	
	@Test
	public void stateDetailTest() {
		EstadoDAO estadoDAO = (EstadoDAO) context.getBean("estadoDAO");
		List<StateDetail> details = estadoDAO.findStatesDetail();
		for(StateDetail det : details) {
			logger.info(det.toString());
		}
		Assert.assertTrue(Boolean.TRUE);
	}
	
	
	public Time fromStringToTime(String str) {
		DateFormat formatter = new SimpleDateFormat("HH:mm");
		Time timeValue = null;
		try {
			timeValue = new Time(formatter.parse(str).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timeValue;
	}
	
	@Test
	public void dispEquiTest() {
		DispositivoDAO dispDAO = (DispositivoDAO)context.getBean("dispositivoDAO");
		Dispositivo disp = dispDAO.findDispByEquiAssigned(2L);
		logger.info(disp);
		Assert.assertTrue(Boolean.TRUE);
	}
	
	@Test
	public void allEquipTest() {
		EquipoDAO equiDAO = (EquipoDAO)context.getBean("equipoDAO");
		List<EquipmentDetail> equiDetail = equiDAO.findAllEquipments();
		for(EquipmentDetail item : equiDetail) {
			logger.info(item.toString());
		}
		Assert.assertTrue(Boolean.TRUE);
	}
	
	@Test
	public void allGps() {
		final int maxLatitudeInPixels = 1200;
	    final int maxLongitudeInPixels = 700;
		
	    DispositivoDAO dispDAO = (DispositivoDAO)context.getBean("dispositivoDAO");
		
	    List<GpsDispositivo> allGps = dispDAO.findAllGps();
		double maxDistance = Double.MIN_VALUE;
		int indexOfLargestDistance = 0;
		int index = 0;
		for(GpsDispositivo item : allGps) {
			double latitud = Math.abs(Double.parseDouble(item.getLatitude()));
			double longitud = Math.abs(Double.parseDouble(item.getLongitud()));
			double distanceSquared = longitud * longitud + latitud*latitud;
	
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
		for(GpsDispositivo item : allGps) {
			double latitud = Double.parseDouble(item.getLatitude())+displaceLatitude;
			double longitud = Double.parseDouble(item.getLongitud())+displaceLongitud;
			
			Coordinates coord = new Coordinates(latitud, longitud);
			coords.add(coord);
			
			if(latitud>maxLatitude) {
				maxLatitude = latitud;
			}
			if(longitud>maxLongitude) {
				maxLongitude = longitud;
			}
		}
		
		double latitudeScale = maxLatitudeInPixels / maxLatitude;
        double longitudeScale = maxLongitudeInPixels / maxLongitude;
        logger.info("latitudeScale: "+latitudeScale+" longitudeScale: "+longitudeScale);
       
        List<Coordinates> scaledCoords = new ArrayList<>();
        for(Coordinates ite : coords) {
        	double scaleLat = ite.getLatitud()*latitudeScale;
        	double scaleLong = ite.getLongitud()*longitudeScale;
        	Coordinates scaledCoord = new Coordinates(scaleLat, scaleLong);
        	scaledCoords.add(scaledCoord);
        	logger.info(scaledCoord.toString());
        }
        
	}
	
//	private class Coordinates{
//		double latitud;
//		double longitud;
//		public Coordinates(double lat, double lng) {
//			this.latitud = lat;
//			this.longitud = lng;
//		}
//		public double getLatitud() {
//			return latitud;
//		}
//		public double getLongitud() {
//			return longitud;
//		}
//		@Override
//		public String toString() {
//			return "L:"+latitud+" | l:"+longitud;
//		}
//	}
	
//	private class ScaledPanel extends JPanel{
//		
//		List<Coordinates> scaledCoords;
//		
//		@Override
//	    @Transient
//	    public Color getBackground() {
//	        return Color.black;
//	    }
//		
//		@Override
//	    @Transient
//	    public Dimension getPreferredSize() {
//	        return new Dimension(1280, 720);
//	    }
//		
//		@Override
//	    protected void paintComponent(Graphics g) {
//			super.paintComponent(g);
//			Graphics2D g2d = (Graphics2D) g.create();
//			
//			for(Coordinates scc : getScaledCoords()) {
//				double newLat = scc.getLatitud();
//				double newLong = scc.getLongitud();
//				
//				Ellipse2D.Double point = new Ellipse2D.Double(newLat,
//						newLong, 5, 5);
//				g2d.setColor(Color.white);
//				g2d.fill(point);
//			}
//		}
//
//		public List<Coordinates> getScaledCoords() {
//			return scaledCoords;
//		}
//
//		public void setScaledCoords(List<Coordinates> scaledCoords) {
//			this.scaledCoords = scaledCoords;
//		}
//		
//		public void addScaledCoords(List<Coordinates> coords) {
//			this.scaledCoords.addAll(coords);
//		}
//	}
//	
//	private class SclaedFrame extends JFrame{
//		ScaledPanel panel;
//		
//		public SclaedFrame() {
//			panel = new ScaledPanel();
//			setLayout(new BorderLayout());
//			add(panel, BorderLayout.CENTER);
//			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			setLocationRelativeTo(null);
//		}
//		
//		public void addScaledCoords(List<Coordinates> coords) {
//			this.panel.addScaledCoords(coords);
//		}
//	}
}
