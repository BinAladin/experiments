package cz.boucnikd.masterjavamultithreadingprogramming;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class CountdownLatchExample {
    public static void main(String[] args) throws Exception {
        var countdownLatch = new CountDownLatch(2);

        var tasks = List.of(createTask(countdownLatch, 100), createTask(countdownLatch, 200));

        var threadPool = Executors.newFixedThreadPool(5);

        threadPool.invokeAll(tasks);

        threadPool.shutdown();
    }

    private static Callable<Integer> createTask(CountDownLatch countdownLatch, int i) {
        return new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                try {
                    Thread.sleep(i);
                    System.out.println(Thread.currentThread().getId() + " " + LocalTime.now());
                    countdownLatch.countDown();
                    countdownLatch.await();
                    System.out.println(Thread.currentThread().getId() + " " + LocalTime.now());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        };
    }
}
