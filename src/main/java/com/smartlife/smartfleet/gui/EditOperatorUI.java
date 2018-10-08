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

import com.smartlife.smartfleet.domain.Operador;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.utils.Constants;

public class EditOperatorUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6717095483742520141L;
	private final JPanel mainPanel = new JPanel();
	private JTextField txtCode;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JButton okButton;
	private JButton cancelButton;
	static final String OK_CMD = "OK";
	static final String CANCEL_CMD = "CANCEL";
	Operador operadorDetail;
	Boolean isNew = Boolean.FALSE;
	OperatorPanel operatorPanel;
	SmartFacade facade;
	EditOperatorActionListener listener;
	private JLabel lblDocumento;
	private JTextField txtDocument;
	/**
	 * Create the dialog.
	 */
	public EditOperatorUI(Operador opeDet, OperatorPanel opePane) {
		
		this.operadorDetail = opeDet;
		this.operatorPanel = opePane;
		this.listener = new EditOperatorActionListener(this);
		if(opeDet.getId() == null) {
			isNew = Boolean.TRUE;
		}
		
		facade = operatorPanel.getSmartFacade();
		
		setTitle("Registro Operador");
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
			lblDocumento = new JLabel("Documento:");
			lblDocumento.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblDocumento = new GridBagConstraints();
			gbc_lblDocumento.anchor = GridBagConstraints.EAST;
			gbc_lblDocumento.insets = new Insets(0, 0, 5, 5);
			gbc_lblDocumento.gridx = 1;
			gbc_lblDocumento.gridy = 1;
			mainPanel.add(lblDocumento, gbc_lblDocumento);
		}
		{
			txtDocument = new JTextField();
			GridBagConstraints gbc_txtDocument = new GridBagConstraints();
			gbc_txtDocument.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDocument.insets = new Insets(0, 0, 5, 5);
			gbc_txtDocument.gridx = 2;
			gbc_txtDocument.gridy = 1;
			mainPanel.add(txtDocument, gbc_txtDocument);
			txtDocument.setColumns(10);
			if (!isNew) {
				String document = operadorDetail.getDocIdent();
				txtDocument.setText(document);
			}
		}
		{
			JLabel lblCodigo = new JLabel("Codigo:");
			lblCodigo.setOpaque(Boolean.FALSE);
			lblCodigo.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
			gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
			gbc_lblCodigo.anchor = GridBagConstraints.EAST;
			gbc_lblCodigo.gridx = 1;
			gbc_lblCodigo.gridy = 2;
			mainPanel.add(lblCodigo, gbc_lblCodigo);
		}
		{
			txtCode = new JTextField();
			GridBagConstraints gbc_txtCode = new GridBagConstraints();
			gbc_txtCode.gridwidth = 2;
			gbc_txtCode.insets = new Insets(0, 0, 5, 0);
			gbc_txtCode.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCode.gridx = 2;
			gbc_txtCode.gridy = 2;
			mainPanel.add(txtCode, gbc_txtCode);
			txtCode.setColumns(10);
			if (!isNew) {
				String categ = operadorDetail.getCodigoOperador();
				txtCode.setText(categ);
			}
			txtCode.addKeyListener(listener);
		}
		{
			JLabel lblNombres = new JLabel("Nombres:");
			lblNombres.setOpaque(Boolean.FALSE);
			lblNombres.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblNombres = new GridBagConstraints();
			gbc_lblNombres.anchor = GridBagConstraints.EAST;
			gbc_lblNombres.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombres.gridx = 1;
			gbc_lblNombres.gridy = 3;
			mainPanel.add(lblNombres, gbc_lblNombres);
		}
		{
			txtNombre = new JTextField();
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.gridwidth = 2;
			gbc_txtNombre.insets = new Insets(0, 0, 5, 0);
			gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNombre.gridx = 2;
			gbc_txtNombre.gridy = 3;
			mainPanel.add(txtNombre, gbc_txtNombre);
			txtNombre.setColumns(10);
			if (!isNew) {
				String categ = operadorDetail.getNombre();
				txtNombre.setText(categ);
			}
			txtNombre.addKeyListener(listener);
		}
		{
			JLabel lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setOpaque(Boolean.FALSE);
			lblApellidos.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
			gbc_lblApellidos.anchor = GridBagConstraints.EAST;
			gbc_lblApellidos.insets = new Insets(0, 0, 0, 5);
			gbc_lblApellidos.gridx = 1;
			gbc_lblApellidos.gridy = 4;
			mainPanel.add(lblApellidos, gbc_lblApellidos);
		}
		{
			txtApellidos = new JTextField();
			GridBagConstraints gbc_txtApellidos = new GridBagConstraints();
			gbc_txtApellidos.gridwidth = 2;
			gbc_txtApellidos.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtApellidos.gridx = 2;
			gbc_txtApellidos.gridy = 4;
			mainPanel.add(txtApellidos, gbc_txtApellidos);
			txtApellidos.setColumns(10);
			if (!isNew) {
				String categ = operadorDetail.getApellido();
				txtApellidos.setText(categ);
			}
			txtApellidos.addKeyListener(listener);
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
			final String docIdent = txtDocument.getText().trim();
			final String code = txtCode.getText().trim();
			final String names = txtNombre.getText().trim();
			final String lastnames = txtApellidos.getText().trim();
					
			if (!isNew) {
				final Long operId = Long.parseLong(operadorDetail.getId().toString());
				Operador existOper = facade.findOperatorById(operId);
				existOper.setDocIdent(docIdent);
				existOper.setCodigoOperador(code);
				existOper.setNombre(names);
				existOper.setApellido(lastnames);
				facade.updateOperator(existOper);

				OperatorTableModel tableModel = (OperatorTableModel)operatorPanel.getOperTable().getModel(); 
				final int rowIndex = operatorPanel.getOperTable().getSelectedRow();
				tableModel.setValueAt(docIdent, rowIndex, 1);
				tableModel.setValueAt(code, rowIndex, 2);
				tableModel.setValueAt(names, rowIndex, 3);
				tableModel.setValueAt(lastnames, rowIndex, 4);
				tableModel.fireTableRowsUpdated(rowIndex, rowIndex);
				tableModel.fireTableDataChanged();
			}else {
				Operador oper = new Operador();
				oper.setDocIdent(docIdent);
				oper.setCodigoOperador(code);
				oper.setNombre(names);
				oper.setApellido(lastnames);
				final Long nId = facade.saveOperator(oper);
				operadorDetail.setId(nId);
				operadorDetail.setDocIdent(docIdent);
				operadorDetail.setCodigoOperador(code);
				operadorDetail.setNombre(names);
				operadorDetail.setApellido(lastnames);
				
				((OperatorTableModel)operatorPanel.getOperTable().getModel()).addRow(operadorDetail);
				operatorPanel.noItems++;
				operatorPanel.getNoItemsLabel().setText(Constants.LBL_ITEMS_TITLE+operatorPanel.noItems);
			}
			
			dispose();
		}
	}

}
