/**
 * 
 */
package com.smartlife.smartfleet.app;

import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smartlife.smartfleet.gui.InitSession;

/**
 * @author Marius-Iulian Grigoras
 *
 *
 */
public class SmartApp {
	protected static final Log logger = LogFactory.getLog(SmartApp.class);

	/**
	 * @param args
	 */
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		AbstractRefreshableApplicationContext contxt = new ClassPathXmlApplicationContext("com/smartlife/smartfleet/config/appcontext.xml");
		logger.info("CONTEXT LOADED");
		logger.info("INVOKE APPLICATION..");
		SwingUtilities.invokeLater(() -> {

			InitSession frame = new InitSession();
			frame.setContext(contxt);
			frame.initUI();
		});
		logger.info("APPLICATION LOADED");
	}
}
