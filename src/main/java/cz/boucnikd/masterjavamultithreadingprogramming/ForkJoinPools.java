package cz.boucnikd.masterjavamultithreadingprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class ForkJoinPools {
    public static void main(String[] args) {

        var atomicInt = new AtomicInteger(1).updateAndGet(o -> o * 12);

        System.out.println(atomicInt);

        ThreadLocalRandom.current().nextInt(5);

        var forkJoinPool = new ForkJoinPool();

        new HashSet<>().add(null);

        var forkJoinTask = new ForkJoinTask<>() {
            @Override
            public Object getRawResult() {
                return null;
            }

            @Override
            protected void setRawResult(Object value) {

            }

            @Override
            protected boolean exec() {
                return false;
            }
        };

        var recursiveAction = new RecursiveAction() {
            @Override
            protected void compute() {
                invokeAll();
            }
        };

        var recursiveTask = new RecursiveTask<>() {
            @Override
            protected Object compute() {
                invokeAll(forkJoinTask);
                return null;
            }
        };

    }
}
