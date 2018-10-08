/**
 * 
 */
package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractRefreshableApplicationContext;

import com.smartlife.smartfleet.domain.ApplicationParameter;
import com.smartlife.smartfleet.domain.GpsDispositivo;
import com.smartlife.smartfleet.domain.security.Usuario;
import com.smartlife.smartfleet.engine.WorkGUI;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.gui.geo.GeoPoint;
import com.smartlife.smartfleet.gui.geo.GeoSegment;
import com.smartlife.smartfleet.gui.geo.LabeledPoint;
import com.smartlife.smartfleet.gui.geo.ShowGpsFrame;
import com.smartlife.smartfleet.ui.common.ColloredButton;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class MainMenuFrame extends JFrame {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -1252187142020565746L;
	protected final Log logger = LogFactory.getLog(getClass());
	final static String EXIT_CMD = "EXIT";

	AbstractRefreshableApplicationContext context;
	SmartFacade smartFacade;

	private ColloredButton btnConfig;
	private ColloredButton btnDumpRecords;
	private ColloredButton btnFuelRecords;
	private ColloredButton btnGpsUtil;
	private ColloredButton btnLoadRec;
	private ColloredButton btnExit;
	private ColloredButton btnTrammedLoads;
	private ColloredButton btnWatterRecords;
	private ColloredButton btnLogger;

	private WorkGUI wGui;
	private ShowGpsFrame smWin;
	private ConfigFrame configFrame;

	Timer timer = new Timer();
	Long lastId = 0L;
	private JLabel lblUsuario;
	Usuario usuario;
	InitSession initSession;
	MainMenuFrameActionListener listener;

	// public MainMenuFrame(InitSession parent) {
	//
	// this.initSession = parent;
	// this.context = parent.getContext();
	// this.usuario = parent.getUsuario();
	// }

	public MainMenuFrame(InitSession parent) {
		this.initSession = parent;
		this.usuario = parent.getUsuario();
		logger.info("ENTER MAIN MENU FRAME");
		this.smartFacade = parent.getFacade();
		logger.info("Smart Facade created");
		this.listener = new MainMenuFrameActionListener(this);
		logger.info("Listener created");
		setTitle("SmartFleet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(MainMenuFrame.class.getResource("/icons/SF_ico_32x32.png")).getImage());
//		setLocationByPlatform(Boolean.TRUE);
		setBounds(100,100,474, 720);
		setResizable(false);

		JPanel container = (JPanel) getContentPane();
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		container.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		JLabel lblSmartfleet = new JLabel("Módulo Gráfico");
		lblSmartfleet.setForeground(Color.WHITE);
		lblSmartfleet.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblSmartfleet.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSmartfleet.setHorizontalAlignment(SwingConstants.CENTER);
		lblSmartfleet.setBounds(0, 16, 450, 29);
		lblSmartfleet.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblSmartfleet);

		panel.add(getBtnConfig());
		panel.add(getBtnDumpRecords());
		panel.add(getBtnFuelRecords());
		panel.add(getBtnGpsUtil());
		panel.add(getBtnLoadRec());
		panel.add(getBtnExit());
		panel.add(getBtnTrammedLoads());
		panel.add(getBtnWatterRecords());
		panel.add(getBtnLogger());

		JLabel lblReportes = new JLabel("Módulo Reportes");
		lblReportes.setHorizontalTextPosition(SwingConstants.CENTER);
		lblReportes.setHorizontalAlignment(SwingConstants.CENTER);
		lblReportes.setForeground(Color.WHITE);
		lblReportes.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblReportes.setAlignmentX(0.5f);
		lblReportes.setBounds(0, 350, 450, 29);
		panel.add(lblReportes);

		JLabel lblModuloAdministracin = new JLabel("Módulo Administración");
		lblModuloAdministracin.setHorizontalTextPosition(SwingConstants.CENTER);
		lblModuloAdministracin.setHorizontalAlignment(SwingConstants.CENTER);
		lblModuloAdministracin.setForeground(Color.WHITE);
		lblModuloAdministracin.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblModuloAdministracin.setAlignmentX(0.5f);
		lblModuloAdministracin.setBounds(0, 176, 450, 29);
		panel.add(lblModuloAdministracin);
		panel.add(getLblUsuario());
		logger.info("Before show worker window");
		showWorkerWindow();
//		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosed(WindowEvent e) {
//				MainMenuFrame mainFrame = (MainMenuFrame) e.getWindow();
//				Timer mainTimer = mainFrame.timer;
//				mainTimer.cancel();
//			}
//		});
		logger.info("END CREATED UI");
		setVisible(Boolean.TRUE);
	}

	/**
	 * 
	 */
	private void showWorkerWindow() {
		logger.info("SHOW WORKER WINDOW");
		ApplicationParameter appParam = smartFacade.findParameter("APP_PORT");
		if (appParam != null) {
			SwingUtilities.invokeLater(() -> {
//				wGui = new WorkGUI(context, "SmartFleet - Transacciones", appParam.getIntValue());
//				wGui.setLocationRelativeTo(this);
//				wGui.setVisible(Boolean.TRUE);
			});
		}
	}

	public ColloredButton getBtnConfig() {
		if (btnConfig == null) {
			btnConfig = new ColloredButton("Configuracion");
			btnConfig.setBorder(null);
			btnConfig.setText("Configuración");
			btnConfig.setForeground(Color.WHITE);
			btnConfig.setBackground(Color.BLACK);
			btnConfig.setBounds(0, 225, 150, 100);
			btnConfig.setHorizontalTextPosition(SwingConstants.CENTER);
			btnConfig.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnConfig.setIcon(new ImageIcon(MainMenuFrame.class.getResource("/icons/settings.png")));
			btnConfig.setVerticalTextPosition(JButton.BOTTOM);
			btnConfig.setPreferredSize(new Dimension(147, 29));
			btnConfig.setEnabled(Boolean.FALSE);
			btnConfig.addActionListener(e -> showConfigFrame());
			if ("admin".equalsIgnoreCase(usuario.getUserRoles().get(0).getRol().getRolNom())) {
				btnConfig.setEnabled(Boolean.TRUE);
			}
		}
		return btnConfig;
	}

	private void showConfigFrame() {
//		configFrame = new ConfigFrame(context);
//		configFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		configFrame.setVisible(Boolean.TRUE);
	}

	public ColloredButton getBtnDumpRecords() {
		if (btnDumpRecords == null) {
			btnDumpRecords = new ColloredButton("Dump Records");
			btnDumpRecords.setBorder(null);
			btnDumpRecords.setText("<html>Registro de<br>Botaderos</html>");
			btnDumpRecords.setBackground(Color.BLACK);
			btnDumpRecords.setForeground(Color.WHITE);
			btnDumpRecords.setBounds(300, 225, 150, 100);
			btnDumpRecords.setHorizontalTextPosition(SwingConstants.CENTER);
			btnDumpRecords.setIcon(new ImageIcon(
					MainMenuFrame.class.getResource("/icons/coal-industry-icons_1284-4557_mountains_60x60.png")));
			btnDumpRecords.setVerticalTextPosition(JButton.BOTTOM);
			btnDumpRecords.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnDumpRecords.setPreferredSize(new Dimension(147, 29));
		}
		return btnDumpRecords;
	}

	public ColloredButton getBtnFuelRecords() {
		if (btnFuelRecords == null) {
			btnFuelRecords = new ColloredButton("Fuel Records");
			btnFuelRecords.setBorder(null);
			btnFuelRecords.setForeground(Color.WHITE);
			btnFuelRecords.setBackground(Color.BLACK);
			btnFuelRecords.setText("<html>Reporte de<br>Combustible</html>");
			btnFuelRecords.setBounds(150, 400, 150, 100);
			btnFuelRecords.setHorizontalTextPosition(SwingConstants.CENTER);
			btnFuelRecords
					.setIcon(new ImageIcon(MainMenuFrame.class.getResource("/icons/RO-IE71-2B35-2201_36x36.png")));
			btnFuelRecords.setVerticalTextPosition(JButton.BOTTOM);
			btnFuelRecords.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnFuelRecords.setPreferredSize(new Dimension(147, 29));
		}
		return btnFuelRecords;
	}

	public ColloredButton getBtnGpsUtil() {
		if (btnGpsUtil == null) {
			btnGpsUtil = new ColloredButton("Rastreo GPS");
			btnGpsUtil.setBorder(null);
			btnGpsUtil.setForeground(Color.WHITE);
			btnGpsUtil.setBackground(Color.BLACK);
			btnGpsUtil.setBounds(0, 60, 150, 100);
			btnGpsUtil.setIcon(new ImageIcon(MainMenuFrame.class.getResource("/icons/icons8-marker-50_36x36.png")));
			btnGpsUtil.setHorizontalTextPosition(SwingConstants.CENTER);
			btnGpsUtil.setVerticalTextPosition(JButton.BOTTOM);
			btnGpsUtil.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnGpsUtil.setPreferredSize(new Dimension(147, 29));
			btnGpsUtil.addActionListener(e -> showGpsView());
		}
		return btnGpsUtil;
	}

	/**
	 * 
	 */
	private void showGpsView() {
SmartMainFrame mainFrame = new SmartMainFrame(initSession);
		// GeoFrame smWin = new GeoFrame();
		smWin = new ShowGpsFrame(mainFrame);
		smWin.setLocationRelativeTo(this);
		smWin.setSize(1000, 1000);
		smWin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		smWin.setIconImage(new ImageIcon(MainMenuFrame.class.getResource("/icons/SF_ico_32x32.png")).getImage());
		smWin.setTitle("SmartFleet - Rastreo GPS");

		List<GpsDispositivo> allGps = smartFacade.findAllGps();
		boolean isInit = false;
		if (!allGps.isEmpty()) {
			isInit = true;
			smWin.addSegment(new GeoSegment(new GeoPoint(0, 0), new GeoPoint(0, 0), Color.BLACK));
			// smWin.addLabeledPoint(new LabeledPoint( -12.09634, -77.06790, "HOME",
			// Color.WHITE));
			addLabelsToWindow(allGps, smWin, Color.WHITE, isInit);
		}

		smWin.setLocationByPlatform(Boolean.TRUE);
		smWin.setLocationRelativeTo(this);
		smWin.setVisible(Boolean.TRUE);

		ApplicationParameter appParam = smartFacade.findParameter("GPS_TIMER");

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				lastId = smWin.getLastId();
				List<GpsDispositivo> newOnes = smartFacade.findAllGpsParam(lastId);
				if (!newOnes.isEmpty()) {
					addLabelsToWindow(newOnes, smWin, Color.GREEN, false);
				}
			}
		};
		timer.schedule(task, 0, appParam.getIntValue());
	}

	/**
	 * @param allGps
	 * @param smWin
	 */
	public void addLabelsToWindow(List<GpsDispositivo> allGps, ShowGpsFrame smWin, Color color, boolean isInit) {
		final GpsDispositivo lastIdDisp = smartFacade.findPreviousGpsLocation(lastId);

		for (GpsDispositivo gps : allGps) {
			lastId = gps.getId();
			double ltd = Double.parseDouble(gps.getLatitude());
			double lng = Double.parseDouble(gps.getLongitud());
			LabeledPoint labeledGps = new LabeledPoint(ltd, lng, "", color);
			smWin.addLabeledPoint(labeledGps);

			if (!isInit) {
				final double lastIdltd = Double.parseDouble(lastIdDisp.getLatitude());
				final double lastIdlng = Double.parseDouble(lastIdDisp.getLongitud());
				LabeledPoint lastIdLabel = new LabeledPoint(lastIdltd, lastIdlng, "", Color.WHITE);

				smWin.addLabeledPoint(lastIdLabel);
				// GeoSegment segment = new GeoSegment(lastIdLabel, labeledGps, Color.ORANGE);
				// smWin.addSegment(segment);
			}
			smWin.getMap().repaint();
		}
		smWin.setLastId(lastId);
	}

	public ColloredButton getBtnLoadRec() {
		if (btnLoadRec == null) {
			btnLoadRec = new ColloredButton("Load Records");
			btnLoadRec.setBorder(null);
			btnLoadRec.setText("<html>Rutas</html>");
			btnLoadRec.setForeground(Color.WHITE);
			btnLoadRec.setBackground(Color.BLACK);
			btnLoadRec.setBounds(300, 60, 150, 100);
			btnLoadRec.setIcon(new ImageIcon(
					MainMenuFrame.class.getResource("/icons/coal-industry-icons_1284-4557_truck60x60.png")));
			btnLoadRec.setHorizontalTextPosition(SwingConstants.CENTER);
			btnLoadRec.setVerticalTextPosition(JButton.BOTTOM);
			btnLoadRec.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnLoadRec.setPreferredSize(new Dimension(147, 29));
		}
		return btnLoadRec;
	}

	public ColloredButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new ColloredButton("Salir");
			btnExit.setBorder(null);
			btnExit.setActionCommand(EXIT_CMD);
			btnExit.addActionListener(listener);
			btnExit.setBackground(Color.BLACK);
			btnExit.setForeground(Color.WHITE);
			btnExit.setBounds(318, 600, 150, 80);
			btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
			btnExit.setVerticalTextPosition(JButton.BOTTOM);
			btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnExit.setPreferredSize(new Dimension(147, 29));
		}
		return btnExit;
	}

	public void closeSession() {
		final int value = JOptionPane.showConfirmDialog(this, "Are you sure you want to close session?", "SmartFleet",
				JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == value) {
			
			
			if (this.wGui != null) {
				logger.info("Channel is closed? -> " + this.wGui.getServerSocket().isClosed());
				if (!this.wGui.getServerSocket().isClosed()) {
					try {
						ServerSocket ss = this.wGui.getServerSocket();
						int port = ss.getLocalPort();
						logger.info("Port " + port);
						ss.close();
					} catch (IOException e) {
						logger.debug(e.getMessage(), e);
					}
				}
				if (this.wGui.isVisible()) {
					this.wGui.dispose();
				}
			}
			if (this.smWin != null && this.smWin.isVisible()) {
				this.smWin.dispose();
			}
			if (this.configFrame != null && this.configFrame.isVisible()) {
				this.configFrame.dispose();
			}
			initSession.setVisible(Boolean.TRUE);
			this.dispose();
		}
	}

	public ColloredButton getBtnTrammedLoads() {
		if (btnTrammedLoads == null) {
			btnTrammedLoads = new ColloredButton("Trammed Loads");
			btnTrammedLoads.setBorder(null);
			btnTrammedLoads.setText("<html>Reporte de<br>Cargas</html>");
			btnTrammedLoads.setForeground(Color.WHITE);
			btnTrammedLoads.setBackground(Color.BLACK);
			btnTrammedLoads.setBounds(0, 400, 150, 100);
			btnTrammedLoads.setIcon(new ImageIcon(MainMenuFrame.class.getResource("/icons/trimLoad60x60.png")));
			btnTrammedLoads.setHorizontalTextPosition(SwingConstants.CENTER);
			btnTrammedLoads.setVerticalTextPosition(JButton.BOTTOM);
			btnTrammedLoads.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnTrammedLoads.setPreferredSize(new Dimension(147, 29));
		}
		return btnTrammedLoads;
	}

	public ColloredButton getBtnWatterRecords() {
		if (btnWatterRecords == null) {
			btnWatterRecords = new ColloredButton("Water Records");
			btnWatterRecords.setBorder(null);
			btnWatterRecords.setBackground(Color.BLACK);
			btnWatterRecords.setForeground(Color.WHITE);
			btnWatterRecords.setText("Agua");
			btnWatterRecords.setBounds(300, 400, 150, 100);
			btnWatterRecords.setIcon(new ImageIcon(MainMenuFrame.class.getResource("/icons/waterRecords_60x60.png")));
			btnWatterRecords.setHorizontalTextPosition(SwingConstants.CENTER);
			btnWatterRecords.setVerticalTextPosition(JButton.BOTTOM);
			btnWatterRecords.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnWatterRecords.setPreferredSize(new Dimension(147, 29));
			btnWatterRecords.setOpaque(Boolean.FALSE);
		}
		return btnWatterRecords;
	}

	public ColloredButton getBtnLogger() {
		if (btnLogger == null) {
			btnLogger = new ColloredButton("Transacciones");
			btnLogger.setBorder(null);
			btnLogger.setBackground(Color.BLACK);
			btnLogger.setForeground(Color.WHITE);
			btnLogger.setHorizontalTextPosition(SwingConstants.CENTER);
			btnLogger.setBounds(150, 60, 150, 100);
			btnLogger.addActionListener(e -> wGui.setVisible(Boolean.TRUE));
		}
		return btnLogger;
	}

	public JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("Usuario: " + usuario.getUsuAcc());
			lblUsuario.setForeground(Color.WHITE);
			lblUsuario.setBounds(0, 660, 450, 20);
		}
		return lblUsuario;
	}

//	public AbstractRefreshableApplicationContext getContext() {
//		return context;
//	}
//
//	public void setContext(AbstractRefreshableApplicationContext context) {
//		this.context = context;
//	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
