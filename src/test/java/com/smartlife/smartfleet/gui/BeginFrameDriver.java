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
public class BeginFrameDriver {

	/**
	 * @param args
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception{
		AbstractRefreshableApplicationContext context = new ClassPathXmlApplicationContext(
				"com/smartlife/smartfleet/config/appcontext.xml");
		BeginFrame s=new BeginFrame();
        s.setVisible(true);
        Thread t=Thread.currentThread();
        t.sleep(10000);
        s.dispose();
        
        SwingUtilities.invokeLater(new Runnable(){
            public void run()
            {
                //opening the main application
//            	javax.swing.JOptionPane.showMessageDialog((java.awt.Component) null, "Welcome", "Welcome Screen:",
//    					javax.swing.JOptionPane.DEFAULT_OPTION);
            	ConfigFrame frame = new ConfigFrame(context);
    			frame.setVisible(Boolean.TRUE);
            }
        });

	}

}
