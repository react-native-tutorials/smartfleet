/**
 * 
 */
package com.smartlife.smartfleet.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.smartlife.smartfleet.dto.EquipmentDetail;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class EquipmentActionListener extends MouseAdapter implements ActionListener, ListSelectionListener {

	EquipmentPanel panel;
	
	public EquipmentActionListener(EquipmentPanel pnl) {
		this.panel = pnl;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		switch (actionCommand) {
		case EquipmentPanel.NEW_CMD:
			panel.createItem();
			break;
		case EquipmentPanel.ED_CMD:
			panel.editItem();
			break;
		case EquipmentPanel.REF_CMD:
			break;
		case EquipmentPanel.DEL_CMD:
			panel.deleteItem();
			break;
		default:
			break;
		}
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		int[] selRows;
		if(!e.getValueIsAdjusting()) {
			selRows = panel.getTblEquipos().getSelectedRows();
			if(selRows.length>0) {
				EquipmentTableModel aModel = (EquipmentTableModel)panel.getTblEquipos().getModel();
				final BigInteger id = (BigInteger)aModel.getValueAt(selRows[0],0);
				final String categoria = (String)aModel.getValueAt(selRows[0], 1);
				final String codigo = (String)aModel.getValueAt(selRows[0], 2);
				final String marca = (String)aModel.getValueAt(selRows[0], 3);
				final String modelo = (String)aModel.getValueAt(selRows[0], 4);
				final BigDecimal capComb = (BigDecimal)aModel.getValueAt(selRows[0], 5);
				final BigDecimal capCharge = (BigDecimal)aModel.getValueAt(selRows[0], 6);
				final Character active = (Character)aModel.getValueAt(selRows[0], 7);
				
				EquipmentDetail equipmentDetail = new EquipmentDetail();
				equipmentDetail.setId(id);
				equipmentDetail.setCategory(categoria);
				equipmentDetail.setCode(codigo);
				equipmentDetail.setMark(marca);
				equipmentDetail.setModel(modelo);
				equipmentDetail.setCapFuel(capComb);
				equipmentDetail.setCapCharge(capCharge);
				equipmentDetail.setActive(active);
			}
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof JTable) {
			final int mouseClicked = e.getClickCount();
			if (mouseClicked == 2) {
				panel.editItem();
			}
		}
	}


	
}
