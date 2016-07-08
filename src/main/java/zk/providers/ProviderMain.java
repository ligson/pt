package zk.providers;

public class ProviderMain {
	public static void main(String[] args) {
		Provider providerA = new Provider("serviceA", 9999, "127.0.0.1:2181");
		Provider providerB = new Provider("serviceB", 8888, "127.0.0.1:2181");
		Thread threadA = new Thread(providerA);
		Thread threadB = new Thread(providerB);
		threadA.start();
		threadB.start();

	}
}
