package org.ligson.pt.loc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.ligson.pt.loc.bd.model.IpReturnContent;
import org.ligson.pt.loc.bd.model.Location;
import org.springframework.util.FileCopyUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pt.common.http.util.ClientUtils;

public class LocConvert {
	/***
	 * 转换成度分秒
	 * 
	 * @param dou
	 * @return 度:分:秒
	 */
	public static String conv1(String dou) {
		BigDecimal decimal = new BigDecimal(dou);
		// decimal.
		int zheng = decimal.intValue();
		BigDecimal xiao = decimal.subtract(new BigDecimal(zheng));
		BigDecimal fen = xiao.multiply(new BigDecimal(60));
		int fenZheng = fen.intValue();
		BigDecimal fenXiao = fen.subtract(new BigDecimal(fenZheng));
		BigDecimal min = fenXiao.multiply(new BigDecimal(60));
		int miaoZheng = min.intValue();
		return zheng + ":" + fenZheng + ":" + miaoZheng;
	}

	/***
	 * 转换成小数
	 * 
	 * @param dou
	 * @return 度:分:秒
	 */
	public static String conv2(String dou) {
		String[] strings = dou.split(":");
		BigDecimal zheng = new BigDecimal(Integer.parseInt(strings[0]));
		BigDecimal fen = new BigDecimal(strings[1]).divide(new BigDecimal(60), 16, RoundingMode.HALF_UP);
		BigDecimal miao = new BigDecimal(strings[2]).divide(new BigDecimal(60 * 60), 16, RoundingMode.HALF_UP);
		return zheng.add(fen).add(miao).toString();
	}

	/***
	 * gps转百度坐标
	 * 
	 * @param longitude
	 *            经度
	 * @param latitude
	 *            维度
	 * @return new String[]{x,y} or null
	 */
	public static String[] gpsToBaidu(String longitude, String latitude) {
		Map<String, String> params = new HashMap<>();
		params.put("coords", longitude + "," + latitude);
		params.put("from", "1");
		params.put("to", "5");
		params.put("ak", "2d8f5d24b455d1442822a389da64a057");
		HttpResponse response = ClientUtils.post(params, "http://api.map.baidu.com/geoconv/v1/", null);
		try {
			JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity(), "UTF-8"));
			System.out.println(jsonObject);
			if (jsonObject.containsKey("status") && jsonObject.getIntValue("status") == 0) {
				JSONArray array = jsonObject.getJSONArray("result");
				if (array.size() == 1) {
					String x = array.getJSONObject(0).getString("x");
					String y = array.getJSONObject(0).getString("y");
					return new String[] { x, y };
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static File saveBDMapPic(String x, String y) {
		HttpResponse response = ClientUtils
				.get("http://api.map.baidu.com/staticimage/v2?ak=2d8f5d24b455d1442822a389da64a057&zoom=18&center=" + x
						+ "," + y + "&markers=" + x + "," + y);
		try {
			File file = new File(UUID.randomUUID().toString() + ".png");
			FileCopyUtils.copy(response.getEntity().getContent(), new FileOutputStream(file));
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static IpReturnContent ipLoc(String ip, boolean pc) {
		// http://lbsyun.baidu.com/index.php?title=webapi/high-acc-ip
		Map<String, String> params = new HashMap<>();
		params.put("qcip", ip);
		params.put("ak", "2d8f5d24b455d1442822a389da64a057");
		params.put("qterm", pc + "");
		HttpResponse response = ClientUtils
				.get("http://api.map.baidu.com/highacciploc/v1?coord=bd09ll&extensions=3&ak=2d8f5d24b455d1442822a389da64a057&qcip="
						+ ip + "&qterm=" + pc);
		// HttpResponse response = ClientUtils.post(params,
		// "http://api.map.baidu.com/highacciploc/v1", null);

		try {
			JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
			System.out.println(jsonObject.toJSONString());
			if (jsonObject.containsKey("result") && jsonObject.getJSONObject("result").getIntValue("error") == 161) {
				return JSONObject.toJavaObject(jsonObject.getJSONObject("content"), IpReturnContent.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(conv1("40.034164830066295"));
		System.out.println(conv1("116.30733311392618"));
		System.out.println(conv2("116:18:26"));
		String[] bdLoc = gpsToBaidu("116.30733311392618", "40.034164830066295");
		System.out.println(Arrays.toString(bdLoc));
		System.out.println(saveBDMapPic(bdLoc[0], bdLoc[1]).getAbsolutePath());
		Location location = ipLoc("111.200.254.59", true).getLocation();
		System.out.println(saveBDMapPic(location.getLng(),location.getLat()).getAbsolutePath());

	}
}
