package pt.common.http.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ClientUtils {
	public static CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	public static HttpClientContext  context = HttpClientContext.create();

	public static HttpResponse post(Map<String, String> map, String url, Header[] headers) {
		List<NameValuePair> pairs = new ArrayList<>();
		for (String key : map.keySet()) {
			pairs.add(new BasicNameValuePair(key, map.get(key)));
		}
		HttpPost post = new HttpPost(url);

		if (headers != null && headers.length > 0) {
			post.setHeaders(headers);
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(pairs));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return request(post);

	}

	public static HttpResponse request(HttpRequestBase requestBase) {
		try {
			return httpClient.execute(requestBase, context);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static HttpResponse post(String data, String url, Header[] headers) {
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new StringEntity(data));
			if(headers!=null&&headers.length>0){
				post.setHeaders(headers);	
			}
			HttpResponse response = httpClient.execute(post, context);
			return response;
		} catch (Exception e) {
			System.out.println(post.getAllHeaders().length);
			for (Header header : post.getAllHeaders()) {
				System.out.println(header.getName() + "===" + header.getValue());
			}
			e.printStackTrace();
		}
		return null;
	}
	public static HttpResponse post(String data, String url) {
		return post(data, url,null);
	}

	public static void printResponse(HttpResponse response) {
		System.out.println("status:" + response.getStatusLine().getStatusCode());
		try {
			System.out.println("reponse:" + EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void postAndPrint(Map<String, String> map, String url) {
		printResponse(post(map, url, null));
	}

	public static void postAndPrint(Map<String, String> map, String url, Header[] headers) {
		printResponse(post(map, url, headers));
	}

	public static void postAndPrint(String data, String url) {
		printResponse(post(data, url));
	}

	public static HttpResponse get(String apiUrl) {
		HttpGet httpGet = new HttpGet(apiUrl);
		return request(httpGet);
	}
	

}
