/**
 * 
 */
package com.smartlife.smartfleet.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JWindow;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class SplashTest extends JWindow {
	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 8440640040012990104L;

	Image img = Toolkit.getDefaultToolkit().getImage(SplashTest.class.getResource("/icons/cube_500.gif"));

	ImageIcon imgicon = new ImageIcon(img);

	public SplashTest() {
		try {

//			setSize(633, 300);
			setSize(600, 600);
			setLocationRelativeTo(null);
			setVisible(Boolean.TRUE);
			Thread.sleep(5000);
			dispose();
			javax.swing.JOptionPane.showMessageDialog((java.awt.Component) null, "Welcome", "Welcome Screen:",
					javax.swing.JOptionPane.DEFAULT_OPTION);
		} catch (Exception exception) {
			javax.swing.JOptionPane.showMessageDialog((java.awt.Component) null, "Error" + exception.getMessage(),
					"Error:", javax.swing.JOptionPane.DEFAULT_OPTION);
		}
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		SplashTest sp = new SplashTest();
	}

}
