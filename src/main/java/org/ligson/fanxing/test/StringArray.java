package org.ligson.fanxing.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringArray {
	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		strings.add(new String("aa"));
		strings.add(new String("aa"));
		System.out.println(strings);
		Set<String> set = new HashSet<>();
		set.add(new String("aa"));
		set.add(new String("aa"));
		set.add("aa");
		System.out.println(set);
	}
}
