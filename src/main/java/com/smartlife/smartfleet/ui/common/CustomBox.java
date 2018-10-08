/**
 * 
 */
package com.smartlife.smartfleet.ui.common;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Box;

/**
 * @author Marius Iulian Grigoras
 *
 * @date 16 jul. 2018
 */
public class CustomBox extends Box {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 6024329393490193122L;

	public CustomBox(int axis) {
		super(axis);
		
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = new Color(102, 204, 255);
        Color color2 = Color.WHITE;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
