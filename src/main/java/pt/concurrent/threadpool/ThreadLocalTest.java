package pt.concurrent.threadpool;

import pt.reflect.model.User;

public class ThreadLocalTest {
    class Task1 implements Runnable{
        private ThreadLocal<User> threadLocal = new ThreadLocal<>();
        private String name;

        public Task1(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            User user = threadLocal.get();
            if(user==null){
                user = new User();
                user.setName(name);
                threadLocal.set(user);
            }else{
                user.setName(name+"_exsit");
            }
            System.out.println(user);
        }
    }
    public static void main(String[] args) {


    }
}
