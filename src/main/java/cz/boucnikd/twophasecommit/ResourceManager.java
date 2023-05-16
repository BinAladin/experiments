package cz.boucnikd.twophasecommit;

import java.rmi.*;

public interface ResourceManager extends Remote {
    boolean prepare() throws RemoteException;

    void commit() throws RemoteException;

    void abort() throws RemoteException;
}
