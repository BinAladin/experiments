package cz.boucnikd.multithreadingconcurrencyperformance;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.IntUnaryOperator;

public class AtomicVars {

    public static void main(String[] args) throws InterruptedException {
        new AtomicInteger().updateAndGet(new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return 0;
            }
        });
        new ReentrantLock().tryLock(5, TimeUnit.MILLISECONDS);

        new ReentrantReadWriteLock().readLock();
    }
}
