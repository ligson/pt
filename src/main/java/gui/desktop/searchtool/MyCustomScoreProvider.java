package gui.desktop.searchtool;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.index.SortedDocValues;
import org.apache.lucene.queries.CustomScoreProvider;

public class MyCustomScoreProvider extends CustomScoreProvider {
	private static Map<String, Float> suffixScoreMap = new HashMap<>();
	static {
		suffixScoreMap.put("exe", 0.9f);
		suffixScoreMap.put("cmd", 0.8f);
		suffixScoreMap.put("bat", 0.7f);
		suffixScoreMap.put("msc", 0.6f);
	}

	private SortedDocValues pathValues;

	public MyCustomScoreProvider(LeafReaderContext context) {
		super(context);
		try {
			pathValues = context.reader().getSortedDocValues("path");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * @param doc
	 *            文档id
	 * @param subQueryScore
	 *            指的是普通Query查询的评分
	 * @param valSrcScore
	 *            指的是FunctionQuery查询的评分
	 */
	@Override
	public float customScore(int doc, float subQueryScore, float valSrcScore) throws IOException {
		System.out.println("------------------");
		float boost = subQueryScore * valSrcScore;
		if((pathValues==null)||(pathValues.get(doc)==null)){
			System.out.println("===="+boost);
			return boost;
		}
		
		File file = new File(pathValues.get(doc).utf8ToString());
		System.out.println("===="+file.getAbsolutePath());
		if (file.isFile()) {
			boost += 1.0f;
			int idx = file.getName().lastIndexOf('.');
			Float score = suffixScoreMap.get(file.getName().substring(idx));
			if (score != null) {
				boost += score;
			}

		}
		return boost;

	}

}
