package cz.boucnikd.multithreadingconcurrencyperformance;

import java.lang.Thread.UncaughtExceptionHandler;

public class MissbehavingThreads {
    public static void main(String[] args) throws InterruptedException {
        var thread = new Thread(() -> {
           throw new AssertionError("Error!");
        });

        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + " thread has thrown exception:" + e);
            }
        });

        thread.start();
        thread.join();
    }
}
