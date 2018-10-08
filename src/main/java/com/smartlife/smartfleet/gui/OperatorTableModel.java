/**
 * 
 */
package com.smartlife.smartfleet.gui;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.smartlife.smartfleet.domain.Operador;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class OperatorTableModel extends AbstractTableModel {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -8332865225452247581L;

	private String[] headers = new String[] { "ID", "DOCUMENT", "CODIGO", "NOMBRES", "APELLIDOS" };

	List<Operador> data = new ArrayList<>();

	public OperatorTableModel(List<Operador> data_) {
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
			return data.get(rowIndex).getDocIdent();
		case 2:
			return data.get(rowIndex).getCodigoOperador();
		case 3:
			return data.get(rowIndex).getNombre();
		case 4:
			return data.get(rowIndex).getApellido();
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
		default:
			return Object.class;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		if (value != null) {
			Operador operator = data.get(row);
			switch (col) {
			case 0:
				operator.setId((Long) value);
				break;
			case 1:
				operator.setDocIdent((String) value);
				break;
			case 2:
				operator.setCodigoOperador((String) value);
				break;
			case 3:
				operator.setNombre((String) value);
				break;
			case 4:
				operator.setApellido((String) value);
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

	public void addRow(Operador operator) {
		data.add(operator);

		fireTableRowsInserted(data.size() - 1, data.size() - 1);
	}

	public void removeRow(int row) {
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}
}
