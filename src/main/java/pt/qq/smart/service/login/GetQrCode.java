package pt.qq.smart.service.login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.cookie.Cookie;
import org.apache.http.util.EntityUtils;
import org.springframework.util.FileCopyUtils;

import com.alibaba.fastjson.JSONObject;

import pt.common.http.util.ClientUtils;

public class GetQrCode {
	private static String qrcodeUrl = "https://ssl.ptlogin2.qq.com/ptqrshow?appid=501004106&e=0&l=M&s=5&d=72&v=4&t=0.733451325179723";
	private static String checkQrStateUrl = "https://ssl.ptlogin2.qq.com/ptqrlogin?webqq_type=10&remember_uin=1&login2qq=1&aid=501004106&u1=http%3A%2F%2Fw.qq.com%2Fproxy.html%3Flogin2qq%3D1%26webqq_type%3D10&ptredirect=0&ptlang=2052&daid=164&from_ui=1&pttype=1&dumy=&fp=loginerroralert&action=0-0-12246&mibao_css=m_webqq&t=undefined&g=1&js_type=0&js_ver=10176&login_sig=&pt_randsalt=0";
	static String ptwebqq = null;

	public static void main(String[] args) throws Exception {
		HttpResponse response = ClientUtils.get(qrcodeUrl);
		File file = new File("./qrcode.png");
		FileCopyUtils.copy(response.getEntity().getContent(), new FileOutputStream(file));
		System.out.println(ClientUtils.context.getCookieStore().getCookies());
		org.apache.http.client.CookieStore cookieStore = ClientUtils.context.getCookieStore();

		for (Cookie cok : cookieStore.getCookies()) {
			if ("ptwebqq".equals(cok.getName())) {
				ptwebqq = cok.getValue();
				break;
			}
		}
		if (ptwebqq == null) {
			System.out.println("ptwebqq is null ");
			return;
		}
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					HttpResponse response = ClientUtils.get(checkQrStateUrl);
					try {
						String returnResult = EntityUtils.toString(response.getEntity());
						System.out.println(returnResult);
						String[] cbArr = returnResult.split(",");
						int startIdx = cbArr[0].indexOf("'");
						int endIdx = cbArr[0].lastIndexOf("'");
						String rt = returnResult.substring(startIdx + 1, endIdx);
						System.out.println(rt);
						if ("0".equals(rt)) {
							String ptUrl = cbArr[2];
							startIdx = ptUrl.indexOf("&uin=") + 5;
							endIdx = ptUrl.substring(startIdx).indexOf("&");
							String uin = ptUrl.substring(startIdx, startIdx + endIdx);
							startIdx = cbArr[5].indexOf("'") + 1;
							endIdx = cbArr[5].lastIndexOf("'");
							String nickName = cbArr[5].substring(startIdx, endIdx);
							System.out.println(uin);
							System.out.println(nickName);
							System.out.println(cbArr[4].substring(1, cbArr[4].length() - 1));
							response = ClientUtils.get("http://s.web2.qq.com/api/getvfwebqq?ptwebqq=" + ptwebqq
									+ "&clientid=53999199&psessionid=&t=" + new Date().getTime());
							JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
							int retcode = jsonObject.getIntValue("retcode");
							if (retcode == 0) {
								String vfwebqq = jsonObject.getJSONObject("result").getString("vfwebqq");
								System.out.println("vfwebqq:" + vfwebqq);
								JSONObject login2Obj = new JSONObject();
								login2Obj.put("ptwebqq", ptwebqq);
								login2Obj.put("clientid", "53999199");
								Map<String, String> login2Map = new HashMap<>();
								login2Map.put("r", login2Obj.toJSONString());
								response = ClientUtils.post(login2Map, "http://d1.web2.qq.com/channel/login2", null);
								JSONObject login2RetObj = JSONObject
										.parseObject(EntityUtils.toString(response.getEntity()));
								if (login2RetObj.getIntValue("retcode") == 0) {
									JSONObject login2ResultObj = login2RetObj.getJSONObject("result");
									String psessionid = login2ResultObj.getString("psessionid");
									System.out.println("psessionid:" + psessionid);
								}

							}
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}
}
