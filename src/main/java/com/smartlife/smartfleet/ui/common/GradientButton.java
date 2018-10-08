/**
 * 
 */
package com.smartlife.smartfleet.ui.common;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JButton;

/**
 * @author Marius Iulian Grigoras
 *
 * @date 9 jul. 2018
 */
public class GradientButton extends JButton {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -4165300038891041520L;

	public GradientButton(String text) {
		super(text);
		setContentAreaFilled(Boolean.FALSE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(new GradientPaint(new Point(0, 0), getBackground(), new Point(0, getHeight() / 3), Color.WHITE));
		g2.fillRect(0, 0, getWidth(), getHeight() / 3);
		g2.setPaint(new GradientPaint(new Point(0, getHeight() / 3), Color.WHITE, new Point(0, getHeight()),
				getBackground()));
		g2.fillRect(0, getHeight() / 3, getWidth(), getHeight());
		g2.dispose();

		super.paintComponent(g);
	}
}
