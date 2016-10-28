package org.ligson.zk.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

/**
 * redis.host=10.0.0.251 redis.port=6379 redis.timeout=300000 redis.maxIdle=300
 * redis.maxActive=600 redis.maxWait=1000 redis.testOnBorrow=true
 * redis.testOnReturn=true
 */

public class RedisTool {
	public static String createKeyForValidCode(String sysPrefix, String appCode, String mobile, String businessDate,
			String validCode) {
		StringBuffer key = new StringBuffer(createKeyForSmsCounter(sysPrefix, appCode, mobile, businessDate));
		key.append("_").append(validCode);
		return key.toString();
	}

	/**
	 * 生成缓存短信计数器key
	 *
	 * @param sysPrefix
	 * @param appCode
	 * @param mobile
	 * @param businessDate
	 * @return
	 */
	public static String createKeyForSmsCounter(String sysPrefix, String appCode, String mobile, String businessDate) {
		StringBuffer key = new StringBuffer("");
		if (!StringUtils.isEmpty(sysPrefix)) {
			key.append(sysPrefix).append("_");
		}
		if (!StringUtils.isEmpty(appCode)) {
			key.append(appCode).append("_");
		}
		if (!StringUtils.isEmpty(mobile)) {
			key.append(mobile).append("_");
		}
		if (!StringUtils.isEmpty(businessDate)) {
			key.append(businessDate);
		}
		return key.toString();
	}

	public static void main(String[] args) {
		
		List<JedisShardInfo> infos = new ArrayList<>();
		JedisShardInfo shardInfo = new JedisShardInfo("10.0.0.251", 6379);
		infos.add(shardInfo);
		ShardedJedis shardedJedis = new ShardedJedis(infos);
		System.out.println(shardedJedis);
		String key = "user_service_ALL_15630589137_20151219";
		//createKeyForValidCode();
		//shardedJedis.getKeyTag(key)
		long r = shardedJedis.del(key);
		System.out.println(r);
	}
}
