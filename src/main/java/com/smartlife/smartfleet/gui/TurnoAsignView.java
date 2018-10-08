package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.smartlife.smartfleet.domain.Operador;
import com.smartlife.smartfleet.domain.Turno;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.ui.common.SortedComboBoxModel;
import com.smartlife.smartfleet.utils.Constants;

public class TurnoAsignView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	SmartFacade facade;
	SmartMainFrame parent;
	private JComboBox cboTurno;
	private JComboBox cboOperario;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public TurnoAsignView(SmartMainFrame frame) {
		this.parent = frame;
		this.facade = (SmartFacade) frame.context.getBean("smartFacade");

		setSize(694, 548);
		final int x = frame.getX() + frame.getWidth();
		final int y = frame.getY() + frame.getHeight() - getHeight();
		setLocation(x, y);
		final Image img = new ImageIcon(SmartMainFrame.class.getResource(Constants.ICON_PATH)).getImage();
		setIconImage(img);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTurno = new JLabel("Turno:");
			lblTurno.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTurno.setBounds(33, 69, 69, 20);
			contentPanel.add(lblTurno);
		}
		{
			JLabel lblOperario = new JLabel("Operario:");
			lblOperario.setHorizontalAlignment(SwingConstants.RIGHT);
			lblOperario.setBounds(33, 104, 69, 20);
			contentPanel.add(lblOperario);
		}
		{
			List<Turno> turnos = facade.findAllTurnos();
			List<String> turnoStr = new ArrayList<>();
			for(Turno item : turnos) {
				turnoStr.add(item.getNomTurno());
			}
			String[] allTurnoArray = turnoStr.stream().map(String::new).toArray(String[]::new);
			SortedComboBoxModel comboModel = new SortedComboBoxModel(allTurnoArray);
			cboTurno = new JComboBox(comboModel);
			cboTurno.setBounds(117, 66, 218, 26);
			contentPanel.add(cboTurno);
		}
		{
			List<Operador> allOper = facade.findAllOpertors();
			List<String> allOperStr = new ArrayList<>();
			for(Operador item :allOper) {
				allOperStr.add(item.getCodigoOperador());
			}
			String[] allOperArray = allOperStr.stream().map(String::new).toArray(String[]::new);
			SortedComboBoxModel cboModel = new SortedComboBoxModel(allOperArray);
			cboOperario = new JComboBox(cboModel);
			cboOperario.setBounds(117, 101, 218, 26);
			contentPanel.add(cboOperario);
		}
		{
			okButton = new JButton("Grabar");
			okButton.setBounds(350, 100, 81, 29);
			okButton.addActionListener(e->saveItem());
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(e->closeFrame());
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void saveItem() {
		
	}
	
	public void closeFrame() {
		parent.setVisible(Boolean.TRUE);
		this.dispose();
	}
}
