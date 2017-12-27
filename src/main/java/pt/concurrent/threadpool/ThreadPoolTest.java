package pt.concurrent.threadpool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    static class Task implements Runnable{

        @Override
        public void run() {
            for(int i = 0;i<10;i++){
                System.out.println(new Date());
            }
        }
    }

    public static void main(String[] args) {
        //同时只有一个线程执行
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        Task task = new Task();
        singleThreadExecutor.execute(task);
        //无限制创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(task);
        //有限大小，超过了进入队列等待
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        fixedThreadPool.execute(task);
        //周期性运行任务
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
        scheduledThreadPool.scheduleAtFixedRate(task,10, 10,TimeUnit.SECONDS);

    }
}
