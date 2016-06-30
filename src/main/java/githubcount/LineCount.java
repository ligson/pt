package githubcount;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/***
 * github代码行数统计 参考文档https://developer.github.com/v3/repos/statistics/
 * 
 * @author ligson
 *
 */
public class LineCount {
	private static HttpClient client = HttpClientBuilder.create().build();
	private static Logger logger = LoggerFactory.getLogger(LineCount.class);

	public static String httpGetText(String url) {
		try {
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = client.execute(httpGet);
			String text = EntityUtils.toString(response.getEntity());
			logger.debug("requset url:{},response:{}", url, text);
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<String> getUserReposStatusUrl(String username) throws Exception {
		// https://api.github.com/users/ligson/repos
		String text = httpGetText("https://api.github.com/users/" + username + "/repos");
		JSONArray reposArray = JSONArray.parseArray(text);
		List<String> list = new ArrayList<>();
		for (int i = 0; i < reposArray.size(); i++) {
			JSONObject repos = reposArray.getJSONObject(i);
			String url = repos.getString("contributors_url");
			list.add(url);
			logger.debug("user:{},contributors_url:{}", username, url);
		}

		return list;
	}

	public static long getReposLineNum(String reposUrl) throws Exception {
		String text = httpGetText(reposUrl);
		JSONArray jsonArray = JSONArray.parseArray(text);
		JSONObject jsonObject = null;
		if (jsonArray.size() > 0) {
			jsonObject = jsonArray.getJSONObject(0);
		} else {
			logger.warn("忽略仓库:{}", reposUrl);
			return 0;
		}

		JSONArray weeks = jsonObject.getJSONArray("weeks");
		long count = 0;
		if (weeks != null) {
			for (int i = 0; i < weeks.size(); i++) {
				long appendLine = weeks.getJSONObject(i).getLong("a");
				long deleteLine = weeks.getJSONObject(i).getLong("d");
				count += appendLine;
				count -= deleteLine;
			}
		} else {
			logger.warn("忽略仓库:{}", reposUrl);
		}
		System.out.println(count);
		logger.debug("reposUrl:{},lineNumber:{}", reposUrl, count);
		return count;
	}

	public static long countUserLineNum(String username) throws Exception {
		List<String> list = getUserReposStatusUrl(username);
		long count = 0;
		for (String url : list) {
			count += getReposLineNum(url);
		}
		return count;
	}

	static long userCount = 0;

	public static void countUserNumber(long offset) {
		System.out.println(userCount);
		String text = httpGetText(
				"https://api.github.com/users?access_token=012f56914224160f495795c704c99a843820071e&since=" + offset
						+ "&per_page=100");
		JSONArray array = JSONArray.parseArray(text);
		if (array.size() != 0) {
			countUserNumber(offset + 100);
		}
		userCount += array.size();
		logger.debug("offset:{},userCount:{}", offset, userCount);
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(countUserLineNum("ligson"));
		countUserNumber(0);
		System.out.println(userCount);
	}
}
