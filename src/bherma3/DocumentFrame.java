package bherma3;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.*;
import javax.swing.border.BevelBorder;


public class DocumentFrame extends JFrame{
	private JTextArea document;
	private DefaultListModel listModel;

	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem update;
	private JMenuItem quit;
	private Socket socket = null;
	private String Author = "beast";

	private JMenuItem newDocument;
	private ActionListener MenuListener;
	private JPanel statusBar;
	private JButton statusText;
	private String id;
	public boolean finishedLoading = false;
	private JButton push;
	private JButton pull;

	private JMenuItem open;
	private JButton send;
	public DocumentFrame(){
		listModel = new DefaultListModel();

		//		client = new Node();
		////////////////MAIN FRAME MENU SETUP////////////////////////////
		setLayout(new BorderLayout());
		MenuListener = new MenuListener();
		menuBar = new JMenuBar();
		file = new JMenu("File");
		open = new JMenuItem("Open");

		quit = new JMenuItem("Quit");

		open.addActionListener(MenuListener);
		quit.addActionListener(MenuListener);

		file.add(quit);
		menuBar.add(file);
		setJMenuBar(menuBar);
		////////////////////////Status Bar Setup////////////////////////
		statusBar = new JPanel();
		statusText = new JButton("Update");
		send = new JButton("Send");
		
//		statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pull = new JButton("Pull");
		push = new JButton("Push");
		
		statusBar.add(pull);
		statusBar.add(push);

		pull.addActionListener(MenuListener);
		push.addActionListener(MenuListener);
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
//		//Get the server's ip address.
//		String ipAddress = (String)JOptionPane.showInputDialog(
//				null,
//				"Enter the server's ip address:\n",
//				"Customized Dialog",
//				JOptionPane.PLAIN_MESSAGE,
//				null,
//				null,
//				"127.0.0.1");
//		//Get the name of the user.
//		Author = (String)JOptionPane.showInputDialog(
//				null,
//				"Enter the username you wish to use:\n",
//				"Customized Dialog",
//				JOptionPane.PLAIN_MESSAGE,
//				null,
//				null,
//				"brian");
//		finishedLoading=true;
	}
	public class MenuListener implements ActionListener {
		private Document docu = new Document("","");

		public void pull(){
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			try {
				String name = "Document";
				DocumentRemote engine = new DocumentImplementation();
				DocumentRemote stub =
						(DocumentRemote) UnicastRemoteObject.exportObject(engine, 0);
				System.out.println(stub.getText());
				document.setText(stub.getText().toString());
				Registry registry = LocateRegistry.getRegistry();
				registry.rebind(name, stub);
				System.out.println("ComputeEngine bound");
			} catch (Exception e) {
				System.err.println("ComputeEngine exception:");
				e.printStackTrace();
			}

		}
		public void push(){
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			try {
				String name = "Document";
				DocumentRemote engine = new DocumentImplementation();
				DocumentRemote stub =
						(DocumentRemote) UnicastRemoteObject.exportObject(engine, 0);
				System.out.println(stub.getText());
				stub.sync(docu);
				Registry registry = LocateRegistry.getRegistry();
				registry.rebind(name, stub);
				System.out.println("ComputeEngine bound");
			} catch (Exception e) {
				System.err.println("ComputeEngine exception:");
				e.printStackTrace();
			}

		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==pull){
				System.out.println("Updating...");
			}
			if(e.getSource()==push){
				System.out.println("Updating...");
			}
			if(e.getSource()==quit){
				//client.disconnect();
				System.exit(0);
			}
			if(e.getSource()==update){
				//client.disconnect();
				String url = (String)JOptionPane.showInputDialog(
						null,
						"New Document",
						"Open",
						JOptionPane.PLAIN_MESSAGE,
						null,
						null,
						"");
				if ((url != null) && (url.length() > 0)) {
					UUID id = null;
					id = UUID.randomUUID();
				}

			}	

		}

	}

	public class KListener implements KeyListener {
		boolean editable = false;
		boolean commandMode = false;
		private int position;
		private String host;

		public void keyHandler(KeyEvent ev){
			int ID = ev.getID();
			if(ID == KeyEvent.KEY_TYPED && !commandMode && editable){
				System.out.println("Document");
				System.out.print(document.getText());

			}else if(ev.getKeyCode()==KeyEvent.VK_ESCAPE){
				editable = !editable;
			}else if(ev.getKeyCode() == KeyEvent.VK_COLON){
				commandMode = !commandMode;

			}
			if(commandMode)
			{
				char c = ev.getKeyChar();
				switch(c){
				case 'W':
					break;
				case 'U':
					break;
				case 'Q':
					System.exit(0);
					break;
				case 'E':
					break;
				case 'O':
					break;
				}

			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			keyHandler(e);
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			keyHandler(e);			

		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			keyHandler(e);			

		}
	}

	public ArrayList<String> getText() {
		String unformatted = document.getText();
		ArrayList<String> result = new ArrayList<String>();
		String[] splitted = unformatted.split("\n");
		for(int i=0; i<splitted.length; i++){
			result.add(splitted[i]);
		}
		return result;
	}
	public void setText(ArrayList<String> array) {
		String text=array.toString();
		if (array.size()==0){
			return;
		}
		for(int i=0; i<array.size(); i++){
			text = array.get(i) + "\n";
		}
		document.setText(text);
	}			
}


