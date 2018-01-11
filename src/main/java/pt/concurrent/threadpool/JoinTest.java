package pt.concurrent.threadpool;

public class JoinTest {
    static class ThreadTest extends Thread{
        private String name;

        public ThreadTest(String name) {
            this.name = name;
        }


        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name);
        }
    }

    public static void main(String[] args) throws Exception{
        ThreadTest threadTest1= new ThreadTest("A");
        ThreadTest threadTest2= new ThreadTest("B");
        ThreadTest threadTest3= new ThreadTest("C");
        threadTest1.start();
        threadTest1.join();
        threadTest2.start();
        threadTest2.join();
        threadTest3.start();
        threadTest3.join();


    }
}
