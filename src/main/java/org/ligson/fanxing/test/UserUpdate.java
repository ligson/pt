package org.ligson.fanxing.test;

public class UserUpdate extends AbstactUpdate<MyArrays> {
	public static void main(String[] args) {
		UserUpdate update = new UserUpdate();
		MyArrays e = update.get(new MyArrays());
		System.out.println(e);
	}

}
