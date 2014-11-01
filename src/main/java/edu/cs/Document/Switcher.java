package edu.cs.uic.Document;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Switcher {
	/*
	 * Switches between server and client.
	 */
	public static void main(String[] args) {
		Object[] options = { "Server", "Client" };
		int n = JOptionPane.showOptionDialog(null, "Do you want to launch a server or client", "Selection",
		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		null, options, options[0]);
		if(n == 0){
			Server s = new Server();
			s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else{
			DocumentFrame df = new DocumentFrame();
		}
	}
}
