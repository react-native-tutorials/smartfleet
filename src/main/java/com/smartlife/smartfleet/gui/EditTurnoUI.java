package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.smartlife.smartfleet.domain.Turno;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.utils.Constants;

public class EditTurnoUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6717095483742520141L;
	protected final Log logger = LogFactory.getLog(getClass());
	
	static final String OK_CMD = "OK";
	static final String CANCEL_CMD = "CANCEL";
	private final JPanel mainPanel = new JPanel();
	private JTextField txtStartTime;
	private JTextField txtEndTime;
	private JTextField txtHours;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblName;
	private JTextField txtName;
	
	Turno turnoDetail;
	Boolean isNew = Boolean.FALSE;
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	TurnosPanel turnoPanel;
	SmartFacade facade;
	EditTurnoActionListener listener;
	
	public EditTurnoUI(Turno turnoDet, TurnosPanel turnoPane) {
		
		this.turnoDetail = turnoDet;
		this.turnoPanel = turnoPane;
		this.listener = new EditTurnoActionListener(this);
		if(turnoDet.getId() == null) {
			isNew = Boolean.TRUE;
		}
		
		facade = turnoPanel.getSmartFacade();
		
		setTitle("Editar Turno");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setBackground(Color.BLACK);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_contentPanel);
		{
			lblName = new JLabel("Nombre:");
			lblName.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblDocumento = new GridBagConstraints();
			gbc_lblDocumento.anchor = GridBagConstraints.EAST;
			gbc_lblDocumento.insets = new Insets(0, 0, 5, 5);
			gbc_lblDocumento.gridx = 1;
			gbc_lblDocumento.gridy = 1;
			mainPanel.add(lblName, gbc_lblDocumento);
		}
		{
			txtName = new JTextField();
			GridBagConstraints gbc_txtDocument = new GridBagConstraints();
			gbc_txtDocument.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDocument.insets = new Insets(0, 0, 5, 5);
			gbc_txtDocument.gridx = 2;
			gbc_txtDocument.gridy = 1;
			mainPanel.add(txtName, gbc_txtDocument);
			txtName.setColumns(10);
			if (!isNew) {
				final String name = turnoDetail.getNomTurno();
				txtName.setText(name);
			}
		}
		{
			JLabel lblStartTime = new JLabel("Hora Inicio:");
			lblStartTime.setOpaque(Boolean.FALSE);
			lblStartTime.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
			gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
			gbc_lblCodigo.anchor = GridBagConstraints.EAST;
			gbc_lblCodigo.gridx = 1;
			gbc_lblCodigo.gridy = 2;
			mainPanel.add(lblStartTime, gbc_lblCodigo);
		}
		{
			txtStartTime = new JTextField();
			GridBagConstraints gbc_txtCode = new GridBagConstraints();
			gbc_txtCode.gridwidth = 2;
			gbc_txtCode.insets = new Insets(0, 0, 5, 0);
			gbc_txtCode.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCode.gridx = 2;
			gbc_txtCode.gridy = 2;
			mainPanel.add(txtStartTime, gbc_txtCode);
			txtStartTime.setColumns(10);
			if (!isNew) {
				final String startTime = timeFormat.format(turnoDetail.getHoraIni());
				txtStartTime.setText(startTime);
			}
			txtStartTime.addKeyListener(listener);
		}
		{
			JLabel lblEndTime = new JLabel("Hora Fin:");
			lblEndTime.setOpaque(Boolean.FALSE);
			lblEndTime.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblNombres = new GridBagConstraints();
			gbc_lblNombres.anchor = GridBagConstraints.EAST;
			gbc_lblNombres.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombres.gridx = 1;
			gbc_lblNombres.gridy = 3;
			mainPanel.add(lblEndTime, gbc_lblNombres);
		}
		{
			txtEndTime = new JTextField();
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.gridwidth = 2;
			gbc_txtNombre.insets = new Insets(0, 0, 5, 0);
			gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNombre.gridx = 2;
			gbc_txtNombre.gridy = 3;
			mainPanel.add(txtEndTime, gbc_txtNombre);
			txtEndTime.setColumns(10);
			if (!isNew) {
				final String endTime = timeFormat.format(turnoDetail.getHoraFin());
				txtEndTime.setText(endTime);
			}
			txtEndTime.addKeyListener(listener);
		}
		{
			JLabel lblHours = new JLabel("Duración:");
			lblHours.setOpaque(Boolean.FALSE);
			lblHours.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
			gbc_lblApellidos.anchor = GridBagConstraints.EAST;
			gbc_lblApellidos.insets = new Insets(0, 0, 0, 5);
			gbc_lblApellidos.gridx = 1;
			gbc_lblApellidos.gridy = 4;
			mainPanel.add(lblHours, gbc_lblApellidos);
		}
		{
			txtHours = new JTextField();
			GridBagConstraints gbc_txtApellidos = new GridBagConstraints();
			gbc_txtApellidos.gridwidth = 2;
			gbc_txtApellidos.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtApellidos.gridx = 2;
			gbc_txtApellidos.gridy = 4;
			mainPanel.add(txtHours, gbc_txtApellidos);
			txtHours.setColumns(10);
			if (!isNew) {
				final String hours = String.valueOf(turnoDetail.getHoras());
				txtHours.setText(hours);
			}
			txtHours.addKeyListener(listener);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.setBackground(Color.BLACK);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Guardar");
				okButton.setActionCommand(OK_CMD);
				okButton.addActionListener(listener);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand(CANCEL_CMD);
				cancelButton.addActionListener(listener);
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * dispose the edit frame
	 */
	public void disposeFrame() {
		final int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to discard changes?",
				Constants.MSG_TITLE, JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == response) {
			dispose();
		}
	}
	
	public void saveItem() {
		final int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to save changes?", Constants.MSG_TITLE,
				JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == response) {
			final String name = txtName.getText().trim();
			final String startTime = txtStartTime.getText().trim();
			Time startHour = null;
			try {
				startHour = new Time(timeFormat.parse(startTime).getTime());
			} catch (ParseException e) {
				logger.error(e.getMessage(), e);
			}
			final String endTime = txtEndTime.getText().trim();
			Time endHour = null;
			try {
				endHour = new Time(timeFormat.parse(endTime).getTime());
			} catch (ParseException e) {
				logger.error(e.getMessage(), e);
			}
			final String hours = txtHours.getText().trim();
			final Integer noHour = Integer.parseInt(hours);
					
			if (!isNew) {
				final Long turnoId = Long.parseLong(turnoDetail.getId().toString());
				Turno existTurno = facade.findTurnoById(turnoId);
				existTurno.setNomTurno(name);
				existTurno.setHoraIni(startHour);
				existTurno.setHoraFin(endHour);
				existTurno.setHoras(Integer.parseInt(hours));
				facade.updateTurno(existTurno);

				TurnosTableModel tableModel = (TurnosTableModel)turnoPanel.getMainTable().getModel(); 
				final int rowIndex = turnoPanel.getMainTable().getSelectedRow();
				tableModel.setValueAt(name, rowIndex, 1);
				tableModel.setValueAt(startHour, rowIndex, 2);
				tableModel.setValueAt(endHour, rowIndex, 3);
				tableModel.setValueAt(noHour, rowIndex, 4);
				tableModel.fireTableRowsUpdated(rowIndex, rowIndex);
				tableModel.fireTableDataChanged();
			}else {
				Turno turno = new Turno();
				turno.setNomTurno(name);
				turno.setHoraIni(startHour);
				turno.setHoraFin(endHour);
				turno.setHoras(noHour);
				final Long nId = facade.saveTurno(turno);
				turnoDetail.setId(nId);
				turnoDetail.setNomTurno(name);
				turnoDetail.setHoraIni(startHour);
				turnoDetail.setHoraFin(endHour);
				turnoDetail.setHoras(noHour);
				
				((TurnosTableModel)turnoPanel.getMainTable().getModel()).addRow(turnoDetail);
				turnoPanel.noItems++;
				turnoPanel.getNoItemsLabel().setText(Constants.LBL_ITEMS_TITLE+turnoPanel.noItems);
			}
			
			dispose();
		}
	}

}
