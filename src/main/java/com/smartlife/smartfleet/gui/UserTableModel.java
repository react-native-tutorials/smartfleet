/**
 * 
 */
package com.smartlife.smartfleet.gui;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.smartlife.smartfleet.domain.security.Usuario;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class UserTableModel extends AbstractTableModel {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -8332865225452247581L;

	private String[] headers = new String[] { "ID", "DOCUMENTO", "NOMBRES", "APELLIDOS", "CUENTA", "CLAVE", "ACTIVO" };

	List<Usuario> data = new ArrayList<>();

	public UserTableModel(List<Usuario> data_) {
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
	public boolean isCellEditable(int row, int col) {
		return Boolean.FALSE;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return data.get(rowIndex).getId();
		case 1:
			return data.get(rowIndex).getUsuDoc();
		case 2:
			return data.get(rowIndex).getUsuNom();
		case 3:
			return data.get(rowIndex).getUsuApe();
		case 4:
			return data.get(rowIndex).getUsuAcc();
		case 5:
			return data.get(rowIndex).getUsuPass();
		case 6:
			return data.get(rowIndex).getActivo();
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
			return String.class;
		default:
			return Object.class;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		if (value != null) {
			Usuario user = data.get(row);
			switch (col) {
			case 0:
				user.setId((Long) value);
				break;
			case 1:
				user.setUsuDoc((String) value);
				break;
			case 2:
				user.setUsuNom((String) value);
				break;
			case 3:
				user.setUsuApe((String) value);
				break;
			case 4:
				user.setUsuAcc((String) value);
				break;
			case 5:
				user.setUsuPass((String) value);
				break;
			case 6:
				user.setActivo((String) value);
				break;
			}
		}
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public void addRow(Usuario user) {
		data.add(user);

		fireTableRowsInserted(data.size() - 1, data.size() - 1);
	}

	public void removeRow(int row) {
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}
}
