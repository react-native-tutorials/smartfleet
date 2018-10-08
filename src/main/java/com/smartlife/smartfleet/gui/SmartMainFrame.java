package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.context.support.AbstractRefreshableApplicationContext;

import com.smartlife.smartfleet.domain.security.Usuario;
import com.smartlife.smartfleet.engine.WorkGUI;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.gui.geo.ShowGpsFrame;
import com.smartlife.smartfleet.ui.common.ColloredButton;
import com.smartlife.smartfleet.utils.Constants;

public class SmartMainFrame extends JFrame {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -621787847489674801L;
	static final String LBL_USER_TITLE = "Usuario: ";
	static final String BTN_GPS_CMD = "GPS_CMD";
	static final String BTN_TX_CMD = "TX_CMD";
	static final String BTN_CFG_CMD = "CFG_CMD";
	static final String BTN_ASIG_STATE_CMD = "ASIG_STATE_CMD";
	static final String BTN_LOAD_REP_CMD = "LOAD_REP_CMD";
	static final String BTN_FUEL_REP_CMD = "FUEL_REP_CMD";
	static final String BTN_CAN_CMD = "CAN_CMD";

	private final JPanel contentPanel = new JPanel();

	private JTabbedPane tabbedPane;
	private JPanel pnlGrafic;
	private JPanel pnlAdmin;
	private JPanel pnlReport;
	private JPanel buttonPane;

	private ColloredButton btnGps;
	private ColloredButton btnTransactions;
	private ColloredButton btnConfig;
	private ColloredButton btnAsigStates;
	private ColloredButton btnLoadReport;
	private ColloredButton btnFuelReport;
	private ColloredButton btnAsigDisp;
	private ColloredButton btnAsigTurnos;
	private ColloredButton btnSendMsg;
	private ColloredButton btnAsigOper;
	private JButton cancelButton;
	private JLabel lblUser;

	AbstractRefreshableApplicationContext context;
	SmartMainFrameActionListener listener;
	WorkGUI workGUI;
	ShowGpsFrame showGpsFrame;
	InitSession parentComponent;
	Usuario user;
	List<SmartConfigFrame> lstCfgFrames = new ArrayList<>();
	List<SmartAsigStateWindow> lstAsigStateFrames = new ArrayList<>();
	List<SmartAssignDisp> lsSmartAssignDisps = new ArrayList<>();
	List<ShowGpsFrame> lstShowGpsFrame = new ArrayList<>();
	List<SendMessageWindow> lstSendMsgFrame = new ArrayList<>();
	List<TurnoAsignView> lstTurnoAsignLst = new ArrayList<>();

	public SmartMainFrame(InitSession initSession) {
		this.parentComponent = initSession;
		this.user = initSession.getUsuario();
		this.context = initSession.getContext();
		this.listener = new SmartMainFrameActionListener(this);
		final Image img = new ImageIcon(SmartMainFrame.class.getResource(Constants.ICON_PATH)).getImage();
		setTitle("SmartFleet");
		setIconImage(img);
		setBounds(100, 100, 478, 594);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getTabbedPane());

