package pt.string;

public class SplitTest {
	public static void main(String[] args) {
		String userAgent = "app.xiaoyumishu.core/1.0.0(Android 5.1)";
		if (userAgent.startsWith("app.")) {
			int idx = userAgent.indexOf('/');
			String appPrefix = userAgent.substring(0, idx);
			String[] prefixes = appPrefix.split("\\.");
			if (prefixes != null && prefixes.length == 3) {
				String appName = prefixes[1];
				String appSubName = prefixes[2];
				int idx2 = userAgent.indexOf("(");
				String appVersion = userAgent.substring(idx+1, idx2);
				System.out.println(appName);
				System.out.println(appSubName);
				System.out.println(appVersion);
			}
		}
	}
}
