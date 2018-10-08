/**
 * 
 */
package com.smartlife.smartfleet.gui;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.smartlife.smartfleet.dto.EquipmentDetail;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class EquipmentTableModel extends AbstractTableModel {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -6891157632645960713L;

	private String[] headers = new String[] { "ID", "CATEGORIA", "CODIGO", "MARCA", "MODELO", "CAP.\nCOMBUSTIBLE",
			"CAP.\nCARGA", "ACTIVO" };

	List<EquipmentDetail> data = new ArrayList<>();

	public EquipmentTableModel(List<EquipmentDetail> data_) {
		super();
		data.addAll(data_);
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}
	
	@Override
    public String getColumnName(int col) {
        return headers[col];
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return data.get(rowIndex).getId();
		case 1:
			return data.get(rowIndex).getCategory();
		case 2:
			return data.get(rowIndex).getCode();
		case 3:
			return data.get(rowIndex).getMark();
		case 4:
			return data.get(rowIndex).getModel();
		case 5:
			return data.get(rowIndex).getCapFuel();
		case 6:
			return data.get(rowIndex).getCapCharge();
		case 7:
			return data.get(rowIndex).getActive();
		default:
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int col) {
		switch (col) {
		case 0:
			return BigInteger.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
			return BigDecimal.class;
		case 6:
			return BigDecimal.class;
		case 7:
			return Character.class;
		default:
			return Object.class;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		if (value != null) {
			EquipmentDetail equipment = data.get(row);
			switch (col) {
			case 0:
				equipment.setId((BigInteger) value);
				break;
			case 1:
				equipment.setCategory((String) value);
				break;
			case 2:
				equipment.setCode((String) value);
				break;
			case 3:
				equipment.setMark((String) value);
				break;
			case 4:
				equipment.setModel((String) value);
				break;
			case 5:
				equipment.setCapFuel((BigDecimal)value);
				break;
			case 6:
				equipment.setCapCharge((BigDecimal)value);
				break;
			case 7:
				equipment.setActive((Character)value);
				break;
			}
		}
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return Boolean.FALSE;
	}
	
	public void addRow(EquipmentDetail equipmentDetail) {
		data.add(equipmentDetail);
		fireTableRowsInserted(data.size()-1, data.size()-1);
	}
	
	public void removeRow(int row) {
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

}
