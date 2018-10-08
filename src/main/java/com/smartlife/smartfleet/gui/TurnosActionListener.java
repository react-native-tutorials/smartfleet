/**
 * 
 */
package com.smartlife.smartfleet.gui;
import static com.smartlife.smartfleet.gui.TurnosPanel.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class TurnosActionListener extends MouseAdapter implements ActionListener {

	TurnosPanel panel;
	
	public TurnosActionListener(TurnosPanel pane) {
		this.panel = pane;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionCommand = e.getActionCommand();
		switch (actionCommand) {
		case NEW_CMD:
			panel.createItem();
			break;
		case ED_CMD:
			panel.editItem();
			break;
		case DEL_CMD:
			panel.deleteItem();
			break;
		default:
			break;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof JTable) {
			final int mouseClicked = e.getClickCount();
			if (mouseClicked == 2) {
				panel.editItem();
			}
		}
	}
}
