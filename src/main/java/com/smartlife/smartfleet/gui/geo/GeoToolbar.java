/**
 * 
 */
package com.smartlife.smartfleet.gui.geo;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class GeoToolbar implements ActionListener {

	private ShowGpsFrame frame;
	private JToolBar toolBar;
	private JButton pencil;
	private JButton line;
	private JButton rectangle;
	private JButton circle;
	private JButton text;

	public GeoToolbar(ShowGpsFrame parent) {
		this.frame = parent;
		this.initializeToolbar();
		rectangle.addActionListener(this);
		line.addActionListener(this);
		circle.addActionListener(this);
		pencil.addActionListener(this);
		text.addActionListener(this);
	}

	private void initializeToolbar() {
		toolBar = new JToolBar(JToolBar.HORIZONTAL);
		toolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		toolBar.setFloatable(false);
		toolBar.setLayout(new GridLayout(0, 18));

		pencil = new JButton("Pencil", new ImageIcon(this.getClass().getResource("/icons/Pencil-24.png")));
		line = new JButton("Line", new ImageIcon(this.getClass().getResource("/icons/Line-24.png")));
		rectangle = new JButton("Rectangle", new ImageIcon(this.getClass().getResource("/icons/Rectangle-24.png")));
		circle = new JButton("Circle", new ImageIcon(this.getClass().getResource("/icons/Circled.png")));
		text = new JButton("Text", new ImageIcon(this.getClass().getResource("/icons/Type-24.png")));

//		toolBar.add(pencil);
//		toolBar.add(line);
//		toolBar.add(rectangle);
//		toolBar.add(circle);
//		toolBar.addSeparator();
//		toolBar.add(text);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		if (source == pencil) {
			frame.getMap().setTool(0);
		} else if (source == line) {
			frame.getMap().setTool(1);
		} else if (source == rectangle) {
			frame.getMap().setTool(2);
		} else if (source == circle) {
			frame.getMap().setTool(3);
		} else if (source == text) {
			frame.getMap().setTool(5);
		}
	}

	public JToolBar getToolBar() {
		return this.toolBar;
	}

}
