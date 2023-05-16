package cz.boucnikd.multithreadingconcurrencyperformance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ThreadSafeQueueTest {

    private ThreadSafeQueue<String> queue;

    @BeforeEach
    void setUp() {
        queue = new ThreadSafeQueue<String>(5);
    }

    @Test
    void test() throws Exception{
        Runnable producer = () -> {

                for (int i = 0; i < 100000; i++) {
                    try {
                        queue.push(String.valueOf(i));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        };

        Runnable consumer = () -> {
            while(true){
                try {
                    queue.pop();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        var threads = Stream.concat(
                IntStream.range(0,6).mapToObj(i -> new Thread(producer)),
                IntStream.range(0,4).mapToObj(i -> new Thread(consumer))
                );

        threads.forEach(Thread::start);

        while(true) {
            Thread.sleep(1);
            System.out.println("Queue size:" + queue.size());
        }

        /*threads.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });*/


    }

    @Test
    void test2(){
        var counter = new AtomicInteger(5);

        counter.decrementAndGet();

    }
}
