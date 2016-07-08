package pt.io.file.lagerfile;

public class UTF8Convert {
	public static void main(String[] args) throws Exception{
		String utf8 = "\u6ca1\u6709\u627e\u7684\u5230\u5bf9\u8c61\uff0c\u7c7b\u578b\uff1a";
		System.out.println(new String(utf8.getBytes("UTF-8")));
	}

}
