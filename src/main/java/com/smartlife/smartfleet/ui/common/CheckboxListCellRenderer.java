/**
 * 
 */
package com.smartlife.smartfleet.ui.common;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * @author Marius Iulian Grigoras
 *
 * @date 9 jul. 2018
 */
public class CheckboxListCellRenderer extends JCheckBox implements ListCellRenderer<Object> {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 3740840304303097072L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		setComponentOrientation(list.getComponentOrientation());
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setSelected(isSelected);
        setEnabled(list.isEnabled());

        setText(value == null ? "" : value.toString());  

        return this;
	}

}
