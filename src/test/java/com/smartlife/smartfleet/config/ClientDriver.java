/**
 * 
 */
package com.smartlife.smartfleet.config;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class ClientDriver extends JFrame{

	private PrintWriter outWriter;
	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 2349237893854590205L;

	public ClientDriver() {
		setTitle("Client Test");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(Boolean.TRUE);
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		JTextField txtData = new JTextField(100);
		panelNorth.add(txtData);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Data: "+txtData.getText());
				outWriter.println(txtData.getText());
				txtData.selectAll();
			}
		});
		panelNorth.add(sendButton);
		getContentPane().add(panelNorth, BorderLayout.CENTER);
	}
	
	
	public void connectToServer() {
		String serverAddress =JOptionPane.showInputDialog(this, "Enter IP Address of the Server:","Welcome client", JOptionPane.QUESTION_MESSAGE);
		int port = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter port no:","Welcome client", JOptionPane.QUESTION_MESSAGE));
		Socket socket;
		try {
			socket = new Socket(serverAddress, port);
			outWriter = new PrintWriter(socket.getOutputStream(), Boolean.TRUE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			ClientDriver loginFram = new ClientDriver();
			loginFram.setVisible(Boolean.TRUE);
			loginFram.connectToServer();
		});
	}

}
