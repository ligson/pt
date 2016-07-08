package sshtest;

public class RandomTest {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println((int)(Math.random() * (2)));
		}
	}
}
