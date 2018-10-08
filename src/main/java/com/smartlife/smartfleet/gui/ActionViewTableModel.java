/**
 * 
 */
package com.smartlife.smartfleet.gui;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.smartlife.smartfleet.domain.view.ActionView;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class ActionViewTableModel extends AbstractTableModel {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -8332865225452247581L;
	protected final Log logger = LogFactory.getLog(getClass());
	private String[] headers = new String[] { "ID", "EQUIPO", "FECHA", "MENSAJE" };

	List<ActionView> data = new ArrayList<>();
	
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

	public ActionViewTableModel(List<ActionView> data_) {
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
			return data.get(rowIndex).getActionId();
		case 1:
			return data.get(rowIndex).getCodEqui();
		case 2:
			return data.get(rowIndex).getDateSent();
		case 3:
			return data.get(rowIndex).getMessage();
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
			return Date.class;
		case 3:
			return String.class;
		default:
			return Object.class;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		if (value != null) {
			ActionView turno = data.get(row);
			switch (col) {
			case 0:
				turno.setActionId((Long) value);
				break;
			case 1:
				turno.setCodEqui((String) value);
				break;
			case 2:
				turno.setDateSent((Date)value);
				break;
			case 3:
				turno.setMessage((String)value);
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

	public void addRow(ActionView actionView) {
		data.add(actionView);

		fireTableRowsInserted(data.size() - 1, data.size() - 1);
	}

	public void removeRow(int row) {
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}
}
