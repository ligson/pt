package org.ligson.pt.thread;

import org.apache.commons.lang3.StringUtils;

public class PadTest {
	public static void main(String[] args) {
		System.out.println(StringUtils.leftPad("123456", 8, "0"));
	}
}
