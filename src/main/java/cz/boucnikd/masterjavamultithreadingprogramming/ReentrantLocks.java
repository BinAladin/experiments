package cz.boucnikd.masterjavamultithreadingprogramming;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocks {
    public static void main(String[] args) {

        var lock = new ReentrantLock().newCondition();

        //new ConcurrentHashMap<>().forEach();
    }
}
