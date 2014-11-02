package bherma3;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface DocumentRemote extends Remote {
	void sync(Document arrayList) throws RemoteException;
	Document getText() throws RemoteException;
}
