package cz.boucnikd.masterjavamultithreadingprogramming;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class ExecutorsExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        var callableA = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                System.out.println("Executed thread:" + Thread.currentThread().getName());
                return Thread.currentThread().getId();
            }
        };
        var callableB = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                System.out.println("Executed thread:" + Thread.currentThread().getName());
                return Thread.currentThread().getId();
            }
        };

        var executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        var results = executor.invokeAll(Arrays.asList(callableA, callableB));

        System.out.println("Callable returned:" + results.stream().map(s -> {
                    try {
                        return s.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(String::valueOf)
                .collect(Collectors.joining(",")));

        executor.shutdown();
    }
}
