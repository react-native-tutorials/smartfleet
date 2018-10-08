/**
 * 
 */
package com.smartlife.smartfleet.ui.draw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class SmartWindow extends JFrame {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 7820417760579180945L;

	
	public SmartWindow() {
		this.setTitle("Smart Coordinates");
		setSize(1000, 1000);
		setLocationByPlatform(Boolean.TRUE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(Boolean.FALSE);
		
		JPanel mainPanel = (JPanel)getContentPane();
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setForeground(Color.YELLOW);
		
		ArrayList<Double> myDiffs = new ArrayList<Double>();
        myDiffs.add(25.0);
        myDiffs.add(9.0);
        myDiffs.add(7.0);
//        myDiffs.add(16.0);
//        myDiffs.add(15.0);
//        myDiffs.add(6.0);
//        myDiffs.add(2.0);
//        myDiffs.add(8.0);
//        myDiffs.add(2.0);
//        myDiffs.add(27.0);
//        myDiffs.add(14.0);
//        myDiffs.add(12.0);
//        myDiffs.add(19.0);
//        myDiffs.add(10.0);
//        myDiffs.add(11.0);
//        myDiffs.add(8.0);
//        myDiffs.add(19.0);
//        myDiffs.add(2.0);
//        myDiffs.add(16.0);
//        myDiffs.add(5.0);
//        myDiffs.add(18.0);
//        myDiffs.add(23.0);
//        myDiffs.add(9.0);
//        myDiffs.add(4.0);
//        myDiffs.add(8.0);
//        myDiffs.add(9.0);
//        myDiffs.add(3.0);
//        myDiffs.add(3.0);
//        myDiffs.add(9.0);
//        myDiffs.add(13.0);
//        myDiffs.add(17.0);
//        myDiffs.add(7.0);
//        myDiffs.add(0.0);
//        myDiffs.add(2.0);
//        myDiffs.add(3.0);
//        myDiffs.add(33.0);
//        myDiffs.add(23.0);
//        myDiffs.add(26.0);
//        myDiffs.add(12.0);
//        myDiffs.add(12.0);
//        myDiffs.add(19.0);
//        myDiffs.add(14.0);
//        myDiffs.add(9.0);
//        myDiffs.add(26.0);
//        myDiffs.add(24.0);
//        myDiffs.add(13.0);
//        myDiffs.add(19.0);
//        myDiffs.add(2.0);
//        myDiffs.add(7.0);
//        myDiffs.add(28.0);
//        myDiffs.add(15.0);
//        myDiffs.add(2.0);
//        myDiffs.add(5.0);
//        myDiffs.add(17.0);
//        myDiffs.add(2.0);
//        myDiffs.add(16.0);
//        myDiffs.add(19.0);
//        myDiffs.add(2.0);
//        myDiffs.add(39.0);
//        myDiffs.add(81.0);
//        myDiffs.add(76.0);
//        myDiffs.add(27.0);
        myDiffs.add(10.0);
        myDiffs.add(15.0);
        myDiffs.add(8.0);
        
        
        DataPanel myPP = new DataPanel(myDiffs,this.getHeight(),this.getWidth());
        mainPanel.add(myPP);
	}
}
