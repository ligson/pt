package org.ligson.pt.aieln.reg;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

import pt.common.http.util.ClientUtils;

public class RegTest {
	private static String regUrl="http://ke.aieln.com/register";
	private static Map<String, String> params = new HashMap<>();

	public static void reg() {
		String pre = RandomStringUtils.random(5);
		String domain = RandomStringUtils.random(5);
		params.put("emailOrMobile", pre + "@" + domain + ".com");
		params.put("nickname", UUID.randomUUID().toString());
		params.put("password", "password");
		ClientUtils.postAndPrint(params, regUrl, null);
	}

	public static void main(String[] args) {
		reg();
	}
}
