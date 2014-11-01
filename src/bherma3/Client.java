package bherma3;

import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;




public class Client implements Runnable{
	static	DocumentFrame MainDocumentFrame = new DocumentFrame();
	static   SecurityManager security = System.getSecurityManager();
	static	DocumentImpl docImp = new DocumentImpl();
	UUID uu = UUID.randomUUID();

	public static void main(String args[]) throws RemoteException{
		MainDocumentFrame.setDefaultCloseOperation(0);
		while (MainDocumentFrame.finishedLoading == false){

		}
		new Thread(new Client()).start();
	}

	@Override
	public void run() {
		while(true){
			String update = MainDocumentFrame.getText();
			String Author = MainDocumentFrame.getName();
			try {
				MainDocumentFrame.setText(docImp.sync(new DocumentDistributedObject(uu.toString(), update,Author)));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
