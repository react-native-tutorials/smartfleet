package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.smartlife.smartfleet.domain.security.Usuario;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.utils.Constants;

public class EditUserUI extends JDialog {

	private static final long serialVersionUID = 6717095483742520141L;
	static final String OK_CMD = "OK";
	static final String CANCEL_CMD = "CANCEL";
	static final String ED_USER_PNL_TITLE="Registro Usuario";
	
	private final JPanel mainPanel = new JPanel();
	private JTextField txtUsuApe;
	private JTextField txtUsuAcc;
	private JTextField txtUsuPass;

	private JLabel lblIp;
	private JTextField txtDocUsu;
	private JButton okButton;
	private JButton cancelButton;
	
	Usuario userDetail;
	Boolean isNew = Boolean.FALSE;
	UserPanel userPanel;
	SmartFacade facade;
	EditUserActionListener listener;
	private JLabel lblPort;
	private JTextField txtUsuNom;
	private JLabel lblActivo;
	private JCheckBox chkActivo;
	
	/**
	 * Create the dialog.
	 */
	public EditUserUI(Usuario userDet, UserPanel userPane) {
		
		this.userDetail = userDet;
		this.userPanel = userPane;
		this.listener = new EditUserActionListener(this);
		if(userDet.getId() == null) {
			isNew = Boolean.TRUE;
		}
		
		facade = userPanel.getSmartFacade();
		
		setTitle(ED_USER_PNL_TITLE);
		setBounds(100, 100, 450, 316);
		getContentPane().setLayout(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setBackground(Color.BLACK);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_contentPanel);
		{
			lblIp = new JLabel("Documento:");
			lblIp.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblIp = new GridBagConstraints();
			gbc_lblIp.anchor = GridBagConstraints.EAST;
			gbc_lblIp.insets = new Insets(0, 0, 5, 5);
			gbc_lblIp.gridx = 1;
			gbc_lblIp.gridy = 1;
			mainPanel.add(lblIp, gbc_lblIp);
		}
		{
			txtDocUsu = new JTextField();
			GridBagConstraints gbc_txtIp = new GridBagConstraints();
			gbc_txtIp.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtIp.insets = new Insets(0, 0, 5, 5);
			gbc_txtIp.gridx = 2;
			gbc_txtIp.gridy = 1;
			mainPanel.add(txtDocUsu, gbc_txtIp);
			txtDocUsu.setColumns(10);
			if (!isNew) {
				final String ip = userDetail.getUsuDoc();
				txtDocUsu.setText(ip);
			}
		}
		{
			lblPort = new JLabel("Nombres:");
			lblPort.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblPort = new GridBagConstraints();
			gbc_lblPort.anchor = GridBagConstraints.EAST;
			gbc_lblPort.insets = new Insets(0, 0, 5, 5);
			gbc_lblPort.gridx = 1;
			gbc_lblPort.gridy = 2;
			mainPanel.add(lblPort, gbc_lblPort);
		}
		{
			txtUsuNom = new JTextField();
			txtUsuNom.setText("Port");
			GridBagConstraints gbc_txtPort = new GridBagConstraints();
			gbc_txtPort.insets = new Insets(0, 0, 5, 5);
			gbc_txtPort.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPort.gridx = 2;
			gbc_txtPort.gridy = 2;
			mainPanel.add(txtUsuNom, gbc_txtPort);
			txtUsuNom.setColumns(10);
			if (!isNew) {
				final String port = userDetail.getUsuNom();
				txtUsuNom.setText(port);
			}
		}
		{
			JLabel lblMac = new JLabel("Apellidos:");
			lblMac.setOpaque(Boolean.FALSE);
			lblMac.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblMac = new GridBagConstraints();
			gbc_lblMac.insets = new Insets(0, 0, 5, 5);
			gbc_lblMac.anchor = GridBagConstraints.EAST;
			gbc_lblMac.gridx = 1;
			gbc_lblMac.gridy = 3;
			mainPanel.add(lblMac, gbc_lblMac);
		}
		{
			txtUsuApe = new JTextField();
			GridBagConstraints gbc_txtMac = new GridBagConstraints();
			gbc_txtMac.gridwidth = 3;
			gbc_txtMac.insets = new Insets(0, 0, 5, 0);
			gbc_txtMac.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtMac.gridx = 2;
			gbc_txtMac.gridy = 3;
			mainPanel.add(txtUsuApe, gbc_txtMac);
			txtUsuApe.setColumns(10);
			if (!isNew) {
				final String macAdd = userDetail.getUsuApe();
				txtUsuApe.setText(macAdd);
			}
			txtUsuApe.addKeyListener(listener);
		}
		{
			JLabel lblGateway = new JLabel("Cuenta:");
			lblGateway.setOpaque(Boolean.FALSE);
			lblGateway.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblGateway = new GridBagConstraints();
			gbc_lblGateway.anchor = GridBagConstraints.EAST;
			gbc_lblGateway.insets = new Insets(0, 0, 5, 5);
			gbc_lblGateway.gridx = 1;
			gbc_lblGateway.gridy = 4;
			mainPanel.add(lblGateway, gbc_lblGateway);
		}
		{
			txtUsuAcc = new JTextField();
			GridBagConstraints gbc_txtGateway = new GridBagConstraints();
			gbc_txtGateway.gridwidth = 3;
			gbc_txtGateway.insets = new Insets(0, 0, 5, 0);
			gbc_txtGateway.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtGateway.gridx = 2;
			gbc_txtGateway.gridy = 4;
			mainPanel.add(txtUsuAcc, gbc_txtGateway);
			txtUsuAcc.setColumns(10);
			if (!isNew) {
				final String gateway = userDetail.getUsuAcc();
				txtUsuAcc.setText(gateway);
			}
			txtUsuAcc.addKeyListener(listener);
		}
		{
			JLabel lblTipo = new JLabel("Clave:");
			lblTipo.setOpaque(Boolean.FALSE);
			lblTipo.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblTipo = new GridBagConstraints();
			gbc_lblTipo.anchor = GridBagConstraints.EAST;
			gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
			gbc_lblTipo.gridx = 1;
			gbc_lblTipo.gridy = 5;
			mainPanel.add(lblTipo, gbc_lblTipo);
		}
		{
			txtUsuPass = new JTextField();
			GridBagConstraints gbc_txtTipo = new GridBagConstraints();
			gbc_txtTipo.insets = new Insets(0, 0, 5, 5);
			gbc_txtTipo.gridwidth = 2;
			gbc_txtTipo.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtTipo.gridx = 2;
			gbc_txtTipo.gridy = 5;
			mainPanel.add(txtUsuPass, gbc_txtTipo);
			txtUsuPass.setColumns(10);
			if (!isNew) {
				String tipoDisp = userDetail.getUsuPass();
				txtUsuPass.setText(tipoDisp);
			}
			txtUsuPass.addKeyListener(listener);
			{
				lblActivo = new JLabel("Activo");
				lblActivo.setForeground(Color.WHITE);
				GridBagConstraints gbc_lblActivo = new GridBagConstraints();
				gbc_lblActivo.insets = new Insets(0, 0, 0, 5);
				gbc_lblActivo.gridx = 1;
				gbc_lblActivo.gridy = 6;
				mainPanel.add(lblActivo, gbc_lblActivo);
			}
			{
				chkActivo = new JCheckBox("");
				GridBagConstraints gbc_chkActivo = new GridBagConstraints();
				gbc_chkActivo.anchor = GridBagConstraints.WEST;
				gbc_chkActivo.insets = new Insets(0, 0, 0, 5);
				gbc_chkActivo.gridx = 2;
				gbc_chkActivo.gridy = 6;
				mainPanel.add(chkActivo, gbc_chkActivo);
				if(!isNew) {
					String act = userDetail.getActivo();
					if("Y".equalsIgnoreCase(act)) {
						chkActivo.setSelected(Boolean.TRUE);
					}else {
						chkActivo.setSelected(Boolean.FALSE);
					}
				}
			}
			
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
			final String docUsu = txtDocUsu.getText().trim();
			final String usuNom = txtUsuNom.getText().trim();
			final String usuApe = txtUsuApe.getText().trim();
			final String usuAcc = txtUsuAcc.getText().trim();
			final String usuPass = txtUsuPass.getText().trim();
			final Boolean isActive = chkActivo.isSelected();
			String active = "N";
			if(isActive) {
				active = "Y";
			}
			
			if (!isNew) {
				final Long userId = Long.parseLong(userDetail.getId().toString());
				Usuario existUser = facade.findUserById(userId);
				existUser.setUsuDoc(docUsu);
				existUser.setUsuNom(usuNom);
				existUser.setUsuApe(usuApe);
				existUser.setUsuAcc(usuAcc);
				existUser.setUsuPass(usuPass);
				existUser.setActivo(active);
				facade.updateUser(existUser);

				UserTableModel tableModel = (UserTableModel)userPanel.getMainTable().getModel(); 
				final int rowIndex = userPanel.getMainTable().getSelectedRow();
				tableModel.setValueAt(docUsu, rowIndex, 1);
				tableModel.setValueAt(usuApe, rowIndex, 2);
				tableModel.setValueAt(usuAcc, rowIndex, 3);
				tableModel.setValueAt(usuPass, rowIndex, 4);
				tableModel.setValueAt(active, rowIndex, 5);
				tableModel.fireTableRowsUpdated(rowIndex, rowIndex);
				tableModel.fireTableDataChanged();
			}else {
				Usuario user = new Usuario();
				user.setUsuDoc(docUsu);
				user.setUsuNom(usuNom);
				user.setUsuApe(usuApe);
				user.setUsuAcc(usuAcc);
				user.setUsuPass(usuPass);
				user.setActivo(active);
				
				final Long nId = facade.saveUser(user);
				userDetail.setId(nId);
				userDetail.setUsuDoc(docUsu);
				userDetail.setUsuNom(usuNom);
				userDetail.setUsuApe(usuApe);
				userDetail.setUsuAcc(usuAcc);
				userDetail.setUsuPass(usuPass);
				userDetail.setActivo(active);
				
				((UserTableModel)userPanel.getMainTable().getModel()).addRow(userDetail);
				userPanel.noItems++;
				userPanel.getNoItemsLabel().setText(Constants.LBL_ITEMS_TITLE+userPanel.noItems);
			}
			
			dispose();
		}
	}

}
