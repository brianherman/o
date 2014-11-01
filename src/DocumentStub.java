package test2;


import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author brianherman
 *
 */
public interface DocumentStub extends Remote {
    String sync (Document d) throws RemoteException;
}
