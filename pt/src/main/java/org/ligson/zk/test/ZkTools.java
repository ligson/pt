package org.ligson.zk.test;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;

public class ZkTools {

	public static void main(String[] args) {
		ZkClient zkClient = new ZkClient("10.0.0.252:2181", 300000, 5000);
		System.out.println(zkClient);
		//com.sankai.user.api.account.AccountApi/providers
		String accountApiRoot = "/dubbo/com.sankai.pay.api.PayApi/providers";
		String validcodeApiRoot = "/dubbo/com.sankai.user.api.validcode.ValidCodeApi/providers";
		List<String> subList = zkClient.getChildren(accountApiRoot);
		System.out.println(subList.size()+"================");
		for (String sub : subList) {
			System.out.println("---->?"+sub);
			if (!sub.contains("10.0.0.252")) {
				
				String path = accountApiRoot+"/"+sub;
				System.out.println(path);
				//boolean b = zkClient.delete(path);
				//System.out.println("delete "+path+" :"+b);
			}

		}
		
	}

}
