package org.ligson.pt.thread;

public class Thread2 {
	private static int n = 100;

	static synchronized void modifyNum(int step) {
		n += step;
	}

	static class MyThread implements Runnable {
		private int step;

		public MyThread(int step) {
			super();
			this.step = step;
		}

		public int getStep() {
			return step;
		}

		public void setStep(int step) {
			this.step = step;
		}

		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				modifyNum(step);
				System.out.println(step + "====" + n);
			}
			System.out.println("+++++++" + n);
		}
	}

	public static void main(String[] args) {
		MyThread myThread1 = new MyThread(-10);
		MyThread myThread2 = new MyThread(10);
		Thread thread1 = new Thread(myThread1);
		Thread thread2 = new Thread(myThread2);
		thread1.start();
		thread2.start();
	}
}
