package edu.cs.uic.Document;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
/**
 * Handles a request from a client/
 * @author brianherman
 *
 */
public class ServerThread implements Runnable {
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in = null;
	private ServerThreadIterface callback = null;
	private String name = null;
	
	public ServerThread(Socket s, ServerThreadIterface stc)
	{
		socket = s;
		callback = stc;
	}
	public void run() {
		callback.log("Connection accepted");
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

			while(true){
				Envelope m = null;
				//read in a evenlope.
				m = (Envelope)in.readObject();
				System.out.print(m.toString());
				if(m == null){
					break;
				}
				/*
				 * Special commands.
				 */
				/**
				 * If it isn't a special command send it to the other clients.
				 */
				callback.send(m);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public String name(){
		return name;
	}
	/**
	 * Allows the server to send an envelope to this client.
	 * @param m, the message to be sent.
	 */
	public void send(Envelope m) 
	{
		try {
			callback.log(m.sender() +" : " + m.getDocument());
			out.writeObject(m);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Allows the server to close this connection.
	 */
	public void close(){
		try{
			out.close();
			in.close();
			socket.close();
			callback.remove(name);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
