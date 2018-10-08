package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.smartlife.smartfleet.domain.Dispositivo;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.utils.Constants;

public class EditDeviceUI extends JDialog {

	private static final long serialVersionUID = 6717095483742520141L;
	static final String OK_CMD = "OK";
	static final String CANCEL_CMD = "CANCEL";
	static final String ED_DEVICE_PNL_TITLE="Registro Dispositivo";
	
	private final JPanel mainPanel = new JPanel();
	private JTextField txtMac;
	private JTextField txtGateway;
	private JTextField txtTipo;
	private JLabel lblIp;
	private JTextField txtIp;
	private JButton okButton;
	private JButton cancelButton;
	
	Dispositivo deviceDetail;
	Boolean isNew = Boolean.FALSE;
	DevicePanel devicePanel;
	SmartFacade facade;
	EditDeviceActionListener listener;
	private JLabel lblPort;
	private JTextField txtPort;
	
	/**
	 * Create the dialog.
	 */
	public EditDeviceUI(Dispositivo deviceDet, DevicePanel devicePane) {
		
		this.deviceDetail = deviceDet;
		this.devicePanel = devicePane;
		this.listener = new EditDeviceActionListener(this);
		if(deviceDet.getId() == null) {
			isNew = Boolean.TRUE;
		}
		
		facade = devicePanel.getSmartFacade();
		
		setTitle(ED_DEVICE_PNL_TITLE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setBackground(Color.BLACK);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_contentPanel);
		{
			lblIp = new JLabel("IP:");
			lblIp.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblIp = new GridBagConstraints();
			gbc_lblIp.anchor = GridBagConstraints.EAST;
			gbc_lblIp.insets = new Insets(0, 0, 5, 5);
			gbc_lblIp.gridx = 1;
			gbc_lblIp.gridy = 1;
			mainPanel.add(lblIp, gbc_lblIp);
		}
		{
			txtIp = new JTextField();
			GridBagConstraints gbc_txtIp = new GridBagConstraints();
			gbc_txtIp.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtIp.insets = new Insets(0, 0, 5, 5);
			gbc_txtIp.gridx = 2;
			gbc_txtIp.gridy = 1;
			mainPanel.add(txtIp, gbc_txtIp);
			txtIp.setColumns(10);
			if (!isNew) {
				final String ip = deviceDetail.getIpDispositivo();
				txtIp.setText(ip);
			}
		}
		{
			lblPort = new JLabel("Port:");
			lblPort.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblPort = new GridBagConstraints();
			gbc_lblPort.anchor = GridBagConstraints.EAST;
			gbc_lblPort.insets = new Insets(0, 0, 5, 5);
			gbc_lblPort.gridx = 3;
			gbc_lblPort.gridy = 1;
			mainPanel.add(lblPort, gbc_lblPort);
		}
		{
			txtPort = new JTextField();
			txtPort.setText("Port");
			GridBagConstraints gbc_txtPort = new GridBagConstraints();
			gbc_txtPort.insets = new Insets(0, 0, 5, 0);
			gbc_txtPort.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPort.gridx = 4;
			gbc_txtPort.gridy = 1;
			mainPanel.add(txtPort, gbc_txtPort);
			txtPort.setColumns(10);
			if (!isNew) {
				final String port = deviceDetail.getPortDisp();
				txtPort.setText(port);
			}
		}
		{
			JLabel lblMac = new JLabel("MAC:");
			lblMac.setOpaque(Boolean.FALSE);
			lblMac.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblMac = new GridBagConstraints();
			gbc_lblMac.insets = new Insets(0, 0, 5, 5);
			gbc_lblMac.anchor = GridBagConstraints.EAST;
			gbc_lblMac.gridx = 1;
			gbc_lblMac.gridy = 2;
			mainPanel.add(lblMac, gbc_lblMac);
		}
		{
			txtMac = new JTextField();
			GridBagConstraints gbc_txtMac = new GridBagConstraints();
			gbc_txtMac.gridwidth = 3;
			gbc_txtMac.insets = new Insets(0, 0, 5, 5);
			gbc_txtMac.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtMac.gridx = 2;
			gbc_txtMac.gridy = 2;
			mainPanel.add(txtMac, gbc_txtMac);
			txtMac.setColumns(10);
			if (!isNew) {
				final String macAdd = deviceDetail.getMacDispositivo();
				txtMac.setText(macAdd);
			}
			txtMac.addKeyListener(listener);
		}
		{
			JLabel lblGateway = new JLabel("Gateway:");
			lblGateway.setOpaque(Boolean.FALSE);
			lblGateway.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblGateway = new GridBagConstraints();
			gbc_lblGateway.anchor = GridBagConstraints.EAST;
			gbc_lblGateway.insets = new Insets(0, 0, 5, 5);
			gbc_lblGateway.gridx = 1;
			gbc_lblGateway.gridy = 3;
			mainPanel.add(lblGateway, gbc_lblGateway);
		}
		{
			txtGateway = new JTextField();
			GridBagConstraints gbc_txtGateway = new GridBagConstraints();
			gbc_txtGateway.gridwidth = 3;
			gbc_txtGateway.insets = new Insets(0, 0, 5, 5);
			gbc_txtGateway.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtGateway.gridx = 2;
			gbc_txtGateway.gridy = 3;
			mainPanel.add(txtGateway, gbc_txtGateway);
			txtGateway.setColumns(10);
			if (!isNew) {
				final String gateway = deviceDetail.getGateway();
				txtGateway.setText(gateway);
			}
			txtGateway.addKeyListener(listener);
		}
		{
			JLabel lblTipo = new JLabel("Tipo:");
			lblTipo.setOpaque(Boolean.FALSE);
			lblTipo.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblTipo = new GridBagConstraints();
			gbc_lblTipo.anchor = GridBagConstraints.EAST;
			gbc_lblTipo.insets = new Insets(0, 0, 0, 5);
			gbc_lblTipo.gridx = 1;
			gbc_lblTipo.gridy = 4;
			mainPanel.add(lblTipo, gbc_lblTipo);
		}
		{
			txtTipo = new JTextField();
			GridBagConstraints gbc_txtTipo = new GridBagConstraints();
			gbc_txtTipo.insets = new Insets(0, 0, 0, 5);
			gbc_txtTipo.gridwidth = 2;
			gbc_txtTipo.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTipo.gridx = 2;
			gbc_txtTipo.gridy = 4;
			mainPanel.add(txtTipo, gbc_txtTipo);
			txtTipo.setColumns(10);
			if (!isNew) {
				String tipoDisp = deviceDetail.getTipoDispositivo();
				txtTipo.setText(tipoDisp);
			}
			txtTipo.addKeyListener(listener);
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
			final String ipDisp = txtIp.getText().trim();
			final String portDisp = txtPort.getText().trim();
			final String macDisp = txtMac.getText().trim();
			final String gateway = txtGateway.getText().trim();
			final String tipoDisp = txtTipo.getText().trim();
					
			if (!isNew) {
				final Long dispId = Long.parseLong(deviceDetail.getId().toString());
				Dispositivo existDisp = facade.findDispById(dispId);
				existDisp.setIpDispositivo(ipDisp);
				existDisp.setPortDisp(portDisp);
				existDisp.setMacDispositivo(macDisp);
				existDisp.setGateway(gateway);
				existDisp.setTipoDispositivo(tipoDisp);
				facade.updateDisp(existDisp);

				DeviceTableModel tableModel = (DeviceTableModel)devicePanel.getMainTable().getModel(); 
				final int rowIndex = devicePanel.getMainTable().getSelectedRow();
				tableModel.setValueAt(ipDisp, rowIndex, 1);
				tableModel.setValueAt(macDisp, rowIndex, 2);
				tableModel.setValueAt(gateway, rowIndex, 3);
				tableModel.setValueAt(tipoDisp, rowIndex, 4);
				tableModel.fireTableRowsUpdated(rowIndex, rowIndex);
				tableModel.fireTableDataChanged();
			}else {
				Dispositivo device = new Dispositivo();
				device.setIpDispositivo(ipDisp);
				device.setPortDisp(portDisp);
				device.setMacDispositivo(macDisp);
				device.setGateway(gateway);
				device.setTipoDispositivo(tipoDisp);
				final Long nId = facade.saveDisp(device);
				deviceDetail.setId(nId);
				deviceDetail.setIpDispositivo(ipDisp);
				deviceDetail.setPortDisp(portDisp);
				deviceDetail.setMacDispositivo(macDisp);
				deviceDetail.setGateway(gateway);
				deviceDetail.setTipoDispositivo(tipoDisp);
				
				((DeviceTableModel)devicePanel.getMainTable().getModel()).addRow(deviceDetail);
				devicePanel.noItems++;
				devicePanel.getNoItemsLabel().setText(Constants.LBL_ITEMS_TITLE+devicePanel.noItems);
			}
			
			dispose();
		}
	}

}
