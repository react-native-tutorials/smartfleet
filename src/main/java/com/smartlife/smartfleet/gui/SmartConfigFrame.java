package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;

import org.springframework.context.support.AbstractRefreshableApplicationContext;

import com.smartlife.smartfleet.utils.Constants;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class SmartConfigFrame extends JDialog {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 4815917763139808926L;
	private JTabbedPane tbdMainPaine;
	private EquipmentPanel pnlEquipos;
	private StatePanel pnlStates;
	private OperatorPanel pnlOperators;
	private TurnosPanel pnlTurnos;
	private DevicePanel pnlDevices;
	private UserPanel pnlUser;
	private AbstractRefreshableApplicationContext context;
	SmartMainFrame parent;
	/**
	 * Create the dialog.
	 */
	public SmartConfigFrame(SmartMainFrame mainFrame) {
		this.parent = mainFrame;
		this.context = mainFrame.context;
		setSize(1150, 688);
		final int x = mainFrame.getX()+mainFrame.getWidth();
		final int y = mainFrame.getY()+mainFrame.getHeight()-getHeight();
		setLocation(x, y);
		final Image img = new ImageIcon(SmartMainFrame.class.getResource(Constants.ICON_PATH)).getImage();
		setIconImage(img);
		setTitle("SmartFleet ~ Configuración");
		getContentPane().add(getTbdMainPaine(), BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parent.setVisible(Boolean.TRUE);
			}
		});
	}

	public JTabbedPane getTbdMainPaine() {
		if(tbdMainPaine == null) {
			tbdMainPaine = new JTabbedPane(JTabbedPane.TOP);
			
			tbdMainPaine.setOpaque(Boolean.FALSE);
			tbdMainPaine.setBackground(Color.BLACK);
			tbdMainPaine.addTab("Equipos", null, getPnlEquipos(), "Configurar los equipos que el sistema maneja");
			tbdMainPaine.setForegroundAt(0, Color.WHITE);
			tbdMainPaine.addTab("Estados", null, getPnlStates(), "Configurar los estados que el sistema gestiona");
			tbdMainPaine.setForegroundAt(1, Color.WHITE);
			tbdMainPaine.addTab("Operadores", null, getPnlOperators(), "Configurar los detalles de los operarios");
			tbdMainPaine.setForegroundAt(2, Color.WHITE);
			tbdMainPaine.addTab("Turnos", null, getPnlTurnos(), "Configurar los detalles de los turnos");
			tbdMainPaine.setForegroundAt(3, Color.WHITE);
			tbdMainPaine.addTab("Dispositivos", null, getPnlDevices(), "Configurar los detalles de los dispositivos");
			tbdMainPaine.setForegroundAt(4, Color.WHITE);
			tbdMainPaine.addTab("Usuarios", null, getPnlUser(), "Configurar los detalles de los usuarios");
			tbdMainPaine.setForegroundAt(5, Color.WHITE);
		}
		return tbdMainPaine;
	}
	public EquipmentPanel getPnlEquipos() {
		if (pnlEquipos == null) {
			pnlEquipos = new EquipmentPanel(context);
		}
		return pnlEquipos;
	}
	
	public StatePanel getPnlStates() {
		if(pnlStates == null) {
			pnlStates = new StatePanel(context);
		}
		return pnlStates;
	}

	public OperatorPanel getPnlOperators() {
		if(pnlOperators == null) {
			pnlOperators = new OperatorPanel(context);
		}
		return pnlOperators;
	}

	public TurnosPanel getPnlTurnos() {
		if(pnlTurnos == null) {
			pnlTurnos = new TurnosPanel(context);
		}
		return pnlTurnos;
	}

	public DevicePanel getPnlDevices() {
		if(pnlDevices==null) {
			pnlDevices = new DevicePanel(context);
		}
		return pnlDevices;
	}

	public UserPanel getPnlUser() {
		if(pnlUser==null) {
			pnlUser = new UserPanel(context);
		}
		return pnlUser;
	}
	
}
