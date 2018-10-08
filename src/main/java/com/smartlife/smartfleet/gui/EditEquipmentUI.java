package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.smartlife.smartfleet.domain.Equipo;
import com.smartlife.smartfleet.domain.common.Tipo;
import com.smartlife.smartfleet.dto.EquipmentDetail;
import com.smartlife.smartfleet.facade.SmartFacade;

public class EditEquipmentUI extends JDialog {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -382480513573531287L;
	protected final Log logger = LogFactory.getLog(getClass());
	
	static final String OK_CMD = "OK";
	static final String CANCEL_CMD = "CANCEL";
	
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> cboCateg;
	private JTextField txtCode;
	private JTextField txtMark;
	private JTextField txtModel;
	private JTextField txtCapFuel;
	private JTextField txtCapCharge;
	private JCheckBox chkActive;
	private JButton okButton;
	private JButton cancelButton;
	
	EquipmentDetail equipmentDetail;
	Boolean isNew = Boolean.FALSE;
	EditEquipActionListener listener;
	EquipmentPanel equiposPanel;
	SmartFacade facade;

	/**
	 * Create the dialog.
	 */
	public EditEquipmentUI(EquipmentDetail det_, EquipmentPanel prt) {
		this.equipmentDetail = det_;
		this.equiposPanel = prt;
		listener = new EditEquipActionListener(this);
		if (det_.getId() == null) {
			isNew = Boolean.TRUE;
		}
		facade = equiposPanel.getSmartFacade();
		setTitle("Nuevo / Editar Equipo");
		setBounds(100, 100, 519, 397);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.BLACK);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 135, 67, 100, 50, 0 };
		gbl_contentPanel.rowHeights = new int[] { 25, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		JLabel lblType = new JLabel("Categoria");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.EAST;
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 1;
		contentPanel.add(lblType, gbc_lblType);
		{
			//String[] equipTypes = new String[] { "CUCHILLA", "CAMION", "PALA", "PERFORADORA", "CAMION DE AGUA" };
			List<Tipo> typeList = facade.findTipoByCategory("TIPO_EQUIPO");
			List<String> typeStrList = new ArrayList<>();
			for(Tipo item : typeList) {
				typeStrList.add(item.getTipo());
			}
			String[] tipoArray = typeStrList.stream().map(String :: new).toArray(String[] :: new);
			cboCateg = new JComboBox<>(tipoArray);
			GridBagConstraints gbc_cboCateg = new GridBagConstraints();
			gbc_cboCateg.fill = GridBagConstraints.HORIZONTAL;
			gbc_cboCateg.gridwidth = 2;
			gbc_cboCateg.anchor = GridBagConstraints.NORTH;
			gbc_cboCateg.insets = new Insets(0, 0, 5, 5);
			gbc_cboCateg.gridx = 1;
			gbc_cboCateg.gridy = 1;
			contentPanel.add(cboCateg, gbc_cboCateg);
			if (!isNew) {
				String categ = equipmentDetail.getCategory();
				cboCateg.setSelectedItem(categ);
			}
		}
		{
			JLabel lblCode = new JLabel("Codigo");
			lblCode.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblCode = new GridBagConstraints();
			gbc_lblCode.insets = new Insets(0, 0, 5, 5);
			gbc_lblCode.anchor = GridBagConstraints.EAST;
			gbc_lblCode.gridx = 0;
			gbc_lblCode.gridy = 2;
			contentPanel.add(lblCode, gbc_lblCode);
		}
		{
			txtCode = new JTextField();
			GridBagConstraints gbc_txtCode = new GridBagConstraints();
			gbc_txtCode.gridwidth = 2;
			gbc_txtCode.insets = new Insets(0, 0, 5, 5);
			gbc_txtCode.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCode.gridx = 1;
			gbc_txtCode.gridy = 2;
			contentPanel.add(txtCode, gbc_txtCode);
			txtCode.setColumns(10);
			logger.info(isNew + " | " + equipmentDetail.getCode());
			if (!isNew) {
				txtCode.setText(equipmentDetail.getCode());
			}
			txtCode.addKeyListener(listener);
		}
		{
			JLabel lblMarca = new JLabel("Marca");
			lblMarca.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblMarca = new GridBagConstraints();
			gbc_lblMarca.anchor = GridBagConstraints.EAST;
			gbc_lblMarca.insets = new Insets(0, 0, 5, 5);
			gbc_lblMarca.gridx = 0;
			gbc_lblMarca.gridy = 3;
			contentPanel.add(lblMarca, gbc_lblMarca);
		}
		{
			txtMark = new JTextField();
			GridBagConstraints gbc_txtMark = new GridBagConstraints();
			gbc_txtMark.gridwidth = 2;
			gbc_txtMark.insets = new Insets(0, 0, 5, 5);
			gbc_txtMark.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtMark.gridx = 1;
			gbc_txtMark.gridy = 3;
			contentPanel.add(txtMark, gbc_txtMark);
			txtMark.setColumns(10);
			if(!isNew) {
				txtMark.setText(equipmentDetail.getMark());
			}
			txtMark.addKeyListener(listener);
		}
		{
			JLabel lblModelo = new JLabel("Modelo");
			lblModelo.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblModelo = new GridBagConstraints();
			gbc_lblModelo.anchor = GridBagConstraints.EAST;
			gbc_lblModelo.insets = new Insets(0, 0, 5, 5);
			gbc_lblModelo.gridx = 0;
			gbc_lblModelo.gridy = 4;
			contentPanel.add(lblModelo, gbc_lblModelo);
		}
		{
			txtModel = new JTextField();
			GridBagConstraints gbc_txtModel = new GridBagConstraints();
			gbc_txtModel.gridwidth = 2;
			gbc_txtModel.insets = new Insets(0, 0, 5, 5);
			gbc_txtModel.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtModel.gridx = 1;
			gbc_txtModel.gridy = 4;
			contentPanel.add(txtModel, gbc_txtModel);
			txtModel.setColumns(10);
			if(!isNew) {
				txtModel.setText(equipmentDetail.getModel());
			}
			txtModel.addKeyListener(listener);
		}
		{
			JLabel lblCapComnbl = new JLabel("Cap. comb. (l.)");
			lblCapComnbl.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblCapComnbl = new GridBagConstraints();
			gbc_lblCapComnbl.anchor = GridBagConstraints.EAST;
			gbc_lblCapComnbl.insets = new Insets(0, 0, 5, 5);
			gbc_lblCapComnbl.gridx = 0;
			gbc_lblCapComnbl.gridy = 5;
			contentPanel.add(lblCapComnbl, gbc_lblCapComnbl);
		}
		{
			txtCapFuel = new JTextField();
			GridBagConstraints gbc_txtCapFuel = new GridBagConstraints();
			gbc_txtCapFuel.gridwidth = 2;
			gbc_txtCapFuel.insets = new Insets(0, 0, 5, 5);
			gbc_txtCapFuel.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCapFuel.gridx = 1;
			gbc_txtCapFuel.gridy = 5;
			contentPanel.add(txtCapFuel, gbc_txtCapFuel);
			txtCapFuel.setColumns(10);
			if(!isNew) {
				txtCapFuel.setText(equipmentDetail.getCapFuel().toString());
			}
		}
		{
			JLabel lblCapCargt = new JLabel("Cap. carg. (t.)");
			lblCapCargt.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblCapCargt = new GridBagConstraints();
			gbc_lblCapCargt.anchor = GridBagConstraints.EAST;
			gbc_lblCapCargt.insets = new Insets(0, 0, 5, 5);
			gbc_lblCapCargt.gridx = 0;
			gbc_lblCapCargt.gridy = 6;
			contentPanel.add(lblCapCargt, gbc_lblCapCargt);
		}
		{
			txtCapCharge = new JTextField();
			GridBagConstraints gbc_txtCapCharge = new GridBagConstraints();
			gbc_txtCapCharge.gridwidth = 2;
			gbc_txtCapCharge.insets = new Insets(0, 0, 5, 5);
			gbc_txtCapCharge.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCapCharge.gridx = 1;
			gbc_txtCapCharge.gridy = 6;
			contentPanel.add(txtCapCharge, gbc_txtCapCharge);
			txtCapCharge.setColumns(10);
			if(!isNew) {
				txtCapCharge.setText(equipmentDetail.getCapCharge().toString());
			}
		}
		{
			JLabel lblActivo = new JLabel("Activo");
			lblActivo.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblActivo = new GridBagConstraints();
			gbc_lblActivo.anchor = GridBagConstraints.EAST;
			gbc_lblActivo.insets = new Insets(0, 0, 0, 5);
			gbc_lblActivo.gridx = 0;
			gbc_lblActivo.gridy = 7;
			contentPanel.add(lblActivo, gbc_lblActivo);
		}
		{
			chkActive = new JCheckBox("");
			chkActive.setBackground(Color.BLACK);
			GridBagConstraints gbc_chkActive = new GridBagConstraints();
			gbc_chkActive.fill = GridBagConstraints.HORIZONTAL;
			gbc_chkActive.insets = new Insets(0, 0, 0, 5);
			gbc_chkActive.gridx = 1;
			gbc_chkActive.gridy = 7;
			contentPanel.add(chkActive, gbc_chkActive);
			if(!isNew) {
				Character act = equipmentDetail.getActive();
				if('Y'==act) {
					chkActive.setSelected(Boolean.TRUE);
				}else {
					chkActive.setSelected(Boolean.FALSE);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
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
				"SmartFleet", JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == response) {
			dispose();
		}
	}

	/**
	 * save the new equipment
	 */
	public void saveItem() {
		final int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to save changes?", "SmartFleet",
				JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == response) {
			
			String categoria = (String) cboCateg.getSelectedItem();
			Tipo tipo = facade.findTipoByName(categoria);
			String codigo = txtCode.getText().trim();
			String mark = txtMark.getText().trim();
			String model = txtModel.getText().trim();
			BigDecimal capFuel = new BigDecimal(txtCapFuel.getText().trim()).setScale(2, RoundingMode.HALF_UP);
			BigDecimal capCharge = new BigDecimal(txtCapCharge.getText().trim()).setScale(2, RoundingMode.HALF_UP);
			Boolean isActive = chkActive.isSelected();
			Character active = 'N';
			if(isActive) {
				active = 'Y';
			}
			
			if (!isNew) {
				final Long equipId = Long.parseLong(equipmentDetail.getId().toString());
				Equipo existEquip = facade.findEquipoById(equipId);
				existEquip.setActivo(active.toString());
				existEquip.setCapCarga(capCharge);
				existEquip.setCapCombustible(capFuel);
				existEquip.setModeloEquipo(model);
				existEquip.setMarcaEquipo(mark);
				existEquip.setCodigoEquipo(codigo);
				existEquip.setCategoriaEquipo(tipo);
				facade.updateEquipment(existEquip);

				EquipmentTableModel tableModel = (EquipmentTableModel)equiposPanel.getTblEquipos().getModel(); 
				int rowIndex = equiposPanel.getTblEquipos().getSelectedRow();
				tableModel.setValueAt(categoria, rowIndex, 1);
				tableModel.setValueAt(codigo, rowIndex, 2);
				tableModel.setValueAt(mark, rowIndex, 3);
				tableModel.setValueAt(model, rowIndex, 4);
				tableModel.setValueAt(capFuel, rowIndex, 5);
				tableModel.setValueAt(capCharge, rowIndex, 6);
				tableModel.setValueAt(active, rowIndex, 7);
				tableModel.fireTableRowsUpdated(rowIndex, rowIndex);
				tableModel.fireTableDataChanged();
			}else {
				Equipo equipo = new Equipo();
				equipo.setActivo(active.toString());
				equipo.setCapCarga(capCharge);
				equipo.setCapCombustible(capFuel);
				equipo.setCodigoEquipo(codigo);
				equipo.setMarcaEquipo(mark);
				equipo.setModeloEquipo(model);
				equipo.setCategoriaEquipo(tipo);
				final Long nId = facade.saveEquipment(equipo);
				equipmentDetail.setId(new BigInteger(nId.toString()));
				equipmentDetail.setCategory(categoria);
				equipmentDetail.setCode(codigo);
				equipmentDetail.setMark(mark);
				equipmentDetail.setModel(model);
				equipmentDetail.setCapFuel(capFuel);
				equipmentDetail.setCapCharge(capCharge);
				equipmentDetail.setActive(active);
				
				((EquipmentTableModel)equiposPanel.getTblEquipos().getModel()).addRow(equipmentDetail);
				equiposPanel.noItems++;
				equiposPanel.getLblNoItems().setText("# items: "+equiposPanel.noItems);
			}
			
			dispose();
		}
	}
}
