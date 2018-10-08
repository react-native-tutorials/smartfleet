/**
 * 
 */
package com.smartlife.smartfleet.ui.common;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class TextFrame extends JFrame{
	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 1642421882609153501L;

	public TextFrame(String content) {
		super("TextFrame");

		JTextArea ta = new JTextArea();
		ta.setText(content);
		getContentPane().add(ta);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});
		
		setSize(400, 400);
	}
}
