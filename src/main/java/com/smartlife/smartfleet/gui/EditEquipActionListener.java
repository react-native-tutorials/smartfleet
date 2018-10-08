/**
 * 
 */
package com.smartlife.smartfleet.gui;
import static com.smartlife.smartfleet.gui.EditEquipmentUI.OK_CMD;
import static com.smartlife.smartfleet.gui.EditEquipmentUI.CANCEL_CMD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class EditEquipActionListener implements ActionListener, KeyListener {

	EditEquipmentUI panel;
	
	public EditEquipActionListener(EditEquipmentUI dialog) {
		this.panel = dialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionCommand = e.getActionCommand();
		switch (actionCommand) {
		case OK_CMD:
			panel.saveItem();
			break;
		case CANCEL_CMD:
			panel.disposeFrame();
			break;
		default:
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		JTextField sourceField = (JTextField)e.getComponent();
		final int pos = sourceField.getCaretPosition();
		sourceField.setText(sourceField.getText().toUpperCase());
		sourceField.setCaretPosition(pos);
	}

}
