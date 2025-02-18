package tset;

public class ConcurrencyExample {
    private static int counter = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter++; // 非原子操作
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) { t.join(); }
        System.out.println("预期值: 1000000, 实际值: " + counter);
    }
}


class ConcurrencyExample2{
    private static int counter = 0;
    // 定义一个静态对象作为同步锁
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length;  i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    // 同步块，保证同一时间只有一个线程能执行
                    synchronized (lock) {
                        counter++;
                    }
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }
        System.out.println(" 预期值: 1000000, 实际值: " + counter);
    }
}
