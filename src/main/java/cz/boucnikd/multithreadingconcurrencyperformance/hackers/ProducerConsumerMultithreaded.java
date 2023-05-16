package cz.boucnikd.multithreadingconcurrencyperformance.hackers;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Semaphore;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ProducerConsumerMultithreaded {

    private static final int CAPACITY = 5;
    private final Semaphore full = new Semaphore(0);
    private final Semaphore spaceLeft = new Semaphore(CAPACITY);

    private final Deque<String> items = new ArrayDeque<>(CAPACITY);

    public void produce(){
        while(true) {
            try {
                spaceLeft.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (items){
                items.push("Item" + System.currentTimeMillis());
            }
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
            synchronized (items){
                items.pop();
            }
            spaceLeft.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var pc = new ProducerConsumerMultithreaded();

        var p = IntStream.range(0, 10).mapToObj((a) -> new Thread(pc::produce)).toList();
        var c = IntStream.range(0, 5).mapToObj((a) -> new Thread(pc::consume)).toList();

        var threads = Stream.concat(p.stream(),c.stream()).toList();
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
