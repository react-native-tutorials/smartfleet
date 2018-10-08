/**
 * 
 */
package com.smartlife.smartfleet.config;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class TestTimer {

	static Timer timer = new Timer();
    static int seconds = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestTimer();
	}
	
	public TestTimer() {
		System.out.println("_______________________________________________________________");
		TimerTask task = new TimerTask() {
			private final int MAX_SECONDS = 100;
			
			@Override
			public void run() {
				
				if (seconds < MAX_SECONDS) {
	                System.out.println("Seconds = " + seconds);
	                seconds++;
	            } else {
	                // stop the timer
	                cancel();
	            }
				
			}
		};
		timer.schedule(task, 0, 10);
	}

}
