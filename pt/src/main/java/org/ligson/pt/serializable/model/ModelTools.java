package org.ligson.pt.serializable.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelTools {
	
	
	
	public static List<User> createTestData(int count) {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			User user = new User("姓名" + i, i % 2 == 0, "这是一个人，卧槽。。。", i, new Date());
			users.add(user);
		}
		return users;
	}

	public static void main(String[] args) {
		System.out.println(createTestData(100));
	}

}
