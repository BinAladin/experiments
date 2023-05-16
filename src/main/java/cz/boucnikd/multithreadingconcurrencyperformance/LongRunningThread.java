package cz.boucnikd.multithreadingconcurrencyperformance;

import java.math.BigInteger;
import java.util.stream.Stream;

public class LongRunningThread extends Thread {
    @Override
    public void run(){
        long sum = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Prematurely interrupted at:" + i);
                return;
            }
            Stream.of(new BigInteger("1"))
                    .reduce(BigInteger::add).get();
            sum += i;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var thread = new LongRunningThread();
        thread.start();
        Thread.sleep(0,10);

        thread.interrupt();
    }
}
