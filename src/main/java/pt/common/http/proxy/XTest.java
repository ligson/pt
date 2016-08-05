package pt.common.http.proxy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.util.EntityUtils;

import pt.common.http.util.ClientUtils;

public class XTest {

	public static void connect(HttpHost proxyHost) {
		HttpGet httpGet = new HttpGet("http://www.youtube.com");
		RequestConfig requestConfig = RequestConfig.custom().setProxy(proxyHost).build();
		httpGet.setConfig(requestConfig);
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("username", "password");
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(proxyHost), credentials);
		
		  // 创建 AuthCache 对象
	    AuthCache authCache = new BasicAuthCache();
	    //创建 BasicScheme，并把它添加到 auth cache中
	    BasicScheme basicAuth = new BasicScheme();
	    authCache.put(proxyHost, basicAuth);
	    ClientUtils.context.setAuthCache(authCache);
		ClientUtils.context.setCredentialsProvider(credsProvider);
		HttpResponse response = ClientUtils.request(httpGet);
		if (response != null) {
			System.out.println(proxyHost + "  status : " + response.getStatusLine().getStatusCode());
			try {
				
				System.out.println("content.."+EntityUtils.toString(response.getEntity()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(proxyHost + "  error : ");
		}

	}

	public static List<HttpHost> readHost() {
		List<HttpHost> list = new ArrayList<>();
		File file = new File("./ip.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				int idx1 = line.indexOf("#");
				String prefix = line.substring(0, idx1);
				int idx2 = prefix.indexOf(":");
				int idx3 = prefix.indexOf("@");
				String host = prefix.substring(0, idx2);
				String port = prefix.substring(idx2 + 1, idx3);
				String scheme = prefix.substring(idx3 + 1);
				HttpHost httpHost = new HttpHost(host, Integer.parseInt(port), scheme);
				list.add(httpHost);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		// List<HttpHost> hosts = readHost();
		// for(HttpHost host:hosts){
		// connect(host);
		// }
		connect(new HttpHost("1.255.53.81", 80, "HTTP"));
	}
}
