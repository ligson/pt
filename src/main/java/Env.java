
public class Env {
	public static void main(String[] args) {
		System.out.println("env : " + System.getenv("ELING_CONFIG"));
		System.out.println("property : " + System.getProperty("ELING_CONFIG"));
	}
}
