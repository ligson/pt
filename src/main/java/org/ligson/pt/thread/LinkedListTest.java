package org.ligson.pt.thread;

import java.util.ArrayList;
import java.util.Iterator;

public class LinkedListTest implements Runnable{
	static ArrayList<Integer> list = new ArrayList<Integer>();
	static {
		for (int i = 0; i < 1000; i++) {
			list.add(i);
		}
	}

	public static void main(String[] args) {
		LinkedListTest test = new LinkedListTest();
		Thread thread = new Thread(test);
		thread.start();
		System.out.println(list);
	}

	@Override
	public void run() {
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()){
			Integer n = iterator.next();
			if(n%3==0){
				iterator.remove();
			}
		}
	}
}
