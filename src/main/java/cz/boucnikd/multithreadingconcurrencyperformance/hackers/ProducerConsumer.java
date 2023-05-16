package cz.boucnikd.multithreadingconcurrencyperformance.hackers;

import java.util.concurrent.Semaphore;

public class ProducerConsumer {

    private Semaphore full = new Semaphore(0);
    private Semaphore empty = new Semaphore(1);

    private String item = null;

    public void produce(){
        while(true) {
            try {
                empty.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            item = "Item";
            full.release();
        }
    }

    public void consume(){
        while(true){
            try {
                full.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            item = null;
            empty.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var pc = new ProducerConsumer();

        var p = new Thread(pc::produce);
        var c = new Thread(pc::consume);

        p.start();
        c.start();

        p.join();
        c.join();
    }
}
