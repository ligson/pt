package pt.concurrent.threadpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Thread1 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"----start");
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"-----stop");
    }
}

public class ThreadPoolExecutorTest {

    public static void main(String[] args) {
        // 如果为 true，则等待线程以 FIFO 的顺序竞争访问；否则顺序是未指定的。 false不公平
        SynchronousQueue synchronousQueue = new SynchronousQueue(false);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 3, TimeUnit.SECONDS, synchronousQueue);
        for (int i = 0; i < 5; i++) {
            executor.execute(new Thread1());

        }
        System.out.println(executor.getActiveCount());


    }
}
