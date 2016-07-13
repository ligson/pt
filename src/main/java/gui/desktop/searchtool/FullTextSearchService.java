package gui.desktop.searchtool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ligson on 2015/7/23.
 */
public class FullTextSearchService {
	private IndexWriter writer;
	private DirectoryReader reader;
	private File indexDir;
	private Analyzer analyzer = new SmartChineseAnalyzer();
	private Directory directory;
	private Logger logger = LoggerFactory.getLogger(FullTextSearchService.class);
	private Directory spellcheckerDirectory;
	private File spellcheckDic;
	private SpellChecker spellChecker;
	private static FullTextSearchService instance;
	

	public synchronized static FullTextSearchService getInstance(File indexDir) {
		if (instance == null) {
			instance = new FullTextSearchService(indexDir);
		}
		return instance;
	}

	private FullTextSearchService(File indexDir) {
		this.indexDir = indexDir;
		if (!indexDir.exists()) {
			indexDir.mkdirs();
		}
		init();
	}

	private void init() {
		try {
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
			indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

			// 文章
			File articleIndex = new File(indexDir, "index");
			if (!articleIndex.exists()) {
				articleIndex.mkdirs();
			}
			directory = FSDirectory.open(articleIndex.toPath());
			writer = new IndexWriter(directory, indexWriterConfig);
			reader = DirectoryReader.open(writer, true);

			// 拼写
			if ((spellcheckDic == null) || (spellcheckDic != null && !spellcheckDic.exists())) {
				spellcheckDic = new File(indexDir, "spelldic.txt");
				spellcheckDic.createNewFile();
			}
			File spellCheckerIndex = new File(indexDir, "spellchecker");
			if (!spellCheckerIndex.exists()) {
				spellCheckerIndex.mkdirs();
			}
			spellcheckerDirectory = FSDirectory.open(spellCheckerIndex.toPath());
			spellChecker = new SpellChecker(spellcheckerDirectory);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	public void destory() {
		try {
			writer.close();
			reader.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	private void addSpell(List<String> names) {

		SmartChineseAnalyzer smartChineseAnalyzer = (SmartChineseAnalyzer) analyzer;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(spellcheckDic));
			String line = null;
			Set<String> hash = new HashSet<>();
			while ((line = reader.readLine()) != null) {
				hash.add(line);
			}
			reader.close();

			FileWriter writer = new FileWriter(spellcheckDic, true);
			for (String name : names) {
				TokenStream tokenStream = smartChineseAnalyzer.tokenStream("field", name);
				CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
				tokenStream.reset();
				while (tokenStream.incrementToken()) {
					String word = charTermAttribute.toString();
					hash.add(word);
				}
				tokenStream.end();
				tokenStream.close();
			}
			String[] nameArr = new String[(int) hash.size()];
			nameArr = hash.toArray(nameArr);
			Arrays.sort(nameArr);

			PrintWriter printWriter = new PrintWriter(writer);
			for (String name : nameArr) {
				printWriter.println(name);
			}
			writer.close();
			printWriter.close();
			PlainTextDictionary plainTextDictionary = new PlainTextDictionary(new FileReader(spellcheckDic));
			IndexWriterConfig spellCheckerWriterConfig = new IndexWriterConfig(analyzer);
			spellChecker.indexDictionary(plainTextDictionary, spellCheckerWriterConfig, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void index(List<File> files) {
		List<String> names = new ArrayList<>();
		logger.debug("--------->>>>>>>>>>开始索引");
		double i = 0.0;
		for (File file : files) {
			names.add(file.getName());
			index(file);
			logger.debug("索引进度:" + ((i++) * 100.00 / files.size()) + "%");
		}
		try {
			writer.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("--------->>>>>>>>>>索引完毕");
		addSpell(names);
	}

	private void index(File file) {
		try {
			writer.deleteDocuments(new Term("id", file.getAbsolutePath()));
			// writer.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		Field idField = new StringField("id", file.getAbsolutePath(), Field.Store.YES);
		Field pathField = new TextField("path", file.getAbsolutePath(), Field.Store.YES);
		Field nameField = new TextField("name", file.getName(), Field.Store.YES);
		// 0:文件夹,1:文件
		Field typeField = new LongField("type", file.isDirectory() ? 0 : 1, Field.Store.NO);
		Field createDateField = new LongField("createDate", file.lastModified(), Field.Store.YES);

		Document document = new Document();
		document.add(idField);
		document.add(pathField);
		document.add(nameField);
		document.add(typeField);
		document.add(createDateField);

		try {
			writer.addDocument(document);
			logger.debug("增加文档:" + document);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

	}

	public void remove(File file) {
		try {
			writer.deleteDocuments(new Term("id", file.getAbsolutePath()));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public void update(File file) {
		remove(file);
		index(file);
	}

	public List<String> hotKey(String key, int max) {
		List<String> results = new ArrayList<>();
		try {
			spellChecker.setAccuracy(1.0f);
			String[] words = spellChecker.suggestSimilar(key, max);
			for (String word : words) {
				results.add(word);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return results;
	}

	public List<File> search(String key, int max) {
		List<File> list = new ArrayList<>();
		String[] fields = new String[] { "name", "path" };
		String[] fieldValues = new String[] { key, key };

		try {
			Query query = MultiFieldQueryParser.parse(fieldValues, fields, analyzer);
			query = new MyCustomScoreQuery(query);
			if (reader == null) {
				reader = DirectoryReader.open(directory);
			} else {
				DirectoryReader tr = DirectoryReader.openIfChanged(reader);
				if (tr != null) {
					reader.close();
					reader = tr;
				}
			}
			IndexSearcher searcher = new IndexSearcher(reader);

			Sort sort = new Sort(new SortField("name", SortField.Type.SCORE, false));
			TopDocs topDocs = searcher.search(query, max + 1, sort);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			for (int i = 0; i < scoreDocs.length; i++) {
				ScoreDoc scoreDoc = scoreDocs[i];
				Document document = searcher.doc(scoreDoc.doc);
				String idString = document.get("id");
				list.add(new File(idString));
				logger.debug("path:" + idString + "    score:" + scoreDoc.score);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}