/**
 * 
 */
package com.smartlife.smartfleet.engine;

import java.awt.Color;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.support.AbstractRefreshableApplicationContext;

import com.smartlife.smartfleet.domain.Dispositivo;
import com.smartlife.smartfleet.domain.Equipo;
import com.smartlife.smartfleet.domain.GpsDispositivo;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.gui.SmartMainFrame;
import com.smartlife.smartfleet.utils.Constants;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class WorkGUI extends JDialog implements Work {
	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 8224480265928997170L;
	protected final Log logger = LogFactory.getLog(getClass());

	enum JSON_ATTRIBUTES {
		dato, id_equipo, zona_utm, x, y, z, velocidad, longitud, latitud, mensaje
	};
	static final String FRAME_TITLE = "SmartFleet  ~ Transacciones";
	static final String GPS_MSG_TITLE = "ubicacion";
	static final String FACADE_BEAN_NAME ="smartFacade";
	
	
	public ServerSocket serverSocket;
	public Socket socket;
	private Scanner inputScanner;
	@SuppressWarnings("unused")
	private DataOutputStream out;
	private int port;
	private AbstractRefreshableApplicationContext context;
	private SmartFacade smartFacade;
	private JScrollPane scrollPane;
	private JTextArea txtMessages;

	public WorkGUI(final SmartMainFrame parentComponent, final int portNo) {
		this.context = parentComponent.getContext();
		this.port = portNo;
		this.smartFacade = (SmartFacade) context.getBean(FACADE_BEAN_NAME);
		setTitle(FRAME_TITLE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 300);
		final int x = parentComponent.getX();
		final int y = parentComponent.getY() + parentComponent.getHeight();
		setLocation(x, y);
		setVisible(Boolean.TRUE);
		setResizable(Boolean.FALSE);
		setIconImage(new ImageIcon(WorkGUI.class.getResource(Constants.ICON_PATH)).getImage());
		JPanel container = (JPanel) getContentPane();
		container.setBackground(Color.BLACK);
		container.setForeground(Color.WHITE);
		container.add(getScrollPane());
		openChannel();
	}

	/**
	 * 
	 */
	private void openChannel() {
		try {
			serverSocket = new ServerSocket(port);
			new Thread(() -> {
				try {
					// accept() blocks the current thread, so must be called on a background thread
					while (true) {
						if (!serverSocket.isClosed()) {
							socket = serverSocket.accept();
							inputScanner = new Scanner(socket.getInputStream());
							out = new DataOutputStream(socket.getOutputStream());
							new Worker(inputScanner, this).execute();
						}
					}
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}).start();
		} catch (IOException e) {
			logger.info(e.getMessage(), e);
		}
	}

	@Override
	public void workLine(String line) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
		Date currentDate = new Date();
		String dateStr = dateFormat.format(currentDate);
		String timeStr = timeFormat.format(currentDate);
		String message = StringUtils.EMPTY;
		JSONParser parser = new JSONParser();
		try {
			if (line.indexOf("\\") > 0) {
				line = line.replaceAll("\\", StringUtils.EMPTY);
			}

			logger.info("Server:" + line);
			line = line.replaceAll("�", StringUtils.EMPTY);

			JSONObject json = (JSONObject) parser.parse(line);
			logger.info(json.values());
			String discriminator = json.get(JSON_ATTRIBUTES.dato.toString()).toString();
			logger.info("Discriminator " + discriminator);
			
			if (discriminator!=null && GPS_MSG_TITLE.equalsIgnoreCase(discriminator)) {
				final String idStr = json.get(JSON_ATTRIBUTES.id_equipo.toString()).toString();
				final String zonaStr = json.get(JSON_ATTRIBUTES.zona_utm.toString()).toString();
				final String xStr = json.get(JSON_ATTRIBUTES.x.toString()).toString();
				final String yStr = json.get(JSON_ATTRIBUTES.y.toString()).toString();
				final String zStr = json.get(JSON_ATTRIBUTES.z.toString()).toString();
				final String latitudStr = json.get(JSON_ATTRIBUTES.latitud.toString()).toString();
				final String longitudStr = json.get(JSON_ATTRIBUTES.longitud.toString()).toString();
				final String speedStr = json.get(JSON_ATTRIBUTES.velocidad.toString()).toString();
				logger.info(idStr + ";" + zonaStr + ";" + xStr + ";" + yStr + ";" + zStr + ";" + speedStr + ";"
						+ latitudStr + ";" + longitudStr);

				GpsDispositivo gps = new GpsDispositivo();
				Equipo equipo = smartFacade.findEquipoById(Long.parseLong(idStr));

				if (equipo == null) {
					logger.info("dispositivo is null");
					JOptionPane.showMessageDialog(this, "Equipo no registrado", Constants.MSG_TITLE,
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Dispositivo dispositivo = smartFacade.findDispByEquiAssigned(equipo);
				if (dispositivo != null) {
					logger.info(dispositivo.toString());
					gps.setDispositivo(dispositivo);
					gps.setLatitude(latitudStr);
					gps.setLongitud(longitudStr);
					gps.setAltitud(zStr);
					gps.setVelocidad(speedStr);
					gps.setFechaGps(currentDate);
					gps.setxUtm(xStr);
					gps.setyUtm(yStr);
					smartFacade.saveGps(gps);
					message = "\n#:" + dateStr + "|" + timeStr + " --> equipo cdg: " + equipo.getCodigoEquipo()
							+ " esta en la ubicacion (X-Y-Z) " + xStr + "-" + yStr + "-" + zStr + " ---";

				} else {
					logger.error("dispositivo is null");
					JOptionPane.showMessageDialog(this, "Dispositivo no registrado", Constants.MSG_TITLE,
							JOptionPane.ERROR_MESSAGE);
				}
			}else {
				message = "\n#:" + dateStr + "|" + timeStr + " --> "+json.get(JSON_ATTRIBUTES.mensaje.toString()).toString();
				logger.info("Differente message: "+message);
			}

		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			message = "Received message: "+line;
			JOptionPane.showMessageDialog(this, "Character invalido", Constants.MSG_TITLE, JOptionPane.ERROR_MESSAGE);
		}
		if (!StringUtils.EMPTY.equals(message)) {
			getTxtMessages().append(message);
		}
		getTxtMessages().setCaretPosition(getTxtMessages().getDocument().getLength());
	}

	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getTxtMessages());
			scrollPane.setOpaque(Boolean.FALSE);
			scrollPane.setBackground(Color.black);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			JScrollBar verticalScroll = scrollPane.getVerticalScrollBar();
			verticalScroll.setValue(verticalScroll.getMaximum());
			scrollPane.setVerticalScrollBar(verticalScroll);
		}
		return scrollPane;
	}

	public JTextArea getTxtMessages() {
		if (txtMessages == null) {
			txtMessages = new JTextArea(20, 20);
			txtMessages.setForeground(Color.YELLOW);
			txtMessages.setOpaque(Boolean.TRUE);
			txtMessages.setBackground(Color.BLACK);
			txtMessages.setEditable(Boolean.FALSE);
		}
		return txtMessages;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

}