package zk.providers;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;

public class Consumer {
	private String zkAddress;

	public Consumer(String zkAddress) {
		super();
		this.zkAddress = zkAddress;
	}

	public String getZkAddress() {
		return zkAddress;
	}

	public void setZkAddress(String zkAddress) {
		this.zkAddress = zkAddress;
	}

	public String judeServiceNameByRandom(List<String> serviceList){
		int idx = (int) (Math.random() * serviceList.size());
		System.out.println("-----------idx"+idx);
		String serviceNodeName = serviceList.get(idx);
		return serviceNodeName;
	}
	public void start() throws Exception {
		ZkClient zkClient = new ZkClient(zkAddress);
		List<String> serviceList = zkClient.getChildren("/provider");
		if (serviceList.size() == 0) {
			throw new Exception("provider list is emtpy!");
		}
		//随机选择
		String serviceNodeName = judeServiceNameByRandom(serviceList);
		
		String serviceAddr = zkClient.readData("/provider/" + serviceNodeName);
		String[] addrs = serviceAddr.split(":");
		System.out.println("connect:"+serviceAddr+":serviceNameL:"+serviceNodeName);
		Socket socket = new Socket(addrs[0], Integer.parseInt(addrs[1]));
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		System.out.println(dis.readUTF());
		socket.close();
	}
}
