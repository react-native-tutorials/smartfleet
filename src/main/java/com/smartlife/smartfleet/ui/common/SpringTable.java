/**
 * 
 */
package com.smartlife.smartfleet.ui.common;

import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class SpringTable extends JTable {
	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -7190220638579279875L;

	public SpringTable(TableModel dm) {
		super(dm);
		setAutoResizeMode(AUTO_RESIZE_OFF);
	}

	public SpringTable() {
		setAutoResizeMode(AUTO_RESIZE_OFF);
	}

	@Override
	public void doLayout() {
		int width = getWidth();
		int columnCount = getColumnCount();
		int columnSize = width / columnCount;
		for (int index = 0; index < columnCount; index++) {
			TableColumn column = getColumnModel().getColumn(index);
			column.setResizable(false);
			column.setMaxWidth(columnSize);
		}
		super.doLayout();
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return true;
	}
	
	
}
