package cz.boucnikd.twophasecommit;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ResourceManagerImpl extends UnicastRemoteObject implements ResourceManager {
    // Implement the ResourceManager interface

    public ResourceManagerImpl() throws RemoteException {
        super();
    }

    public boolean prepare() throws RemoteException {
        // Prepare the resource for commit
        return true;
    }

    public void commit() throws RemoteException {
        // Commit the transaction
    }

    public void abort() throws RemoteException {
        // Rollback the transaction
    }

    // The main function starts a resource manager
    public static void main(String args[]) {
        try {
            // Create a new resource manager
            ResourceManagerImpl rm = new ResourceManagerImpl();

            // Register the resource manager with the naming service
            Naming.rebind("//localhost:1099/ResourceManager", rm);

            System.out.println("Resource Manager ready.");
        } catch (Exception e) {
            System.err.println("Resource Manager exception:");
            e.printStackTrace();
        }
    }
}

