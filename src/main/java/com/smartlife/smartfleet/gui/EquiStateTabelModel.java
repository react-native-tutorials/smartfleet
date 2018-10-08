/**
 * 
 */
package com.smartlife.smartfleet.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.smartlife.smartfleet.domain.view.EquiStateView;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class EquiStateTabelModel extends AbstractTableModel {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -8332865225452247581L;
	protected final Log logger = LogFactory.getLog(getClass());
	private String[] headers = new String[] { "ID", "EQUIPO", "ESTADO", "RAZON", "COMENTARIO", "FECHA INICIO", "FECHA FIN" };

	List<EquiStateView> data = new ArrayList<>();
	
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

	public EquiStateTabelModel(List<EquiStateView> data_) {
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
			return data.get(rowIndex).getEquipo();
		case 2:
			return data.get(rowIndex).getEstado();
		case 3:
			return data.get(rowIndex).getRazon();
		case 4:
			return data.get(rowIndex).getComentario();
		case 5:
			return data.get(rowIndex).getFechaInicio();
		case 6:
			return data.get(rowIndex).getFechaFin();
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
			return String.class;
		case 4:
			return String.class;
		case 5:
			return Date.class;
		case 6:
			return Date.class;
		default:
			return Object.class;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		if (value != null) {
			EquiStateView turno = data.get(row);
			switch (col) {
			case 0:
				turno.setId((Long) value);
				break;
			case 1:
				turno.setEquipo((String) value);
				break;
			case 2:
				turno.setEstado((String)value);
				break;
			case 3:
				turno.setRazon((String)value);
				break;
			case 4:
				turno.setComentario((String)value);
				break;
			case 5:
				turno.setFechaInicio((Date)value);
				break;
			case 6:
				turno.setFechaFin((Date)value);
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

	public void addRow(EquiStateView turno) {
		data.add(turno);

		fireTableRowsInserted(data.size() - 1, data.size() - 1);
	}

	public void removeRow(int row) {
		data.remove(row);
		fireTableRowsDeleted(row, row);
	}
}
