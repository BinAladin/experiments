package cz.boucnikd.twophasecommit;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class CoordinatorImpl extends UnicastRemoteObject implements Coordinator {
    private Map<String, ResourceManager> resources = new HashMap<String, ResourceManager>();

    public CoordinatorImpl() throws RemoteException {
        super();
    }

    public boolean enlist(String resourceName, ResourceManager rm) throws RemoteException {
        if (!resources.containsKey(resourceName)) {
            resources.put(resourceName, rm);
            return true;
        } else {
            return false;
        }
    }

    public boolean commit() throws RemoteException {
        for (ResourceManager rm : resources.values()) {
            if (!rm.prepare()) {
                abort();
                return false;
            }
        }
        commit();
        return true;
    }

    private void doCommit() {
        for (ResourceManager rm : resources.values()) {
            try {
                rm.commit();
            } catch (RemoteException e) {
                System.err.println("Error committing transaction:");
                e.printStackTrace();
            }
        }
    }

    private void abort() {
        for (ResourceManager rm : resources.values()) {
            try {
                rm.abort();
            } catch (RemoteException e) {
                System.err.println("Error aborting transaction:");
                e.printStackTrace();
            }
        }
    }

    // The main function starts a coordinator
    public static void main(String args[]) {
        try {
            // Create a new coordinator
            CoordinatorImpl coor = new CoordinatorImpl();

            // Register the coordinator with the naming service
            Naming.rebind("//localhost:1099/Coordinator", coor);

            System.out.println("Coordinator ready.");
        } catch (Exception e) {
            System.err.println("Coordinator exception:");
            e.printStackTrace();
        }
    }
}
