/**
 * 
 */
package com.smartlife.smartfleet.tcp;

import java.io.IOException;

import javax.swing.SwingUtilities;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class SimpleServerClient {
	private static final int PORT = 9001;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            try {
                SimpleServer server = new SimpleServer(PORT, "Server", false);
                SimpleClient client = new SimpleClient(PORT, "Client", true);
                server.createGui();
                client.createGui();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
	}

}
