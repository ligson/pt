package org.ligson.pt.thread;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class NewTask {
	public static void main(String[] args) {
		System.out.println(new Date());
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		ScheduledFuture<?> future = service.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println(new Date());
			}
		}, 1, 1, TimeUnit.SECONDS);
		System.out.println("任务取消");
		future.cancel(true);
		service.shutdown();
		
	}
}
