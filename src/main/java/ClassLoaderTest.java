
public class ClassLoaderTest {
	public static void main(String[] args) throws Exception {
		ClassLoader loader1 = new ClassLoader() {
		};
		ClassLoader loader2 = new ClassLoader() {
		};
		Class class1 = loader1.loadClass("redis.clients.util.Hashing");
		Class class2 = loader2.loadClass("redis.clients.util.Hashing");
		Class class3 = Thread.currentThread().getContextClassLoader().loadClass("redis.clients.util.Hashing");
		System.out.println(class1==class2);
		System.out.println(class1==class3);
	}
}
