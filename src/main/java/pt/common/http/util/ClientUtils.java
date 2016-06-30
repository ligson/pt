package pt.common.http.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class ClientUtils {
	public static CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	 public static HttpContext context = new BasicHttpContext();
	public static HttpResponse post(Map<String, String> map, String url) {
		List<NameValuePair> pairs = new ArrayList<>();
		for (String key : map.keySet()) {
			pairs.add(new BasicNameValuePair(key, map.get(key)));
		}
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new UrlEncodedFormEntity(pairs));
			return httpClient.execute(post,context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static HttpResponse post(String data, String url) {
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new StringEntity(data));
			return httpClient.execute(post,context);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void printResponse(HttpResponse response) {
		System.out.println("status:" + response.getStatusLine().getStatusCode());
		try {
			System.out.println("reponse:" + EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void postAndPrint(Map<String, String> map, String url){
		printResponse(post(map, url));
	}
	public static void postAndPrint(String data, String url){
		printResponse(post(data, url));
	}


}
