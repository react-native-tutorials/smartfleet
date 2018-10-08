/**
 * 
 */
package com.smartlife.smartfleet.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class StateActionListener extends MouseAdapter implements ActionListener {

	StatePanel panel;

	public StateActionListener(StatePanel pnl_) {
		this.panel = pnl_;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionCommand = e.getActionCommand();
		switch (actionCommand) {
		case StatePanel.NEW_CMD:
			panel.createItem();
			break;
		case StatePanel.ED_CMD:
			panel.editItem();
			break;
		case StatePanel.DEL_CMD:
			panel.deleteItem();
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JTable) {
			final int mouseClicked = e.getClickCount();
			if (mouseClicked == 2) {
				panel.editItem();
			}
		}
	}
}
