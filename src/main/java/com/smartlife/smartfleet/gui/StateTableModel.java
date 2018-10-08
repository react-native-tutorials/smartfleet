/**
 * 
 */
package com.smartlife.smartfleet.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.smartlife.smartfleet.dto.StateDetail;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class StateTableModel extends AbstractTableModel {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 1768976235499067974L;

	private String[] headers = new String[] {"ID", "CATEGORIA", "ESTADO", "COLOR","RAZON"};
	
	List<StateDetail> data = new ArrayList<>();
	/**
	 * 
	 */
	public StateTableModel(List<StateDetail> data_) {
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
            return data.get(rowIndex).getCategoria();
        case 2:
            return data.get(rowIndex).getEstado();
        case 3:
            return data.get(rowIndex).getColor();
        case 4:
            return data.get(rowIndex).getRazon();
        default:
            return null;
        }
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Class getColumnClass(int col) {
        switch (col) {
        case 0:
            return Long.class;
        case 1:
            return String.class;
        case 2:
            return String.class;
        case 3:
            return Integer.class;
        case 4:
            return String.class;            
        default:
            return Object.class;
        }
    }
	
	@Override
    public boolean isCellEditable(int row, int col) {
        return Boolean.FALSE;
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        if (value != null) {
        	StateDetail estado = data.get(row);
            switch (col) {
            case 0:
                estado.setId((Long) value);
                break;
            case 1:
                estado.setCategoria((String) value);
                break;
            case 2:
                estado.setEstado((String) value);
                break;
            case 3:
                estado.setColor((Integer)value);
                break;
            case 4:
                estado.setRazon((String) value);
                break;
           
            }
        }
    }
    
    public void addRow(StateDetail emp) {
        data.add(emp);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
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
