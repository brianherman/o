package bherma3;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DocumentImpl implements DocumentRemote{

	@Override
	public String sync(DocumentDistributedObject arg) throws RemoteException {
        String name = "Document";
//        Registry registry = LocateRegistry.getRegistry("Document");
        Registry registry = LocateRegistry.getRegistry(1099);
        DocumentRemote doc = null;
		try {
			doc = (DocumentRemote) registry.lookup(name);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return arg.getText();
	}

}
