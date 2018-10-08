package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractRefreshableApplicationContext;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class ConfigFrame extends JFrame {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -7782591770571566623L;
	protected final Log logger = LogFactory.getLog(getClass());
	
	private JPanel mainPane;
	private AbstractRefreshableApplicationContext context;
	
	private JTabbedPane tbdMainPaine;
	private EquipmentPanel pnlEquipos;
	private StatePanel pnlStates;
	private OperatorPanel pnlOperators;

	/**
	 * Create the frame.
	 */
	public ConfigFrame(AbstractRefreshableApplicationContext ctx) {
		this.context = ctx;
		setResizable(Boolean.TRUE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationByPlatform(Boolean.TRUE);
		setSize(1200, 700);
		setTitle("SmartFleet - Configuración");
		setIconImage(new ImageIcon(MainMenuFrame.class.getResource("/icons/SF_ico_32x32.png")).getImage());
		setContentPane(getMainPane());
	}

	public JPanel getMainPane() {
		if(mainPane == null) {
			mainPane = new JPanel();
			mainPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			mainPane.setLayout(new BorderLayout(0, 0));
			mainPane.setBackground(Color.BLACK);
			mainPane.add(getTbdMainPaine(), BorderLayout.CENTER);
		}
		return mainPane;
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
		}
		return tbdMainPaine;
	}
	
	private EquipmentPanel getPnlEquipos() {
		if (pnlEquipos == null) {
			pnlEquipos = new EquipmentPanel(getContext());
		}
		return pnlEquipos;
	}


	public StatePanel getPnlStates() {
		if(pnlStates == null) {
			pnlStates = new StatePanel(getContext());
		}
		return pnlStates;
	}

	public OperatorPanel getPnlOperators() {
		if(pnlOperators == null) {
			pnlOperators = new OperatorPanel(getContext());
		}
		return pnlOperators;
	}

	public AbstractRefreshableApplicationContext getContext() {
		return context;
	}
	
	
}
