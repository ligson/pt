package gui.desktop.searchtool;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindFile implements Runnable {
	private static List<String> pathList = Collections.synchronizedList(new ArrayList<String>());

	private File root;

	public FindFile(File root) {
		super();
		this.root = root;
	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("java.io.tmpdir"));
		File[] roots = File.listRoots();
		for (File root : roots) {
			FindFile findFile = new FindFile(root);
			Thread thread = new Thread(findFile);
			thread.start();
		}
	}

	public void find(File root) {
		if(root.getName().startsWith(".")){
			return;
		}
		File tmpDir = new File(System.getProperty("java.io.tmpdir"));
		if(tmpDir.equals(root.getAbsolutePath())){
			return;
		}
		pathList.add(root.getAbsolutePath());
		System.out.println(root.getAbsolutePath());
		if (root.isDirectory()) {
			File[] files = root.listFiles();
			if (files != null) {
				for (File file : files) {
					find(file);
				}
			}
		}

	}

	@Override
	public void run() {
		find(this.root);
	}

}
