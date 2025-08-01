package org.example.concurrency;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumer {

    private static final AtomicInteger count = new AtomicInteger();
    public static void main(String[] args) {
        //BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        MyBlockingQueue<Integer> blockingQueue = new MyBlockingQueue<>(10);

        final Runnable producer = () -> {
            try {
                while(true){
                    blockingQueue.put(count.incrementAndGet());
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        final Runnable consumer = () -> {
            try {
                while(true){
                    System.out.println(blockingQueue.take());
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        new Thread(producer).start();
        new Thread(producer).start();

        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}
