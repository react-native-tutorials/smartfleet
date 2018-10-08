/**
 * 
 */
package com.smartlife.smartfleet.engine;

import java.util.Scanner;

import javax.swing.SwingWorker;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class Worker extends SwingWorker<Void, Void> {

	public static final String LINE = "line";
	private Scanner inputScanner;
	private Work work;
	private String line = "";
	
	public Worker(Scanner inputScanner, Work work) {
		this.inputScanner = inputScanner;
		this.work = work;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		while (inputScanner.hasNext()) {
			setLine(inputScanner.nextLine());
			work.workLine(getLine());
			
		}
		return null;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
		firePropertyChange(LINE, null, line);
	}

	
}
