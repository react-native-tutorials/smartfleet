/**
 * 
 */
package com.smartlife.smartfleet;

import javax.swing.SwingUtilities;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.smartlife.smartfleet.ui.draw.SmartWindow;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class CoordinatesApp {
	protected static final Log logger = LogFactory.getLog(CoordinatesApp.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String userName = System.getProperty("user.name");
		final String osName = System.getProperty("os.name");
		final String osVersion = System.getProperty("os.version");
		logger.info("User name: "+userName + " |OS Name: "+osName+" |OS Version: "+osVersion);
		showOS();
		SwingUtilities.invokeLater(()->{
			SmartWindow smartFrame = new SmartWindow();
			smartFrame.setVisible(Boolean.TRUE);
		});
	}
	
	public static void showOS() {
		if(SystemUtils.IS_OS_WINDOWS_7) {
			logger.info("It is a Windows 7 OS");
		}
		if(SystemUtils.IS_OS_WINDOWS_8) {
			logger.info("It is a Windows 8 OS");
		}
		if(SystemUtils.IS_OS_WINDOWS_10) {
			logger.info("It is a Windows 10 OS");
		}
		if(SystemUtils.IS_OS_LINUX) {
			logger.info("It is a Linux OS");
		}
		if(SystemUtils.IS_OS_MAC) {
			logger.info("It is a MAC OS");
		}
	}

}
