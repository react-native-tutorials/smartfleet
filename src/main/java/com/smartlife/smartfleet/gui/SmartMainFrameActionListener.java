/**
 * 
 */
package com.smartlife.smartfleet.gui;
import static com.smartlife.smartfleet.gui.SmartMainFrame.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class SmartMainFrameActionListener implements ActionListener, WindowListener {

	SmartMainFrame frame;
	
	public SmartMainFrameActionListener(SmartMainFrame frm) {
		this.frame = frm;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionCommand = e.getActionCommand();
		switch (actionCommand) {
		case BTN_GPS_CMD:
			frame.showGpsWindow();
			break;
		case BTN_CFG_CMD:
			frame.showCfgWindow();
			break;
		case BTN_TX_CMD:
			frame.showTxListingWindow();
			break;
		case BTN_ASIG_STATE_CMD:
			frame.showAsigStateWindow();
			break;
		case BTN_LOAD_REP_CMD:
			break;
		case BTN_FUEL_REP_CMD:
			break;
		case BTN_CAN_CMD:
			frame.closeSession();
			break;
		default:
			break;
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		frame.closeSession();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
