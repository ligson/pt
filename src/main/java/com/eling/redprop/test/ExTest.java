package com.eling.redprop.test;

import junit.framework.Test;

public class ExTest {
	public static void test() throws Exception{
		throw new Exception("---");
	}
	public static  void test2() throws Exception{
		try {
			test();
		} finally {
			System.out.println("finallly ...");
		}
		System.out.println("let go ..");
	}
	public static void main(String[] args) {
		try {
			test2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
