/**
 * 
 */
package com.smartlife.smartfleet.utils;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.JTextComponent;

/**
 * @author Marius Iulian Grigoras
 *
 * @date May 8, 2018
 */
public class UIUtils {

	// public static void resizeColumnWidth(JTable table) {
	//
	// final TableColumnModel columnModel = table.getColumnModel();
	// int width = 50;
	// for (int column = 0; column < table.getColumnCount(); column++) {
	// // Min width
	// for (int row = 0; row < table.getRowCount(); row++) {
	// TableCellRenderer renderer = table.getCellRenderer(row, column);
	// Component comp = table.prepareRenderer(renderer, row, column);
	// width = Math.max(comp.getPreferredSize().width + 1, width);
	//
	// }
	// if (width > 300)
	// width = 300;
	// columnModel.getColumn(column).setPreferredWidth(width);
	// }
	//
	// MultiLineHeaderRenderer multiLineHeaderRenderer = new
	// MultiLineHeaderRenderer();
	// Enumeration<TableColumn> listColumn = table.getColumnModel().getColumns();
	// while (listColumn.hasMoreElements()) {
	// ((TableColumn)
	// listColumn.nextElement()).setHeaderRenderer(multiLineHeaderRenderer);
	// }
	// }

	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			// UIManager.put("InternalFrame.activeTitleBackground", Color.red);
			// UIManager.put("InternalFrame.activeTitleForeground", Color.blue);
			// UIManager.put("InternalFrame.inactiveTitleBackground", Color.black);
			// UIManager.put("InternalFrame.inactiveTitleForeground", Color.yellow);

			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");

			// not-so-sure look and feel
//			System.setProperty("os.name", "Windows");
//			System.setProperty("os.version", "8.1");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} 
	}

	public static int getMaxValue(int[] numbers) {
		int maxValue = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > maxValue) {
				maxValue = numbers[i];
			}
		}
		return maxValue;
	}
	
	public static void displayReadFile(String str_, JTextComponent txtComponent) {
		try {
            FileReader fr = new FileReader(str_);
            txtComponent.read(fr, null);
            fr.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
	}

	/**
	 * Shows an already existing JFrame as if it were a modal JDialog. JFrames have
	 * the upside that they can be maximized.
	 * <p>
	 * A hidden modal JDialog is "shown" to effect the modality.
	 * <p>
	 * When the JFrame is closed, this method's listener will pick up on that, close
	 * the modal JDialog, and remove the listener.
	 *
	 * made by dreamspace-president.com
	 *
	 * @param window
	 *            the JFrame to be shown
	 * @param owner
	 *            the owner window (can be null)
	 * @throws IllegalArgumentException
	 *             if argument "window" is null
	 */
	public static void showModalJFrame(final JFrame window, final Frame owner) {

		if (window == null) {
			throw new IllegalArgumentException();
		}
		window.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
		window.setVisible(true);
		window.setAlwaysOnTop(true);

		final JDialog hiddenDialogForModality = new JDialog(owner, true);
		final class CustomWindowCloseListener extends WindowAdapter {
			@Override
			public void windowClosed(final WindowEvent e) {
				window.dispose();
				hiddenDialogForModality.dispose();
			}
		}

		final CustomWindowCloseListener myWindowCloseListener = new CustomWindowCloseListener();
		window.addWindowListener(myWindowCloseListener);

		final Dimension smallSize = new Dimension(80, 80);
		hiddenDialogForModality.setMinimumSize(smallSize);
		hiddenDialogForModality.setSize(smallSize);
		hiddenDialogForModality.setMaximumSize(smallSize);
		hiddenDialogForModality.setLocation(-smallSize.width * 2, -smallSize.height * 2);
		hiddenDialogForModality.setVisible(true);
		window.removeWindowListener(myWindowCloseListener);
	}
	
	public static Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
}
