/**
 * 
 */
package com.smartlife.smartfleet.gui;

import javax.swing.SwingUtilities;

import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AbstractRefreshableApplicationContext context = new ClassPathXmlApplicationContext(
				"com/smartlife/smartfleet/config/appcontext.xml");
		SwingUtilities.invokeLater(()->{
//			SmartMainFrame frame = new SmartMainFrame(context);
//			frame.setVisible(Boolean.TRUE);
		});

	}

}
