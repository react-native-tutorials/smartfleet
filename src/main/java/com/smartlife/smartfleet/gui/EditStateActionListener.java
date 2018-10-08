/**
 * 
 */
package com.smartlife.smartfleet.gui;
import static com.smartlife.smartfleet.gui.EditStateUI.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class EditStateActionListener implements ActionListener, KeyListener {

	EditStateUI panel;
	
	public EditStateActionListener(EditStateUI pane_) {
		this.panel = pane_;
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
		case COLOR_CMD:
			panel.chooseColor();
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
