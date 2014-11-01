package test2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client {

    private static Document current_document_state;

	private Client() {}

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
		System.setSecurityManager( new SecurityManager());//singleton
		String status;
		 try {
	            Server obj = new Server();
	            DocumentStub stub = (DocumentStub) UnicastRemoteObject.exportObject(obj, 0);

	            // Bind the remote object's stub in the registry
	            Registry registry = LocateRegistry.getRegistry();
	            registry.bind("Document", stub);
	            status = "Sync performing!";
	            System.err.println("Server ready");
	        } catch (Exception e) {
	            status = ("Server exception: " + e.toString());
	            e.printStackTrace();
	        }
		 
        try {
        	Registry registry = LocateRegistry.createRegistry(1099);
            DocumentStub stub = (DocumentStub) registry.lookup("Document");
            String response = stub.sync(current_document_state);
            DocumentFrame df = new DocumentFrame();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
