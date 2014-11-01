package bherma3;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface DocumentRemote extends Remote {

	/** This is a sample method. Delete it and add one of your own. */
	String sync(Document arg) throws RemoteException;
}
