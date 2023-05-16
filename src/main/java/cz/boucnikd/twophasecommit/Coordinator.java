package cz.boucnikd.twophasecommit;

import java.rmi.*;

public interface Coordinator extends Remote {
    boolean enlist(String resourceName, ResourceManager rm) throws RemoteException;

    boolean commit() throws RemoteException;
}
