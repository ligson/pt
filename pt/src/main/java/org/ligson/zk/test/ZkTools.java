package org.ligson.zk.test;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;

public class ZkTools {

	public static void main(String[] args) {
		ZkClient zkClient = new ZkClient("10.0.0.251:2181", 300000, 5000);
		System.out.println(zkClient);
		String accountApiRoot = "/dubbo/com.sankai.user.api.account.AccountApi/providers";
		List<String> subList = zkClient.getChildren(accountApiRoot);
		for (String sub : subList) {
			System.out.println(sub);
			if (!sub.contains("10.0.0.251")) {
				
				String path = accountApiRoot+"/"+sub;
				System.out.println(sub);
				//boolean b = zkClient.delete(path);
				//System.out.println("delete "+path+" :"+b);
			}

		}
		
	}

}
