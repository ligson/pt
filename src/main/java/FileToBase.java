import java.io.File;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.FileCopyUtils;

public class FileToBase {
	public static void main(String[] args) throws Exception{
		File file = new File("./baner.jpg");
		System.out.println(file.exists());
		byte[] buf = FileCopyUtils.copyToByteArray(file);
		System.out.println(Base64.encodeBase64String(buf));
	}
}
