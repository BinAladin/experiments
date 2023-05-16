package cz.boucnikd.multithreadingconcurrencyperformance;

public class Threads {
    public static void main(String[] args) throws InterruptedException {
        var thread = new Thread(() -> {
            printThreadInfo();
        });

        printThreadInfo();
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        Thread.sleep(1);
        printThreadInfo();

        //Thread.sleep(1000000);
    }

    public static void printThreadInfo() {
        var thread = Thread.currentThread();
        System.out.println("I am thread:" + thread.getName() +
                " with id:"+ thread.getId() + " and priority:" + thread.getPriority());
    }
}
