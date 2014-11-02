package bherma3;

import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;




public class Client{
	static	DocumentFrame MainDocumentFrame = new DocumentFrame();
	static   SecurityManager security = System.getSecurityManager();
	static ArrayList<String> text = new ArrayList<String>();
	public static void main(String args[]) throws RemoteException{
	     MainDocumentFrame.setDefaultCloseOperation(0);

	}
}
