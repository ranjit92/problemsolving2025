package org.example.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {
    private Queue<E> queue = new LinkedList<>();
    private int capacity;
    private ReentrantLock reentrantLock = new ReentrantLock(true);
    private Condition notFull = reentrantLock.newCondition();
    private Condition notEmpty = reentrantLock.newCondition();

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(E e) {

        reentrantLock.lock();
        try {
            while (queue.size() == capacity) {
                notFull.await();
            }
            queue.add(e);
            notEmpty.signal();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        } finally {
            reentrantLock.unlock();
        }
    }

    public E take() {
        reentrantLock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            E e = queue.poll();
            notFull.signal();
            return e;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }

}
