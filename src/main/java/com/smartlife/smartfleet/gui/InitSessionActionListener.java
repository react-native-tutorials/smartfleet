/**
 * 
 */
package com.smartlife.smartfleet.gui;

import static com.smartlife.smartfleet.gui.InitSession.OK_CMD;
import static com.smartlife.smartfleet.gui.InitSession.CANCEL_CMD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class InitSessionActionListener implements ActionListener {

	InitSession frame;

	public InitSessionActionListener(InitSession fr_) {
		this.frame = fr_;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionCommand = e.getActionCommand();
		if (OK_CMD.equals(actionCommand)) {
			frame.enterApp();
		} else if (CANCEL_CMD.equals(actionCommand)) {
			frame.exitApp();
		}
	}

}
