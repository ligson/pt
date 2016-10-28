package pt.string;

import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

public class HexTest {
	public static void main(String[] args) {
		// 3F916562
		BigInteger int1 = new BigInteger("0734749826");
		System.out.println(int1.toString(16));
		System.out.println(int1.toString(2));
		// 2bcb6482 8264cb2b
		BigInteger int2 = new BigInteger("8264cb2b", 16);
		System.out.println(int2.toString());
		System.out.println(int2.toString(2));

		long num = int2.longValue();
		System.out.println(num);
		long num1 = num >>> 8;
		long num2 = num << 16;
		long num3 = num << 24;
		long num4 = num << 32;
		String numString = num1 + "-" + num2 + "-" + num3 + "-" + num4;
		System.out.println(numString);
		System.out.println(StringUtils.leftPad("1234", 10, "0"));

	}

}
