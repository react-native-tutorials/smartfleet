/**
 * 
 */
package com.smartlife.smartfleet.ui.common;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

/**
 * @author Marius Iulian Grigoras
 *
 * @date 10 jul. 2018
 */
public class ColloredButton extends JButton {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -8058692210459312173L;

	public ColloredButton(String text) {
		super.setText(text);
		setContentAreaFilled(Boolean.FALSE);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.dispose();
		super.paintComponent(g);
	}
}
