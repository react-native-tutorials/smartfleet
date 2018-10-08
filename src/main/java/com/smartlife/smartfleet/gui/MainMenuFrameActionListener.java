/**
 * 
 */
package com.smartlife.smartfleet.gui;
import static com.smartlife.smartfleet.gui.MainMenuFrame.EXIT_CMD;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class MainMenuFrameActionListener implements ActionListener {

	MainMenuFrame frame;
	
	public MainMenuFrameActionListener(MainMenuFrame fr) {
		this.frame = fr;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionCommand = e.getActionCommand();
		if(EXIT_CMD.equals(actionCommand)) {
			frame.closeSession();
		}

	}

}
