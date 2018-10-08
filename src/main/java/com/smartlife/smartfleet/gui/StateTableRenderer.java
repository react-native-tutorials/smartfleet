/**
 * 
 */
package com.smartlife.smartfleet.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class StateTableRenderer extends DefaultTableCellRenderer {

	@SuppressWarnings("unused")
	private int row, col;
	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 3510924954271332769L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// Save row and column information for use in setValue().

		this.row = row;
		this.col = column;

		// Allow superclass to return rendering component.

		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

	protected void setValue(Object v) {
		// Allow superclass to set the value.

		super.setValue(v);

		// If in names column, color cell with even row number white on
		// dark green, and cell with odd row number black on white.

		if (col == 3) {
			final Color colorCell = new Color((Integer)v);
			setForeground(colorCell);
			setBackground(colorCell);

			return;
		}

		// Must be in balances column. Make sure v is valid.

//		if (v == null)
//			return;
//
//		// Extract the cell's numeric value.
//
//		Double d = (Double) v;
//
//		// If numeric value is less than zero, color cell yellow on red.
//		// Otherwise, color cell black on white.
//
//		if (d.doubleValue() < 0) {
//			setForeground(Color.yellow);
//			setBackground(Color.red);
//		} else {
//			setForeground(UIManager.getColor("Table.foreground"));
//			setBackground(UIManager.getColor("Table.background"));
//		}
	}
}
