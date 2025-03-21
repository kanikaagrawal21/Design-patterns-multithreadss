package multithread;

public class SimpleThreadedCounter {
    private final int limit;
    private final int threadCount;
    private int counter = 1;
    private final Object lock = new Object();

    public SimpleThreadedCounter(int limit, int threadCount) {
        this.limit = limit;
        this.threadCount = threadCount;
    }

    class CounterThread extends Thread {
        private final int threadId;

        public CounterThread(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (counter <= limit && counter % threadCount != threadId) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    if (counter > limit) {
                        lock.notifyAll();
                        break;
                    }

                    System.out.println("Thread-" + threadId + ": " + counter);
                    counter++;
                    lock.notifyAll();
                }
            }
        }
    }

    public void startCounting() {
        for (int i = 0; i < threadCount; i++) {
            new CounterThread(i).start();
        }
    }

    public static void main(String[] args) {
        int limit = 20;
        int threads = 4;

        SimpleThreadedCounter counter = new SimpleThreadedCounter(limit, threads);
        counter.startCounting();
    }
}
