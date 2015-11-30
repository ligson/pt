package org.ligson.pt.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.ligson.pt.serializable.model.ModelTools;
import org.ligson.pt.serializable.model.User;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

public class ToolsTest {
	private static List<User> users = null;
	private static DecimalFormat decimalFormat = new DecimalFormat("#.####");
	private static int count = 2;

	static {
		users = ModelTools.createTestData(count * 10000);
	}

	public static byte[] serialize(Object obj) throws IOException {
		if (obj == null)
			throw new NullPointerException();

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HessianOutput ho = new HessianOutput(os);
		ho.writeObject(obj);
		return os.toByteArray();
	}

	public static Object deserialize(byte[] by) throws IOException {
		if (by == null)
			throw new NullPointerException();

		ByteArrayInputStream is = new ByteArrayInputStream(by);
		HessianInput hi = new HessianInput(is);
		return hi.readObject();
	}

	/***
	 * javaio 测试
	 * 
	 * @throws Exception
	 */
	public static void javaio() throws Exception {
		System.out.println("javaio测试:" + count + "万条数据");
		long writeStartTime = System.nanoTime();

		// List<byte[]> buffers = new ArrayList<>();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		for (User user : users) {
			oos.writeObject(user);
		}
		bos.close();
		oos.close();

		byte[] buffers = bos.toByteArray();

		long writeEndTime = System.nanoTime();
		double writeTime = (writeEndTime - writeStartTime) / 1000000000.0;
		System.out.println(users.size() + "条数据,写花费:" + decimalFormat.format(writeTime) + "秒");

		long readStartTime = System.nanoTime();
		ByteArrayInputStream bis = new ByteArrayInputStream(buffers);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Object object = null;
		while ((ois.available() > 0) && ((object = ois.readObject()) != null)) {
			User user = (User) object;
		}
		bis.close();
		ois.close();
		long readEndTime = System.nanoTime();

		double readTime = (readEndTime - readStartTime) / 1000000000.0;

		System.out.println(users.size() + "条数据,读取花费:" + decimalFormat.format(readTime) + "秒");
	}

	/***
	 * hessina 测试
	 * 
	 * @throws Exception
	 */
	public static void hessina() throws Exception {
		System.out.println("hessina测试:" + count + "万条数据");
		long writeStartTime = System.nanoTime();

		List<byte[]> buffers = new ArrayList<>();

		for (User user : users) {
			byte[] buffer = serialize(user);
			buffers.add(buffer);
		}

		long writeEndTime = System.nanoTime();
		double writeTime = (writeEndTime - writeStartTime) / 1000000000.0;
		System.out.println(users.size() + "条数据,写花费:" + decimalFormat.format(writeTime) + "秒");

		long readStartTime = System.nanoTime();
		for (byte[] buffer : buffers) {
			User user = (User) deserialize(buffer);
		}
		long readEndTime = System.nanoTime();

		double readTime = (readEndTime - readStartTime) / 1000000000.0;

		System.out.println(users.size() + "条数据,读取花费:" + decimalFormat.format(readTime) + "秒");

	}

	/***
	 * fastjson
	 * 
	 * @throws Exception
	 */
	public static void fastjson() throws Exception {
		System.out.println("fastjson测试:" + count + "万条数据");
		long writeStartTime = System.nanoTime();

		List<byte[]> buffers = new ArrayList<>();
		for (User user : users) {
			String jsonString = JSON.toJSONString(user);
			buffers.add(jsonString.getBytes());
		}

		long writeEndTime = System.nanoTime();

		double writeTime = (writeEndTime - writeStartTime) / 1000000000.0;

		System.out.println(users.size() + "条数据,写花费:" + decimalFormat.format(writeTime) + "秒");

		long readStartTime = System.nanoTime();

		for (byte[] buffer : buffers) {
			String jsonString = new String(buffer);
			JSONObject jsonObject = JSONObject.parseObject(jsonString);
			User user = JSON.toJavaObject(jsonObject, User.class);
		}
		long readEndTime = System.nanoTime();

		double readTime = (readEndTime - readStartTime) / 1000000000.0;

		System.out.println(users.size() + "条数据,读取花费:" + decimalFormat.format(readTime) + "秒");

	}

	public static void main(String[] args) throws Exception {
		fastjson();
		hessina();
		javaio();
	}

}
