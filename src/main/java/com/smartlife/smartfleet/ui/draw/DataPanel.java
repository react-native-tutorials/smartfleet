/**
 * 
 */
package com.smartlife.smartfleet.ui.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.AffineTransform;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class DataPanel extends JPanel {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -3355049136136677718L;

	Insets ins;
	ArrayList<Double> myDiffs;
	double maxDiff = Double.NEGATIVE_INFINITY;
	double minDiff = Double.POSITIVE_INFINITY;
	double maxPlot;
	AffineTransform at;
	
	public DataPanel(ArrayList<Double> diffs_, int h, int w) {
		setOpaque(Boolean.FALSE);
		final Dimension dim = new Dimension(w, h);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		this.myDiffs = diffs_;
		repaint();
		setVisible(Boolean.TRUE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int height = getHeight();
		int width = getWidth();
		ins = getInsets();
		Graphics2D g2d = (Graphics2D) g;
		FontMetrics fontMetrics = g2d.getFontMetrics();
		String xString = ("x-axis label");
		int xStrWidth = fontMetrics.stringWidth(xString);
		int xStrHeight = fontMetrics.getHeight();
		String yString = ("y-axis label");
		int yStrWidth = fontMetrics.stringWidth(yString);
		int yStrHeight = fontMetrics.getHeight();
		String titleString = "Title of Graphic";
		int titleStrWidth = fontMetrics.stringWidth(titleString);
		int titleStrHeight = fontMetrics.getHeight();
		int leftMargin = ins.left;
		// set parameters for inner rectangle
		int hPad = 10;
		int vPad = 6;
		int testLeftStartPlotWindow = ins.left + 5 + (3 * yStrHeight);
		int testInnerWidth = width - testLeftStartPlotWindow - ins.right - hPad;
		getMaxMinDiffs();
		getMaxPlotVal();
		double increment = 5.0;
		int numTicks = (int) (maxPlot / increment); // will use numTicks for: remainder, leftStartPlotWindow,
													// innerRectangle+labels+tickmarks
		int remainder = testInnerWidth % numTicks;
		int leftStartPlotWindow = testLeftStartPlotWindow - remainder;
		System.out.println("Remainder is: " + remainder);
		int bottomPad = (3 * xStrHeight) - vPad;
		int blueTop = ins.bottom + (vPad / 2) + titleStrHeight;
		int blueHeight = height - bottomPad - blueTop;
		int blueWidth = blueHeight;
		int blueBottom = blueHeight + blueTop;

		// plot outer rectangle
		g.setColor(Color.RED);
		int redWidth = width - leftMargin - 1;
		g.drawRect(leftMargin, ins.bottom, redWidth, height - ins.bottom - 1);

		// write top label
		g.setColor(Color.WHITE);
		g.drawString(titleString, leftStartPlotWindow + ((blueWidth / 2) - (titleStrWidth / 2)), titleStrHeight);

		// fill, then plot, inner rectangle
		g.setColor(Color.black);
		g.fillRect(leftStartPlotWindow, blueTop, blueWidth, blueHeight);
		g.setColor(Color.yellow);
		g.drawRect(leftStartPlotWindow, blueTop, blueWidth, blueHeight);

		// scale the diffs to fit window
		double Scalar = blueWidth / maxPlot;
		ArrayList<Double> scaledDiffs = new ArrayList<Double>();
		for (int e = 0; e < myDiffs.size(); e++) {
			scaledDiffs.add(myDiffs.get(e) * Scalar);
		}

		// plot the scaled Diffs
		at = g2d.getTransform();// save the graphics context's transform
		// g2d.translate(leftStartPlotWindow, blueTop);// translate origin to bottom-left corner of blue rectangle
		g2d.translate(width/2, height/2);
		g2d.scale(1, -1);// invert the y-axis
		for (int w = 0; w < scaledDiffs.size(); w++) {
			if (w > 0) {
				double prior = scaledDiffs.get(w - 1);
				int priorInt = (int) prior;
				double current = scaledDiffs.get(w);
				int currentInt = (int) current;
				g2d.drawOval(priorInt, currentInt, 4, 4);
			}
		}
		g2d.setTransform(at);// restore the transform for conventional rendering

		// write x-axis label
		g.setColor(Color.red);
		g.drawString(xString, leftStartPlotWindow + ((blueWidth / 2) - (xStrWidth / 2)), height - ins.bottom - vPad);

		// write y-axis label
		g2d.rotate(Math.toRadians(-90), 0, 0);// rotate text 90 degrees counter-clockwise
		g.drawString(yString, -(height / 2) - (yStrWidth / 2), yStrHeight);
		g2d.rotate(Math.toRadians(+90), 0, 0);// rotate text 90 degrees clockwise

		// draw tick marks on x-axis
		NumberFormat formatter = new DecimalFormat("#0.0");
		double k = (double) blueWidth / (double) numTicks;
		double iteration = 0;
		for (int h = 0; h <= numTicks; h++) {
			int xval = (int) (h * k);
			g.setColor(Color.red);
			g.drawLine(leftStartPlotWindow + xval, blueBottom + 2, leftStartPlotWindow + xval,
					blueBottom + (xStrHeight / 2));// draw tick marks
			g.drawString(formatter.format(iteration),
					leftStartPlotWindow + xval - (fontMetrics.stringWidth(Double.toString(iteration)) / 2),
					blueBottom + (xStrHeight / 2) + 13);
			iteration += increment;
		}
		
		// draw tick marks on y-axis
	    iteration = 0;
	    for(int h=0;h<=numTicks;h++){
	        int yval = (int)(h*k);
	        System.out.println(yval);
	        g.setColor(Color.red);
	        g.drawLine(leftStartPlotWindow-2, blueBottom-yval, leftStartPlotWindow-(yStrHeight/2), blueBottom-yval);//draw tick marks
	        g2d.rotate(Math.toRadians(-90), 0, 0);//rotate text 90 degrees counter-clockwise
	        g.drawString(formatter.format(iteration),leftStartPlotWindow-2,blueBottom-(fontMetrics.stringWidth(Double.toString(iteration))/2));
	        g2d.rotate(Math.toRadians(+90), 0, 0);//rotate text 90 degrees clockwise
	        iteration+=increment;
	        System.out.println(iteration);
	    }
	}

	void getMaxMinDiffs() {// get max and min of Diffs
		for (int u = 0; u < myDiffs.size(); u++) {
			if (myDiffs.get(u) > maxDiff) {
				maxDiff = myDiffs.get(u);
			}
			if (myDiffs.get(u) < minDiff) {
				minDiff = myDiffs.get(u);
			}
		}
	}

	void getMaxPlotVal() {
		maxPlot = maxDiff;
		maxPlot += 1;// make sure maxPlot is bigger than the max data value
		while (maxPlot % 5 != 0) {
			maxPlot += 1;
		} // make sure maxPlot is a multiple of 5
	}
}
