package cz.boucnikd.multithreadingconcurrencyperformance;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeQueue<T> {

    private final Deque<T> queue;
    private final int maxCapacity;

    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition fullCondition = lock.newCondition();
    private final Condition emptyCondition = lock.newCondition();

    public ThreadSafeQueue(int maxCapacity){
        this.queue = new ArrayDeque<>(maxCapacity);
        this.maxCapacity = maxCapacity;
    }
    public void push(T item) throws Exception{
        synchronized (lock){
            if(queue.size() == maxCapacity){
                fullCondition.await();
            }
            queue.push(item);
            emptyCondition.signal();
        }
    }

    public T pop() throws Exception {
        synchronized (lock){
            if(queue.size() == 0){
                emptyCondition.await();
            }
            var item = queue.pop();
            fullCondition.signal();
            return item;
        }
    }

    public int size() {
        return queue.size();
    }
}
