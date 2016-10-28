package zk.providers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.I0Itec.zkclient.ZkClient;

public class Provider implements Runnable {
	private final static String rootNode = "/provider";
	private String serviceName;
	private int port;
	private String zkAddress;
	private ServerSocket serverSocket;

	public Provider(String serviceName, int port, String zkAddress) {
		super();
		this.serviceName = serviceName;
		this.port = port;
		ZkClient zkClient = new ZkClient(zkAddress);
		if (!zkClient.exists(rootNode)) {
			zkClient.createPersistent(rootNode);
		}
		String provderName = rootNode + "/" + serviceName;
		if (zkClient.exists(provderName)) {
			zkClient.delete(provderName);
		}
		zkClient.createPersistent(provderName, "127.0.0.1:" + port);
	}

	public void stop() {
		if (!serverSocket.isClosed()) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getZkAddress() {
		return zkAddress;
	}

	public void setZkAddress(String zkAddress) {
		this.zkAddress = zkAddress;
	}

	@Override
	public void run() {
		try {
			System.out.println("启动服务:" + port + ":" + getServiceName());
			serverSocket = new ServerSocket(port);
			while (true) {
				Socket socket = serverSocket.accept();
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				dos.writeUTF(serviceName);
				dos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
