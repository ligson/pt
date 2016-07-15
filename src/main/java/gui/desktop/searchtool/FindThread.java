package gui.desktop.searchtool;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindThread {
	public static List<File> pathList = Collections.synchronizedList(new ArrayList<File>());
	public static final File searchToolRoot =  new File(System.getProperty("user.home") + "/.searchtool");
	
	
	public static void index() throws Exception{
		System.out.println(System.getProperty("java.io.tmpdir"));
		File[] roots = File.listRoots();
		List<Thread> threads = new ArrayList<>();
		for (File root : roots) {
			FindFile findFile = new FindFile(root);
			Thread thread = new Thread(findFile);
			thread.start();
			threads.add(thread);
		}

		for (Thread thread : threads) {
			thread.join();
		}

		FullTextSearchService searchService = FullTextSearchService
				.getInstance(searchToolRoot);
		searchService.index(pathList);
	}
	
	public static void search(){
		FullTextSearchService searchService = FullTextSearchService
				.getInstance(searchToolRoot);
		//System.out.println(searchService.hotKey("qq", 100));;
		List<File> key =  searchService.search("qq",100);
		for(int i = 0;i<key.size();i++ ){
			System.out.println(i+"=="+key.get(i));
		}
	}
	public static void main(String[] args) throws Exception{
		search();
		//index();
	}

}
