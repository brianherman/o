package edu.cs.uic.Document;


import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;



public class DocumentFrame extends JFrame{
	private JTextArea document;
	private DefaultListModel listModel;
	
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem connect;
	private JMenuItem quit;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private Socket socket = null;
	private String name = "beast";

	private JMenuItem newDocument;
	private ActionListener MenuListener;
	private JPanel statusBar;
	private JLabel statusText;
	
	public DocumentFrame(){
		listModel = new DefaultListModel();
		
//		client = new Node();
		////////////////MAIN FRAME MENU SETUP////////////////////////////
		setLayout(new BorderLayout());
		MenuListener = new MenuListener();
		menuBar = new JMenuBar();
		file = new JMenu("File");
		connect = new JMenuItem("Connect");

		quit = new JMenuItem("Quit");

		connect.addActionListener(MenuListener);
		quit.addActionListener(MenuListener);
		file.add(connect);

		file.add(quit);
		menuBar.add(file);
		setJMenuBar(menuBar);
		////////////////////////Status Bar Setup////////////////////////
		statusBar = new JPanel();
		statusText = new JLabel("Ready!");
		statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusBar.add(statusText);
		statusText.setMinimumSize(new Dimension(24,24));
		add(statusBar, BorderLayout.SOUTH);
		////////////////////TEXTAREA SETUP//////////////////////////////
		document = new JTextArea();
		JScrollPane listScroller = new JScrollPane(document);
		document.addKeyListener(new KListener());
		////////////////////////////////////////////////////////////////
		add(listScroller);
		
		////////////////////////////////////////////////////////////////
		//From java trail  http://docs.oracle.com/javase/tutorial/uiswing/misc/modality.html
//		String url = (String)JOptionPane.showInputDialog(
//                null,
//                "Document by URL",
//                "Open",
//                JOptionPane.PLAIN_MESSAGE,
//                null,
//                null,
//                "");
//		if ((url != null) && (url.length() > 0)) {
//			//client.connect(url);
//			//client.sync();
//		}
		connect();
		pack();
		setSize(640, 480);
		setVisible(true);
	}
	public void connect(){
		//Get the server's ip address.
		String ipAddress = (String)JOptionPane.showInputDialog(
				null,
				"Enter the server's ip address:\n",
						"Customized Dialog",
						JOptionPane.PLAIN_MESSAGE,
						null,
						null,
						"127.0.0.1");
		//Get the name of the user.
		name = (String)JOptionPane.showInputDialog(
				null,
				"Enter the username you wish to use:\n",
						"Customized Dialog",
						JOptionPane.PLAIN_MESSAGE,
						null,
						null,
						"brian");
		try{
			//Open the socket and get the input/output streams.
			socket = new Socket(ipAddress,25565);
			out = new ObjectOutputStream(socket.getOutputStream());
			in  = new ObjectInputStream(socket.getInputStream());
			//Send inital connection envelope.
			ArrayList<String> recipiants = new ArrayList<String>();
			recipiants.add("Server");
			Envelope e = new Envelope(name, "Initial Connection.", recipiants);
			out.writeObject(e);
			out.flush();
			//Retrieve the people connected.

		}catch(IOException e){
			e.printStackTrace();
		}
	}
 public class MenuListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==quit){
			//client.disconnect();
			System.exit(0);
		}
		if(e.getSource()==connect){
			//client.disconnect();
			String url = (String)JOptionPane.showInputDialog(
	                null,
	                "Document by URL",
	                "Open",
	                JOptionPane.PLAIN_MESSAGE,
	                null,
	                null,
	                "");
			if ((url != null) && (url.length() > 0)) {
			//	client.connect(url);
			//	client.sync();
			}
		 
		}	
	}
	 
 }
 
 public class KListener implements KeyListener {
	 	boolean editable = false;
	 	boolean commandMode = false;
		private int position;

		public void keyHandler(KeyEvent ev){
			int ID = ev.getID();
			if(ID == KeyEvent.KEY_TYPED){
				try{
					//Open the socket and get the input/output streams.
					socket = new Socket("127.0.0.1",25566);
					out = new ObjectOutputStream(socket.getOutputStream());
					in  = new ObjectInputStream(socket.getInputStream());
					//Send inital connection envelope.
					ArrayList<String> recipiants = new ArrayList<String>();
					recipiants.add("Server");
					Envelope e = new Envelope(name, "Initial Connection.", recipiants);
					out.writeObject(e);
					out.flush();
					//Retrieve the people connected.
					e = (Envelope)in.readObject();
					
				}catch(IOException ex){
					ex.printStackTrace();
				} catch (ClassNotFoundException ex) {
					ex.printStackTrace();
				}
			}else if(ev.getKeyCode()==KeyEvent.VK_ESCAPE){
				editable = !editable;
			}else if(ev.getKeyCode() == KeyEvent.VK_COLON){
				commandMode = !commandMode;
				
			}
			if(commandMode)
			{
				char c = ev.getKeyChar();
				
			}else{
				ArrayList<String> recipiants = new ArrayList<String>();
				recipiants.add("TOALL");
				update(new Envelope(name, document.getText(), recipiants));
			}
			
				
			}
		
		public void update(Envelope m) 
		{
			try {
				//callback.log(m.sender() +" : " + m.message());
				out.writeObject(m);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Send all keyboard events to the same place!
		public void keyPressed(KeyEvent e) {
			keyHandler(e);			
		}
		public void keyReleased(KeyEvent e) {
			//keyHandler(e);
		}
		public void keyTyped(KeyEvent e) {
			//keyHandler(e);
		}
	}
 /**
	 * This thread listens for incoming messages.
	 * @author brianherman
	 *
	 */
	private class ClientThread implements Runnable {
		public void run() {
			Envelope e = null;
			try {
				/*
				 * Loop to check for incoming messages.
				 */
				while((e=(Envelope)in.readObject()) != null)
				{
					String temp = e.getDocument();
					document.setText(temp);
					temp=null;
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}

	} 
}

	
