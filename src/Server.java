package test2;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
        
public class Server implements DocumentStub{
        
    public Server() {}

    public String sayHello() {
        return "Hello, world!";
    }
	public String sync(Document d) throws RemoteException {
		 String status;
		
		return "Synced on Server";
	    }

	}
