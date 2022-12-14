package cz.boucnikd.masterjavamultithreadingprogramming.threads;

import java.util.List;

public class SimpleThreadsTwo {
    public static void main(String[] args) throws InterruptedException {
        // busy spin - checking interruption
        // sleeping - receiving interrupted exception

        // always print getInterrupted + interrupted + getInterrupted +
        // print the exception and finish!

        Runnable busySpinning = () -> {
            while (true) {
                // do smth
                if (Thread.currentThread().isInterrupted()) {
                    print("Interrupted by flag");
                    Thread.interrupted();
                    print("Interrupted:" + Thread.currentThread().isInterrupted());
                    break;
                }
            }
        };
        Runnable sleeping = () -> {
            try {
                Thread.sleep(999999);
            } catch (InterruptedException e) {
                print("Interrupted by exception");
                print("Interrupted:" + Thread.currentThread().isInterrupted());
            }
        };

        var threads = List.of(new Thread(busySpinning), new Thread(sleeping));

        threads.forEach(Thread::start);
        Thread.sleep(100);
        threads.forEach(Thread::interrupt);
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private static void print(String name) {
        System.out.println(Thread.currentThread().getName() + name);
    }
}
