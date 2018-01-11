package pt.concurrent.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTest {
    public static void main(String[] args) throws Exception{
        FutureTask<String> futureTask = new FutureTask(() -> "A");
        futureTask.run();
        System.out.println(futureTask.get());
        FutureTask futureTask2 = new FutureTask<String>(new Runnable() {
            @Override
            public void run() {
                System.out.println("aaaaaaa");
            }
        },"AAAAA");
        System.out.println(futureTask2.get());
    }
}
