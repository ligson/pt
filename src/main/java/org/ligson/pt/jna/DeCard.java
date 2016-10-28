package org.ligson.pt.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public class DeCard {
	interface DeCardDll extends Library {
		int init();
	}

	public static void main(String[] args) {
		DeCardDll deCardDll = (DeCardDll) Native.loadLibrary("decard_x64.dll",DeCardDll.class);
		int init = deCardDll.init();
		System.out.println(init);
	}
}
