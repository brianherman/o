package bherma3;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.UUID;

public class DocumentImplementation implements DocumentRemote{
	public Document doc;
	Registry registry;
	boolean created = false;
	public DocumentImplementation() throws RemoteException{
		if(!created){
			registry = LocateRegistry.createRegistry(1099);
			created = true;
		}
		doc = new Document("",UUID.randomUUID().toString());
	}
	@Override
	public void sync(Document sync) throws RemoteException {
		String name = "Document";
		System.out.println(sync);
		//Registry registry = LocateRegistry.getRegistry("Document");
		//       Registry registry = LocateRegistry.getRegistry(1099);
		DocumentRemote doc = null;
		try {
			doc = (DocumentRemote) registry.lookup(name);
			doc.sync(new Document(sync.toString(),sync.getUUID()));
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Document getText() throws RemoteException {
		String name = "Document";
		//      Registry registry = LocateRegistry.getRegistry("Document");
		Registry registry = LocateRegistry.getRegistry(1099);
		DocumentRemote doc = null;
		try {
			doc = (DocumentRemote) registry.lookup(name);
			return	doc.getText();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return new Document(null,null);
	}


}
