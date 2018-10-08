/**
 * 
 */
package com.smartlife.smartfleet.tcp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class SimpleClient extends DefaultGui {
	public SimpleClient(int port, String name, boolean myTurn) throws IOException {
        super(port, name, myTurn);
        socket = new Socket("localhost", port);
        inputScanner = new Scanner(socket.getInputStream());
        out = new PrintStream(socket.getOutputStream());
        new MyWorker(inputScanner, this).execute();
    }  
}
