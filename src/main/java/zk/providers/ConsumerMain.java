package zk.providers;

public class ConsumerMain {
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 10; i++) {
			Consumer consumer = new Consumer("127.0.0.1:2181");
			consumer.start();
		}
	}
}
