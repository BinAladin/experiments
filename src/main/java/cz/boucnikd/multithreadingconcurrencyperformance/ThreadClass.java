package cz.boucnikd.multithreadingconcurrencyperformance;

import static cz.boucnikd.multithreadingconcurrencyperformance.Threads.printThreadInfo;

public class ThreadClass {
    public static void main(String[] args) throws InterruptedException {

        class MyThread extends Thread{
            @Override
            public void run(){
                printThreadInfo();
            }
        }

        var thread = new MyThread();
        thread.start();
        thread.join();
    }
}