		{
			getContentPane().add(getButtonPane(), BorderLayout.SOUTH);
		}
		addWindowListener(listener);
		final int portValue = ((SmartFacade) context.getBean("smartFacade")).findParameter("APP_PORT").getIntValue();
		workGUI = new WorkGUI(this, portValue);
	}

	public JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

			tabbedPane.addTab("Grafico", null, getPnlGrafic(), null);
			tabbedPane.setForegroundAt(0, new Color(230, 230, 250));
			tabbedPane.setBackgroundAt(0, Color.BLACK);

			tabbedPane.addTab("Administración", null, getPnlAdmin(), null);
			tabbedPane.setForegroundAt(1, new Color(230, 230, 250));
			tabbedPane.setBackgroundAt(1, Color.BLACK);

			tabbedPane.addTab("Reportes", null, getPnlReport(), null);
			tabbedPane.setForegroundAt(2, new Color(230, 230, 250));
			tabbedPane.setBackgroundAt(2, Color.BLACK);
		}
		return tabbedPane;
	}

	public JPanel getPnlGrafic() {
		if (pnlGrafic == null) {
			pnlGrafic = new JPanel();
			pnlGrafic.setBackground(Color.BLACK);
			pnlGrafic.setLayout(null);

			JLabel lblTitleGrafic = new JLabel("Módulo Gráfico");
			lblTitleGrafic.setBounds(67, 8, 145, 23);
			lblTitleGrafic.setHorizontalTextPosition(SwingConstants.CENTER);
			lblTitleGrafic.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitleGrafic.setForeground(Color.WHITE);
			lblTitleGrafic.setFont(new Font("Tahoma", Font.BOLD, 19));
			lblTitleGrafic.setAlignmentX(0.5f);
			pnlGrafic.add(lblTitleGrafic);
			pnlGrafic.add(getBtnGps());
			pnlGrafic.add(getBtnTransactions());
		}
		return pnlGrafic;
	}

	public ColloredButton getBtnGps() {
		if (btnGps == null) {
			btnGps = new ColloredButton("Rastreo GPS");
			btnGps.setBounds(15, 69, 147, 29);
			btnGps.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnGps.setPreferredSize(new Dimension(147, 29));
			btnGps.setHorizontalTextPosition(SwingConstants.CENTER);
			btnGps.setForeground(Color.WHITE);
			btnGps.setBorder(null);
			btnGps.setBackground(Color.BLACK);
			btnGps.setAlignmentX(0.5f);
			btnGps.setActionCommand(BTN_GPS_CMD);
			btnGps.addActionListener(listener);
		}
		return btnGps;
	}

	public ColloredButton getBtnTransactions() {
		if (btnTransactions == null) {
			btnTransactions = new ColloredButton("Transacciones");
			btnTransactions.setBounds(173, 73, 99, 21);
			btnTransactions.setHorizontalTextPosition(SwingConstants.CENTER);
			btnTransactions.setForeground(Color.WHITE);
			btnTransactions.setBorder(null);
			btnTransactions.setBackground(Color.BLACK);
			btnTransactions.setActionCommand(BTN_TX_CMD);
			btnTransactions.addActionListener(listener);
		}
		return btnTransactions;
	}

	public JPanel getPnlAdmin() {
		if (pnlAdmin == null) {
			pnlAdmin = new JPanel();
			pnlAdmin.setBackground(Color.BLACK);
			pnlAdmin.setLayout(null);

			JLabel lblTitleAdmin = new JLabel("<html>Módulo Administración</html>");
			lblTitleAdmin.setBounds(49, 5, 221, 23);
			lblTitleAdmin.setHorizontalTextPosition(SwingConstants.CENTER);
			lblTitleAdmin.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitleAdmin.setForeground(Color.WHITE);
			lblTitleAdmin.setFont(new Font("Tahoma", Font.BOLD, 19));
			lblTitleAdmin.setAlignmentX(0.5f);
			pnlAdmin.add(lblTitleAdmin);
			pnlAdmin.add(getBtnAsigStates());
			pnlAdmin.add(getBtnConfig());
			pnlAdmin.add(getBtnAsigDisp());
			pnlAdmin.add(getBtnAsigTurnos());
			pnlAdmin.add(getBtnAsigOper());
			pnlAdmin.add(getBtnSendMsg());
		}
		return pnlAdmin;
	}

	public ColloredButton getBtnConfig() {
		if (btnConfig == null) {
			btnConfig = new ColloredButton("<html>Configuración</html>");
			btnConfig.setBounds(0, 44, 147, 29);
			btnConfig.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnConfig.setPreferredSize(new Dimension(147, 29));
			btnConfig.setHorizontalTextPosition(SwingConstants.CENTER);
			btnConfig.setForeground(Color.WHITE);
			btnConfig.setBorder(null);
			btnConfig.setBackground(Color.BLACK);
			btnConfig.setAlignmentX(0.5f);
			btnConfig.setActionCommand(BTN_CFG_CMD);
			btnConfig.addActionListener(listener);
		}
		return btnConfig;
	}

	public ColloredButton getBtnAsigStates() {
		if (btnAsigStates == null) {
			btnAsigStates = new ColloredButton("<html>Operaciones</html>");
			btnAsigStates.setText("<html>Estados Equipos</html>");
			btnAsigStates.setBounds(142, 35, 147, 47);
			btnAsigStates.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnAsigStates.setPreferredSize(new Dimension(147, 29));
			btnAsigStates.setHorizontalTextPosition(SwingConstants.CENTER);
			btnAsigStates.setForeground(Color.WHITE);
			btnAsigStates.setBorder(null);
			btnAsigStates.setBackground(Color.BLACK);
			btnAsigStates.setAlignmentX(0.5f);
			btnAsigStates.setActionCommand(BTN_ASIG_STATE_CMD);
			btnAsigStates.addActionListener(listener);
		}
		return btnAsigStates;
	}

	public ColloredButton getBtnAsigDisp() {
		if (btnAsigDisp == null) {
			btnAsigDisp = new ColloredButton("<html>Operaciones</html>");
			btnAsigDisp.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnAsigDisp.setText("<html>Asignar<br>dispositivos</html>");
			btnAsigDisp.setPreferredSize(new Dimension(147, 29));
			btnAsigDisp.setHorizontalTextPosition(SwingConstants.CENTER);
			btnAsigDisp.setForeground(Color.WHITE);
			btnAsigDisp.setBorder(null);
			btnAsigDisp.setBackground(Color.BLACK);
			btnAsigDisp.setAlignmentX(0.5f);
			btnAsigDisp.setActionCommand("ASIG_STATE_CMD");
			btnAsigDisp.setBounds(0, 89, 147, 47);
			btnAsigDisp.addActionListener(e -> showAsigDispWindow());
		}
		return btnAsigDisp;
	}

	public ColloredButton getBtnAsigTurnos() {
		if (btnAsigTurnos == null) {
			btnAsigTurnos = new ColloredButton("<html>Operaciones</html>");
			btnAsigTurnos.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnAsigTurnos.setText("<html>Asignar<br>turnos</html>");
			btnAsigTurnos.setPreferredSize(new Dimension(147, 29));
			btnAsigTurnos.setHorizontalTextPosition(SwingConstants.CENTER);
			btnAsigTurnos.setForeground(Color.WHITE);
			btnAsigTurnos.setBorder(null);
			btnAsigTurnos.setBackground(Color.BLACK);
			btnAsigTurnos.setAlignmentX(0.5f);
			btnAsigTurnos.setActionCommand("ASIG_STATE_CMD");
			btnAsigTurnos.setBounds(152, 89, 147, 47);
			btnAsigTurnos.addActionListener(e->showTurnoAsignView());
		}
		return btnAsigTurnos;
	}

	public ColloredButton getBtnSendMsg() {
		if (btnSendMsg == null) {
			btnSendMsg = new ColloredButton("<html>Operaciones</html>");
			btnSendMsg.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnSendMsg.setText("<html>Enviar<br>acciones</html>");
			btnSendMsg.setPreferredSize(new Dimension(147, 29));
			btnSendMsg.setHorizontalTextPosition(SwingConstants.CENTER);
			btnSendMsg.setForeground(Color.WHITE);
			btnSendMsg.setBorder(null);
			btnSendMsg.setBackground(Color.BLACK);
			btnSendMsg.setAlignmentX(0.5f);
			btnSendMsg.setActionCommand("ASIG_STATE_CMD");
			btnSendMsg.setBounds(0, 152, 147, 47);
			btnSendMsg.addActionListener(e->showSendMessageWindow());
		}
		return btnSendMsg;
	}

	

	public ColloredButton getBtnAsigOper() {
		if (btnAsigOper == null) {
			btnAsigOper = new ColloredButton("<html>Operaciones</html>");
			btnAsigOper.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnAsigOper.setText("<html>Asignar<br>operarios</html>");
			btnAsigOper.setPreferredSize(new Dimension(147, 29));
			btnAsigOper.setHorizontalTextPosition(SwingConstants.CENTER);
			btnAsigOper.setForeground(Color.WHITE);
			btnAsigOper.setBorder(null);
			btnAsigOper.setBackground(Color.BLACK);
			btnAsigOper.setAlignmentX(0.5f);
			btnAsigOper.setActionCommand("ASIG_STATE_CMD");
			btnAsigOper.setBounds(162, 152, 147, 47);
		}
		return btnAsigOper;
	}

	public JPanel getPnlReport() {
		if (pnlReport == null) {
			pnlReport = new JPanel();
			pnlReport.setBackground(Color.BLACK);
			pnlReport.setLayout(null);

			JLabel lblTitleRep = new JLabel("Módulo Reportes");
			lblTitleRep.setBounds(78, 5, 163, 23);
			lblTitleRep.setHorizontalTextPosition(SwingConstants.CENTER);
			lblTitleRep.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitleRep.setForeground(Color.WHITE);
			lblTitleRep.setFont(new Font("Tahoma", Font.BOLD, 19));
			lblTitleRep.setAlignmentX(0.5f);
			pnlReport.add(lblTitleRep);
			pnlReport.add(getBtnLoadReport());
			pnlReport.add(getBtnFuelReport());
		}
		return pnlReport;
	}

	public ColloredButton getBtnLoadReport() {
		if (btnLoadReport == null) {
			btnLoadReport = new ColloredButton("<html>Reporte de<br>Cargas</html>");
			btnLoadReport.setBounds(0, 69, 147, 48);
			btnLoadReport.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnLoadReport.setPreferredSize(new Dimension(147, 29));
			btnLoadReport.setHorizontalTextPosition(SwingConstants.CENTER);
			btnLoadReport.setForeground(Color.WHITE);
			btnLoadReport.setBorder(null);
			btnLoadReport.setBackground(Color.BLACK);
			btnLoadReport.setAlignmentX(0.5f);
			btnLoadReport.setActionCommand(BTN_LOAD_REP_CMD);
			btnLoadReport.addActionListener(listener);
		}
		return btnLoadReport;
	}

	public ColloredButton getBtnFuelReport() {
		if (btnFuelReport == null) {
			btnFuelReport = new ColloredButton("<html>Reporte de<br>Combustible</html>");
			btnFuelReport.setBounds(172, 69, 147, 48);
			btnFuelReport.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnFuelReport.setPreferredSize(new Dimension(147, 29));
			btnFuelReport.setHorizontalTextPosition(SwingConstants.CENTER);
			btnFuelReport.setForeground(Color.WHITE);
			btnFuelReport.setBorder(null);
			btnFuelReport.setBackground(Color.BLACK);
			btnFuelReport.setAlignmentX(0.5f);
			btnFuelReport.setActionCommand(BTN_FUEL_REP_CMD);
			btnFuelReport.addActionListener(listener);
		}
		return btnFuelReport;
	}

	public JPanel getButtonPane() {
		if (buttonPane == null) {
			buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.add(getLblUser());

			Component rigidArea = Box.createRigidArea(new Dimension(155, 20));
			buttonPane.add(rigidArea);
			{
				buttonPane.add(getCancelButton());
			}
		}
		return buttonPane;
	}

	public JLabel getLblUser() {
		if (lblUser == null) {
			lblUser = new JLabel(LBL_USER_TITLE);
			lblUser.setForeground(Color.WHITE);
			lblUser.setText(LBL_USER_TITLE + user.getUsuAcc());
		}
		return lblUser;
	}

	public JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton("Cerrar Sessión");
			cancelButton.setActionCommand(BTN_CAN_CMD);
			cancelButton.addActionListener(listener);

		}
		return cancelButton;
	}

	public void closeSession() {
		final int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to close the user session?",
				Constants.MSG_TITLE, JOptionPane.YES_NO_OPTION);
		if (JOptionPane.YES_OPTION == response) {
			parentComponent.setVisible(Boolean.TRUE);
			if (workGUI.isVisible()) {
				workGUI.dispose();
			}
			if (!lstCfgFrames.isEmpty()) {
				for (SmartConfigFrame cfgItem : lstCfgFrames) {
					cfgItem.dispose();
				}
			}
			if (!lstAsigStateFrames.isEmpty()) {
				for (SmartAsigStateWindow item : lstAsigStateFrames) {
					item.dispose();
				}
			}
			if (!lsSmartAssignDisps.isEmpty()) {
				for (SmartAssignDisp item : lsSmartAssignDisps) {
					item.dispose();
				}
			}
			if (!lstShowGpsFrame.isEmpty()) {
				for (ShowGpsFrame item : lstShowGpsFrame) {
					item.dispose();
				}
			}
			if(!lstSendMsgFrame.isEmpty()) {
				for (SendMessageWindow item : lstSendMsgFrame) {
					item.dispose();
				}
			}
			if(!lstTurnoAsignLst.isEmpty()) {
				for (TurnoAsignView item : lstTurnoAsignLst) {
					item.dispose();
				}
			}
			this.dispose();
		} else {
			return;
		}
	}

	public void showCfgWindow() {
		SmartConfigFrame frame = new SmartConfigFrame(this);
		frame.setVisible(Boolean.TRUE);
		lstCfgFrames.add(frame);
	}

	public void showAsigStateWindow() {
		SmartAsigStateWindow frame = new SmartAsigStateWindow(this);
		frame.setVisible(Boolean.TRUE);
		lstAsigStateFrames.add(frame);
	}

	public void showAsigDispWindow() {
		SmartAssignDisp frame = new SmartAssignDisp(this);
		frame.setVisible(Boolean.TRUE);
		lsSmartAssignDisps.add(frame);
	}

	public void showTxListingWindow() {
		if (workGUI != null) {
			workGUI.setVisible(Boolean.TRUE);
		}
	}

	public void showSendMessageWindow() {
		SendMessageWindow frame = new SendMessageWindow(this);
		frame.setVisible(Boolean.TRUE);
		lstSendMsgFrame.add(frame);
	}
	
	public void showTurnoAsignView() {
		TurnoAsignView frame = new TurnoAsignView(this);
		frame.setVisible(Boolean.TRUE);
		lstTurnoAsignLst.add(frame);
	}
	
	public AbstractRefreshableApplicationContext getContext() {
		return context;
	}

	public void showGpsWindow() {
		showGpsFrame = new ShowGpsFrame(this);
		showGpsFrame.setVisible(Boolean.TRUE);

		lstShowGpsFrame.add(showGpsFrame);
	}
}
