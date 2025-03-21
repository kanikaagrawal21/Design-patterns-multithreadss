package multithread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Shared Resource
class TaskQueue {
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public void produce(String task) throws InterruptedException {
        queue.put(task);
        System.out.println("Produced: " + task);
    }

    public void consume() throws InterruptedException {
        String task = queue.take();
        System.out.println("Consumed: " + task);
    }
}

class Producer implements Runnable {
    private final TaskQueue taskQueue;

    public Producer(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        int taskNumber = 1;
        try {
            while (true) {
                String task = "Task-" + taskNumber++;
                taskQueue.produce(task);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Consumer Thread
class Consumer implements Runnable {
    private final TaskQueue taskQueue;

    public Consumer(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                taskQueue.consume();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Main
public class ProducerConsumer {
    public static void main(String[] args) {
        TaskQueue queue = new TaskQueue();

        Thread producerThread = new Thread(new Producer(queue));
        Thread consumerThread = new Thread(new Consumer(queue));

        producerThread.start();
        consumerThread.start();
    }
}
