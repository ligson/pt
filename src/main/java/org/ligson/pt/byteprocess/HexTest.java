package org.ligson.pt.byteprocess;

import java.math.BigInteger;
import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;

public class HexTest {
	/***
	 * //cc80 02 03 01 01 00 01 //aa80 02 03 01 01 00 01
	 * 
	 * //cc80 02 03 01 02 00 02
	 * 
	 * println new BigInteger("7273a3c3",16);
	 * 
	 * println "‭1920181187".size() println ((byte)(0xCC))
	 * 
	 * byte[] connectBuffer =
	 * [(byte)(0xCC),(byte)(0x80),(byte)(0x02),(byte)(0x03),(byte)(0x01),(byte)(
	 * 0x01),(byte)(0x00),(byte)(0x01)]; println connectBuffer
	 * 
	 * println new BigInteger(connectBuffer).toString(16);
	 * 
	 * @param args
	 */
	private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	public static void main(String[] args) {
		byte[] connectBuffer = new byte[] { (byte) (0xCC), (byte) (0x80), (byte) (0x02), (byte) (0x03), (byte) (0x01),
				(byte) (0x01), (byte) (0x00), (byte) (0x01) };
		System.out.println(Arrays.toString(connectBuffer));
		BigInteger bigInteger = new BigInteger(connectBuffer);
		System.out.println(bigInteger);
		System.out.println(bigInteger.toString(16));
		System.out.println(bigInteger.toString(10));
		System.out.println();
		System.out.println(Hex.encodeHexString(connectBuffer).toUpperCase());
		System.out.println(encodeHex(connectBuffer, DIGITS_UPPER));
		byte b = (byte)(-127);
		
		System.out.println(new BigInteger(new byte[]{b}));
		
		System.out.println(0<<8);
		//有符号转无符号
		System.out.println(-113&0x0FF);
	}
	
	protected static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }
}
