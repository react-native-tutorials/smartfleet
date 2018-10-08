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
public class ConfigFrameDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AbstractRefreshableApplicationContext context = new ClassPathXmlApplicationContext(
				"com/smartlife/smartfleet/config/appcontext.xml");
		SwingUtilities.invokeLater(()->{
			ConfigFrame frame = new ConfigFrame(context);
			frame.setVisible(Boolean.TRUE);
		});

	}

}
