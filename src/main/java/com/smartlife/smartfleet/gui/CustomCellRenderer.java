/**
 * 
 */
package com.smartlife.smartfleet.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Time;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class CustomCellRenderer implements TableCellRenderer {

	protected final Log logger = LogFactory.getLog(getClass());
	JLabel label;
	int targetRow, targetCol;

	public CustomCellRenderer() {
		label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		targetRow = -1;
		targetCol = -1;
	}
	
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
			label.setBackground(table.getSelectionBackground());
			label.setForeground(table.getSelectionForeground());
		} else {
			label.setBackground(table.getBackground());
			label.setForeground(table.getForeground());
		}
		if (row == targetRow && column == targetCol) {
			label.setBorder(BorderFactory.createLineBorder(Color.red));
			label.setFont(table.getFont().deriveFont(Font.BOLD));
		} else {
			label.setBorder(null);
			label.setFont(table.getFont());
		}
		if(value instanceof Time) {
			value = timeFormat.format(value);
		}

		label.setText(String.valueOf(value));
		return label;
	}

	public void setTargetCell(int row, int col) {
		targetRow = row;
		targetCol = col;
	}

}
