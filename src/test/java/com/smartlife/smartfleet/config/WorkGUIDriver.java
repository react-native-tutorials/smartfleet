/**
 * 
 */
package com.smartlife.smartfleet.config;

import javax.swing.SwingUtilities;

import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smartlife.smartfleet.engine.WorkGUI;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class WorkGUIDriver {

	
	public static void main(String[] args) {
		WorkGUIDriver workGUIDriver = new WorkGUIDriver();
		workGUIDriver.workDriver();
	}
	
	public void workDriver() {
		AbstractRefreshableApplicationContext contxt = new ClassPathXmlApplicationContext(
				"com/smartlife/smartfleet/config/appcontext.xml");
		SwingUtilities.invokeLater(()->{
//			WorkGUI workFrame = new WorkGUI(contxt, "Work Driver Frame", 9190);
//			workFrame.setVisible(Boolean.TRUE);
		});
	}
}
