package pt.io.file.lagerfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;

public class FileReplace {
	public static void main(String[] args) throws Exception{
		File file = new File("F:/homebak.sql");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		System.out.println(reader.readLine());
		System.out.println(reader.readLine());
		RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
		//accessFile.seek(pos);
		//accessFile.write(b);
	}

}
