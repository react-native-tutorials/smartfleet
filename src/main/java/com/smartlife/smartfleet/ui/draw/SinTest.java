/**
 * 
 */
package com.smartlife.smartfleet.ui.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class SinTest extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6935982620082435978L;
	private static final int SIZE = 400;
    private AffineTransform at;

    public static void main(String[] args) {
        EventQueue.invokeLater(new SinTest());
    }
    @Override
    public void run() {
        JFrame f = new JFrame("ArcTest");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public SinTest() {
        this.setPreferredSize(new Dimension(640, 480));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        g2d.setColor(Color.gray);
        g2d.drawLine(w / 2, 0, w / 2, h);
        g2d.drawLine(0, h / 2, w, h / 2);

        at = g2d.getTransform();
        g2d.translate(w / 2, h / 2);
        g2d.scale(1, -1);
        g2d.setColor(Color.blue);
        double r = SIZE / 2;
        double q = -Math.PI / 2;
        double d = Math.PI / 180d;
        int x1 = (int) Math.round(q * r);
        int y1 = (int) Math.round(Math.sin(q) * r);
        int x2, y2;
        for (int i = 0; i < 180; i++) {
            q += d;
            x2 = (int) Math.round(q * r);
            y2 = (int) Math.round(Math.sin(q) * r);
            g2d.drawLine(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }

        g2d.setTransform(at);
        g2d.setColor(Color.blue);
        g2d.drawString("y = sin(x)", 100, 100);

    }

}
