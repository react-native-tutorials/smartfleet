/**
 * 
 */
package com.smartlife.smartfleet.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.smartlife.smartfleet.domain.view.DispAssignView;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class DispAssignTableModel extends AbstractTableModel {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 1768976235499067974L;

	private String[] headers = new String[] {"ID", "EQUIPO", "DISPOSITIVO", "FECHA","ACTIVO"};
	
	List<DispAssignView> data = new ArrayList<>();
	/**
	 * 
	 */
	public DispAssignTableModel(List<DispAssignView> data_) {
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
            return data.get(rowIndex).getAssignId();
        case 1:
            return data.get(rowIndex).getEquiCode();
        case 2:
            return data.get(rowIndex).getDispIp();
        case 3:
            return data.get(rowIndex).getFechaAsig();
        case 4:
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
            return Long.class;
        case 1:
            return String.class;
        case 2:
            return String.class;
        case 3:
            return Date.class;
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
        	DispAssignView estado = data.get(row);
            switch (col) {
            case 0:
                estado.setAssignId((Long) value);
                break;
            case 1:
                estado.setEquiCode((String) value);
                break;
            case 2:
                estado.setDispIp((String) value);
                break;
            case 3:
                estado.setFechaAsig((Date)value);
                break;
            case 4:
                estado.setActive((String) value);
                break;
           
            }
        }
    }
    
    public void addRow(DispAssignView emp) {
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
