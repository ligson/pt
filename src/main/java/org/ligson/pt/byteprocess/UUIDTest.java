package org.ligson.pt.byteprocess;

import java.util.Random;
import java.util.UUID;

public class UUIDTest {
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12));
	}
}
